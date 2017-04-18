package com.rest.controller;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.config.UrlMapping;
import com.rest.domain.User;
import com.rest.service.UserService;
import com.rest.validator.ProcessValidator;

@RestController
@RequestMapping(UrlMapping.USER_CONTROLLER)
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = UrlMapping.USER_CONTROLLER_GET, method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		return userService.get(id);
	}

	@RequestMapping(value = UrlMapping.USER_CONTROLLER_LIST, method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		return userService.list();
	}

	@RequestMapping(value = UrlMapping.USER_CONTROLLER_SAVE, method = RequestMethod.POST)
	public ResponseEntity<?> save(@Valid @RequestBody User user, Errors errors) {
		try {
			return userService.save(user);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(
					ProcessValidator.getErrorsPaire(errors),
					HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(value = UrlMapping.USER_CONTROLLER_UPDATE, method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") String id,
			@RequestBody User user) {
		return userService.update(id, user);
	}

	@RequestMapping(value = UrlMapping.USER_CONTROLLER_DELETE, method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") String id,
			@RequestBody User user) {
		return userService.delete(id, user);
	}

}
