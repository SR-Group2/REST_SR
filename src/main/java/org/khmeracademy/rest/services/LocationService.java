package org.khmeracademy.rest.services;

import java.util.List;

import org.khmeracademy.rest.entities.Locations;
import org.springframework.stereotype.Service;



@Service
public interface LocationService {
	
	//public ArrayList<Locations> getAlllocation();
	
	List<Locations> getAllCities();
	
	List<Locations> getAllDistrictsByCityId(int cityId);
	
	List<Locations> getAllCommunesByDistrictById(int districtId);
	
	List<Locations> getAllVillagesByCommuneId(int communeId);
}
