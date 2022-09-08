package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

public class TrackFeatures {
	private int duration_ms;
	private int key;
	private int mode;
	private int time_signature;
	private double acousticness;
	private double danceability;
	private double energy;
	private double instrumentalness;
	private double liveness;
	private double loudness;
	private double speechiness;
	private double valence;
	private double tempo;
	private String id;
	private String uri;
	private String track_href;
	private String analysis_url;
	private String type;

	public double getAcousticness() {
		return acousticness;
	}

	public double getDanceability() {
		return danceability;
	}

	public double getEnergy() {
		return energy;
	}

	public double getInstrumentalness() {
		return instrumentalness;
	}

	public double getLoudness() {
		return loudness;
	}

	public double getValence() {
		return valence;
	}

	public double getTempo() {
		return tempo;
	}

	public String getId() {
		return id;
	}

	public String getUri() {
		return uri;
	}

	public String getTrack_href() {
		return track_href;
	}

}