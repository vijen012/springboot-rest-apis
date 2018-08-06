package com.restful.account.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long accountId;
	@JsonIgnore
	private Long userId;
	private String accountType;
	private String accountNumber;
	private double amount;

	public Account() {

	}

	public Account(Long accountId, Long userId, String accountType, String accountNumber, double amount) {
		super();
		this.accountId = accountId;
		this.userId = userId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
