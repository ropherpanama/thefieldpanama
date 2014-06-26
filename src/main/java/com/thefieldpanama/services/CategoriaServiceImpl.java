package com.thefieldpanama.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thefieldpanama.beans.Categoria;
import com.thefieldpanama.dao.CategoriaDAO;

@Transactional(readOnly = true)
public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO categoriaDAO;

	public CategoriaDAO getCategoriaDAO() {
		return categoriaDAO;
	}

	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addCategoria(Categoria c) {
		categoriaDAO.addCategoria(c);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Categoria> listCategorias() {
		return categoriaDAO.listCategorias();
	}

	@Override
	@Transactional(readOnly = false)
	public void removeCategoria(Integer id) {
		categoriaDAO.removeCategoria(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateCategoria(Categoria c) {
		categoriaDAO.updateCategoria(c);
	}

	@Override
	@Transactional(readOnly = false)
	public Categoria getCategoriaById(int id) {
		return categoriaDAO.getCategoriaById(id);
	}
}
