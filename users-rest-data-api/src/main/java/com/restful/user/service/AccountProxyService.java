package com.restful.user.service;

import com.restful.user.data.AccountResponseData;

public interface AccountProxyService {
	public AccountResponseData getAccountDetail(Long userId);
}
