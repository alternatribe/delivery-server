package net.endrigo.delivery.server.controller.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.endrigo.delivery.server.model.User;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -3520555348770158667L;
	private UUID id;
	private String name;
	private String email;

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}
}
