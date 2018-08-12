package com.restful.address.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AddressController.class)
public class AddressControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	@MockBean
//	private RestTemplate restTemplate;
//
//	@MockBean
//	private AddressService addressService;
//
//	private MockTestData mockTestData;
//
//	@Before
//	public void setUp() {
//		// Set pretty printing of json
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		mockTestData = new MockTestData();
//	}
//
//	@Test
//	public void getAllAddress_ShouldReturnAllTheAddressWhenAddressDoesExistForGivenUserId()
//			throws JsonProcessingException, Exception {
//		List<Address> addressList = mockTestData.getAddressList();
//		when(addressService.findAllAddressByUserId(anyLong())).thenReturn(addressList);
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/addresses")
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		MvcResult result = mockMvc.perform(requestBuilder)
//				.andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(addressList)))
//				.andReturn();
//		// @formatter:on
//		verify(addressService).findAllAddressByUserId(anyLong());
//	}
//
//	@Test
//	public void getAllAddress_ShouldReturnAllTheAddressWhenAddressDoesNotExistForGivenUserId()
//			throws JsonProcessingException, Exception {
//		List<Address> addressList = new ArrayList<>();
//		when(addressService.findAllAddressByUserId(anyLong())).thenReturn(addressList);
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/addresses")
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		MvcResult result = mockMvc.perform(requestBuilder)
//				.andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(addressList)))
//				.andReturn();
//		// @formatter:on
//		verify(addressService).findAllAddressByUserId(anyLong());
//	}
//
//	@Test
//	public void getAllAddress_ShouldThrowExceptionWhenPathVariableUserIdIsString()
//			throws JsonProcessingException, Exception {
//		List<Address> addressList = mockTestData.getAddressList();
//		when(addressService.findAllAddressByUserId(anyLong())).thenReturn(addressList);
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101str/addresses")
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		mockMvc.perform(requestBuilder)
//				.andExpect(status().isBadRequest());
//		// @formatter:on
//	}
//
//	@Test
//	public void getAddress_ShoudlReturnTheAddressWhenGivenAddressIdIsValid() throws JsonProcessingException, Exception {
//		Address address = mockTestData.getAddress();
//		when(addressService.findAddress(anyLong())).thenReturn(address);
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/addresses/201")
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		MvcResult result = mockMvc.perform(requestBuilder).andExpect(status().isOk())
//				.andExpect(content().json(objectMapper.writeValueAsString(address))).andReturn();
//		// @formatter:on
//		verify(addressService).findAddress(anyLong());
//
//	}
//
//	@Test
//	public void getAddress_ShoudlThrowAddressNotFoundExceptionWhenGivenAddressIdDoesNotExist()
//			throws JsonProcessingException, Exception {
//		doThrow(AddressNotFoundException.class).when(addressService).findAddress(anyLong());
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/addresses/201")
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
//		// @formatter:on
//		verify(addressService).findAddress(anyLong());
//	}
//
//	@Test
//	public void getAddress_ShoudlThrowBadRequestWhenGivenAddressIdIsString() throws Exception {
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/101/addresses/201kl")
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		mockMvc.perform(requestBuilder).andExpect(status().isBadRequest());
//		// @formatter:on
//	}
//
//	@Test
//	public void createAddress_ShouldReturnSavedAddressWhenRequestPayloadAndUserIdIsValid()
//			throws JsonProcessingException, Exception {
//		Address address = mockTestData.getAddress();
//		when(addressService.saveAddress(anyLong(), any(Address.class))).thenReturn(address);
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/101/addresses")
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(objectMapper.writeValueAsString(address))
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		MvcResult mvcResult = mockMvc.perform(requestBuilder)
//				.andExpect(status().isCreated())
//				.andExpect(content().json(objectMapper.writeValueAsString(address)))
//				.andReturn();
//		// @formatter:on
//		verify(addressService).saveAddress(anyLong(), any(Address.class));
//	}
//
//	@Test
//	public void createAddress_ShouldUserNotFoundExeceptionWhenRequestPayloadIsValidButUserIdDoesNotExist()
//			throws JsonProcessingException, Exception {
//		Address address = mockTestData.getAddress();
//		doThrow(UserNotFoundException.class).when(addressService).saveAddress(anyLong(), any(Address.class));
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/101/addresses")
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(objectMapper.writeValueAsString(address))
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		mockMvc.perform(requestBuilder)
//				.andExpect(status().isNotFound());
//		// @formatter:on
//		verify(addressService).saveAddress(anyLong(), any(Address.class));
//	}
//
//	@Test
//	public void createAddress_ShouldThrowBadRequestWhenRequestPayloadIsValidButUserIdIsString()
//			throws JsonProcessingException, Exception {
//		Address address = mockTestData.getAddress();
//		when(addressService.saveAddress(anyLong(), any(Address.class))).thenReturn(address);
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/101kl/addresses")
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(objectMapper.writeValueAsString(address))
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		mockMvc.perform(requestBuilder)
//				.andExpect(status().isBadRequest());
//		// @formatter:on
//	}
//
//	@Test
//	public void createAddress_ShouldThrowBadRequestWhenRequestPayloadIsInValidButUserIdIsValid()
//			throws JsonProcessingException, Exception {
//		Address address = mockTestData.getAddress();
//		address.setCity("");
//		// @formatter:off
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/101/addresses")
//				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//				.content(objectMapper.writeValueAsString(address))
//				.accept(MediaType.APPLICATION_JSON_VALUE);
//		mockMvc.perform(requestBuilder)
//				.andExpect(status().isBadRequest());
//		// @formatter:on
//	}

}
