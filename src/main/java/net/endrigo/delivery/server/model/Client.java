package net.endrigo.delivery.server.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class Client extends User {

	private Address address;

	public Client(String name, String email, String password) {
		super(name, email, password);
	}
}
