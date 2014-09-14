package com.thefieldpanama.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.Grupos;
import com.thefieldpanama.beans.ResumenEquipo;

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
		return sessionFactory.getCurrentSession().createQuery("from Grupos where id_grupo != ? order by id_grupo")
				.setParameter(0, 0) 
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

	@SuppressWarnings("unchecked")
	@Override
	public List<List<ResumenEquipo>> getResumenResultados(List<Equipo> e) {
		StringBuilder sql = new StringBuilder();
		sql.append("select r.nom_equipo as nombreEquipo, r.pos as posicion, p.pts_equipo_1 as pts1, p.pts_equipo_2 as pts2, r.id_partido as idPartido, r.id_categoria as idCategoria "); 
		sql.append("from periodos p, (select e.nom_equipo, s.id_partido, s.id_equipo_1, s.id_equipo_2, s.fecha, 1 as pos, e.id_categoria "); 
		sql.append("from partidos s, equipos e ");
		sql.append("where s.id_equipo_1 = ? ");
		sql.append("and s.ind_playoff = 0 ");
		sql.append("and s.id_equipo_1 = e.id_equipo) r ");
		sql.append("where r.id_partido = p.id_partido ");
		sql.append("union "); 
		sql.append("select r.nom_equipo as nombreEquipo, r.pos as posicion, p.pts_equipo_1 as pts1, p.pts_equipo_2 as pts2, r.id_partido as idPartido, r.id_categoria as idCategoria "); 
		sql.append("from periodos p, (select e.nom_equipo, s.id_partido, s.id_equipo_1, s.id_equipo_2, s.fecha, 2 as pos, e.id_categoria ");
		sql.append("from partidos s, equipos e ");
		sql.append("where s.id_equipo_2 = ? ");
		sql.append("and s.ind_playoff = 0 ");
		sql.append("and s.id_equipo_2 = e.id_equipo) r ");
		sql.append("where r.id_partido = p.id_partido ");
		
		List<List<ResumenEquipo>> resultados = new ArrayList<List<ResumenEquipo>>();
		
		for(Equipo equipo : e) {
			List<ResumenEquipo> list = getSessionFactory().getCurrentSession()
					.createSQLQuery(sql.toString().replace("?", String.valueOf(equipo.getId_equipo())))
					.setResultTransformer(Transformers.aliasToBean(ResumenEquipo.class) )
					.list();
			
			resultados.add(list);
		}
		
		return resultados; 
	}
}
