package com.rest.service;

import org.springframework.http.ResponseEntity;

import com.rest.domain.User;

public interface UserService {

	ResponseEntity<?> get(String userId);

	ResponseEntity<?> list();

	ResponseEntity<?> save(User user);

	ResponseEntity<?> update(String id, User user);

	ResponseEntity<?> delete(String id, User user);
}
