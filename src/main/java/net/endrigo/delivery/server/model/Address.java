package net.endrigo.delivery.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue
	private Long id ;
	
	@Column(name = "street", nullable = false)
	private String street;
	
	private String number;
	
	@Column(name = "zipCode", nullable = false)
	private String zip;
	
	@Column(name = "city", nullable = false)
	private String city;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	private String reference;
	
	@Column(nullable = false)
	private Boolean defaultAdress = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Boolean getDefaultAdress() {
		return defaultAdress;
	}

	public void setDefaultAdress(Boolean defaultAdress) {
		this.defaultAdress = defaultAdress;
	}

	

}
