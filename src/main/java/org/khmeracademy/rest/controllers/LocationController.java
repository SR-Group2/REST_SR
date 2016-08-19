package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Locations;
import org.khmeracademy.rest.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	
	@Autowired
	private  LocationService locationService ;
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllLocation(){
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			ArrayList<Locations> locations = locationService.getAlllocation();
			if(!locations.isEmpty()){
				map.put("DATA", locations);
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
	
}
