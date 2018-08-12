package com.restful.user.data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restful.address.data.Address;

public class UserResponseData {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDate;

	@JsonProperty("account")
	private AccountResponseData accountResponseData;

	@JsonProperty("addresses")
	private List<Address> addressList;

	public UserResponseData() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public AccountResponseData getAccountResponseData() {
		return accountResponseData;
	}

	public void setAccountResponseData(AccountResponseData accountResponseData) {
		this.accountResponseData = accountResponseData;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddrssList(List<Address> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "UserResponseData [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", birthDate=" + birthDate + "]";
	}
}