package com.rest.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.dao.UserDao;
import com.rest.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao<User> userDao;

	@Override
	public ResponseEntity<?> get(String userId) {
		try {
			User user = userDao.load(userId);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> list() {
		return new ResponseEntity<>(userDao.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> save(User user) {
		return new ResponseEntity<>(userDao.save(user), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> update(String id, User user) {
		try {
			User updatableUser = userDao.load(id);
			if (updatableUser.getVersion() > user.getVersion()) {
				return new ResponseEntity<>("Record already update",
						HttpStatus.BAD_REQUEST);
			} else {
				updatableUser.setVersion(user.getVersion());
				updatableUser.setFirstName(user.getFirstName());
				updatableUser.setLastName(user.getLastName());
				updatableUser.setEmail(user.getEmail());
				updatableUser.setTodoList(user.getTodoList());
				return new ResponseEntity<>(userDao.save(user), HttpStatus.OK);
			}
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> delete(String id, User user) {
		try {
			User deleteableUser = userDao.load(id);
			userDao.delete(deleteableUser);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (ObjectNotFoundException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
