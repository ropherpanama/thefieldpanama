package com.thefieldpanama.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import com.thefieldpanama.beans.Usuario;

public class UsuariosDAOImpl implements UsuariosDAO{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Usuario getUser(String login) {
        @SuppressWarnings("unchecked")
		List<Usuario> list = getSessionFactory().getCurrentSession()
				.createQuery("from Usuario u where u.login = ?")
				.setParameter(0, login).list();

        if (list.size() > 0)
            return list.get(0);
        else
            return null;   
    }
}
