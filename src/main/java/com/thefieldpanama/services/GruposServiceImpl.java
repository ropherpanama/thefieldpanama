package com.thefieldpanama.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.Grupos;
import com.thefieldpanama.beans.ResumenEquipo;
import com.thefieldpanama.dao.GruposDAO;

@Transactional(readOnly = true)
public class GruposServiceImpl implements GruposService{

	private GruposDAO grupoDAO;
	
	public GruposDAO getGrupoDAO() {
		return grupoDAO;
	}

	public void setGrupoDAO(GruposDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void add(Grupos l) {
		grupoDAO.add(l);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Grupos> list() {
		return grupoDAO.list();
	}

	@Override
	@Transactional(readOnly = false)
	public void remove(Integer id) {
		grupoDAO.remove(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void update(Grupos l) {
		grupoDAO.update(l);
	}

	@Override
	@Transactional(readOnly = false)
	public Grupos getById(int id) {
		return grupoDAO.getById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<List<ResumenEquipo>> getResumenResultados(List<Equipo> e) {
		return grupoDAO.getResumenResultados(e); 
		
	}
}
