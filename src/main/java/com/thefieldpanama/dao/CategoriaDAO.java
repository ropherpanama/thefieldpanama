package com.thefieldpanama.dao;

import java.util.List;

import com.thefieldpanama.beans.Categoria;

public interface CategoriaDAO {
	public void addCategoria(Categoria c);
	public List<Categoria> listCategorias();
	public void removeCategoria(Integer id);
	public void updateCategoria(Categoria c);
	public Categoria getCategoriaById(int id);
}
