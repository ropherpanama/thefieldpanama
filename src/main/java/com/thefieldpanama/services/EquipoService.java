package com.thefieldpanama.services;

import java.util.List;

import com.thefieldpanama.beans.Equipo;

public interface EquipoService {
	public void addEquipo(Equipo e);

	public List<Equipo> listEquipos();

	public void removeEquipo(Integer id);

	public void updateEquipo(Equipo e);

	public Equipo getEquipoById(int id);
	
	public List<Equipo> getEquiposSinGrupo();
	
	public List<Equipo> getEquipoByGrupo(int idGrupo);
}
