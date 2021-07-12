package net.endrigo.delivery.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.endrigo.delivery.server.model.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem, Long> {
	
}
