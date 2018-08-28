package com.restful.user.data;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restful.address.data.AddressRequestData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//@JsonFilter("UserFilter")
@ApiModel
public class UserRequestData {

	private Long id;

	@ApiModelProperty(notes = "firstName attribute must be atleast 2 char long")
	@Size(min = 2, message = "firstName attribute must be atleast 2 char long")
	private String firstName;

	@ApiModelProperty(notes = "lastName attribute must be atleast 2 char long")
	@Size(min = 2, message = "lastName attribute must be atleast 2 char long")
	private String lastName;

	@ApiModelProperty(notes = "email must be valid")
	@Email(message = "email must be valid")
	private String email;

	@ApiModelProperty(notes = "birthDate must be in past")
	@Past(message = "birthDate must be in past")
	private LocalDate birthDate;

	@JsonProperty("addresses")
	private List<AddressRequestData> addressRequestDataList;

	@JsonProperty("accounts")
	private List<AccountResponseData> accountResponseDataList;

	public UserRequestData() {

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

	public List<AddressRequestData> getAddressRequestDataList() {
		return addressRequestDataList;
	}

	public void setAddressRequestDataList(List<AddressRequestData> addressRequestDataList) {
		this.addressRequestDataList = addressRequestDataList;
	}

	public List<AccountResponseData> getAccountResponseDataList() {
		return accountResponseDataList;
	}

	public void setAccountResponseDataList(List<AccountResponseData> accountResponseDataList) {
		this.accountResponseDataList = accountResponseDataList;
	}

	@Override
	public String toString() {
		return "UserRequestData [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", birthDate=" + birthDate + "]";
	}
}
