package net.endrigo.delivery.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.endrigo.delivery.server.business.ProductBC;
import net.endrigo.delivery.server.controller.dto.ProductDTO;

@RestController
@RequestMapping("/product")
@Tag(name = "Product", description = "API de controle de produtos")
public class ProductController {
	
	@Autowired
	private ProductBC productBC;
	
	@Operation(description = "Obter Produto")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> get(@PathVariable Long id) {
		return this.productBC.obter(id);
	}
	
	@Operation(description = "Listar os Produto")
	@GetMapping(value = "/")
	public ResponseEntity<List<ProductDTO>> list() {
		return this.productBC.list();
	}
	


}
