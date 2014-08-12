package com.thefieldpanama.dao;

import java.util.List;
import org.hibernate.SessionFactory;
import com.thefieldpanama.beans.Categoria;

public class CategoriaDAOImpl implements CategoriaDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addCategoria(Categoria c) {
		sessionFactory.getCurrentSession().save(c);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Categoria> listCategorias() {
		return sessionFactory.getCurrentSession().createQuery("from Categoria as c order by c.nom_categoria").list();
	}

	@Override
	public void removeCategoria(Integer id) {
		Categoria l = (Categoria) sessionFactory.getCurrentSession().load(
				Categoria.class, id);
		if (null != l) {
			sessionFactory.getCurrentSession().delete(l);
		}
	}

	@Override
	public void updateCategoria(Categoria c) {
		sessionFactory.getCurrentSession().update(c);
	}

	@Override
	public Categoria getCategoriaById(int id) {
		@SuppressWarnings("unchecked")
		List<Categoria> list = getSessionFactory().getCurrentSession()
				.createQuery("from Categoria  where id_categoria = ?")
				.setParameter(0, id).list();
		return (Categoria) list.get(0);
	}
}
