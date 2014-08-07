package com.thefieldpanama.services;

import org.springframework.transaction.annotation.Transactional;

import com.thefieldpanama.beans.Roles;
import com.thefieldpanama.dao.RolesDAO;

@Transactional(readOnly = true)
public class RolesServiceImpl implements RolesService {
	private RolesDAO rolesDAO;

	@Override
	@Transactional(readOnly = false)
	public Roles getRole(int id) {
		return rolesDAO.getRole(id);
	}

	public RolesDAO getRolesDAO() {
		return rolesDAO;
	}

	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}
}
