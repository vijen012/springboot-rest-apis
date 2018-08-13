package com.restful.address.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.restful.address.data.Address;

public interface AddressService {
	public List<Address> findAllAddressByUserId(Long userId);

	public Address findAddress(Long addressId);

	public Address saveAddress(Long userId, Address address);

	public Address updateAddress(Long userId, Long addressId, Address address);

	public List<Address> updateAllAddress(Long userId, List<Address> addressList);

	public HttpStatus deleteAddress(Long addressId);

	public HttpStatus deleteAllAddress(Long userId);
}
