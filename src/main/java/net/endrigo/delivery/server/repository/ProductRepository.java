package net.endrigo.delivery.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.endrigo.delivery.server.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
