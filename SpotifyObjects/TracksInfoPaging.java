package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class TracksInfoPaging {
	private String href;
	@SerializedName("tracks")
	private List<TrackInfo> tracksinfo;
	public int limit;
	public String next;
	public int offset;
	public Object previous;
	public int total;

	public List<TrackInfo> getTracksInfo() {
		return tracksinfo;
	}

}
