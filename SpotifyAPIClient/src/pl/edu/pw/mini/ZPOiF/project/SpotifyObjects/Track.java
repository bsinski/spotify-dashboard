package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import java.util.List;

public class Track {
	private List<Artist> artists;
	private List<String> available_markets;
	private int disc_number;
	private int duration_ms;
	private boolean explicit;
	private ExternalUrls2 external_urls;
	private String href;
	private String id;
	private String name;
	private String preview_url;
	private int track_number;
	private String type;
	private String uri;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Artist> getArtists() {
		return artists;
	}

}