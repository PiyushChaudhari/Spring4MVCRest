package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.commandobject.LoginCommanObject;
import com.rest.config.UrlMapping;
import com.rest.service.AuthenticateService;

@RestController
@RequestMapping(UrlMapping.AUTHENTICATE_CONTROLLER)
public class AuthenticateController {

	@Autowired
	AuthenticateService authenticateService;

	@RequestMapping(value = UrlMapping.AUTHENTICATE_CONTROLLER_LOGIN, method = RequestMethod.POST)
	public ResponseEntity<?> login(
			@RequestBody LoginCommanObject loginCommanObject) {
		return authenticateService.login(loginCommanObject);

	}

}
