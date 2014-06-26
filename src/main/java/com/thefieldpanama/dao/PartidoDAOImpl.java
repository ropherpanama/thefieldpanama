package com.thefieldpanama.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.thefieldpanama.beans.Partido;

public class PartidoDAOImpl implements PartidoDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPartido(Partido p) {
		sessionFactory.getCurrentSession().save(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> listPartidos() {
		return sessionFactory.getCurrentSession().createQuery("from Partido")
				.list();
	}

	@Override
	public void removePartido(Integer id) {
		Partido p = (Partido) sessionFactory.getCurrentSession().load(
				Partido.class, id);
		if (null != p) {
			sessionFactory.getCurrentSession().delete(p);
		}
	}

	@Override
	public void updatePartido(Partido p) {
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public Partido getPartidoById(int id) {
		@SuppressWarnings("unchecked")
		List<Partido> list = getSessionFactory().getCurrentSession()
				.createQuery("from Partido  where id_partido = ?")
				.setParameter(0, id).list();
		return (Partido) list.get(0);
	}
}
