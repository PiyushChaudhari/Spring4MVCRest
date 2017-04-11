package com.rest.config;

public class UrlMapping {

	private UrlMapping() {
	}

	public static final String TODO_CONTROLLER = "/todo";
	public static final String TODO_CONTROLLER_LIST = "/list";
	public static final String TODO_CONTROLLER_GET = "/get/{id}";
	public static final String TODO_CONTROLLER_SAVE = "/save";
	public static final String TODO_CONTROLLER_UPDATE = "/update/{id}";
	public static final String TODO_CONTROLLER_DELETE = "/delete/{id}";
}
