package com.thefieldpanama.dao;

/**
 * Author: Rosendo Peña Hernandez
 * ropherpanama@gmail.com
 * Date: 23/06/2014
 */

import java.util.List;

import org.hibernate.SessionFactory;

import com.thefieldpanama.beans.Liga;

public class LigaDAOImpl implements LigaDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addLiga(Liga l) {
		sessionFactory.getCurrentSession().save(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Liga> listLigas() {
		return sessionFactory.getCurrentSession().createQuery("from Liga order by nom_liga")
				.list();
	}

	@Override
	public void removeLiga(Integer id) {
		Liga l = (Liga) sessionFactory.getCurrentSession().load(Liga.class, id);
		if (null != l) {
			sessionFactory.getCurrentSession().delete(l);
		}
	}

	@Override
	public void updateLiga(Liga l) {
		sessionFactory.getCurrentSession().update(l);
	}

	@Override
	public Liga getLigaById(int id) {
		@SuppressWarnings("unchecked")
		List<Liga> list = getSessionFactory().getCurrentSession()
				.createQuery("from Liga  where id_liga = ?")
				.setParameter(0, id).list();
		return (Liga) list.get(0);
	}
}
