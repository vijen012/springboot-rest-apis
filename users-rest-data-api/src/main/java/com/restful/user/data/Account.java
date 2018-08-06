package com.restful.user.data;

public class Account {

	private Long accountId;
	private String accountType;
	private String accountNumber;
	private double amount;

	public Account() {

	}

	public Account(Long accountId, String accountType, String accountNumber, double amount) {
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
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
		return "Account [accountId=" + accountId + ", accountType=" + accountType + ", accountNumber=" + accountNumber
				+ ", amount=" + amount + "]";
	}

}
