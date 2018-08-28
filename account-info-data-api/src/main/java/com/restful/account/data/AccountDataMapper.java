package com.restful.account.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public List<AccountResponseData> getAccountResponseDataList(List<Account> accountList) {
		List<AccountResponseData> accountResponseDataList = new ArrayList<>();
		Optional<List<Account>> accountListOpt = Optional.ofNullable(accountList);
		accountListOpt.ifPresent(accounts -> {
			accounts.forEach(account -> {
				AccountResponseData accountResponseData = new AccountResponseData();
				accountResponseData.setAccountId(account.getAccountId());
				accountResponseData.setUserId(account.getUserId());
				accountResponseData.setAccountType(account.getAccountType());
				accountResponseData.setAccountNumber(account.getAccountNumber());
				accountResponseData.setAmount(account.getAmount());
				accountResponseDataList.add(accountResponseData);
			});
		});
		return accountResponseDataList;
	}
}
