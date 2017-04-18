package com.rest.dao;

import org.springframework.stereotype.Repository;

import com.rest.base.presistance.AbstractHibernateDao;
import com.rest.domain.User;

@Repository
@SuppressWarnings("rawtypes")
public class UserDaoImpl extends AbstractHibernateDao implements UserDao {

	@SuppressWarnings("unchecked")
	UserDaoImpl() {
		setClazz(User.class);
	}
}
