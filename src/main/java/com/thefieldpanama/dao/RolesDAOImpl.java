package com.thefieldpanama.dao;

import org.hibernate.SessionFactory;
import com.thefieldpanama.beans.Roles;

public class RolesDAOImpl implements RolesDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Roles getRole(int id) {
		return (Roles) sessionFactory.getCurrentSession().load(Roles.class, id);
	}
}
