package net.endrigo.delivery.server.security.jwt;

import lombok.Getter;

@Getter
public class CredentialsJWT {
	private String token;
	private String type = "Bearer";

	public CredentialsJWT(String token) {
		this.token = token;
	}
}