package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TracksPaging {
	private String href;
	@SerializedName("items")
	private List<Track> tracks;
	public int limit;
	public String next;
	public int offset;
	public Object previous;
	public int total;

	public List<Track> getTracks() {
		return tracks;
	}

}
