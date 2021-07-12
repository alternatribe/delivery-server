package net.endrigo.delivery.server.model;

import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
public class Address {

	private String street;
	private String houseNumber;
	private String reference;
	private String district;
	private String zip;
	private String city;
	private String state;

}
