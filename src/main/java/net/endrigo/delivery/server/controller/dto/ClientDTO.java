package net.endrigo.delivery.server.controller.dto;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.endrigo.delivery.server.model.Client;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ClientDTO implements Serializable {

	private static final long serialVersionUID = -3520555348770158667L;
	private UUID id;
	private String name;
	private String email;
	private AddressDTO address;

	public ClientDTO(Client user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		if (user.getAddress() != null) {
			this.address = new AddressDTO(user.getAddress());
		}
	}
}
