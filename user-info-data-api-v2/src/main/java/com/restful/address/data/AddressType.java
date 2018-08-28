package com.restful.address.data;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AddressType {
	HOME("HOME"), WORK("WORK"), OFFICE("OFFICE");

	private final String addressType;

	private AddressType(String addressType) {
		this.addressType = addressType;
	}

	@JsonValue
	public String getAddressType() {
		return this.addressType;
	}

}
