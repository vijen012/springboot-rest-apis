package com.restful.account.data;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {
	CURRENT("CURRENT"), SAVING("SAVING"), DEPOSIT("DEPOSIT");

	private final String accountType;

	private AccountType(String accountType) {
		this.accountType = accountType;
	}

	@JsonValue
	public String getAccountType() {
		return this.accountType;
	}
}
