package com.thefieldpanama.dao;

import java.util.List;

import com.thefieldpanama.beans.Equipo;
import com.thefieldpanama.beans.Grupos;
import com.thefieldpanama.beans.ResumenEquipo;

public interface GruposDAO {

	public void add(Grupos l);

	public List<Grupos> list();

	public void remove(Integer id);

	public void update(Grupos l);

	public Grupos getById(int id);
	
	public List<List<ResumenEquipo>> getResumenResultados(List<Equipo> e);
}
