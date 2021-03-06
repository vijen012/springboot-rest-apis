package com.restful.user.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.restful.address.data.Address;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the user")
//@JsonFilter("UserFilter")
@Entity
public class User {

	@Id
	@GeneratedValue
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

//	@Temporal(TemporalType.DATE)
	@Past(message = "birthDate should be in past")
	@ApiModelProperty(notes = "birthdate should be in past")
	private LocalDate birthDate;

	@Transient
	@JsonInclude
	private AccountResponseData account;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addressList;

	public User() {

	}

	public User(Long id, @Size(min = 2, message = "firstName attribute should be atleast 2 char long") String firstName,
			@Size(min = 2, message = "lastName attribute should be atleast 2 char long") String lastName,
			@Email(message = "email should be valid") String email,
			@Past(message = "birthDate should be in past") LocalDate birthDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthDate = birthDate;
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

	public AccountResponseData getAccount() {
		return account;
	}

	public void setAccount(AccountResponseData account) {
		this.account = account;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddrssList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Address addAddress(Address address) {
		if (address != null) {
			if (addressList == null) {
				addressList = new ArrayList<>();
			}
			addressList.add(address);
			address.setUser(this);
		}
		return address;
	}

	public Address removeAddress(Address address) {
		if (address != null) {
			addressList.remove(address);
			address.setUser(null);
		}
		return address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", birthDate=" + birthDate + "]";
	}

}
