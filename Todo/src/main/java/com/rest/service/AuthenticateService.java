package com.rest.service;

import org.springframework.http.ResponseEntity;

import com.rest.commandobject.LoginCommanObject;

public interface AuthenticateService {

	ResponseEntity<?> login(LoginCommanObject loginCommanObject);
}
