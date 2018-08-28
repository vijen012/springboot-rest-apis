package com.restful.user.service;

import java.util.List;

import com.restful.user.data.AccountResponseData;

public interface AccountProxyService {
	public List<AccountResponseData> getAccountsDetail(Long userId);
}
