package com.thefieldpanama.services;

import java.util.List;

import com.thefieldpanama.beans.Periodo;
import com.thefieldpanama.beans.Scores;

public interface PeriodoService {
	public void addPeriodo(Periodo p);
	public List<Periodo> listPeriodos();
	public void removePeriodo(Integer id);
	public void updatePeriodo(Periodo p);
	public Periodo getPeriodoById(int id);
	public List<Periodo> getPeriodosByIdPartido(int id_partido);
	/**
	 * Se agrega este metodo para procesar el tab de scores
	 * ya que a la app se le hace muy pesado organizar los 
	 * partidos y sus scores para el dia actual
	 * @return scores del dia
	 */
	public List<Scores> getTodayScores();
}
