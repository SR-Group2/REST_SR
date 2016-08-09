package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
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
	
	@RequestMapping(value = "/insert-restaurant", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insertRestaurant(@RequestBody Restaurants restaurant){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(restaurantService.insertRestaurant(restaurant)){
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
	}
	
	@RequestMapping(value = "/{rest-id}", method = RequestMethod.PUT)
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
	}
	
	@RequestMapping(value = "/{rest-id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteRestaurant(@PathVariable("rest-id") int rest_id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(restaurantService.deleteRestaurant(rest_id)){
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
	
	@RequestMapping(value = "/{rest-id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findRestaurantById(@PathVariable("rest-id") int rest_id){
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
}
