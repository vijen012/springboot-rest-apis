package com.restful.mock;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.restful.address.data.Address;
import com.restful.address.data.AddressRequestData;
import com.restful.address.data.AddressResponseData;
import com.restful.user.data.AccountResponseData;
import com.restful.user.data.User;
import com.restful.user.data.UserRequestData;
import com.restful.user.data.UserResponseData;

public class MockTestData {

	public List<User> getUserList() {
		String userListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 101,\r\n" + "    \"firstName\": \"Whitney\",\r\n"
				+ "    \"lastName\": \"Simmons\",\r\n" + "    \"email\": \"whitney.simmons@gmail.com\",\r\n"
				+ "    \"birthDate\": \"2018-07-27\"\r\n" + "  },\r\n" + "  {\r\n" + "    \"id\": 102,\r\n"
				+ "    \"firstName\": \"Cass\",\r\n" + "    \"lastName\": \"Martin\",\r\n"
				+ "    \"email\": \"cass.martin@gmail.com\",\r\n" + "    \"birthDate\": \"2018-09-29\"\r\n" + "  },\r\n"
				+ "  {\r\n" + "    \"id\": 103,\r\n" + "    \"firstName\": \"Martin\",\r\n"
				+ "    \"lastName\": \"Hussy\",\r\n" + "    \"email\": \"martin.hussy@gmail.com\",\r\n"
				+ "    \"birthDate\": \"2018-05-16\"\r\n" + "  }  \r\n" + "]";
		List<User> userList = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			userList = objectMapper.readValue(userListJsonString, new TypeReference<List<User>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public List<UserRequestData> getUserRequestDataList() {
		String userListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 101,\r\n" + "    \"firstName\": \"Whitney\",\r\n"
				+ "    \"lastName\": \"Simmons\",\r\n" + "    \"email\": \"whitney.simmons@gmail.com\",\r\n"
				+ "    \"birthDate\": \"2018-07-27\"\r\n" + "  },\r\n" + "  {\r\n" + "    \"id\": 102,\r\n"
				+ "    \"firstName\": \"Cass\",\r\n" + "    \"lastName\": \"Martin\",\r\n"
				+ "    \"email\": \"cass.martin@gmail.com\",\r\n" + "    \"birthDate\": \"2018-09-29\"\r\n" + "  },\r\n"
				+ "  {\r\n" + "    \"id\": 103,\r\n" + "    \"firstName\": \"Martin\",\r\n"
				+ "    \"lastName\": \"Hussy\",\r\n" + "    \"email\": \"martin.hussy@gmail.com\",\r\n"
				+ "    \"birthDate\": \"2018-05-16\"\r\n" + "  }  \r\n" + "]";
		List<UserRequestData> userRequestDataList = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			userRequestDataList = objectMapper.readValue(userListJsonString,
					new TypeReference<List<UserRequestData>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userRequestDataList;
	}

	public List<UserResponseData> getUserResponseDataList() {
		String userListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 101,\r\n" + "    \"firstName\": \"Whitney\",\r\n"
				+ "    \"lastName\": \"Simmons\",\r\n" + "    \"email\": \"whitney.simmons@gmail.com\",\r\n"
				+ "    \"birthDate\": \"2018-07-27\"\r\n" + "  },\r\n" + "  {\r\n" + "    \"id\": 102,\r\n"
				+ "    \"firstName\": \"Cass\",\r\n" + "    \"lastName\": \"Martin\",\r\n"
				+ "    \"email\": \"cass.martin@gmail.com\",\r\n" + "    \"birthDate\": \"2018-09-29\"\r\n" + "  },\r\n"
				+ "  {\r\n" + "    \"id\": 103,\r\n" + "    \"firstName\": \"Martin\",\r\n"
				+ "    \"lastName\": \"Hussy\",\r\n" + "    \"email\": \"martin.hussy@gmail.com\",\r\n"
				+ "    \"birthDate\": \"2018-05-16\"\r\n" + "  }  \r\n" + "]";
		List<UserResponseData> userResponseDataList = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			userResponseDataList = objectMapper.readValue(userListJsonString,
					new TypeReference<List<UserResponseData>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userResponseDataList;
	}

	public User getUser() {
		String userJsonString = "{\r\n" + "  \"id\": 101,\r\n" + "  \"firstName\": \"Whitney\",\r\n"
				+ "  \"lastName\": \"Simmons\",\r\n" + "  \"email\": \"whitney.simmons@gmail.com\",\r\n"
				+ "  \"birthDate\": \"2018-07-27\"\r\n" + "}";
		User user = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			user = objectMapper.readValue(userJsonString, User.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return user;
	}

	public UserRequestData getUserRequestData() {
		String userJsonString = "{\r\n" + "  \"id\": 101,\r\n" + "  \"firstName\": \"Whitney\",\r\n"
				+ "  \"lastName\": \"Simmons\",\r\n" + "  \"email\": \"whitney.simmons@gmail.com\",\r\n"
				+ "  \"birthDate\": \"2018-07-27\"\r\n" + "}";
		UserRequestData userRequestData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			userRequestData = objectMapper.readValue(userJsonString, UserRequestData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userRequestData;
	}

	public UserResponseData getUserResponseData() {
		String userJsonString = "{\r\n" + "  \"id\": 101,\r\n" + "  \"firstName\": \"Whitney\",\r\n"
				+ "  \"lastName\": \"Simmons\",\r\n" + "  \"email\": \"whitney.simmons@gmail.com\",\r\n"
				+ "  \"birthDate\": \"2018-07-27\"\r\n" + "}";
		UserResponseData userResponseData = null;
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			userResponseData = objectMapper.readValue(userJsonString, UserResponseData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userResponseData;
	}

	public List<Address> getAddressList() {
		String addressListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 201,\r\n"
				+ "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  },\r\n" + "  {\r\n"
				+ "    \"id\": 201,\r\n" + "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  }\r\n" + "]";
		ObjectMapper objectMapper = new ObjectMapper();
		List<Address> addressList = null;
		try {
			addressList = objectMapper.readValue(addressListJsonString, new TypeReference<List<Address>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressList;
	}

	public List<AddressRequestData> getAddressRequstDataList() {
		String addressListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 201,\r\n"
				+ "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  },\r\n" + "  {\r\n"
				+ "    \"id\": 201,\r\n" + "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  }\r\n" + "]";
		ObjectMapper objectMapper = new ObjectMapper();
		List<AddressRequestData> addressRequestDataList = null;
		try {
			addressRequestDataList = objectMapper.readValue(addressListJsonString,
					new TypeReference<List<AddressRequestData>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressRequestDataList;
	}

	public List<AddressResponseData> getAddressResponseDataList() {
		String addressListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 201,\r\n"
				+ "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  },\r\n" + "  {\r\n"
				+ "    \"id\": 201,\r\n" + "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  }\r\n" + "]";
		ObjectMapper objectMapper = new ObjectMapper();
		List<AddressResponseData> addressResponseDataList = null;
		try {
			addressResponseDataList = objectMapper.readValue(addressListJsonString,
					new TypeReference<List<AddressResponseData>>() {
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressResponseDataList;
	}

	public Address getAddress() {
		String addressJsonString = "{\r\n" + "  \"id\": 201,\r\n" + "  \"houseNumber\": \"201\",\r\n"
				+ "  \"addressType\": \"HOME\",\r\n" + "  \"street\": \"Stains Road\",\r\n"
				+ "  \"city\": \"London\",\r\n" + "  \"postCode\": \"TW33GE\",\r\n" + "  \"country\": \"UK\"\r\n" + "}";
		ObjectMapper objectMapper = new ObjectMapper();
		Address address = null;
		try {
			address = objectMapper.readValue(addressJsonString, Address.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return address;
	}

	public AddressResponseData getAddressResponseData() {
		String addressJsonString = "{\r\n" + "  \"id\": 201,\r\n" + "  \"houseNumber\": \"201\",\r\n"
				+ "  \"addressType\": \"HOME\",\r\n" + "  \"street\": \"Stains Road\",\r\n"
				+ "  \"city\": \"London\",\r\n" + "  \"postCode\": \"TW33GE\",\r\n" + "  \"country\": \"UK\"\r\n" + "}";
		ObjectMapper objectMapper = new ObjectMapper();
		AddressResponseData addressResponseData = null;
		try {
			addressResponseData = objectMapper.readValue(addressJsonString, AddressResponseData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressResponseData;
	}

	public AddressRequestData getAddressRequestData() {
		String addressJsonString = "{\r\n" + "  \"id\": 201,\r\n" + "  \"houseNumber\": \"201\",\r\n"
				+ "  \"addressType\": \"HOME\",\r\n" + "  \"street\": \"Stains Road\",\r\n"
				+ "  \"city\": \"London\",\r\n" + "  \"postCode\": \"TW33GE\",\r\n" + "  \"country\": \"UK\"\r\n" + "}";
		ObjectMapper objectMapper = new ObjectMapper();
		AddressRequestData addressRequestData = null;
		try {
			addressRequestData = objectMapper.readValue(addressJsonString, AddressRequestData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return addressRequestData;
	}

	public AccountResponseData getAccountResponseData() {
		String accountDataJsonString = "{\r\n" + "	\"accountId\": 1000,\r\n" + "	\"accountType\": \"SAVING\",\r\n"
				+ "	\"accountNumber\": \"1223BBC\",\r\n" + "	\"amount\": 2000\r\n" + "}";
		ObjectMapper objectMapper = new ObjectMapper();
		AccountResponseData accountResponseData = null;
		try {
			accountResponseData = objectMapper.readValue(accountDataJsonString, AccountResponseData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return accountResponseData;
	}
}
