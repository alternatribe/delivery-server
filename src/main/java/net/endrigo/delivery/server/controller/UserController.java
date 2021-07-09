package net.endrigo.delivery.server.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.endrigo.delivery.server.business.UserBC;
import net.endrigo.delivery.server.controller.command.UserRequest;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "API de controle de usuários")
public class UserController {
	
	@Autowired
	private UserBC userBC;
	
	@Operation(description = "Obter Dados do Cliente")
	@SecurityRequirement(name = "Authorization")
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCliente(@PathVariable UUID id) {
		return this.userBC.obter(id);

	}
	
	@SecurityRequirement(name = "Authorization")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> remove(@PathVariable UUID id) {
		this.userBC.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@SecurityRequirement(name = "Authorization")
	@PutMapping(value = "/")
	public ResponseEntity<?> update(@RequestBody UserRequest user) {
		this.userBC.update(user);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
