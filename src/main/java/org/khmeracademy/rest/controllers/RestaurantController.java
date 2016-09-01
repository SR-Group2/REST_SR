package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.DRestaurant;
import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.filters.RestypeFilter;
import org.khmeracademy.rest.form.RestaurantForm;
import org.khmeracademy.rest.services.RestaurantService;
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
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	//==================== SEARCH RESTAURANT==============
	@ApiImplicitParams({
		@ApiImplicitParam(name = "keyword", dataType = "string", paramType = "query", defaultValue="",
	            value = "search restaurant"),
		@ApiImplicitParam(name = "category_id", dataType = "int", paramType = "query", defaultValue="0",
        value = "Category Id"),
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue="1",
	            value = "Results page you want to retrieve)"),
	    @ApiImplicitParam(name = "limit", dataType = "integer", paramType = "query", defaultValue="15",
	            value = "Number of records per page."),
	})
	
	@RequestMapping(value = "/search-rest",method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllRestype(@ApiIgnore RestypeFilter filter, 
			@ApiIgnore Pagination pagination){
		Map<String , Object> map = new Hashtable<String , Object>();
		
		try{
			
			pagination.setTotalCount(restaurantService.countRestById(filter.getCategory_id(), filter.getKeyword()));
			
			System.out.println("PAGINATION TOTAL COUNT==> "+ pagination.getTotalCount());
			System.out.println(pagination.getTotalCount() + filter.getKeyword());
//			pagination.setOffset(pagination.getOffset());
			ArrayList<Restaurants> rest = restaurantService.searchRest(pagination, filter);
			
			for(int i=0;i<rest.size();i++){
				System.out.println("NAME "+ rest.get(i).getRest_name());
				System.out.println("Offset " + pagination.getOffset());
			}
			if(!rest.isEmpty()){
				map.put("DATA", rest);
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
	//================== DELETE RESTAURANT BY ID =====================
		@ApiImplicitParams({
			@ApiImplicitParam(name = "rest_id", dataType = "int", paramType = "query", defaultValue="0",
		            value = "rest_id"),
			@ApiImplicitParam(name = "address_id", dataType = "int", paramType = "query", defaultValue="0",
	        value = "address_id")
		})
		@RequestMapping(value="/delete", method = RequestMethod.DELETE)
		public ResponseEntity<Map<String, Object>> deleteRestaurant(@ApiIgnore DRestaurant dRestaurant){
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(dRestaurant.getAddress_id());
			System.out.println(dRestaurant.getRest_id());
			try {
				if(restaurantService.deleteRestaurant(dRestaurant.getRest_id(),dRestaurant.getAddress_id())){
					map.put("CODE", "200 OK");
					map.put("STATUS", true);
					map.put("MESSAGE", "Restaurant Has Been Deleted!");
				}else{
					map.put("STATUS", false);
					map.put("CODE", "300 NOT DELETED");
					map.put("MESSAGE", "Restaurant Has Been Deleted!");
				}
			} catch (Exception e) {
				map.put("STATUS", false);
				map.put("CODE", "404 NOT FOUND");
				map.put("MESSAGE", " NOT FOUND!");
				e.printStackTrace();
			}
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
	//==================== GET RESTAURANT WITH CATEGORY==============
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", defaultValue="1",
	            value = "Results page you want to retrieve)"),
	    @ApiImplicitParam(name = "limit", dataType = "integer", paramType = "query", defaultValue="15",
	            value = "Number of records per page."),
	})
	@RequestMapping(value = "/get-restaurant-with-category", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getRestuarantWidthCaegory(@ApiIgnore Pagination pagination){
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			pagination.setTotalCount(restaurantService.countRestOwner());
			ArrayList<Restaurants> restaurants = restaurantService.findRestaurantWithCategory(pagination);
			if(!restaurants.isEmpty()){
				map.put("DATA", restaurants);
				map.put("PAGINATION", pagination);
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 DATA NOT FOUND");
				map.put("MESSAGE", "DATA NOT FOUND");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	//==================== GET ALL RESTAURANT==============
	@RequestMapping(value = "/get-restaurant", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllRestaurant(){
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			ArrayList<Restaurants> restaurants = restaurantService.getAllRestaurant();
			if(!restaurants.isEmpty()){
				map.put("DATA", restaurants);
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 DATA NOT FOUND");
				map.put("MESSAGE", "DATA NOT FOUND");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/insert-restaurant", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insertRestaurant(@RequestBody RestaurantForm restaurantForm){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(restaurantService.addNewRestaurant(restaurantForm)){
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "Restaurant Has Been Inserted!");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 NOT INSERT");
				map.put("MESSAGE", "Restaurant Has Been Inserted!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}*/
	/*
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateRestaurant(@RequestBody Restaurants restaurant){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(restaurantService.updateRestaurant(restaurant)){
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "Restaurant Has Been Updated!");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 NOT UPDATED");
				map.put("MESSAGE", "Restaurant Has Been Updated!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}*/
	
	//============================= FIND RESTAURANT BY ID
	@RequestMapping(value = "/{rest_id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findRestaurantById(@PathVariable("rest_id") int rest_id){
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
		
			
			Restaurants restaurants = restaurantService.findRestaurantById(rest_id);
			
			if(!restaurants.equals(null)){
				map.put("DATA", restaurants);
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DATA FOUND!");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 NOT FOUND");
				map.put("MESSAGE", "CANNOT FIND!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/total-restaurant", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> countRest(){
		Map<String, Object> map= new Hashtable<String, Object>();
		
		try{
			int  totlal = restaurantService.countRest();
			if(totlal>0){
				map.put("DATA", totlal);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "Restaurant found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "Restaurant don't have");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/count-by-user-id", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> topRest(){
		Map<String, Object> map= new Hashtable<String, Object>();
		
		try{
			ArrayList<Restaurants> restaurant = restaurantService.topRest();
			if(!restaurant.isEmpty()){
				map.put("DATA", restaurant);
				map.put("STATUS", true);
				map.put("CODE", "200");
				map.put("MESSAGE", "Restaurant found");
			}else{
				map.put("STATUS", true);
				map.put("CODE", "404");
				map.put("MESSAGE", "Restaurant don't have");
			}
		}catch(Exception e){
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
