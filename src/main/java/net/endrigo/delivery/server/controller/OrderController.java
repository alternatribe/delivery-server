package net.endrigo.delivery.server.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.endrigo.delivery.server.business.OrderBC;

@RestController
@RequestMapping("/order")
@Tag(name = "Product", description = "API de controle de produtos")
public class OrderController {
	
	@Autowired
	private OrderBC orderBC;
	
	@Operation(description = "Salva o pedido")
	@PostMapping("/{id}")
	public ResponseEntity<?> save(@PathVariable UUID id, @RequestBody String[] itens) {
		return orderBC.save(itens);
	}
	


}
