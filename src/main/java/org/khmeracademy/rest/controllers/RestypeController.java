package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.filters.RestypeFilter;
import org.khmeracademy.rest.services.RestypeService;
import org.khmeracademy.rest.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/restype")
public class RestypeController {
	
	@Autowired
	private RestypeService restypeService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "keyword", dataType = "string", paramType = "query", defaultValue="",
	            value = "search restaurant"),
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue="1",
	            value = "Results page you want to retrieve)"),
	    @ApiImplicitParam(name = "limit", dataType = "integer", paramType = "query", defaultValue="15",
	            value = "Number of records per page."),
	})
	
	@RequestMapping(value = "/get-restype",method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllRestype(@ApiIgnore RestypeFilter restypeFilter, 
			@ApiIgnore Pagination pagination){
		Map<String , Object> map = new Hashtable<String , Object>();
		
		try{
			
			pagination.setTotalCount(restypeService.countRestype(restypeFilter.getKeyword()));
//			pagination.setOffset(pagination.getOffset());
			ArrayList<Restypes> restypes = restypeService.getAllRestype(pagination, restypeFilter);
			for(int i=0;i<restypes.size();i++){
				System.out.println("NAME "+ restypes.get(i).getRestype_name());
				System.out.println("Offset " + pagination.getOffset());
			}
			if(!restypes.isEmpty()){
				map.put("DATA", restypes);
				map.put("PAGINATION", pagination);
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			}else{
				map.put("STATUS", false);
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
	
	@RequestMapping(value = "/find-restype-by-keyword",method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findRestypeByKeyword(
			@RequestParam(value="keyword" , required = false) String keyword){
		Map<String , Object> map = new Hashtable<String , Object>();
		
		try{
			
			ArrayList<Restypes> restypes = restypeService.findRestypeByKeyword(keyword);
			if(!restypes.isEmpty()){
				map.put("DATA", restypes);
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			}else{
				map.put("STATUS", false);
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
	

	
	@ApiImplicitParams({
	
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue="1",
	            value = "Results page you want to retrieve)"),
	    @ApiImplicitParam(name = "limit", dataType = "integer", paramType = "query", defaultValue="15",
	            value = "Number of records per page."),
	})
	@RequestMapping(value = "/{restype_id}",method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findRestypeById(@PathVariable("restype_id") int restype_id,
			@ApiIgnore Pagination pagination){
		Map<String , Object> map = new Hashtable<String , Object>();
		try{
			
			pagination.setTotalCount(restypeService.countRest(restype_id));
			
			ArrayList<Restaurants> restypes = restypeService.findRestypeById(restype_id, pagination);
			if(!restypes.isEmpty()){
				map.put("DATA", restypes);
				map.put("CODE", "200 OK");
				map.put("PAGINATION", pagination);
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			}else{
				map.put("STATUS", false);
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
