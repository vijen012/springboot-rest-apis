package com.restful.user.service;

import static net.jadler.Jadler.closeJadler;
import static net.jadler.Jadler.initJadlerUsing;
import static net.jadler.Jadler.onRequest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.restful.user.data.AccountResponseData;

import net.jadler.Jadler;
import net.jadler.stubbing.server.jdk.JdkStubHttpServer;

public class AccountProxyServiceImplTest {

	private static final String USER_ID = "101";
	private static final String RESPONSE_BODY = "{\r\n" + "    \"accountId\": 1000,\r\n"
			+ "    \"accountType\": \"SAVING\",\r\n" + "    \"accountNumber\": \"98764532\",\r\n"
			+ "    \"amount\": 10000\r\n" + "}";

	private AccountProxyServiceImpl accountProxyServiceImpl;

	@Before
	public void setUp() throws Exception {
		/*
		 * initJadler(); MockitoAnnotations.initMocks(this);
		 */

		initJadlerUsing(new JdkStubHttpServer());
		accountProxyServiceImpl = new AccountProxyServiceImpl(new RestTemplate());
	}

	@After
	public void tearDown() throws Exception {
		closeJadler();
	}

	@Test
	public void getAccountDetails_ShouldReturnAccountDetailsWhenAccountDetailsExistForGivenUserId() {
		// @formatter:off
		onRequest()
			.havingMethodEqualTo("GET")			
			.havingPathEqualTo("/accounts")
			.havingParameterEqualTo("userId", USER_ID)
			.havingHeaderEqualTo("Accept", "application/json")					
		.respond()
			.withStatus(200)
			.withBody(RESPONSE_BODY)
			.withContentType("application/json; charset=UTF-8");
		// @formatter:on
		accountProxyServiceImpl.setAccountServiceUrl("http://localhost:" + Jadler.port());
		AccountResponseData account = accountProxyServiceImpl.getAccountDetail(101L);
		assertThat(account).isNotNull();
		assertThat(account.getAccountId()).isEqualTo(1000L);
	}

}
