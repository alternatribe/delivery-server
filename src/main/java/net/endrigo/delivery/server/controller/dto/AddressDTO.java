package net.endrigo.delivery.server.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.endrigo.delivery.server.model.Address;

@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AddressDTO {

	private String street;
	private String houseNumber;
	private String reference;
	private String district;
	private String zip;
	private String city;
	private String state;
	
	
	public AddressDTO(Address address) {
		this.street = address.getStreet();
		this.houseNumber = address.getHouseNumber();
		this.reference = address.getReference();
		this.district = address.getDistrict();
		this.zip = address.getZip();
		this.city = address.getCity();
		this.state = address.getState();
	}

}