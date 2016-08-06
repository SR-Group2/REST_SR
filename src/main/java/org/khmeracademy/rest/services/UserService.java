package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Roles;
import org.khmeracademy.rest.entities.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public ArrayList<Users> getAllUsers();
	
	
	
	public boolean insertUser(Users user);
	
	public boolean  updateUser(Users user);
	
	public boolean deleteUser(int id);
	
	public ArrayList<Users> findUserById(int id);
	
	public ArrayList<Roles> findRolesByRoleId( int role_id);
	
	public Users findUserByUsername(String username);
}
