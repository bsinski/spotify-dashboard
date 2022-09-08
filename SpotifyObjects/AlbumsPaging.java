package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class AlbumsPaging {

	private String href;

	@SerializedName("items")
	private List<Album> albums = null;
	private Integer limit;
	private String next;
	private Integer offset;
	private Object previous;
	private Integer total;

	public List<Album> getAlbums() {
		return albums;
	}

}