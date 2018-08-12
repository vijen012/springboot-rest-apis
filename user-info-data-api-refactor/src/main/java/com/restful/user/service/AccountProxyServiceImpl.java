package com.restful.user.service;

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

import com.restful.exception.ResourceNotFoundException;
import com.restful.user.data.AccountResponseData;

@Service
public class AccountProxyServiceImpl implements AccountProxyService {

	private final RestTemplate restTemplate;

	@Value("${accountServiceUrl}")
	private String accountServiceUrl;

	@Autowired
	public AccountProxyServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setAccountServiceUrl(String accountServiceUrl) {
		this.accountServiceUrl = accountServiceUrl;
	}

	@Override
	public AccountResponseData getAccountDetail(Long userId) {
		String url = accountServiceUrl + "/accounts?userId=" + userId;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AccountResponseData> responseEntity = null;
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, AccountResponseData.class);
		} catch (HttpClientErrorException ex) {
			throw new ResourceNotFoundException("request resource url " + url + " doesn't exist");
			// return null;
		}
		return responseEntity.getBody();
	}
}
