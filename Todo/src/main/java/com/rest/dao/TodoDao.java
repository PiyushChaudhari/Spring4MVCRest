package com.rest.dao;

import java.io.Serializable;

import com.rest.base.presistance.HibernateDao;

public interface TodoDao<T extends Serializable> extends HibernateDao<T>{

}
