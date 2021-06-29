package net.endrigo.delivery.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.endrigo.delivery.server.business.UserBC;
import net.endrigo.delivery.server.controller.command.LoginRequest;
import net.endrigo.delivery.server.controller.command.SignupRequest;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "API de controle de acesso")
public class AuthController {
	
	@Autowired
	private UserBC userBC;
	
	@Operation(description = "Logar Usuario")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userBC.login(loginRequest);
	
	}
	
	@Operation(description = "Permite incluir um novo Cliente")
	@PostMapping("/signup")
	public ResponseEntity<?> registerClient(@Valid @RequestBody SignupRequest signUpRequest) {
		return userBC.createClient(signUpRequest);
	}

}
