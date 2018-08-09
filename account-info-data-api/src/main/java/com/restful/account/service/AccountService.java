package com.restful.account.service;

import com.restful.account.data.AccountRequestData;
import com.restful.account.data.AccountResponseData;

public interface AccountService {

	public AccountResponseData findAccount(Long userId);

	public AccountResponseData saveAccount(AccountRequestData accountRequestData);
}
