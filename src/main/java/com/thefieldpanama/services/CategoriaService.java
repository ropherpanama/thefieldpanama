package com.thefieldpanama.services;

import java.util.List;

import com.thefieldpanama.beans.Categoria;

public interface CategoriaService {
	public void addCategoria(Categoria c);
	public List<Categoria> listCategorias();
	public void removeCategoria(Integer id);
	public void updateCategoria(Categoria c);
	public Categoria getCategoriaById(int id);
}
