package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

import java.util.List;

public class AlbumInfo {
    private String album_type;
    private List<Artist> artists;
    private List<String> available_markets;
    private List<Copyright> copyrights;
    private ExternalIds external_ids;
    private ExternalUrls2 external_urls;
    private List<Object> genres;
    private String href;
    private String id;
    private List<Image> images;
    private String name;
    private int popularity;
    private String release_date;
    private String release_date_precision;
    private String type;
    private TracksPaging tracks;
    private String uri;
	
    public int getPopularity() {
		return popularity;
	}

	public String getName() {
		return name;
	}

}
