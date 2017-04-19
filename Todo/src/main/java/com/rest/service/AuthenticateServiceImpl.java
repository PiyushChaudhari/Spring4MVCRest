package com.rest.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest.commandobject.LoginCommanObject;
import com.rest.constants.TodoConstant;
import com.rest.dao.UserDao;
import com.rest.domain.User;
import com.rest.security.JwtSecurity;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	private static final Logger LOGGER = Logger
			.getLogger(AuthenticateServiceImpl.class.getName());

	@Autowired
	UserDao<User> userDao;
	@Autowired
	JwtSecurity jwtSecurity;

	@Override
	public ResponseEntity<?> login(LoginCommanObject loginCommanObject) {
		try {
			User user = userDao.findUserByEmailAndPassword(
					loginCommanObject.getEmail(),
					loginCommanObject.getPassword());

			if (user != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.add(TodoConstant.JWT_SECURITY_AUTHENTICATE_KEY,
						jwtSecurity.createAuthenticateToken(user.getEmail()));
				return new ResponseEntity<>(user, headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Invalid email or password.",
						HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Error",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
