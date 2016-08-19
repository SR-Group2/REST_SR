package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Locations;
import org.springframework.stereotype.Service;



@Service
public interface LocationService {
	
	public ArrayList<Locations> getAlllocation();
	
}
