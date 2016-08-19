package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Locations;
import org.khmeracademy.rest.repositories.LocationRepository;
import org.khmeracademy.rest.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImp implements LocationService{
	
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public ArrayList<Locations> getAlllocation() {
		
		return locationRepository.getAlllocation();
	}
	
	
	
}
