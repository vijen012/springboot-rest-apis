package com.restful.address.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restful.user.data.User;

@Component
public class AddressDataMapper {

	public List<Address> getAddressList(User user, List<AddressRequestData> addressRequestDataList) {
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
//			address.setUser(user);
			addressList.add(address);
		});
		return addressList;
	}

}
