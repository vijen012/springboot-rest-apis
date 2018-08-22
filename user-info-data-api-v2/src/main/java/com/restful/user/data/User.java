package com.restful.user.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.restful.address.data.Address;

@Entity
public class User {

	@Id
	@GeneratedValue
	private Long id;

	private String firstName;
	private String lastName;
	private String email;
	private LocalDate birthDate;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addressList;

	public User() {

	}

	public User(Long id, String firstName, String lastName, String email, LocalDate birthDate) {
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

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
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
