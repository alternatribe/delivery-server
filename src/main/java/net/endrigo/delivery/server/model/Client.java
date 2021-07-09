package net.endrigo.delivery.server.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Client extends User {

	@OneToOne
	private Address address;

	public Client() {
	}

	public Client(String name, String email, String password) {
		super(name, email, password);
	}
	
	public Client(String name, String email, String password, Address address) {
		super(name, email, password);
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
