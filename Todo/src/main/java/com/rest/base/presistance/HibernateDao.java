package com.rest.base.presistance;

import java.io.Serializable;
import java.util.List;

public interface HibernateDao<T extends Serializable> {

	T load(final String id);
	
	T get(final String id);

    void create(final T entity);

    T save(final T entity);

    void delete(final T entity);

    void deleteById(final String entityId);
    
    List<T> findAll();
}
