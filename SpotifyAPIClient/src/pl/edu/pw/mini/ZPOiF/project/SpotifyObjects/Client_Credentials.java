package pl.edu.pw.mini.ZPOiF.project.SpotifyObjects;

public class Client_Credentials {
	private String access_token;
	private String token_type;
	private int expires_in;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
