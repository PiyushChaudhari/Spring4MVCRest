package com.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.config.UrlMapping;
import com.rest.domain.Todo;
import com.rest.service.TodoService;

@RestController
@RequestMapping(UrlMapping.TODO_CONTROLLER)
public class TodoController {

	@Autowired
	TodoService todoService;

	@RequestMapping(value = UrlMapping.TODO_CONTROLLER_GET, method = RequestMethod.GET)
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		return todoService.get(id);
	}

	@RequestMapping(value = UrlMapping.TODO_CONTROLLER_LIST, method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		return todoService.list();
	}

	@RequestMapping(value = UrlMapping.TODO_CONTROLLER_SAVE, method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Todo todo) {
		return todoService.save(todo);
	}

	@RequestMapping(value = UrlMapping.TODO_CONTROLLER_UPDATE, method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable("id") String id,
			@RequestBody Todo todo) {
		return todoService.update(id, todo);
	}

	@RequestMapping(value = UrlMapping.TODO_CONTROLLER_DELETE, method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable("id") String id,
			@RequestBody Todo todo) {
		return todoService.delete(id, todo);
	}
}
