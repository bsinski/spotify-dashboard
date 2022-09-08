package pl.edu.pw.mini.ZPOiF.project.Functions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import com.google.gson.Gson;

import pl.edu.pw.mini.ZPOiF.project.Exceptions.WrongArtistName;
import pl.edu.pw.mini.ZPOiF.project.SpotifyObjects.*;

public class QueryMaker {
	public static String TOKEN;

	// TODO: doda� static zmienn� Client_Credentials �eby generowa� token tylko gdy
	// stary sie przedawni
	public static void setToken() throws IOException {
		URL url = new URL("https://accounts.spotify.com/api/token");
		final String clientId = "a47ed541263b476e988adc1868133699";
		final String clientSecret = "94d3f2a7fa364eb98af402b0b9893083";
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		String basicAuth = "Basic "
				+ Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
		conn.setRequestProperty("Authorization", basicAuth);
		String body = "grant_type=client_credentials";
		byte[] out = body.getBytes(StandardCharsets.UTF_8);
		try (OutputStream os = conn.getOutputStream()) {
			os.write(out);
		}

		InputStream result = conn.getInputStream();
		String s = new String(result.readAllBytes());
		Gson gson = new Gson();
		Client_Credentials cc = gson.fromJson(s, Client_Credentials.class);
		TOKEN = cc.getAccess_token();
		TOKEN = "Bearer " + TOKEN;

	}

	public static String getJSONfromURL(URL url) throws IOException, InterruptedException, WrongArtistName {
		HttpURLConnection conn2 = (HttpURLConnection) url.openConnection();
		conn2.setDoInput(true);
		conn2.setRequestMethod("GET");
		conn2.setRequestProperty("Authorization", TOKEN);
		if (conn2.getResponseCode() == 429) {
			System.out.println("HTTP Error 429. Retrying................");
			Thread.sleep(2000);
			return getJSONfromURL(url);
		}
		if (conn2.getResponseCode() == 503) {
			System.out.println("HTTP Error 503. Retrying................");
			Thread.sleep(200);
			return getJSONfromURL(url);
		}
		InputStream result = null;

		try {
			result = conn2.getInputStream();
		} catch (IOException e) {
			if (conn2.getResponseCode() == 400) {
				throw new WrongArtistName();

			}
		}
		String s = new String(result.readAllBytes());
		return s;
	}

	public static Artist fetchArtist(String q) throws IOException, InterruptedException, WrongArtistName {
		URL url = new URL("https://api.spotify.com/v1/search?" + "type=artist&limit=1&q=" + q);
		String s = getJSONfromURL(url);
		Gson gson = new Gson();
		SearchPaging root = gson.fromJson(s, SearchPaging.class);
		if (root.getArtistPaging().getArtistList().size() == 0)
			throw new WrongArtistName();
		Artist a = root.getArtistPaging().getArtistList().get(0);
		return a;

	}

	public static List<Album> fetchAlbumsfromArtist(String q)
			throws IOException, InterruptedException, WrongArtistName {
		URL url = new URL("https://api.spotify.com/v1/artists/" + q + "/albums?include_groups=album");
		String s = getJSONfromURL(url);
		Gson gson = new Gson();
		AlbumsPaging root = gson.fromJson(s, AlbumsPaging.class);
		List<Album> l = root.getAlbums();
		return l;

	}

	public static List<Track> fetchTracksfromAlbum(String q) throws IOException, InterruptedException, WrongArtistName {
		URL url = new URL("https://api.spotify.com/v1/albums/" + q + "/tracks");
		String s = getJSONfromURL(url);
		Gson gson = new Gson();
		TracksPaging root = gson.fromJson(s, TracksPaging.class);
		return root.getTracks();
	}

	public static TrackFeatures fetchTrackFeatures(String q) throws IOException, InterruptedException, WrongArtistName {
		URL url = new URL("https://api.spotify.com/v1/audio-features/" + q);
		String s = getJSONfromURL(url);
		Gson gson = new Gson();
		TrackFeatures tf = gson.fromJson(s, TrackFeatures.class);

		return tf;
	}

	public static List<TrackInfo> fetchTopTracksFromArtist(String q)
			throws IOException, InterruptedException, WrongArtistName {
		URL url = new URL("https://api.spotify.com/v1/artists/" + q + "/top-tracks?country=PL");
		String s = getJSONfromURL(url);
		Gson gson = new Gson();
		TracksInfoPaging ttop = gson.fromJson(s, TracksInfoPaging.class);
		return ttop.getTracksInfo();
	}

	public static AlbumInfo fetchAlbumInfo(Album album) throws IOException, InterruptedException, WrongArtistName {
		URL url = new URL(album.getHref());
		String s = getJSONfromURL(url);
		Gson gson = new Gson();
		AlbumInfo ai = gson.fromJson(s, AlbumInfo.class);
		return ai;
	}

	public static Recommendation fetchRecommendation(List<String> ArtistIDs)
			throws IOException, InterruptedException, WrongArtistName {
		String q = String.join(",", ArtistIDs);
		URL url = new URL("https://api.spotify.com/v1/recommendations?limit=10&market=PL&seed_artists=" + q);
		String s = getJSONfromURL(url);
		Gson gson = new Gson();
		Recommendation r = gson.fromJson(s, Recommendation.class);
		return r;
	}

}