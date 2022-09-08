package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import java.util.List;

public class Artist {
	private ExternalUrls external_urls;
	private Followers followers;
	private List<String> genres;
	private String href;
	private String id;
	private List<Image> images;
	private String name;
	private int popularity;
	private String type;
	private String uri;

	public List<String> getGenres() {
		return genres;
	}

	public String getId() {
		return id;
	}

	public List<Image> getImages() {
		return images;
	}

	public String getName() {
		return name;
	}

}