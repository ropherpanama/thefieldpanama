package com.thefieldpanama.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.thefieldpanama.beans.Periodo;
import com.thefieldpanama.beans.Scores;
import com.thefieldpanama.dao.PeriodoDAO;

@Transactional(readOnly = true)
public class PeriodoServiceImpl implements PeriodoService {
	private PeriodoDAO periodoDAO;

	public PeriodoDAO getPeriodoDAO() {
		return periodoDAO;
	}

	public void setPeriodoDAO(PeriodoDAO periodoDAO) {
		this.periodoDAO = periodoDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addPeriodo(Periodo p) {
		periodoDAO.addPeriodo(p); 
	}

	@Override
	@Transactional(readOnly = false)
	public List<Periodo> listPeriodos() {
		return periodoDAO.listPeriodos();
	}

	@Override
	@Transactional(readOnly = false)
	public void removePeriodo(Integer id) {
		periodoDAO.removePeriodo(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updatePeriodo(Periodo p) {
		periodoDAO.updatePeriodo(p); 
	}

	@Override
	@Transactional(readOnly = false)
	public Periodo getPeriodoById(int id) {
		return periodoDAO.getPeriodoById(id);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Periodo> getPeriodosByIdPartido(int id_partido) {
		return periodoDAO.getPeriodosByIdPartido(id_partido);
	}

	@Override
	public List<Scores> getTodayScores() {
		return periodoDAO.getTodayScores();
	}
}
