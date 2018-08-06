package com.restful.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.account.data.Account;
import com.restful.account.repos.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getAccountDetails(Long userId) {
		return accountRepository.findByUserId(userId);
	}
}
