package com.restful.mock;

public class MockITData {
	public String getAllUsers() {
		String users = "[\r\n" + "  {\r\n" + "    \"id\": 102,\r\n" + "    \"firstName\": \"Alex\",\r\n"
				+ "    \"lastName\": \"Staler\",\r\n" + "    \"email\": \"alex.staler@gmail.com\",\r\n"
				+ "    \"birthDate\": \"2018-08-20\",\r\n" + "    \"account\": {\r\n" + "      \"accountId\": 1002,\r\n"
				+ "      \"accountType\": \"DEPOSIT\",\r\n" + "      \"accountNumber\": \"97764532\",\r\n"
				+ "      \"amount\": 90000\r\n" + "    },\r\n" + "    \"addresses\": [\r\n" + "      {\r\n"
				+ "        \"id\": 203,\r\n" + "        \"addressType\": \"WORK\",\r\n"
				+ "        \"houseNumber\": \"London\",\r\n" + "        \"street\": \"UK\",\r\n"
				+ "        \"city\": \"12/21\",\r\n" + "        \"postCode\": \"EC1Y4XX\",\r\n"
				+ "        \"country\": \"Chiswell Street\"\r\n" + "      }\r\n" + "    ]\r\n" + "  },\r\n" + "  {\r\n"
				+ "    \"id\": 105,\r\n" + "    \"firstName\": \"Alex\",\r\n" + "    \"lastName\": \"Silver\",\r\n"
				+ "    \"email\": \"alex.silver@gmail.com\",\r\n" + "    \"birthDate\": \"2018-08-20\",\r\n"
				+ "    \"addresses\": [\r\n" + "      {\r\n" + "        \"id\": 206,\r\n"
				+ "        \"addressType\": \"WORK\",\r\n" + "        \"houseNumber\": \"London\",\r\n"
				+ "        \"street\": \"UK\",\r\n" + "        \"city\": \"A-79\",\r\n"
				+ "        \"postCode\": \"EC1Y4XX\",\r\n" + "        \"country\": \"Chiswell Street\"\r\n"
				+ "      },\r\n" + "      {\r\n" + "        \"id\": 207,\r\n" + "        \"addressType\": \"HOME\",\r\n"
				+ "        \"houseNumber\": \"London\",\r\n" + "        \"street\": \"UK\",\r\n"
				+ "        \"city\": \"A-99\",\r\n" + "        \"postCode\": \"TW33GE\",\r\n"
				+ "        \"country\": \"Stains Road\"\r\n" + "      }\r\n" + "    ]\r\n" + "  },\r\n" + "  {\r\n"
				+ "    \"id\": 101,\r\n" + "    \"firstName\": \"Bill\",\r\n" + "    \"lastName\": \"Garry\",\r\n"
				+ "    \"email\": \"bill.garry@gmail.com\",\r\n" + "    \"birthDate\": \"2018-08-20\",\r\n"
				+ "    \"account\": {\r\n" + "      \"accountId\": 1001,\r\n"
				+ "      \"accountType\": \"CURRENT\",\r\n" + "      \"accountNumber\": \"99764532\",\r\n"
				+ "      \"amount\": 5000\r\n" + "    },\r\n" + "    \"addresses\": [\r\n" + "      {\r\n"
				+ "        \"id\": 201,\r\n" + "        \"addressType\": \"HOME\",\r\n"
				+ "        \"houseNumber\": \"London\",\r\n" + "        \"street\": \"UK\",\r\n"
				+ "        \"city\": \"B-41\",\r\n" + "        \"postCode\": \"TW33GE\",\r\n"
				+ "        \"country\": \"Stains Road\"\r\n" + "      },\r\n" + "      {\r\n"
				+ "        \"id\": 202,\r\n" + "        \"addressType\": \"OFFICE\",\r\n"
				+ "        \"houseNumber\": \"London\",\r\n" + "        \"street\": \"UK\",\r\n"
				+ "        \"city\": \"121\",\r\n" + "        \"postCode\": \"E16DU\",\r\n"
				+ "        \"country\": \"Spital Square\"\r\n" + "      }\r\n" + "    ]\r\n" + "  }\r\n" + "]";
		return users;
	}

	public String getUser() {
		final String RESPONSE_BODY = "{\"id\":101,\"firstName\":\"Bill\",\"lastName\":\"Garry\",\"email\":\"bill.garry@gmail.com\",\"birthDate\":\"2018-08-20\",\"account\":{\"accountId\":1001,\"accountType\":\"CURRENT\",\"accountNumber\":\"99764532\",\"amount\":5000.0},\"addresses\":[{\"id\":201,\"addressType\":\"HOME\",\"houseNumber\":\"London\",\"street\":\"UK\",\"city\":\"B-41\",\"postCode\":\"TW33GE\",\"country\":\"Stains Road\"},{\"id\":202,\"addressType\":\"OFFICE\",\"houseNumber\":\"London\",\"street\":\"UK\",\"city\":\"121\",\"postCode\":\"E16DU\",\"country\":\"Spital Square\"}]}";
		return RESPONSE_BODY;
	}

	public String getAccountDetails() {
		final String RESPONSE_BODY = "{\r\n" + "    \"accountId\": 1000,\r\n" + "    \"accountType\": \"SAVING\",\r\n"
				+ "    \"accountNumber\": \"98764532\",\r\n" + "    \"amount\": 10000\r\n" + "}";
		return RESPONSE_BODY;
	}
}
