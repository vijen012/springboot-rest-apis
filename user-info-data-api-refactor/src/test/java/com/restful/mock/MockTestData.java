package com.restful.mock;

public class MockTestData {

//	public List<User> getUserList() {
//		String userListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 101,\r\n" + "    \"firstName\": \"Whitney\",\r\n"
//				+ "    \"lastName\": \"Simmons\",\r\n" + "    \"email\": \"whitney.simmons@gmail.com\",\r\n"
//				+ "    \"birthDate\": \"2018-07-27\"\r\n" + "  },\r\n" + "  {\r\n" + "    \"id\": 102,\r\n"
//				+ "    \"firstName\": \"Cass\",\r\n" + "    \"lastName\": \"Martin\",\r\n"
//				+ "    \"email\": \"cass.martin@gmail.com\",\r\n" + "    \"birthDate\": \"2018-09-29\"\r\n" + "  },\r\n"
//				+ "  {\r\n" + "    \"id\": 103,\r\n" + "    \"firstName\": \"Martin\",\r\n"
//				+ "    \"lastName\": \"Hussy\",\r\n" + "    \"email\": \"martin.hussy@gmail.com\",\r\n"
//				+ "    \"birthDate\": \"2018-05-16\"\r\n" + "  }  \r\n" + "]";
//		List<User> userList = null;
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//		try {
//			userList = objectMapper.readValue(userListJsonString, new TypeReference<List<User>>() {
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return userList;
//	}
//
//	public User getUser() {
//		String userJsonString = "{\r\n" + "  \"id\": 101,\r\n" + "  \"firstName\": \"Whitney\",\r\n"
//				+ "  \"lastName\": \"Simmons\",\r\n" + "  \"email\": \"whitney.simmons@gmail.com\",\r\n"
//				+ "  \"birthDate\": \"2018-07-27\"\r\n" + "}";
//		User user = null;
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.registerModule(new JavaTimeModule());
//		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//		try {
//			user = objectMapper.readValue(userJsonString, User.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return user;
//	}
//
//	public List<Address> getAddressList() {
//		String addressListJsonString = "[\r\n" + "  {\r\n" + "    \"id\": 201,\r\n"
//				+ "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
//				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
//				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  },\r\n" + "  {\r\n"
//				+ "    \"id\": 201,\r\n" + "    \"houseNumber\": \"201\",\r\n" + "    \"addressType\": \"HOME\",\r\n"
//				+ "    \"street\": \"Stains Road\",\r\n" + "    \"city\": \"London\",\r\n"
//				+ "    \"postCode\": \"TW33GE\",\r\n" + "    \"country\": \"UK\"\r\n" + "  }\r\n" + "]";
//		ObjectMapper objectMapper = new ObjectMapper();
//		List<Address> addressList = null;
//		try {
//			addressList = objectMapper.readValue(addressListJsonString, new TypeReference<List<Address>>() {
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return addressList;
//	}
//
//	public Address getAddress() {
//		String addressJsonString = "{\r\n" + "  \"id\": 201,\r\n" + "  \"houseNumber\": \"201\",\r\n"
//				+ "  \"addressType\": \"HOME\",\r\n" + "  \"street\": \"Stains Road\",\r\n"
//				+ "  \"city\": \"London\",\r\n" + "  \"postCode\": \"TW33GE\",\r\n" + "  \"country\": \"UK\"\r\n" + "}";
//		ObjectMapper objectMapper = new ObjectMapper();
//		Address address = null;
//		try {
//			address = objectMapper.readValue(addressJsonString, Address.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return address;
//	}
//
//	public AccountResponseData getAccountResponseData() {
//		String accountDataJsonString = "{\r\n" + "	\"accountId\": 1000,\r\n" + "	\"accountType\": \"SAVING\",\r\n"
//				+ "	\"accountNumber\": \"1223BBC\",\r\n" + "	\"amount\": 2000\r\n" + "}";
//		ObjectMapper objectMapper = new ObjectMapper();
//		AccountResponseData accountResponseData = null;
//		try {
//			accountResponseData = objectMapper.readValue(accountDataJsonString, AccountResponseData.class);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return accountResponseData;
//	}
}
