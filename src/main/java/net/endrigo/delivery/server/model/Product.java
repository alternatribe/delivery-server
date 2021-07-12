package net.endrigo.delivery.server.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="productenerator")
	@SequenceGenerator(name="productenerator", sequenceName="PRODUCT_SEQ", allocationSize=1, initialValue=1)
	private Long id;
	private String name;
	private String description;
	private BigDecimal unitPrice = new BigDecimal(0);
}
