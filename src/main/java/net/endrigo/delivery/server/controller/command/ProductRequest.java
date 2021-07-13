package net.endrigo.delivery.server.controller.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
	private String id;
	private String name;
	private String description;
	private String unitPrice;

}
