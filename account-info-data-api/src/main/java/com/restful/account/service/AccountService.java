package com.restful.account.service;

import javax.validation.Valid;

import com.restful.account.data.Account;

public interface AccountService {

	public Account findAccount(Long userId);

	public Account saveAccount(@Valid Account account);
}
