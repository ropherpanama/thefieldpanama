package com.thefieldpanama.dao;

import java.util.List;

import com.thefieldpanama.beans.Partido;

public interface PartidoDAO {
	public void addPartido(Partido p);
	public List<Partido> listPartidos();
	public void removePartido(Integer id);
	public void updatePartido(Partido p);
	public Partido getPartidoById(int id);
	public List<Partido> getPartidosByCategoryAndDate(int cat_id, java.util.Date f);
	public List<Partido> getPartidosToday();
}
