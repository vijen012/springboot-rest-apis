package com.restful.address.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.restful.address.data.AddressRequestData;
import com.restful.address.data.AddressResponseData;

public interface AddressService {
	public List<AddressResponseData> findAllAddressByUserId(Long userId);

	public AddressResponseData findAddress(Long addressId);

	public AddressResponseData saveAddress(Long userId, AddressRequestData addressRequestData);

	public AddressResponseData updateAddress(Long userId, Long addressId, AddressRequestData addressRequestData);

	public List<AddressResponseData> updateAllAddress(Long userId, List<AddressRequestData> addressRequestDataList);

	public HttpStatus deleteAddress(Long addressId);

	public HttpStatus deleteAllAddress(Long userId);
}
