package com.restful.address.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
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

import com.restful.address.data.AddressRequestData;
import com.restful.address.data.AddressResponseData;
import com.restful.address.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	@Autowired
	private Logger logger;

	@GetMapping(path = "/users/{userId}/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AddressResponseData>> getAllAddress(@PathVariable Long userId) {
		logger.trace(userId + " Enter --->");
		ResponseEntity<List<AddressResponseData>> addressResponseEntities = new ResponseEntity<List<AddressResponseData>>(
				addressService.findAllAddressByUserId(userId), HttpStatus.OK);
		logger.trace(userId + " <--- Exit");
		return addressResponseEntities;
	}

	@GetMapping(path = "/users/{userId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponseData> getAddress(@PathVariable("addressId") Long addressId) {
		logger.trace(addressId + " Enter --->");
		ResponseEntity<AddressResponseData> addressResponseEntity = new ResponseEntity<>(
				addressService.findAddress(addressId), HttpStatus.OK);
		logger.trace(addressId + " <--- Exit");
		return addressResponseEntity;
	}

	@PostMapping(path = "/users/{userId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponseData> createAddress(@PathVariable Long userId,
			@Valid @RequestBody(required = true) AddressRequestData addressRequestData) {
		logger.trace(userId + " Enter --->");
		AddressResponseData savedAddress = addressService.saveAddress(userId, addressRequestData);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAddress.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		ResponseEntity<AddressResponseData> addressResponseEntity = new ResponseEntity<AddressResponseData>(
				savedAddress, headers, HttpStatus.CREATED);
		logger.trace(userId + " <--- Exit");
		return addressResponseEntity;
	}

	@PutMapping(path = "/users/{userId}/addresses/{addressId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressResponseData> updateAddress(@PathVariable Long userId, @PathVariable Long addressId,
			@Valid @RequestBody(required = true) AddressRequestData addressRequestData) {
		logger.trace(addressId + " Enter --->");
		ResponseEntity<AddressResponseData> addressResponseEntity = new ResponseEntity<>(
				addressService.updateAddress(userId, addressId, addressRequestData), HttpStatus.OK);
		logger.trace(addressId + " <--- Exit");
		return addressResponseEntity;
	}

	@PutMapping(path = "/users/{userId}/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AddressResponseData>> updateAllAddress(@PathVariable Long userId,
			@Valid @RequestBody(required = true) List<AddressRequestData> addressRequestDataList) {
		logger.trace(userId + " Enter --->");
		ResponseEntity<List<AddressResponseData>> addressResponseEntities = new ResponseEntity<>(
				addressService.updateAllAddress(userId, addressRequestDataList), HttpStatus.OK);
		logger.trace(userId + " <--- Exit");
		return addressResponseEntities;
	}

	@DeleteMapping(path = "/users/{userId}/addresses/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long userId, @PathVariable Long addressId) {
		logger.trace(addressId + " Enter --->");
		ResponseEntity<Void> responseEntity = new ResponseEntity<Void>(addressService.deleteAddress(addressId));
		logger.trace(addressId + " <--- Exit");
		return responseEntity;
	}

	@DeleteMapping(path = "/users/{userId}/addresses")
	public ResponseEntity<Void> deleteAllAddress(@PathVariable Long userId) {
		logger.trace(userId + " Enter --->");
		ResponseEntity<Void> responseEntity = new ResponseEntity<Void>(addressService.deleteAllAddress(userId));
		logger.trace(userId + " <--- Exit");
		return responseEntity;
	}

}
