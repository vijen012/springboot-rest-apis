package com.restful.user.data;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.restful.address.data.AddressRequestData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the user")
//@JsonFilter("UserFilter")
public class UserRequestData {

	private Long id;

	@Size(min = 2, message = "firstName attribute should be atleast 2 char long")
	@ApiModelProperty(notes = "firstName should be atleast 2 character long")
	private String firstName;

	@Size(min = 2, message = "lastName attribute should be atleast 2 char long")
	@ApiModelProperty(notes = "lastName should be atleast 2 character long")
	private String lastName;

	@Email(message = "email should be valid")
	@ApiModelProperty(notes = "email should be valid")
	private String email;

	@Past(message = "birthDate should be in past")
	@ApiModelProperty(notes = "birthdate should be in past")
	private LocalDate birthDate;

	private List<AddressRequestData> addressRequestDataList;

	private AccountResponseData accountResponseData;

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

	public void setAddrssRequestDataList(List<AddressRequestData> addressRequestDataList) {
		this.addressRequestDataList = addressRequestDataList;
	}

	public AccountResponseData getAccountResponseData() {
		return accountResponseData;
	}

	public void setAccountResponseData(AccountResponseData accountResponseData) {
		this.accountResponseData = accountResponseData;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", birthDate="
				+ birthDate + "]";
	}
}
