package com.thefieldpanama.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.thefieldpanama.beans.Periodo;
import com.thefieldpanama.beans.Scores;
import com.thefieldpanama.utilities.Utilities;

public class PeriodoDAOImpl implements PeriodoDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addPeriodo(Periodo p) {
		sessionFactory.getCurrentSession().save(p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Periodo> listPeriodos() {
		return sessionFactory.getCurrentSession().createQuery("from Periodo")
				.list();
	}

	@Override
	public void removePeriodo(Integer id) {
		Periodo p = (Periodo) sessionFactory.getCurrentSession().load(
				Periodo.class, id);
		if (null != p) {
			sessionFactory.getCurrentSession().delete(p);
		}
	}

	@Override
	public void updatePeriodo(Periodo p) {
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public Periodo getPeriodoById(int id) {
		@SuppressWarnings("unchecked")
		List<Periodo> list = getSessionFactory().getCurrentSession()
				.createQuery("from Periodo  where id_periodo = ?")
				.setParameter(0, id).list();
		return (Periodo) list.get(0);
	}

	public List<Periodo> getPeriodosByIdPartido(int id_partido) {
		@SuppressWarnings("unchecked")
		List<Periodo> list = getSessionFactory().getCurrentSession()
				.createQuery("from Periodo  where id_partido = ?")
				.setParameter(0, id_partido).list();
		return list;
	}

	@Override
	public List<Scores> getTodayScores() {
		@SuppressWarnings("unchecked")
		List<Scores> list = getSessionFactory().getCurrentSession()
		.createQuery("select new com.thefieldpanama.beans.Scores(p.equipo1.nom_equipo, p.equipo2.nom_equipo, sum(periodos.pts_equipo_1) as pts1, sum(periodos.pts_equipo_2) as pts2, ' ' as status, p.hora) "
				+ "from Partido p left join p.periodosPartido periodos "
				+ "where p.fecha = ? "
				+ "group by p.equipo1.nom_equipo, p.equipo2.nom_equipo, p.hora order by p.hora")
		.setParameter(0, Utilities.fechahoy())
		.list();
		return list;
	}
}
