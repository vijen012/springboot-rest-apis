package com.restful.user.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.restful.UserInfoDataApiAppV2;
import com.restful.mock.MockITData;
import com.restful.user.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserInfoDataApiAppV2.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIT {

	@Autowired
	private UserService userService;

	@Autowired
	private Logger logger;

	@Autowired
	private MockMvc mockMvc;

	private MockITData mockITData;

	@Before
	public void setUp() {
		// Set pretty printing of json
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		objectMapper.registerModule(new JavaTimeModule());
//		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		// objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		mockITData = new MockITData();
	}

	@Test
	public void getAllUsers_ShouldReturnAllTheUsersWhichAreStoredInDatabase() throws Exception {
		String userJsonArray = mockITData.getAllUsers();
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
				.param("pageNumber", "0")
				.param("pageSize", "3")
				.header("x-request-header", "abc")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0].firstName", is("Alex")))
				.andExpect(jsonPath("$[1].firstName", is("Alex")))
				.andExpect(jsonPath("$[2].firstName", is("Bill")))
				.andExpect(content().json(userJsonArray))
				.andReturn();
		// @formatter:on
	}

	@Test
	public void getAllUsers_ShouldThrowBadRequestWhenHeaderIsMissingInRequest() throws Exception {
		// @formatter:off
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
				.param("pageNumber", "0")
				.param("pageSize", "3")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.statusCode", is(400)))
				.andExpect(jsonPath("$.message", is("Missing request header 'x-request-header' for method parameter of type String")))
				.andReturn();		
		// @formatter:on
	}

}
