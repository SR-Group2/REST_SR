package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.rest.entities.Foods;
import org.khmeracademy.rest.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@RequestMapping(value="/insert-food" , method = RequestMethod.POST , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> insertFood(@RequestBody Foods food){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(foodService.insertFood(food)) {
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
	
	@RequestMapping(value="/get-food" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> getFood(){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			ArrayList<Foods> food = foodService.getFood();
			if(!food.isEmpty()) {
				map.put("CODE", "200 OK");
				map.put("DATA", food);
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
	
	@RequestMapping(value="/find-food-by-id/{food_id}" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> findFoodById(@PathVariable("food_id") int food_id){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			ArrayList<Foods> food = foodService.findFoodById(food_id);
			if(!food.isEmpty()) {
				map.put("CODE", "200 OK");
				map.put("DATA", food);
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
	
	@RequestMapping(value="/delete-food/{food_id}" , method = RequestMethod.DELETE , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> deleteFood(@PathVariable("food_id") int food_id){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(foodService.deleteFood(food_id)) {
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
	
	@RequestMapping(value="/update-food" , method = RequestMethod.PUT , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> updateFood(@RequestBody Foods food){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(foodService.updateFood(food)) {
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
