package com.restful.account.data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AccountRequestData {

	@NotNull(message = "userId must not be null")
	private Long userId;
	private AccountType accountType;

	@NotBlank(message = "accountNumber must not be blank")
	@NotEmpty(message = "accountNumber must not be empty")
	private String accountNumber;

	@NotNull(message = "amount must not be null")
	private double amount;

	public AccountRequestData() {

	}

	public AccountRequestData(Long userId, AccountType accountType, String accountNumber, double amount) {
		super();
		this.userId = userId;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.amount = amount;
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
		return "AccountRequestData [accountType=" + accountType + ", accountNumber=" + accountNumber + ", amount="
				+ amount + "]";
	}

}
