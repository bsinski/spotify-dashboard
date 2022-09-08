package pl.edu.pw.mini.ZPOiF.project.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import pl.edu.pw.mini.ZPOiF.project.Exceptions.WrongArtistName;
import pl.edu.pw.mini.ZPOiF.project.Functions.ArtistSummary;
import pl.edu.pw.mini.ZPOiF.project.Functions.ArtistSummaryWrapper;
import pl.edu.pw.mini.ZPOiF.project.Functions.QueryMaker;
import pl.edu.pw.mini.ZPOiF.project.SpotifyObjects.Artist;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Application extends javafx.application.Application {

	private Scene scene1;
	private Scene scene2;
	private Map<String, Object> artiststats;

	@Override
	public void start(Stage stage) throws Exception {
		initGUI(stage);
	}

	private void initGUI(Stage stage) throws IOException, InterruptedException, WrongArtistName {
		firstScene(stage);
		stage.setScene(scene1);
		stage.show();
	}

	private void firstScene(Stage stage) throws IOException, InterruptedException, WrongArtistName {
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(30, 10, 30, 10));
		layout.setHgap(10);
		layout.setVgap(8);
		FileInputStream fis = new FileInputStream("src/pl/edu/pw/mini/ZPOiF/project/GUI/spotifylogo.png");
		Image spotilogo = new Image(fis);
		fis.close();
		ImageView logoView = new ImageView(spotilogo);
		logoView.setFitHeight(100);
		logoView.setPreserveRatio(true);
		layout.add(logoView, 0, 0);
		Text text = new Text("Spotify API Client");
		layout.add(text, 1, 0);
		Label lab1 = new Label("Artist name :");
		TextField insertname = new TextField();
		lab1.setLabelFor(insertname);
		Label error = new Label("");
		layout.add(lab1, 0, 1);
		layout.add(insertname, 1, 1, 2, 1);
		Button accept = new Button("Submit");
		layout.add(accept, 2, 2);
		layout.add(error, 1, 2);
		accept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String test = formatString(insertname.getText());
				try {
					artiststats = ArtistSummaryWrapper.getArtistStats(test);
					secondScene(stage);
					stage.setScene(scene2);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				} catch (WrongArtistName w) {
					error.setText("Wrong artist name. Try Again.");
					insertname.clear();
				}
			}
		});
		layout.setAlignment(Pos.CENTER);
		text.setStyle("-fx-font: normal bold 50px 'Arial' ; -fx-fill: white;");
		lab1.setStyle("-fx-font: normal normal 20px 'Arial' ; -fx-text-fill: white;");
		error.setStyle("-fx-font: normal normal 17px 'Arial' ; -fx-text-fill: white;");
		Scene scene = new Scene(layout, 750, 750);
		scene.getStylesheets().add(Application.class.getResource("app.css").toExternalForm());
		scene1 = scene;
	}

	private void secondScene(Stage stage) throws InterruptedException, IOException, WrongArtistName {
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(30, 10, 30, 10));
		layout.setHgap(10);
		layout.setVgap(10);
		Text header = new Text("Results for: " + ((Artist) artiststats.get("artist")).getName());
		header.setStyle("-fx-font: normal bold 30px 'Arial' ; -fx-fill: white;");
		layout.add(header, 0, 0);
		// ArtistSummary.saveArtistImage(artistname);
		Text featuresheader = new Text("Artist average track features:");
		featuresheader.getStyleClass().add("customheader");
		layout.add(featuresheader, 0, 1);
		Label trackfeatures = new Label((String) artiststats.get("track features"));
		layout.add(trackfeatures, 0, 2);
		List<String> genreslist = (List<String>) artiststats.get("genres");
		Text genresheader = new Text("Artist genres:");
		genresheader.getStyleClass().add("customheader");
		layout.add(genresheader, 0, 3);
		Label artistgenres = new Label(String.join("\n", genreslist));
		layout.add(artistgenres, 0, 4);
		GridPane.setValignment(artistgenres, VPos.TOP);
		File path = new File("./tmp/ArtistImages/" + ((Artist) artiststats.get("artist")).getName() + ".jpg");
		FileInputStream fis = new FileInputStream(path);
		Image artistphoto = new Image(fis);
		fis.close();
		ImageView imageView = new ImageView(artistphoto);
		imageView.setFitHeight(200);
		imageView.setPreserveRatio(true);
		layout.add(imageView, 1, 0, 1, 5);
		Text tracksheader = new Text("Top 10 tracks:");
		tracksheader.getStyleClass().add("customheader");
		layout.add(tracksheader, 0, 5);
		Label toptracks = new Label(String.join("\n", (List<String>) artiststats.get("top tracks")));
		layout.add(toptracks, 0, 6);
		Text albumheader = new Text("Top 10 albums:");
		albumheader.getStyleClass().add("customheader");
		layout.add(albumheader, 1, 5);
		Label topalbums = new Label(String.join("\n", (List<String>) artiststats.get("top albums")));
		layout.add(topalbums, 1, 6);
		GridPane.setValignment(topalbums, VPos.TOP);
		List<String> artistlist = new ArrayList();
		artistlist.add(formatString(((Artist) artiststats.get("artist")).getName()));
		Text recommendationheader = new Text(
				"Songs Recommendations for: " + ((Artist) artiststats.get("artist")).getName().toLowerCase());
		recommendationheader.getStyleClass().add("customheader");
		layout.add(recommendationheader, 0, 7);
		Label artistrecomendation = new Label(String.join("\n", ArtistSummary.getRecommendedTracks(artistlist)));
		layout.add(artistrecomendation, 0, 8);
		Label text2 = new Label("Insert other artist(max 4) and update recommendations(separate with comma): ");
		layout.add(text2, 0, 9);
		TextField insertother = new TextField();
		layout.add(insertother, 1, 9);
		Button submit = new Button("Submit");
		layout.add(submit, 2, 9);
		Label error = new Label("");
		layout.add(error, 1, 10);

		submit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String[] artists = insertother.getText().split(",");
				ObservableList<String> otherlist = FXCollections.observableArrayList();
				for (String a : artists) {
					otherlist.add(formatString(a));
				}
				otherlist.add(formatString(((Artist) artiststats.get("artist")).getName()));
				try {
					recommendationheader.setText(
							"Songs Recommendations for: " + ((Artist) artiststats.get("artist")).getName().toLowerCase()
									+ ", " + String.join(", ", artists));
					artistrecomendation.setText(String.join("\n", ArtistSummary.getRecommendedTracks(otherlist)));
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (WrongArtistName wrongArtistName) {
					error.setText("Wrong artist name. Try again");
					insertother.clear();
				}
			}
		});

		Button returnb = new Button("Return");
		layout.add(returnb, 2, 0);
		GridPane.setValignment(returnb, VPos.TOP);
		GridPane.setHalignment(returnb, HPos.RIGHT);
		returnb.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				try {
					firstScene(stage);
				} catch (IOException | InterruptedException | WrongArtistName e) {
					e.printStackTrace();
				}
				stage.setScene(scene1);

			}
		});
		Button saveImage = new Button("Save results to file");
		layout.add(saveImage, 0, 11);
		saveImage.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				WritableImage writableImage = layout.snapshot(new SnapshotParameters(), null);
				File file = new File("./screenshots/" + formatString(((Artist) artiststats.get("artist")).getName())
						+ "_results.png");
				try {
					ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
					System.out.println("Captured: " + file.getAbsolutePath());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		layout.setMinHeight(750);
		layout.setMinWidth(750);
		Scene scene = new Scene(layout);
		scene.getStylesheets().add(Application.class.getResource("app.css").toExternalForm());
		scene2 = scene;
	}

	public static String formatString(String name) {
		name = name.toLowerCase().trim();
		name = URLEncoder.encode(name, StandardCharsets.UTF_8);
		name = name.replace("+", "%20");
		return name;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
