package com.restful.account.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.account.data.Account;
import com.restful.account.repos.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findAccount(Long userId) {
		return accountRepository.findByUserId(userId);
	}

	@Override
	public Account saveAccount(@Valid Account account) {
		return accountRepository.save(account);
	}
}
