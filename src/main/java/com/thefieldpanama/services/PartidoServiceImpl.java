package com.thefieldpanama.services;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thefieldpanama.beans.Partido;
import com.thefieldpanama.dao.PartidoDAO;

@Transactional(readOnly = true)
public class PartidoServiceImpl implements PartidoService {
	private PartidoDAO partidoDAO;

	public PartidoDAO getPartidoDAO() {
		return partidoDAO;
	}

	public void setPartidoDAO(PartidoDAO partidoDAO) {
		this.partidoDAO = partidoDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addPartido(Partido p) {
		partidoDAO.addPartido(p);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Partido> listPartidos() {
		return partidoDAO.listPartidos();
	}

	@Override
	@Transactional(readOnly = false)
	public void removePartido(Integer id) {
		partidoDAO.removePartido(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updatePartido(Partido p) {
		partidoDAO.updatePartido(p);
	}

	@Override
	@Transactional(readOnly = false)
	public Partido getPartidoById(int id) {
		return partidoDAO.getPartidoById(id);
	}

	@Override
	public List<Partido> getPartidosByCategoryAndDate(int cat_id, Date f) {
		return partidoDAO.getPartidosByCategoryAndDate(cat_id, f);
	}
}
