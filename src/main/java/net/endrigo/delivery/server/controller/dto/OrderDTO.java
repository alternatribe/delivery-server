package net.endrigo.delivery.server.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.endrigo.delivery.server.model.OrderItem;

@Data
@NoArgsConstructor
public class OrderDTO {
	
	private String name;
	private String description;
	private BigDecimal unitPrice = new BigDecimal(0);
	private LocalDateTime data;

	public OrderDTO(OrderItem product) {
		super();
		this.name = product.getName();
		this.description = product.getDescription();
		this.unitPrice = product.getUnitPrice();
		this.data = product.getData();
	}
}
