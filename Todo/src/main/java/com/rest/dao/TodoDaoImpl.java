package com.rest.dao;

import org.springframework.stereotype.Repository;

import com.rest.base.presistance.AbstractHibernateDao;
import com.rest.domain.Todo;

@Repository
@SuppressWarnings("rawtypes")
public class TodoDaoImpl extends AbstractHibernateDao implements TodoDao {
	
	@SuppressWarnings("unchecked")
	TodoDaoImpl(){
		setClazz(Todo.class);
	}

}
