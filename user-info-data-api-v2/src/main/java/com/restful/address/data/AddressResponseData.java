package com.restful.address.data;

public class AddressResponseData {

	private Long id;
	private AddressType addressType;
	private String houseNumber;
	private String street;
	private String city;
	private String postCode;
	private String country;

	public AddressResponseData() {

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
		return "AddressResponseData [id=" + id + ", addressType=" + addressType + ", houseNumber=" + houseNumber
				+ ", street=" + street + ", city=" + city + ", postCode=" + postCode + ", country=" + country + "]";
	}
}
