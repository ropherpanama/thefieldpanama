package com.thefieldpanama.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.thefieldpanama.beans.FormulasCalculo;

public class FormulasCalculoDAOImpl implements FormulasCalculoDAO{

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void add(FormulasCalculo l) {
		sessionFactory.getCurrentSession().save(l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FormulasCalculo> list() {
		return sessionFactory.getCurrentSession().createQuery("from FormulasCalculo order by id_formula_calculo")
				.list();
	}

	@Override
	public void remove(Integer id) {
		FormulasCalculo l = (FormulasCalculo) sessionFactory.getCurrentSession().load(FormulasCalculo.class, id);
		if (null != l) {
			sessionFactory.getCurrentSession().delete(l);
		}
	}

	@Override
	public void update(FormulasCalculo l) {
		sessionFactory.getCurrentSession().update(l);
	}

	@Override
	public FormulasCalculo getById(int id) {
		@SuppressWarnings("unchecked")
		List<FormulasCalculo> list = getSessionFactory().getCurrentSession()
				.createQuery("from FormulasCalculo  where id_formula_calculo = ?")
				.setParameter(0, id).list();
		return (FormulasCalculo) list.get(0);
	}

}
