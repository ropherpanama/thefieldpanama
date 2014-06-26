package com.thefieldpanama.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.thefieldpanama.beans.Equipo;

public class EquipoDAOImpl implements EquipoDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addEquipo(Equipo e) {
		sessionFactory.getCurrentSession().save(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Equipo> listEquipos() {
		return sessionFactory.getCurrentSession().createQuery("from Equipo")
				.list();
	}

	@Override
	public void removeEquipo(Integer id) {
		Equipo e = (Equipo) sessionFactory.getCurrentSession().load(
				Equipo.class, id);
		if (null != e) {
			sessionFactory.getCurrentSession().delete(e);
		}
	}

	@Override
	public void updateEquipo(Equipo e) {
		sessionFactory.getCurrentSession().update(e);
	}

	@Override
	public Equipo getEquipoById(int id) {
		@SuppressWarnings("unchecked")
		List<Equipo> list = getSessionFactory().getCurrentSession()
				.createQuery("from Equipo  where id_equipo = ?")
				.setParameter(0, id).list();
		return (Equipo) list.get(0);
	}

}