package org.khmeracademy.rest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.khmeracademy.rest.entities.Addresses;
import org.khmeracademy.rest.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "/get-address", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> getAllAddress(){
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			ArrayList<Addresses> addresses = addressService.getAllAddress();
			if(!addresses.isEmpty()){
				map.put("DATA", addresses);
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
	
	@RequestMapping(value = "/insert-address", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insertAddress(@RequestBody Addresses address){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(addressService.insertAddress(address)){
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "Address Has Been Inserted!");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 NOT INSERT");
				map.put("MESSAGE", "Address Has not Been Inserted!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{address-id}", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> updateAddress(@RequestBody Addresses address){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(addressService.updateAddress(address)){
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "Address Has Been Updated!");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 NOT UPDATED");
				map.put("MESSAGE", "Address Has not Been Updated!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{address-id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> deleteAddress(@PathVariable("address-id") int address_id){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(addressService.deleteAddress(address_id)){
				map.put("CODE", "200 OK");
				map.put("STATUS", true);
				map.put("MESSAGE", "Address Has Been Deleted!");
			}else{
				map.put("STATUS", false);
				map.put("CODE", "300 NOT DELETED");
				map.put("MESSAGE", "Address Has not Been Deleted!");
			}
		} catch (Exception e) {
			map.put("STATUS", false);
			map.put("CODE", "404 NOT FOUND");
			map.put("MESSAGE", " NOT FOUND!");
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{address-id}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> findAddressById(@PathVariable("address-id") int address_id){
		Map<String, Object> map = new Hashtable<String, Object>();
		try {
			ArrayList<Addresses> addresses = addressService.findAddressById(address_id);
			if(!addresses.isEmpty()){
				map.put("DATA", addresses);
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
