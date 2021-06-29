package net.endrigo.delivery.server.security.jwt;

import java.util.UUID;

public class CredentialsJWT {
	private String token;
	private String type = "Bearer";
	private UUID id;
	private String name;
	private String email;
	private String role;

	public CredentialsJWT(String accessToken, UUID id, String name, String email, String role) {
		this.token = accessToken;
		this.id = id;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public UUID getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}
}