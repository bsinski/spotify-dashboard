
package pl.edu.pw.mini.ZPOiF.project.Functions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.edu.pw.mini.ZPOiF.project.Exceptions.WrongArtistName;
import pl.edu.pw.mini.ZPOiF.project.GUI.Application;
import pl.edu.pw.mini.ZPOiF.project.SpotifyObjects.Artist;

// stworzona na potrzeby szybszego działania aplikacji. 
public class ArtistSummaryWrapper {
	public static Map<String, Object> getArtistStats(String q)
			throws IOException, InterruptedException, WrongArtistName {

		Map<String, Object> result = new HashMap<>();
		QueryMaker.setToken();
		Artist a = QueryMaker.fetchArtist(q);
		result.put("artist", a);
		if (!(Application.formatString(a.getName()).equals(q)))
			throw new WrongArtistName();
		result.put("track features", ArtistSummary.getTrackFeatures(a));
		result.put("top albums", ArtistSummary.getTop10Albums(a));
		result.put("top tracks", ArtistSummary.getTop10Tracks(a));
		result.put("genres", ArtistSummary.getGenres(a));
		ArtistSummary.saveArtistImage(a);
		return result;
	}

}