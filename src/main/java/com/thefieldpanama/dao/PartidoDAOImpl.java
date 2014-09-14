package com.thefieldpanama.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import com.thefieldpanama.beans.Partido;
import com.thefieldpanama.utilities.Utilities;

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
		return sessionFactory.getCurrentSession().createQuery("from Partido order by fecha desc")
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

	@Override
	public List<Partido> getPartidosByCategoryAndDate(int cat_id, Date f) {
		@SuppressWarnings("unchecked")
		List<Partido> list = getSessionFactory().getCurrentSession()
				.createQuery("from Partido as p where p.fecha = ? and p.equipo1.categoria.id_categoria = ?")
				.setParameter(0, f)
				.setParameter(1, cat_id).list(); 
		return list;
	}

	@Override
	public List<Partido> getPartidosToday() {
		@SuppressWarnings("unchecked")
		List<Partido> list = getSessionFactory().getCurrentSession()
				.createQuery("from Partido as p where p.fecha = ? ")
				.setParameter(0, Utilities.fechahoy(Utilities.YYYYMMDDGUION)) 
				.list(); 
		return list;
	}
}
