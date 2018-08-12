package com.restful.address.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AddressDataMapper {

	public Address getAddress(AddressRequestData addressRequestData) {
		Address address = new Address();
		address.setId(addressRequestData.getId());
		address.setAddressType(addressRequestData.getAddressType());
		address.setHouseNumber(addressRequestData.getHouseNumber());
		address.setStreet(addressRequestData.getStreet());
		address.setCity(addressRequestData.getCity());
		address.setCountry(addressRequestData.getCountry());
		address.setPostCode(addressRequestData.getPostCode());
		return address;
	}

	public List<Address> getAddressList(List<AddressRequestData> addressRequestDataList) {
		List<Address> addressList = new ArrayList<>();
		addressRequestDataList.forEach(addressRequestData -> {
			Address address = new Address();
			address.setId(addressRequestData.getId());
			address.setAddressType(addressRequestData.getAddressType());
			address.setHouseNumber(addressRequestData.getHouseNumber());
			address.setStreet(addressRequestData.getStreet());
			address.setCity(addressRequestData.getCity());
			address.setCountry(addressRequestData.getCountry());
			address.setPostCode(addressRequestData.getPostCode());
			addressList.add(address);
		});
		return addressList;
	}

	public List<AddressResponseData> getAddressResponseDataList(List<Address> addressList) {
		List<AddressResponseData> addressResponseDataList = new ArrayList<>();
		addressList.forEach(address -> {
			AddressResponseData addressResponseData = new AddressResponseData();
			addressResponseData.setId(address.getId());
			addressResponseData.setAddressType(address.getAddressType());
			addressResponseData.setHouseNumber(address.getHouseNumber());
			addressResponseData.setStreet(address.getStreet());
			addressResponseData.setCity(address.getCity());
			addressResponseData.setCountry(address.getCountry());
			addressResponseData.setPostCode(address.getPostCode());
			addressResponseDataList.add(addressResponseData);
		});
		return addressResponseDataList;
	}

	public AddressResponseData getAddressResponseData(Address address) {
		AddressResponseData addressResponseData = new AddressResponseData();
		addressResponseData.setId(address.getId());
		addressResponseData.setAddressType(address.getAddressType());
		addressResponseData.setHouseNumber(address.getHouseNumber());
		addressResponseData.setStreet(address.getStreet());
		addressResponseData.setCity(address.getCity());
		addressResponseData.setCountry(address.getCountry());
		addressResponseData.setPostCode(address.getPostCode());
		return addressResponseData;
	}

}
