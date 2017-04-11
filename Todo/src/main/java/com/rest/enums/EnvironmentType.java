package com.rest.enums;

public enum EnvironmentType {

	DEVELOPMENT("dev"), PRODUCTION("prod");
	private String value;

	EnvironmentType(String value) {
		this.value = value;
	}

	static EnvironmentType[] list() {
		return EnvironmentType.values();
	}

	@Override
	public String toString() {
		return value;
	}
}
