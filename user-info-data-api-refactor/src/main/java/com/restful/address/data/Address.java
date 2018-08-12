package com.restful.address.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restful.user.data.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Information about user address")
public class Address {

	@Id
	@GeneratedValue
	private Long id;

	@ApiModelProperty(notes = "must be HOME, WORK AND OFFICE")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	@ApiModelProperty(notes = "must not be blank or empty")
	@NotBlank(message = "houseNumber must not be blank")
	@NotEmpty(message = "houseNumber must not be empty")
	private String houseNumber;

	@ApiModelProperty(notes = "must not be blank or empty")
	@NotBlank(message = "street must not be blank")
	@NotEmpty(message = "street must not be empty")
	private String street;

	@ApiModelProperty(notes = "must not be blank or empty")
	@NotBlank(message = "city must not be blank")
	@NotEmpty(message = "city must not be empty")
	private String city;

	@ApiModelProperty(notes = "must not be blank or null")
	@NotBlank(message = "postcode must not be blank")
	@NotEmpty(message = "postcode must not be empty")
	private String postCode;

	@ApiModelProperty(notes = "must not be blank or null")
	@NotBlank(message = "country must not be blank")
	@NotEmpty(message = "country must not be empty")
	private String country;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Address() {

	}

	public Address(AddressType addressType, @NotNull(message = "houseNumber must not be null") String houseNumber,
			@NotBlank(message = "street must not be blank") @NotEmpty(message = "street must not be empty") String street,
			@NotBlank(message = "city must not be blank") @NotEmpty(message = "city must not be empty") String city,
			@NotBlank(message = "postcode must not be blank") @NotEmpty(message = "postcode must not be empty") String postCode,
			@NotBlank(message = "country must not be blank") @NotEmpty(message = "country must not be empty") String country) {
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
