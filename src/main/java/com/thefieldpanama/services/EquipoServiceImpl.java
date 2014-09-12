package com.thefieldpanama.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.dao.EquipoDAO;

@Transactional(readOnly = true)
public class EquipoServiceImpl implements EquipoService {

	EquipoDAO equipoDAO;

	public EquipoDAO getEquipoDAO() {
		return equipoDAO;
	}

	public void setEquipoDAO(EquipoDAO equipoDAO) {
		this.equipoDAO = equipoDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addEquipo(Equipo e) {
		equipoDAO.addEquipo(e);

	}

	@Override
	@Transactional(readOnly = false)
	public List<Equipo> listEquipos() {
		return equipoDAO.listEquipos();
	}

	@Override
	@Transactional(readOnly = false)
	public void removeEquipo(Integer id) {
		equipoDAO.removeEquipo(id);

	}

	@Override
	@Transactional(readOnly = false)
	public void updateEquipo(Equipo e) {
		equipoDAO.updateEquipo(e);

	}

	@Override
	@Transactional(readOnly = false)
	public Equipo getEquipoById(int id) {
		return equipoDAO.getEquipoById(id);
	}

	@Override
	public List<Equipo> getEquiposSinGrupo() {
		return equipoDAO.getEquiposSinGrupo();
	}
}
