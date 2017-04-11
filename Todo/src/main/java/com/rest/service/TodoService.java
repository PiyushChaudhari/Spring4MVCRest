package com.rest.service;

import org.springframework.http.ResponseEntity;

import com.rest.domain.Todo;

public interface TodoService {
	
	ResponseEntity<?> get(String todoId);
	ResponseEntity<?> list();
	ResponseEntity<?> save(Todo todo);
	ResponseEntity<?> update(String id,Todo todo);
	ResponseEntity<?> delete(String id,Todo todo);

}
