package com.restful.user.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import com.restful.exception.LoggableTimeoutException;
import com.restful.exception.ResourceNotFoundException;
import com.restful.user.data.AccountResponseData;

@Service
public class AccountProxyServiceImpl implements AccountProxyService {

	private final RestTemplate restTemplate;
	private final Logger logger;

	@Value("${accountServiceUrl}")
	private String accountServiceUrl;

	@Autowired
	public AccountProxyServiceImpl(RestTemplate restTemplate, Logger logger) {
		this.restTemplate = restTemplate;
		this.logger = logger;
	}

	public void setAccountServiceUrl(String accountServiceUrl) {
		this.accountServiceUrl = accountServiceUrl;
	}

	@Override
	@HystrixCommand(fallbackMethod = "fallbackGetAccountDetail", commandKey = "accountService")
	public AccountResponseData getAccountDetail(Long userId) {
		logger.trace(userId + " Enter --->");
		String url = accountServiceUrl + "/accounts?userId=" + userId;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AccountResponseData> responseEntity = null;
		try {
			logger.info("making a http request to account-request-data api for userId: " + userId);
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, AccountResponseData.class);
		} catch (HttpClientErrorException ex) {
			ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException(
					"request resource url " + url + " doesn't exist");
			logger.error(resourceNotFoundException + " <--- Exit");
			throw resourceNotFoundException;
			// return null;
		}
		logger.trace(userId + " <--- Exit");
		return responseEntity.getBody();
	}

	private AccountResponseData fallbackGetAccountDetail(Long userId, Throwable cause) {
		fallback(userId, cause);
		return null;
	}

	private void fallback(Long userId, Throwable cause) {
		logger.trace(userId + " Enter  --->");
		if (cause instanceof HystrixTimeoutException) {
			LoggableTimeoutException loggableTimeoutException = new LoggableTimeoutException(
					"HystrixTimeoutException occured while making a call to account-info-api", cause);
			logger.error("userId: " + userId, loggableTimeoutException);
		} else {
			logger.error("userId: " + userId, cause);
		}
		logger.trace(userId + " <--- Exit");

	}
}
