package org.khmeracademy.rest.services.impl;


import java.util.ArrayList;

import org.khmeracademy.rest.entities.Roles;
import org.khmeracademy.rest.entities.Users;
import org.khmeracademy.rest.repositories.UserRepository;
import org.khmeracademy.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ArrayList<Users> getAllUsers() {
		return userRepository.getAllUsers();
	}

	@Override
	public boolean insertUser(Users user) {
		return userRepository.insertUser(user);
	}

	@Override
	public boolean updateUser(Users user) {
		return userRepository.updateUser(user);
	}

	@Override
	public boolean deleteUser(int id) {
		return userRepository.deleteUser(id);
	}

	@Override
	public Users findUserById(int id) {
		return userRepository.findUserById(id);
	}


	@Override
	public ArrayList<Roles> findRolesByRoleId(int role_id) {
		
		return userRepository.findRolesByRoleId(role_id);
	}

	@Override
	public Users findUserByUsername(String username) {
		
		return userRepository.findUserByUsername(username);
	}

	@Override
	public boolean signUpUser(Users user) {
		return userRepository.signUpUser(user);
	}


}
