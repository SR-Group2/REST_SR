package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Roles;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
	public ArrayList<Roles> getAllRoles();
	public boolean insertRole(Roles role);
	public boolean  updateRole(Roles role);
	public boolean deleteRole(int id);
	public ArrayList<Roles> getRoleById(int id);
}
