package com.restful.user.data;

public class AccountResponseData {

	private Long accountId;
	private AccountType accountType;
	private String accountNumber;
	private double amount;

	public AccountResponseData() {

	}

	public AccountResponseData(Long accountId, AccountType accountType, String accountNumber, double amount) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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
