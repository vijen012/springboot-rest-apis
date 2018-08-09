package com.restful.account.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountResponseData {

	private Long accountId;

	@JsonIgnore
	private Long userId;
	private AccountType accountType;
	private String accountNumber;
	private double amount;

	public AccountResponseData() {

	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "AccountResponseData [accountId=" + accountId + ", accountType=" + accountType + ", accountNumber="
				+ accountNumber + ", amount=" + amount + "]";
	}
}
