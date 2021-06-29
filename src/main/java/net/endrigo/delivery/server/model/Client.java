package net.endrigo.delivery.server.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {

	@OneToMany
	private Collection<Address> address;

	public Client(String name, String email, String password) {
		super(name, email, password);
	}

	public Collection<Address> getAddress() {
		return address;
	}

	public void setAddress(Collection<Address> address) {
		this.address = address;
	}

}
