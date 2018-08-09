package com.restful.account.data;

import org.springframework.stereotype.Component;

@Component
public class AccountDataMapper {

	public Account getAccount(AccountRequestData accountRequestData) {
		Account account = new Account();
		account.setUserId(accountRequestData.getUserId());
		account.setAccountType(accountRequestData.getAccountType());
		account.setAccountNumber(accountRequestData.getAccountNumber());
		account.setAmount(accountRequestData.getAmount());
		return account;
	}

	public AccountResponseData getAccountResponseData(Account account) {
		AccountResponseData accountResponseData = new AccountResponseData();
		accountResponseData.setAccountId(account.getAccountId());
		accountResponseData.setUserId(account.getUserId());
		accountResponseData.setAccountType(account.getAccountType());
		accountResponseData.setAccountNumber(account.getAccountNumber());
		accountResponseData.setAmount(account.getAmount());
		return accountResponseData;
	}
}
