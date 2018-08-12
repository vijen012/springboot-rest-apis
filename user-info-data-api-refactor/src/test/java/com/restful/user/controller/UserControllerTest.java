package com.restful.user.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@MockBean
//	private UserService userService;
//
//	@MockBean
//	private RestTemplate restTemplate;
//
//	private MockTestData mockTestData;
//
//	@Before
//	public void setUp() {
//		// Set pretty printing of json
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		objectMapper.registerModule(new JavaTimeModule());
//		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//		// objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//		mockTestData = new MockTestData();
//	}
//
//	@Test
//	public void getAllUsers_ShouldReturnAllTheUsersWhichAreStoredInDatabase() throws Exception {
//		List<User> userList = mockTestData.getUserList();
//		when(userService.findAllUser(0, 2)).thenReturn(userList);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").param("pageNumber", "0")
//				.param("pageSize", "2").header("x-request-header", "abc").accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(userList))).andReturn();
//		verify(userService, times(1)).findAllUser(anyInt(), anyInt());
//	}
//
//	@Test
//	public void getAllUsers_ShouldReturnEmptyJsonArrayIfThereAreNotRecordsInDatabase() throws Exception {
//		List<User> userList = new ArrayList<User>();
//		when(userService.findAllUser(0, 2)).thenReturn(userList);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users").header("x-request-header", "abc")
//				.accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(userList))).andReturn();
//		verify(userService, times(1)).findAllUser(anyInt(), anyInt());
//	}
//
//	@Test
//	public void getUser_ShouldReturnUserForValidUserId() throws Exception {
//		User user = mockTestData.getUser();
//		when(userService.findUser(anyLong())).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101").accept(MediaType.APPLICATION_JSON);
//		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(user))).andReturn();
//		verify(userService, times(1)).findUser(anyLong());
//	}
//
//	@Test
//	public void createUser_ShouldReturnSavedUserAndStatusShouldBeCreated() throws Exception {
//
//		User user = mockTestData.getUser();
//		when(userService.saveUser(any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isCreated())
//				.andExpect(content().json(objectMapper.writeValueAsString(user)));
//		verify(userService, times(1)).saveUser(any(User.class));
//	}
//
//	@Test
//	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenFirstNameAndLastNameStringLengthLessThanTwo()
//			throws Exception {
//		User user = mockTestData.getUser();
//		user.setFirstName("M");
//		user.setLastName("H");
//		when(userService.saveUser(any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenEmailIsNotPerSwagger()
//			throws Exception {
//		User user = mockTestData.getUser();
//		user.setEmail("martin.hussy.gmail.com");
//		when(userService.saveUser(any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenBirthDateInFuture()
//			throws Exception {
//		User user = mockTestData.getUser();
//		user.setBirthDate(LocalDate.of(2022, 6, 12));
//		when(userService.saveUser(any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void createUser_ShouldThrowBadRequestExceptionAndStatusShouldBeBadRequestWhenRequstPayloadIsEmpty()
//			throws Exception {
//		User user = mockTestData.getUser();
//		when(userService.saveUser(any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("");
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void deleteUser_ShouldReturnHttpStatusNoContentWhenUserIsExist() throws Exception {
//		when(userService.deleteUser(anyLong())).thenReturn(HttpStatus.NO_CONTENT);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/101");
//		mockMvc.perform(requestBuilder).andExpect(status().isNoContent());
//		verify(userService, times(1)).deleteUser(anyLong());
//	}
//
//	@Test
//	public void deleteUser_ShouldReturnHttpStatusAcceptedWhenUserDoesNotExist() throws Exception {
//		when(userService.deleteUser(anyLong())).thenReturn(HttpStatus.ACCEPTED);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/101");
//		mockMvc.perform(requestBuilder).andExpect(status().isAccepted());
//		verify(userService, times(1)).deleteUser(anyLong());
//	}
//
//	@Test
//	public void updateUser_ShouldReturnUpdatedRecoredAndStatusShouldBeOkWhenRequestIsValid() throws Exception {
//		User user = mockTestData.getUser();
//		when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(user)));
//		verify(userService, times(1)).updateUser(anyLong(), any(User.class));
//	}
//
//	@Test
//	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenRequstPayloadIsEmpty() throws Exception {
//		User user = mockTestData.getUser();
//		when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content("");
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenFirstNameAndLastNameStringLengthIsLessThanTwo()
//			throws Exception {
//		User user = mockTestData.getUser();
//		user.setFirstName("M");
//		user.setLastName("H");
//		when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenEmailIsNotAsPerSwagger() throws Exception {
//		User user = mockTestData.getUser();
//		user.setEmail("martin.hussy");
//		when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
//
//	@Test
//	public void updateUser_ShouldThrowExceptionAndStatusShouldBeBadRequstWhenBirthDateInFuture() throws Exception {
//		User user = mockTestData.getUser();
//		user.setBirthDate(LocalDate.of(2022, 6, 12));
//		when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/101").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(objectMapper.writeValueAsString(user));
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//	}
}
