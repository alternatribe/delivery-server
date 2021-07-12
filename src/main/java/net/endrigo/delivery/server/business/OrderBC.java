package net.endrigo.delivery.server.business;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import net.endrigo.delivery.server.model.OrderItem;
import net.endrigo.delivery.server.repository.OrderRepository;

@Component
public class OrderBC {
	
	@Autowired
	OrderRepository orderRepository;

	@Transactional
	public ResponseEntity<?> save(String[] itens) {
		OrderItem item = new OrderItem();
		item.setName("teste");
		orderRepository.save(item);
		// TODO Auto-generated method stub
		return null;
	}


}
