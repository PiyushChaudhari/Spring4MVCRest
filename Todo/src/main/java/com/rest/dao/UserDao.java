package com.rest.dao;

import java.io.Serializable;

import com.rest.base.presistance.HibernateDao;
import com.rest.domain.User;

public interface UserDao<T extends Serializable> extends HibernateDao<T> {

	public User findUserByEmailAndPassword(String email, String password);
}
