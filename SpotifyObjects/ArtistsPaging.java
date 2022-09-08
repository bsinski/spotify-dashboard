package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import com.google.gson.annotations.*;

import java.util.List;

public class ArtistsPaging {
	private String href;
	@SerializedName("items")
	private List<Artist> artistList;
	private int limit;
	private String next;
	private int offset;
	private Object previous;
	private int total;

	public List<Artist> getArtistList() {
		return artistList;
	}

}