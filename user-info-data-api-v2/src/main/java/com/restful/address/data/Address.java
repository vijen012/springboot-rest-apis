package com.restful.address.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.restful.user.data.User;

@Entity
public class Address {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	private String houseNumber;
	private String street;
	private String city;
	private String postCode;
	private String country;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public Address() {

	}

	public Address(AddressType addressType, String houseNumber, String street, String city, String postCode,
			String country) {
		super();
		this.addressType = addressType;
		this.houseNumber = houseNumber;
		this.street = street;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", addressType=" + addressType + ", houseNumber=" + houseNumber + ", street="
				+ street + ", city=" + city + ", postCode=" + postCode + ", country=" + country + "]";
	}

}
