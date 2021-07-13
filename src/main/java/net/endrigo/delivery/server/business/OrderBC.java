package net.endrigo.delivery.server.business;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.endrigo.delivery.server.controller.command.ProductRequest;
import net.endrigo.delivery.server.controller.dto.OrderDTO;
import net.endrigo.delivery.server.exception.DeliveryException;
import net.endrigo.delivery.server.model.OrderItem;
import net.endrigo.delivery.server.repository.ClientRepository;
import net.endrigo.delivery.server.repository.OrderRepository;

@Component
public class OrderBC {
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ClientRepository clientRepository;
	
	@Transactional
	public boolean save(UUID id, ProductRequest[] itens) {
		try {
			for (ProductRequest item : itens) {
				OrderItem orderItem = new OrderItem();
				orderItem.setClient(id);
				orderItem.setData(LocalDateTime.now());
				orderItem.setName(item.getName());
				orderItem.setDescription(item.getDescription());
				orderItem.setUnitPrice(new BigDecimal(item.getUnitPrice()));
				orderRepository.save(orderItem);			
			}
			return true;			
		} catch (Exception e) {
			throw new DeliveryException(e.getMessage());
		}
	}

	public ResponseEntity<List<OrderDTO>> list(Long id) {
		List<OrderDTO> lista = this.orderRepository.findAll().stream().map(product -> new OrderDTO(product)).collect(Collectors.toList());
		return ResponseEntity.ok().body(lista);
	}


}
