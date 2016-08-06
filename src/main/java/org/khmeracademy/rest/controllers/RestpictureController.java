package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Restpictures;
import org.khmeracademy.rest.services.RestpictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restpicture")
public class RestpictureController {

	@Autowired
	private RestpictureService restpictureService;
	
	@RequestMapping(value = "/get-restpicture", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getAllRestpicture(){
		Map<String , Object> map = new Hashtable<String , Object>();
		try{
			ArrayList<Restpictures> restpictures = restpictureService.getAllRestpicture();
			if(!restpictures.isEmpty()){
				map.put("DATA", restpictures);
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
	
	@RequestMapping(value = "/insert-restpicture", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insertRestpicture(@RequestBody Restpictures restpicture){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(restpictureService.insertRestpicture(restpicture)){
				map.put("MESSAGE", "Restpicture has been inserted.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Restpicture has not been inserted.");
				map.put("CODE", "201 RESTPICTURE NOT INSERTED!");
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
	
	@RequestMapping(value = "/{restpicture-id}", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateRestpicture(@RequestBody Restpictures restpicture){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(restpictureService.updateRestpicture(restpicture)){
				map.put("MESSAGE", "Restpicture has been Updated.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Restpicture has not been Updated.");
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
	
	@RequestMapping(value = "/{restpicture-id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteRestpicture(@PathVariable("restpicture-id") int picture_id){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(restpictureService.deleteRestpicture(picture_id)){
				map.put("MESSAGE", "Restpicture has been Deleted.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Restpicture has not been Deleted.");
				map.put("CODE", "201 RESTPICTURE NOT DELETED!");
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
	
	@RequestMapping(value = "/{restpicture-id}",method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findRestpictureById(@PathVariable("restpicture-id") int picture_id){
		Map<String , Object> map = new Hashtable<String , Object>();
		try{
			ArrayList<Restpictures> restpictures = restpictureService.findRestpictureById(picture_id);
			if(!restpictures.isEmpty()){
				map.put("DATA", restpictures);
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
