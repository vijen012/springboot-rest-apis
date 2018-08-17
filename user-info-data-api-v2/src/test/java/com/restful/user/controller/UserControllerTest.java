package com.restful.user.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.restful.mock.MockTestData;
import com.restful.user.data.User;
import com.restful.user.data.UserRequestData;
import com.restful.user.data.UserResponseData;
import com.restful.user.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private UserService userService;

	@MockBean
	private RestTemplate restTemplate;

	@MockBean
	private Logger logger;

	private MockTestData mockTestData;

	@Before
	public void setUp() {
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		// objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		mockTestData = new MockTestData();
	}

	@Test
	public void getAllUsers_ShouldReturnAllTheUsersWhichAreStoredInDatabase() throws Exception {
		List<User> userList = mockTestData.getUserList();
		List<UserResponseData> userResponseDataList = mockTestData.getUserResponseDataList();
		when(userService.findAllUser(0, 2)).thenReturn(userResponseDataList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").param("pageNumber", "0")
				.param("pageSize", "2").header("x-request-header", "abc").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(userResponseDataList))).andReturn();
		verify(userService, times(1)).findAllUser(anyInt(), anyInt());
	}

	@Test
	public void getAllUsers_ShouldReturnEmptyJsonArrayIfThereAreNotRecordsInDatabase() throws Exception {
		List<UserResponseData> userResponseDataList = new ArrayList<UserResponseData>();
		when(userService.findAllUser(0, 2)).thenReturn(userResponseDataList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").header("x-request-header", "abc")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(userResponseDataList))).andReturn();
		verify(userService, times(1)).findAllUser(anyInt(), anyInt());
	}

	@Test
	public void getUser_ShouldReturnUserForValidUserId() throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		when(userService.findUser(anyLong())).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(userResponseData))).andReturn();
		verify(userService, times(1)).findUser(anyLong());
	}

	@Test
	public void createUser_ShouldReturnSavedUserAndStatusShouldBeCreated() throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		when(userService.saveUser(any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(userResponseData)));
		verify(userService, times(1)).saveUser(any(UserRequestData.class));
	}

	@Test
	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenFirstNameAndLastNameStringLengthLessThanTwo()
			throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		userRequestData.setFirstName("M");
		userRequestData.setLastName("H");
		when(userService.saveUser(any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenEmailIsNotPerSwagger()
			throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		userRequestData.setEmail("martin.hussy.gmail.com");
		when(userService.saveUser(any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenBirthDateInFuture()
			throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		userRequestData.setBirthDate(LocalDate.of(2022, 6, 12));
		when(userService.saveUser(any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenRequstPayloadIsEmpty()
			throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("");
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void deleteUser_ShouldReturnHttpStatusNoContentWhenUserIsExist() throws Exception {
		when(userService.deleteUser(anyLong())).thenReturn(HttpStatus.NO_CONTENT);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/101");
		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());
		verify(userService, times(1)).deleteUser(anyLong());
	}

	@Test
	public void deleteUser_ShouldReturnHttpStatusAcceptedWhenUserDoesNotExist() throws Exception {
		when(userService.deleteUser(anyLong())).thenReturn(HttpStatus.ACCEPTED);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/101");
		mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
		verify(userService, times(1)).deleteUser(anyLong());
	}

	@Test
	public void updateUser_ShouldReturnUpdatedRecoredAndStatusShouldBeOkWhenRequestIsValid() throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		when(userService.updateUser(anyLong(), any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(userResponseData)));
		verify(userService, times(1)).updateUser(anyLong(), any(UserRequestData.class));
	}

	@Test
	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenRequstPayloadIsEmpty() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("");
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenFirstNameAndLastNameStringLengthIsLessThanTwo()
			throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		userRequestData.setFirstName("M");
		userRequestData.setLastName("H");
		when(userService.updateUser(anyLong(), any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenEmailIsNotAsPerSwagger() throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		userRequestData.setEmail("martin.hussy");
		when(userService.updateUser(anyLong(), any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}

	@Test
	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenBirthDateInFuture() throws Exception {
		UserResponseData userResponseData = mockTestData.getUserResponseData();
		UserRequestData userRequestData = mockTestData.getUserRequestData();
		userRequestData.setBirthDate(LocalDate.of(2022, 6, 12));
		when(userService.updateUser(anyLong(), any(UserRequestData.class))).thenReturn(userResponseData);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(objectMapper.writeValueAsString(userRequestData));
		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
	}
}
