package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.services.RestypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restype")
public class RestypeController {
	
	@Autowired
	private RestypeService restypeService;
	
	@RequestMapping(value = "/get-restype",method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllRestype(){
		Map<String , Object> map = new Hashtable<String , Object>();
		try{
			ArrayList<Restypes> restypes = restypeService.getAllRestype();
			if(!restypes.isEmpty()){
				map.put("DATA", restypes);
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "300 DATA NOT FOUND");
				map.put("MESSAGE", "DATA NOT FOUND!");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", "NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map ,HttpStatus.OK) ;
	}
	
	@RequestMapping(value = "/insert-restype", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insertRestype(@RequestBody Restypes restype){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(restypeService.insertRestype(restype)){
				map.put("MESSAGE", "Restype has been inserted.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Restype has not been inserted.");
				map.put("CODE", "201 RESTYPE NOT INSERTED!");
				map.put("STATUS", false);
			}
		}catch(Exception e){
			map.put("MESSAGE", "NOT FOUND!");
			map.put("CODE", "404 NOT FOUND");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{restype-id}", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateRestype(@RequestBody Restypes restype){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(restypeService.updateRestype(restype)){
				map.put("MESSAGE", "Restype has been Updated.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Restype has not been Updated.");
				map.put("CODE", "201 RESTYPE NOT UPDATED!");
				map.put("STATUS", false);
			}
		}catch(Exception e){
			map.put("MESSAGE", "NOT FOUND!");
			map.put("CODE", "404 NOT FOUND");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{restype-id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteRestype(@PathVariable("restype-id") int restype_id){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(restypeService.deleteRestype(restype_id)){
				map.put("MESSAGE", "Restype has been Deleted.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Restype has not been Deleted.");
				map.put("CODE", "201 RESTYPE NOT DELETED!");
				map.put("STATUS", false);
			}
		}catch(Exception e){
			map.put("MESSAGE", "NOT FOUND!");
			map.put("CODE", "404 NOT FOUND");
			map.put("STATUS", false);
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{restype-id}",method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findRestypeById(@PathVariable("restype-id") int restype_id){
		Map<String , Object> map = new Hashtable<String , Object>();
		try{
			ArrayList<Restypes> restypes = restypeService.findRestypeById(restype_id);
			if(!restypes.isEmpty()){
				map.put("DATA", restypes);
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "300 DATA NOT FOUND");
				map.put("MESSAGE", "DATA NOT FOUND!");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", "NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(map ,HttpStatus.OK) ;
	}
}
