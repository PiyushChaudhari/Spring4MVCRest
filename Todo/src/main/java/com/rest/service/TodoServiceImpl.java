package com.rest.service;

import javax.transaction.Transactional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rest.dao.TodoDao;
import com.rest.domain.Todo;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoDao<Todo> todoDao;

	@Override
	public ResponseEntity<?> get(String id) {
		try {
			Todo todo = todoDao.load(id);
			return new ResponseEntity<>(todo, HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> list() {
		return new ResponseEntity<>(todoDao.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> save(@RequestBody Todo todo) {
		return new ResponseEntity<>(todoDao.save(todo), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(String id, Todo todo) {
		try {
			Todo updatableTodo = todoDao.load(id);
			if (updatableTodo.getVersion() > todo.getVersion()) {
				return new ResponseEntity<>("Record already update",
						HttpStatus.BAD_REQUEST);
			} else {
				updatableTodo.setVersion(todo.getVersion());
				updatableTodo.setName(todo.getName());
				return new ResponseEntity<>(todoDao.save(updatableTodo),
						HttpStatus.OK);
			}
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> delete(String id, Todo todo) {
		try {
			Todo deletableTodo = todoDao.load(id);
			todoDao.delete(deletableTodo);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
