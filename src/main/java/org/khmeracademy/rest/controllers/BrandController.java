package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Brands;
import org.khmeracademy.rest.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brand")
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	@RequestMapping(value = "/get-brand",method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> getAllBrand(){
		Map<String , Object> map = new Hashtable<String , Object>();
		try{
			ArrayList<Brands> brands = brandService.getAllBrand();
			if(!brands.isEmpty()){
				map.put("DATA", brands);
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
	
	@RequestMapping(value = "/insert-brand", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insertBrand(@RequestBody Brands brand){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(brandService.insertBrand(brand)){
				map.put("MESSAGE", "Brand has been inserted.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Brand has not been inserted.");
				map.put("CODE", "201 BRAND NOT INSERTED!");
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
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateBrand(@RequestBody Brands brand){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(brandService.updateBrand(brand)){
				map.put("MESSAGE", "Brand has been Updated.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Brand has not been Updated.");
				map.put("CODE", "201 BRAND NOT UPDATED!");
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
	
	@RequestMapping(value = "/{brand-id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteBrand(@PathVariable("brand-id") int brand_id){
		Map<String , Object> map = new HashMap<String , Object>();
		try{
			if(brandService.deleteBrand(brand_id)){
				map.put("MESSAGE", "Brand has been Deleted.");
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "Brand has not been Deleted.");
				map.put("CODE", "201 BRAND NOT DELETED!");
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
	
	@RequestMapping(value = "/{brand-id}",method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> findBrandById(@PathVariable("brand-id") int brand_id){
		Map<String , Object> map = new Hashtable<String , Object>();
		try{
			Brands brands = brandService.findBrandById(brand_id);
			if(brands != null){
				map.put("DATA", brands);
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
