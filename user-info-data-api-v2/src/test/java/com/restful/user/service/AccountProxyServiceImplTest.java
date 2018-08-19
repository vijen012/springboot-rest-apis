package com.restful.user.service;

import static net.jadler.Jadler.closeJadler;
import static net.jadler.Jadler.initJadlerUsing;
import static net.jadler.Jadler.onRequest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.restful.mock.MockTestData;
import com.restful.user.data.AccountResponseData;

import net.jadler.Jadler;
import net.jadler.stubbing.server.jdk.JdkStubHttpServer;

public class AccountProxyServiceImplTest {

	private static final String USER_ID = "101";

	@Mock
	private Logger logger;

	private ObjectMapper objectMapper;

	private AccountProxyServiceImpl accountProxyServiceImpl;

	private MockTestData mockTestData;

	@Before
	public void setUp() throws Exception {
		/*
		 * initJadler(); MockitoAnnotations.initMocks(this);
		 */
		MockitoAnnotations.initMocks(this);
		initJadlerUsing(new JdkStubHttpServer());
		accountProxyServiceImpl = new AccountProxyServiceImpl(new RestTemplate(), logger);
		objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		mockTestData = new MockTestData();
	}

	@After
	public void tearDown() throws Exception {
		closeJadler();
	}

	@Test
	public void getAccountDetails_ShouldReturnAccountDetailsWhenAccountDetailsExistForGivenUserId()
			throws JsonProcessingException {
		AccountResponseData accountResData = mockTestData.getAccountResponseData();
		final String BODY = objectMapper.writeValueAsString(accountResData);
		// @formatter:off
		onRequest()
			.havingMethodEqualTo("GET")			
			.havingPathEqualTo("/accounts")
			.havingParameterEqualTo("userId", USER_ID)
			.havingHeaderEqualTo("Accept", "application/json")					
		.respond()
			.withStatus(200)
			.withBody(BODY)
			.withContentType("application/json; charset=UTF-8");
		// @formatter:on
		accountProxyServiceImpl.setAccountServiceUrl("http://localhost:" + Jadler.port());
		AccountResponseData account = accountProxyServiceImpl.getAccountDetail(101L);
		assertThat(account).isNotNull();
		assertThat(account.getAccountId()).isEqualTo(1000L);
	}

}
