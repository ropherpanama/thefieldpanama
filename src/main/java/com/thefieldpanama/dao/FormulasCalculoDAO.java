package com.thefieldpanama.dao;

import java.util.List;

import com.thefieldpanama.beans.FormulasCalculo;

public interface FormulasCalculoDAO {
	
	public void add(FormulasCalculo l);

	public List<FormulasCalculo> list();

	public void remove(Integer id);

	public void update(FormulasCalculo l);

	public FormulasCalculo getById(int id);
}
