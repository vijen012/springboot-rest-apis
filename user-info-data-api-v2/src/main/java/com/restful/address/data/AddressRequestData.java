package com.restful.address.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AddressRequestData {

	private Long id;
	private AddressType addressType;

	@ApiModelProperty(notes = "houseNumber must not be blank or empty")
	@NotBlank(message = "houseNumber must not be blank")
	@NotEmpty(message = "houseNumber must not be empty")
	private String houseNumber;

	@ApiModelProperty(notes = "street must not be blank or empty")
	@NotBlank(message = "street must not be blank")
	@NotEmpty(message = "street must not be empty")
	private String street;

	@ApiModelProperty(notes = "city must not be blank or empty")
	@NotBlank(message = "city must not be blank")
	@NotEmpty(message = "city must not be empty")
	private String city;

	@ApiModelProperty(notes = "postcode must not be blank or empty")
	@NotBlank(message = "postcode must not be blank")
	@NotEmpty(message = "postcode must not be empty")
	private String postCode;

	@ApiModelProperty(notes = "country must not be blank or empty")
	@NotBlank(message = "country must not be blank")
	@NotEmpty(message = "country must not be empty")
	private String country;

	public AddressRequestData() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AddressRequestData [addressType=" + addressType + ", houseNumber=" + houseNumber + ", street=" + street
				+ ", city=" + city + ", postCode=" + postCode + ", country=" + country + "]";
	}
}
