package com.restful.address.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restful.address.data.Address;
import com.restful.address.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping(path = "/users/{userId}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> getAllAddress(@PathVariable Long userId) {
		return new ResponseEntity<List<Address>>(addressService.findAllAddressByUserId(userId), HttpStatus.OK);
	}

	@GetMapping(path = "/users/{userId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> getAddress(@PathVariable("addressId") Long addressId) {
		return new ResponseEntity<>(addressService.findAddress(addressId), HttpStatus.OK);
	}

	@PostMapping(path = "/users/{userId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> createAddress(@PathVariable Long userId,
			@Valid @RequestBody(required = true) Address address) {
		Address savedAddress = addressService.saveAddress(userId, address);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAddress.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<Address>(savedAddress, headers, HttpStatus.CREATED);
	}

	@PutMapping(path = "/users/{userId}/addresses/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Address> updateAddress(@PathVariable Long userId, @PathVariable Long addressId,
			@Valid @RequestBody(required = true) Address address) {
		return new ResponseEntity<>(addressService.updateAddress(userId, addressId, address), HttpStatus.OK);
	}

	@PutMapping(path = "/users/{userId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Address>> updateAllAddress(@PathVariable Long userId,
			@Valid @RequestBody(required = true) List<Address> addressList) {
		return new ResponseEntity<>(addressService.updateAllAddress(userId, addressList), HttpStatus.OK);
	}

	@DeleteMapping(path = "/users/{userId}/addresses/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long userId, @PathVariable Long addressId) {
		return new ResponseEntity<Void>(addressService.deleteAddress(addressId));
	}

	@DeleteMapping(path = "/users/{userId}/addresses")
	public ResponseEntity<Void> deleteAllAddress(@PathVariable Long userId) {
		return new ResponseEntity<Void>(addressService.deleteAllAddress(userId));
	}

}
