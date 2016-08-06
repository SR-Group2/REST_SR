package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.rest.entities.FoodPictures;
import org.khmeracademy.rest.services.FoodPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food-picture")
public class FoodPictureController {
	
	@Autowired
	private FoodPictureService foodPictureService;
	
	@RequestMapping(value="/insert-food-picutre" , method = RequestMethod.POST , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> insertFoodPicture(@RequestBody FoodPictures foodPictues){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(foodPictureService.insertFoodPictures(foodPictues)) {
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
	
	@RequestMapping(value="/get-food-picture" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> getFoodPicture(){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			ArrayList<FoodPictures> foodpicture = foodPictureService.getFoodPictures();
			if(!foodpicture.isEmpty()) {
				map.put("CODE", "200 OK");
				map.put("DATA", foodpicture);
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
	
	@RequestMapping(value="/find-food-picture-by-id/{picture_id}" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> findFoodPictureById(@PathVariable("picture_id") int picture_id){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			ArrayList<FoodPictures> foodpicture = foodPictureService.findFoodPicturesById(picture_id);
			if(!foodpicture.isEmpty()) {
				map.put("CODE", "200 OK");
				map.put("DATA", foodpicture);
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
	
	@RequestMapping(value="/delete-food-picture/{picture_id}" , method = RequestMethod.DELETE , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> deleteFoodPicture(@PathVariable("picture_id") int picture_id){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(foodPictureService.deleteFoodPictures(picture_id)) {
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
	
	@RequestMapping(value="/update-food-picture" , method = RequestMethod.PUT , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> updateFoodPicture(@RequestBody FoodPictures foodPictures){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(foodPictureService.updateFoodPictures(foodPictures)) {
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
