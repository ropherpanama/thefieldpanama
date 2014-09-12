package com.thefieldpanama.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.thefieldpanama.beans.Grupos;

public class GruposDAOImpl implements GruposDAO{
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void add(Grupos l) {
		sessionFactory.getCurrentSession().save(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grupos> list() {
		return sessionFactory.getCurrentSession().createQuery("from Grupos order by id_grupo")
				.list();
	}

	@Override
	public void remove(Integer id) {
		Grupos l = (Grupos) sessionFactory.getCurrentSession().load(Grupos.class, id);
		if (null != l) {
			sessionFactory.getCurrentSession().delete(l);
		}
	}

	@Override
	public void update(Grupos l) {
		sessionFactory.getCurrentSession().update(l);
	}

	@Override
	public Grupos getById(int id) {
		@SuppressWarnings("unchecked")
		List<Grupos> list = getSessionFactory().getCurrentSession()
				.createQuery("from Grupos  where id_grupo = ?")
				.setParameter(0, id).list();
		return (Grupos) list.get(0);
	}
}
