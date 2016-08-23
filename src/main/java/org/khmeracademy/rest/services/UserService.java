package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.khmeracademy.rest.entities.Roles;
import org.khmeracademy.rest.entities.Users;
import org.khmeracademy.rest.entities.Users.Users2;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	public ArrayList<Users> getAllUsers();
	
	
	
	public boolean insertUser(Users user);
	
	public boolean signUpUser(Users2 user2);
	
	public boolean  updateUser(Users user);
	
	public boolean deleteUser(int id);
	
	public Users findUserById(int id);
	
	public ArrayList<Roles> findRolesByRoleId( int role_id);
	
	public Users findUserByUsername(String username);
	
	public boolean addUser(Users2 user);
	
	public ArrayList<Users> getUserOwner();
	public int countUser(int role_id);
}
