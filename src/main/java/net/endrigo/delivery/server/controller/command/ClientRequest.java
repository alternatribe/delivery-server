package net.endrigo.delivery.server.controller.command;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.endrigo.delivery.server.controller.dto.AddressDTO;

@Data
@NoArgsConstructor
public class ClientRequest implements Serializable {

	private static final long serialVersionUID = -3520555348770158667L;
	private UUID id;
	private String name;
	private String email;
	private String password;
	private String newPassword;
	private AddressDTO address;

}
