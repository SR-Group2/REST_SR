package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Users;
import org.khmeracademy.rest.filters.UserFilter;
import org.khmeracademy.rest.form.UserLogin;
import org.khmeracademy.rest.services.UserService;
import org.khmeracademy.rest.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;


@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	//================== get user owner ==================
	@RequestMapping(value="/get-user-owner", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getUserOwner(){
		Map<String, Object> map= new Hashtable<String, Object>();
		
		try{
			ArrayList<Users> user= userService.getUserOwner();
			if(!user.isEmpty()){
				map.put("DATA", user);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "User found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "User not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/get-user", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllUsers(){
		Map<String, Object> map= new Hashtable<String, Object>();
		
		try{
			ArrayList<Users> user= userService.getAllUsers();
			if(!user.isEmpty()){
				map.put("DATA", user);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "User found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "User not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	//=========== Login Username ================
	@RequestMapping(value="find-user-by-username", method = RequestMethod.POST , headers = "Accept=application/json")
	public ResponseEntity<Map<String , Object>> findUserByUsername(@RequestBody UserLogin login){
		Map< String , Object> map = new HashMap<String , Object>();
		try{
			Users user = userService.findUserByUsername(login.getUsername());
			System.out.println(login.getUsername());
			if(user != null){
				map.put("STATUS",true);
				map.put("MESSAGE","Success");
				map.put("DATA", user);
				System.out.println(map);
			}else{
				map.put("STATUS",false);
				map.put("MESSAGE","Unsuccess");
			}
				
			
		}catch(Exception e){
			map.put("STATUS",false);
			map.put("MESSAGE", "Something is broken. Please contact to developers team!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/insert-user", method= RequestMethod.POST)
	public  ResponseEntity<Map<String, Object>> insertUser(@RequestBody Users user){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(userService.insertUser(user)){
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "SUCCESSFULLY INSERTED");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "404");
				map.put("MESSAGE", "Insert failed");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	/*================= Sing Up User===================*/
	
	/*@RequestMapping(value="/sign-up", method= RequestMethod.POST)
	public  ResponseEntity<Map<String, Object>> signUptUser(@RequestBody Users user){
		System.out.println("API");
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(userService.signUpUser(user)){
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "SUCCESSFULLY SING UP");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "404");
				map.put("MESSAGE", "Failed to Sing Up");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}*/
	
	@RequestMapping(method= RequestMethod.PUT)
	public  ResponseEntity<Map<String, Object>> updateUser(@RequestBody Users user){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(userService.updateUser(user)){
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "SUCCESSFULLY UPDATED");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "404");
				map.put("MESSAGE", "Update failed");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{user-id}", method= RequestMethod.DELETE)
	public  ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("user-id") int id){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(userService.deleteUser(id)){
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "SUCCESSFULLY DELETED");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "404");
				map.put("MESSAGE", "Delete failed");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
	}
	@RequestMapping(value="/{user-id}", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable("user-id") int id){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			Users user= userService.findUserById(id);
			if(!user.equals(null)){
				map.put("DATA", user);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "User fond");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "User not found");
			}
			
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "500");
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
