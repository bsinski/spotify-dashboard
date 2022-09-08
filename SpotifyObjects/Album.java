package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;
import java.util.List;

public class Album{

	private String albumGroup;
	private String albumType;
	private List<Artist> artists = null;
	private List<String> availableMarkets = null;
	private ExternalUrls externalUrls;
	private String href;
	private String id;
	private List<Image> images = null;
	private String name;
	private String releaseDate;
	private String releaseDatePrecision;
	private Integer totalTracks;
	private String type;
	private String uri;
	public String getHref() {
		return href;
	}
	public String getId() {
		return id;
	}
}