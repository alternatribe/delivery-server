package net.endrigo.delivery.server.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.endrigo.delivery.server.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
	
}
