package com.restful.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restful.account.data.Account;
import com.restful.account.data.AccountDataMapper;
import com.restful.account.data.AccountRequestData;
import com.restful.account.data.AccountResponseData;
import com.restful.account.repos.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AccountDataMapper accountDataMapper;

	@Override
	public AccountResponseData findAccount(Long userId) {
		Account account = accountRepository.findByUserId(userId);
		AccountResponseData accountResponseData = account != null ? accountDataMapper.getAccountResponseData(account)
				: null;
		return accountResponseData;
	}

	@Override
	public AccountResponseData saveAccount(AccountRequestData accountRequestData) {
		Account account = accountDataMapper.getAccount(accountRequestData);
		account = accountRepository.save(account);
		return accountDataMapper.getAccountResponseData(account);
	}
}
