package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import com.google.gson.annotations.*;

public class SearchPaging {
	@SerializedName("artists")
	private ArtistsPaging artistPaging;

	public ArtistsPaging getArtistPaging() {
		return artistPaging;
	}

}
