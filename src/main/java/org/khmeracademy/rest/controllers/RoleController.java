package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Roles;
import org.khmeracademy.rest.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/get-role", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllRoles(){
		Map<String, Object> map= new Hashtable<String, Object>();
		try{
			ArrayList<Roles> role= roleService.getAllRoles();
			if(!role.isEmpty()){
				map.put("DATA", role);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "Role found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "Role not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/insert-role", method= RequestMethod.POST)
	public  ResponseEntity<Map<String, Object>> insertRole(@RequestBody Roles role){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(roleService.insertRole(role)){
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
	@RequestMapping(method= RequestMethod.PUT)
	public  ResponseEntity<Map<String, Object>> updateRole(@RequestBody Roles role){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(roleService.updateRole(role)){
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
	@RequestMapping(value="/{role-id}", method= RequestMethod.DELETE)
	public  ResponseEntity<Map<String, Object>> deleteRole(@PathVariable("role-id") int id){
		Map<String, Object> map= new HashMap<String, Object>();
		try{
			if(roleService.deleteRole(id)){
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
	@RequestMapping(value="/{role-id}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getRoleById(@PathVariable("role-id") int id){
		Map<String, Object> map= new Hashtable<String, Object>();
		try{
			ArrayList<Roles> role= roleService.getRoleById(id);
			if(!role.isEmpty()){
				map.put("DATA", role);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "role found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "role not found");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
