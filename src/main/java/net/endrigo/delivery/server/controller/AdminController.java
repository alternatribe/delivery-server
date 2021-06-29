package net.endrigo.delivery.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import net.endrigo.delivery.server.business.UserBC;
import net.endrigo.delivery.server.controller.command.SignupRequest;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserBC userBC;
	
	
	@Operation(description = "Incluir Usuário")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userBC.createUser(signUpRequest);
	}

}
