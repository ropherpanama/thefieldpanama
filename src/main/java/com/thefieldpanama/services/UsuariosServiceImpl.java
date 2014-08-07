package com.thefieldpanama.services;

import org.springframework.transaction.annotation.Transactional;
import com.thefieldpanama.beans.Usuario;
import com.thefieldpanama.dao.UsuariosDAO;

@Transactional(readOnly = true)
public class UsuariosServiceImpl implements UsuariosService{

	private UsuariosDAO usuariosDAO;

	public UsuariosDAO getUsuariosDAO() {
		return usuariosDAO;
	}

	public void setUsuariosDAO(UsuariosDAO usuariosDAO) {
		this.usuariosDAO = usuariosDAO;
	}

	@Override
	@Transactional(readOnly = false)
	public Usuario getUser(String login) {
		return usuariosDAO.getUser(login);
	}
}
