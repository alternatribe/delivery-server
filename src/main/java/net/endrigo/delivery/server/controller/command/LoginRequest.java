package net.endrigo.delivery.server.controller.command;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
	@NotBlank
	private String email;

	@NotBlank
	private String password;

}
