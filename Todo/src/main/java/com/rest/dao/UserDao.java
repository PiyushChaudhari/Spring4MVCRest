package com.rest.dao;

import java.io.Serializable;

import com.rest.base.presistance.HibernateDao;

public interface UserDao<T extends Serializable> extends HibernateDao<T> {

}
