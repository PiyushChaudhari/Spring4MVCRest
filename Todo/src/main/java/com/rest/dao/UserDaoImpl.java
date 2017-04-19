package com.rest.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rest.base.presistance.AbstractHibernateDao;
import com.rest.domain.User;

@Repository
@SuppressWarnings("rawtypes")
public class UserDaoImpl extends AbstractHibernateDao implements UserDao {

	@SuppressWarnings("unchecked")
	UserDaoImpl() {
		setClazz(User.class);
	}

	@Override
	@Transactional
	public User findUserByEmailAndPassword(String email, String password) {
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.add(Restrictions.eq("password", password));
		Object object = criteria.uniqueResult();
		if (object != null) {
			return (User) object;
		} else {
			return null;
		}
	}
}
