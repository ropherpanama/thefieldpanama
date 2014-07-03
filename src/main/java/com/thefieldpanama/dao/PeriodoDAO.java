package com.thefieldpanama.dao;

import java.util.List;

import com.thefieldpanama.beans.Periodo;

public interface PeriodoDAO {
	public void addPeriodo(Periodo p);
	public List<Periodo> listPeriodos();
	public void removePeriodo(Integer id);
	public void updatePeriodo(Periodo p);
	public Periodo getPeriodoById(int id);
	public List<Periodo> getPeriodosByIdPartido(int id_partido);
}
