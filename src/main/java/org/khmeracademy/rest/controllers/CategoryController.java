package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.rest.entities.Categories;
import org.khmeracademy.rest.entities.Foods;
import org.khmeracademy.rest.services.CategoryService;
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
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//======================= INSERT CATEGORY =============================
	@RequestMapping(value="/insert-category" , method = RequestMethod.POST , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> insertCategory(@RequestBody Categories category){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(categoryService.insertCategory(category)) {
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
	//======================= GET CATEGORY =============================
	@RequestMapping(value="/get-category" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> getCategory(){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			ArrayList<Categories> category = categoryService.getCategory();
			if(!category.isEmpty()) {
				map.put("CODE", "200 OK");
				map.put("DATA", category);
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
	//======================= GET CATEGORY BY RESTAURANT ID =============================
		@RequestMapping(value="/get-category-by-rest-id/{rest_id}" , method = RequestMethod.GET , headers = "Accept=Application/json")
		public ResponseEntity<Map<String , Object>> getCategoryByRestID(@PathVariable int rest_id){
			Map<String , Object> map = new HashMap<String , Object>();
		
			try{
				ArrayList<Categories> category = categoryService.getCategoryByRestId(rest_id);
				if(!category.isEmpty()) {
					map.put("CODE", "200 OK");
					map.put("DATA", category);
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
	//======================= FIND CATEGORY BY ID =============================
	@RequestMapping(value="/find-category-by-id/{category_id}" , method = RequestMethod.GET , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> findCategoryById(@PathVariable("category_id") int category_id){
		Map<String , Object> map = new HashMap<String , Object>();
	
		try{
			ArrayList<Categories> category = categoryService.findCategoryById(category_id);
			if(!category.isEmpty()) {
				map.put("CODE", "200 OK");
				map.put("DATA", category);
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
	//======================= DELETE  CATEGORY =============================
	@RequestMapping(value="/delete-category/{category_id}" , method = RequestMethod.DELETE , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> deleteCategory(@PathVariable("category_id") int category_id){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(categoryService.deleteCategory(category_id)) {
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
	@RequestMapping(value="/update-category" , method = RequestMethod.PUT , headers = "Accept=Application/json")
	public ResponseEntity<Map<String , Object>> updateCategory(@RequestBody Categories category){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(categoryService.updateCategory(category)) {
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
