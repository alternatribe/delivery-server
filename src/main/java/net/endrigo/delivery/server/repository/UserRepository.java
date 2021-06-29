package net.endrigo.delivery.server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.endrigo.delivery.server.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

	Boolean existsByName(String name);

	Boolean existsByEmail(String email);
}
