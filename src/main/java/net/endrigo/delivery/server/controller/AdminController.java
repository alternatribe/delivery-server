package net.endrigo.delivery.server.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.endrigo.delivery.server.business.UserBC;
import net.endrigo.delivery.server.controller.command.SignupRequest;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
@Tag(name = "Admin", description = "API de acesso administrativo")
public class AdminController {
	
	@Autowired
	private UserBC userBC;
	
	
	@Operation(description = "Permite incluir um novo Usuário. Se não for informado o perfil será criado um no perfil Cliente")
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		return userBC.createUser(signUpRequest);
	}
	
	@Operation(description = "Lista todos os usuários")
	@SecurityRequirement(name = "Authorization")
	@GetMapping(value = "/")
	public ResponseEntity<?> getUsers() {
		return this.userBC.findAll();
	}

}
