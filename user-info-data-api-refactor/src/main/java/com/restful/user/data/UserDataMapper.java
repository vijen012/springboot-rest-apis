package com.restful.user.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restful.address.data.AddressDataMapper;

@Component
public class UserDataMapper {

	@Autowired
	private AddressDataMapper addressDataMapper;

	public UserResponseData getUserResponseData(User user, AccountResponseData accountResponseData) {
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
		userResponseData.setAccountResponseData(accountResponseData);
		return userResponseData;
	}

	public List<UserResponseData> getUserResponseDataList(Map<User, AccountResponseData> userResponseDataMap) {
		List<UserResponseData> userResponseDataList = new ArrayList<>();
		userResponseDataMap.forEach((user, accountResponseData) -> {
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
			userResponseData.setAccountResponseData(accountResponseData);
			userResponseDataList.add(userResponseData);

		});
		return userResponseDataList;
	}

	public User getUser(UserRequestData userRequestData) {
		User user = new User();
		user.setId(userRequestData.getId());
		user.setFirstName(userRequestData.getFirstName());
		user.setLastName(userRequestData.getLastName());
		user.setEmail(userRequestData.getEmail());
		user.setBirthDate(userRequestData.getBirthDate());
		if (userRequestData.getAddressRequestDataList() != null) {
			user.setAddrssList(addressDataMapper.getAddressList(userRequestData.getAddressRequestDataList()));
		}
		return user;
	}
}
