package com.rest.base.presistance;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unchecked")
public abstract class AbstractHibernateDao<T extends Serializable> implements
		HibernateDao<T> {

	private Class<T> clazz;

	@Autowired
	private SessionFactory sessionFactory;

	protected final void setClazz(final Class<T> clazzToSet) {
		clazz = clazzToSet;
	}

	@Override
	public T load(final String id) {
		return (T) getCurrentSession().load(clazz, id);
	}

	@Override
	public T get(final String id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	@Override
	public void create(T entity) {
		getCurrentSession().saveOrUpdate(entity);

	}

	@Override
	public T save(T entity) {
		return (T) getCurrentSession().merge(entity);
	}

	@Override
	public void delete(T entity) {
		getCurrentSession().delete(entity);
	}

	@Override
	public void deleteById(String entityId) {
		final T entity = get(entityId);
		delete(entity);
	}

	@Override
	public List<T> findAll() {
		return getCurrentSession().createQuery("from " + clazz.getName())
				.list();
	}

	public final Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

}
