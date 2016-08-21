package org.khmeracademy.rest.controllers;

import java.util.HashMap;
import java.util.Map;

import org.khmeracademy.rest.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	
	@Autowired
	private  LocationService locationService ;
	
//	@RequestMapping( method = RequestMethod.GET)
//	public ResponseEntity<Map<String, Object>> getAllLocation(){
//		Map<String, Object> map = new Hashtable<String, Object>();
//		try {
//			ArrayList<Locations> locations = locationService.getAlllocation();
//			if(!locations.isEmpty()){
//				map.put("DATA", locations);
//				map.put("CODE", "200 OK");
//				map.put("STATUS", true);
//				map.put("MESSAGE", "DATA FOUND");
//			}else{
//				map.put("STATUS", false);
//				map.put("CODE", "300 DATA NOT FOUND");
//				map.put("MESSAGE", "DATA NOT FOUND");
//			}
//		} catch (Exception e) {
//			map.put("STATUS", false);
//			map.put("CODE", "404 NOT FOUND");
//			map.put("MESSAGE", " NOT FOUND!");
//			e.printStackTrace();
//		}
//		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
//	}
	
	
	//value="/v1/api/admin/cities", 
	@RequestMapping(value = "/cities", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllCities(){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("DATA", locationService.getAllCities());
		model.put("MESSAGE", "ALL CITIES HAVE BEEN FIND SUCCESSFULLY.");
		model.put("CODE", "200");
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cities/{cityId}/districts", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllDistrictsByCityId(@PathVariable("cityId")int cityId){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("DATA", locationService.getAllDistrictsByCityId(cityId));
		model.put("MESSAGE", "ALL DISTRICTS HAVE BEEN FIND SUCCESSFULLY.");
		model.put("CODE", "200");
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	//value="/v1/api/admin/districts/{districtId}/commnunes"
	@RequestMapping(value="/districts/{districtId}/commnunes", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllCommunesByDistrictId(@PathVariable("districtId")int districtId){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("DATA", locationService.getAllCommunesByDistrictById(districtId));
		model.put("MESSAGE", "ALL COMMUNES HAVE BEEN FIND SUCCESSFULLY.");
		model.put("CODE", "200");
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
	//value="/v1/api/admin/commnunes/{communeId}/villages"
	@RequestMapping(value="/commnunes/{communeId}/villages", method= RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllVillagesByCommuneId(@PathVariable("communeId") int communeId){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("DATA", locationService.getAllVillagesByCommuneId(communeId));
		model.put("MESSAGE", "ALL VILLAGES HAVE BEEN FIND SUCCESSFULLY.");
		model.put("CODE", "200");
		return new ResponseEntity<Map<String, Object>>(model, HttpStatus.OK);
	}
	
}
