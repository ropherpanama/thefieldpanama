package com.thefieldpanama.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thefieldpanama.beans.FormulasCalculo;
import com.thefieldpanama.dao.FormulasCalculoDAO;

@Transactional(readOnly = true)
public class FormulasCalculoServiceImpl implements FormulasCalculoService{

	private FormulasCalculoDAO formulasDAO;
	
	public FormulasCalculoDAO getFormulasDAO() {
		return formulasDAO;
	}

	public void setFormulasDAO(FormulasCalculoDAO formulasDAO) {
		this.formulasDAO = formulasDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(FormulasCalculo l) {
		formulasDAO.add(l);
	}

	@Override
	@Transactional(readOnly = false)
	public List<FormulasCalculo> list() {
		return formulasDAO.list();
	}

	@Override
	@Transactional(readOnly = false)
	public void remove(Integer id) {
		formulasDAO.remove(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(FormulasCalculo l) {
		formulasDAO.update(l);
	}

	@Override
	@Transactional(readOnly = false)
	public FormulasCalculo getById(int id) {
		return formulasDAO.getById(id);
	}
}
