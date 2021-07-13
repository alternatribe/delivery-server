package net.endrigo.delivery.server.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderItem {
	
    @Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ordergenerator")
	@SequenceGenerator(name="ordergenerator", sequenceName="ORDER_SEQ", allocationSize=1, initialValue=1)
	private Long id;
    private UUID client;
	private String name;
	private String description;
	private BigDecimal unitPrice = new BigDecimal(0);
	 @Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime data;
}
