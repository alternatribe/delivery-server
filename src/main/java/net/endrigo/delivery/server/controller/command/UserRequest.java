package net.endrigo.delivery.server.controller.command;

import java.io.Serializable;
import java.util.UUID;

import net.endrigo.delivery.server.model.User;

public class UserRequest implements Serializable {

	private static final long serialVersionUID = -3520555348770158667L;
	private UUID id;
	private String name;
	private String email;
	private String password;
	private String newPassword;
	
	public UserRequest(UUID id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public UserRequest(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
