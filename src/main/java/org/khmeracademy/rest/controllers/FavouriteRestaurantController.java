package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.khmeracademy.rest.entities.FavouriteRestaurants;
import org.khmeracademy.rest.services.FavouriteRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favourite-restaurant")
public class FavouriteRestaurantController {
	@Autowired
	private FavouriteRestaurantService favRestService;
	

	//======================= INSERT Favourite Restaurant =============================
	@RequestMapping(value="/insert-fav-restaurnt" , method = RequestMethod.POST , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> insertFavouriteRestaurant(@RequestBody FavouriteRestaurants favouriteRestaurant){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(favRestService.insertFavouriteRestaurant(favouriteRestaurant)) {
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "INSERT SUCCESSFULLY!");
			}else{
				map.put("MESSAGE", "FAILED TO INSERT!");
				map.put("STATUS", true);
			}
		}catch(Exception e){
			map.put("CODE", "404 NOT FOUND");
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	//======================= GET Favourite Restaurant =============================
	@RequestMapping(value="/get-fav-restaurant" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> getFavouriteRestaurant(){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			ArrayList<FavouriteRestaurants> favReataurant = favRestService.getFavouriteRestaurant();
			if(!favReataurant.isEmpty()) {
				map.put("CODE", "200 OK");
				map.put("DATA", favReataurant);
				map.put("STATUS", true);
				map.put("MESSAGE", "GET SUCCESSFULLY!");
			}else{
				map.put("MESSAGE", "FAILED TO GET!");
				map.put("STATUS", true);
			}
		}catch(Exception e){
			map.put("CODE", "404 NOT FOUND");
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	//======================= FIND Favourite Restaurant BY ID =============================
	@RequestMapping(value="/{favrest_id}" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> findFavouriteRestaurantById(@PathVariable("favrest_id") int favrest_id){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			FavouriteRestaurants favouriteRestaurants= favRestService.findFavouriteRestaurantById(favrest_id);
			if(!favouriteRestaurants.equals(null)) {
				map.put("CODE", "200 OK");
				map.put("DATA", favouriteRestaurants);
				map.put("STATUS", true);
				map.put("MESSAGE", "GET SUCCESSFULLY!");
			}else{
				map.put("MESSAGE", "FAILED TO GET!");
				map.put("STATUS", true);
				map.put("CODE", "404 NOT FOUND");
			}
		}catch(Exception e){
			map.put("CODE", "500 SERVER ERROR!");
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	//======================= DELETE  CATEGORY =============================
	@RequestMapping(value="/{favrest_id}" , method = RequestMethod.DELETE , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> deleteFavouriteRestaurant(@PathVariable("favrest_id") int favrest_id){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(favRestService.deleteFavouriteRestaurant(favrest_id)) {
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "DELETE SUCCESSFULLY!");
			}else{
				map.put("MESSAGE", "FAILED TO DELETE!");
				map.put("STATUS", true);
			}
		}catch(Exception e){
			map.put("CODE", "404 NOT FOUND");
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	//======================= UPDATE CATEGORY =============================
	@RequestMapping(method = RequestMethod.PUT , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> updateFavouriteRestaurant(@RequestBody FavouriteRestaurants favouriteRestaurant){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(favRestService.updateFavouriteRestaurant(favouriteRestaurant)) {
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "UPDATE SUCCESSFULLY!");
			}else{
				map.put("MESSAGE", "FAILED TO UPDATE!");
				map.put("STATUS", true);
			}
		}catch(Exception e){
			map.put("CODE", "404 NOT FOUND");
			map.put("STATUS", false);
			map.put("MESSAGE", "ERROR!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
}
