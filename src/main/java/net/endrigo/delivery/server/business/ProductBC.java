package net.endrigo.delivery.server.business;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.endrigo.delivery.server.controller.dto.ProductDTO;
import net.endrigo.delivery.server.model.Product;
import net.endrigo.delivery.server.repository.ProductRepository;

@Component
public class ProductBC {
	
	@Autowired
	ProductRepository productRepository;

	public ResponseEntity<ProductDTO> obter(Long id) {
		Product product = this.productRepository.getById(id);
		return ResponseEntity.ok().body(new ProductDTO(product));
	}

	public ResponseEntity<List<ProductDTO>> list() {
		List<ProductDTO> lista = this.productRepository.findAll().stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}

}
