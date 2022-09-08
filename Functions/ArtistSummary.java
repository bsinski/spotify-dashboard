package pl.edu.pw.mini.ZPOiF.project.Functions;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import pl.edu.pw.mini.ZPOiF.project.Exceptions.WrongArtistName;
import pl.edu.pw.mini.ZPOiF.project.SpotifyObjects.*;

public class ArtistSummary {

	// TODO - na razie ka�da z funkcji jest od siebie niezale�na - docelowo trzeba
	// je jako�
	// po��czy� �eby np podaj�c string tylko raz szuka� artysty

	// zwraca �rednie warto�ci atrybut�w dla utwor�w artysty
	// artysta podany na razie jako sformatowany string, typ returnu do ustalenia
	// TODO doda� funkcje formatuj�ca stringi, np spacja to %20
	public static String getTrackFeatures(Artist a) throws IOException, InterruptedException, WrongArtistName {
		List<Album> albumy = QueryMaker.fetchAlbumsfromArtist(a.getId());
		TrackFeatures tf = null;
		HashMap<String, TrackFeatures> lt = new HashMap<>();
		for (Album b : albumy) {
			for (Track t : QueryMaker.fetchTracksfromAlbum(b.getId())) {
				tf = QueryMaker.fetchTrackFeatures(t.getId());
				lt.put(t.getName(), tf);
			}
		}

		Double dance = lt.values().stream().mapToDouble(z -> z.getDanceability()).average().orElse(0);
		Double tempo = lt.values().stream().mapToDouble(z -> z.getTempo()).average().orElse(0);
		Double valence = lt.values().stream().mapToDouble(z -> z.getValence()).average().orElse(0);
		Double energy = lt.values().stream().mapToDouble(z -> z.getEnergy()).average().orElse(0);
		Double loudness = lt.values().stream().mapToDouble(z -> z.getLoudness()).average().orElse(0);
		String s = "danceability = " + String.format("%.2f", dance) + ", tempo = " + String.format("%.2f", tempo)
				+ ", valence = " + String.format("%.2f", valence) + ", energy  = " + String.format("%.2f", energy)
				+ ", loudness =" + String.format("%.2f", loudness);
		return s;

	}

	// zwracanie nazw 10 (lub mniej je�eli artysta tylu nie ma) najbardziej
	// popularnych album�w artysyty
	public static List<String> getTop10Albums(Artist a) throws IOException, InterruptedException, WrongArtistName {
		List<Album> albumy = QueryMaker.fetchAlbumsfromArtist(a.getId());
		List<AlbumInfo> informacje = new ArrayList<>();
		for (Album al : albumy) {
			informacje.add(QueryMaker.fetchAlbumInfo(al));
		}
		return informacje.stream().sorted(Comparator.comparing(AlbumInfo::getPopularity).reversed()).limit(10)
				.map(AlbumInfo::getName).collect(Collectors.toList());
	}

	// zwracanie nazw 10 (lub mniej je�eli artysta tylu nie ma) najbardziej
	// popularnych utwor�w artysty
	public static List<String> getTop10Tracks(Artist a) throws IOException, InterruptedException, WrongArtistName {
		List<TrackInfo> utwory = QueryMaker.fetchTopTracksFromArtist(a.getId());
		return utwory.stream().map(TrackInfo::getName).collect(Collectors.toList());
	}

	// zwraca liste rekomendowanych utwor�w na podstawie podanych max. 5 artyst�w.
	public static List<String> getRecommendedTracks(List<String> nameArt)
			throws IOException, InterruptedException, WrongArtistName {
		List<String> idArt = new ArrayList<>();
		for (String j : nameArt) {
			idArt.add(QueryMaker.fetchArtist(j).getId());

		}
		Recommendation r = QueryMaker.fetchRecommendation(idArt);
		List<Track> tr = r.getTracks();
		List<String> names = new ArrayList<>();
		for (Track i : tr) {
			names.add(i.getArtists().get(0).getName() + " - " + i.getName());
		}
		return names;
	}

	// zwraca gatunki muzyczne dla danego artysty
	public static List<String> getGenres(Artist a) throws IOException, InterruptedException, WrongArtistName {
		return a.getGenres().stream().limit(8).collect(Collectors.toList());
	}

	// zapisuje najszerszy dost�pny obraz artysty w nowouworzonym podkatalogu tmp
	// usuwane po zakończeniu pracy aplikacji
	public static void saveArtistImage(Artist a) throws IOException, InterruptedException, WrongArtistName {
		URL url = new URL(a.getImages().get(0).getUrl());
		InputStream in = new BufferedInputStream(url.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1 != (n = in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();
		String path = "./tmp/ArtistImages/" + a.getName() + ".jpg";
		File f = new File(path);
		if (!f.exists())
			;
		{
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(response);
			fos.close();
			f.deleteOnExit();
		}
	}

}
