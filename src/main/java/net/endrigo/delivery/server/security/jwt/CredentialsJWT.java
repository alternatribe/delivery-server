package net.endrigo.delivery.server.security.jwt;

public class CredentialsJWT {
	private String token;
	private String type = "Bearer";

	public CredentialsJWT(String token) {
		this.token = token;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return type;
	}
}