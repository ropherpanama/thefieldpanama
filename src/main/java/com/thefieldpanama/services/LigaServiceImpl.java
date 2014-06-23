package com.thefieldpanama.services;

/**
 * Author: Rosendo Peña Hernandez
 * ropherpanama@gmail.com
 * Date: 23/06/2014
 */

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.thefieldpanama.beans.Liga;
import com.thefieldpanama.dao.LigaDAO;

@Transactional(readOnly = true)
public class LigaServiceImpl implements LigaService {

	private LigaDAO ligaDAO;

	public LigaDAO getLigaDAO() {
		return ligaDAO;
	}

	public void setLigaDAO(LigaDAO ligaDAO) {
		this.ligaDAO = ligaDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public void addLiga(Liga l) {
		ligaDAO.addLiga(l);
	}

	@Override
	@Transactional(readOnly = false)
	public List<Liga> listLigas() {
		return ligaDAO.listLigas();
	}

	@Override
	@Transactional(readOnly = false)
	public void removeLiga(Integer id) {
		ligaDAO.removeLiga(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void updateLiga(Liga l) {
		ligaDAO.updateLiga(l);
	}

	@Override
	@Transactional(readOnly = false)
	public Liga getLigaById(int id) {
		return ligaDAO.getLigaById(id);
	}
}
