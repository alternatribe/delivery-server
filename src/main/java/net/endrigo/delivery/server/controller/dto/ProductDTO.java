package net.endrigo.delivery.server.controller.dto;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.endrigo.delivery.server.model.Product;

@Data
@NoArgsConstructor
public class ProductDTO {
	

	private Long id;
	private String name;
	private String description;
	private BigDecimal unitPrice = new BigDecimal(0);

	public ProductDTO(Product product) {
		super();
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.unitPrice = product.getUnitPrice();
	}
}
