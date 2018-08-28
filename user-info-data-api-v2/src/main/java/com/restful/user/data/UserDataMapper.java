package com.restful.user.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.restful.address.data.AddressDataMapper;

@Component
public class UserDataMapper {

	public UserResponseData getUserResponseData(User user, List<AccountResponseData> accountResponseDataList) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		UserResponseData userResponseData = new UserResponseData();
		userResponseData.setId(user.getId());
		userResponseData.setFirstName(user.getFirstName());
		userResponseData.setLastName(user.getLastName());
		userResponseData.setEmail(user.getEmail());
		userResponseData.setBirthDate(user.getBirthDate());
		if (user.getAddressList() != null) {
			userResponseData
					.setAddressResponseDataList(addressDataMapper.getAddressResponseDataList(user.getAddressList()));
		}
		userResponseData.setAccountResponseDataList(accountResponseDataList);
		return userResponseData;
	}

	public List<UserResponseData> getUserResponseDataList(Map<User, List<AccountResponseData>> userResponseDataMap) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		List<UserResponseData> userResponseDataList = new ArrayList<>();
		userResponseDataMap.forEach((user, accountResponseDataList) -> {
			UserResponseData userResponseData = new UserResponseData();
			userResponseData.setId(user.getId());
			userResponseData.setFirstName(user.getFirstName());
			userResponseData.setLastName(user.getLastName());
			userResponseData.setEmail(user.getEmail());
			userResponseData.setBirthDate(user.getBirthDate());
			if (user.getAddressList() != null) {
				userResponseData.setAddressResponseDataList(
						addressDataMapper.getAddressResponseDataList(user.getAddressList()));
			}
			userResponseData.setAccountResponseDataList(accountResponseDataList);
			userResponseDataList.add(userResponseData);

		});
		return userResponseDataList;
	}

	public User getUser(UserRequestData userRequestData) {
		AddressDataMapper addressDataMapper = new AddressDataMapper();
		User user = new User();
		user.setId(userRequestData.getId());
		user.setFirstName(userRequestData.getFirstName());
		user.setLastName(userRequestData.getLastName());
		user.setEmail(userRequestData.getEmail());
		user.setBirthDate(userRequestData.getBirthDate());
		if (userRequestData.getAddressRequestDataList() != null) {
			user.setAddressList(addressDataMapper.getAddressList(userRequestData.getAddressRequestDataList()));
		}
		return user;
	}
}
