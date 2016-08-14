package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restaurants;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {

	public ArrayList<Restaurants> getAllRestaurant();
	
	public boolean insertRestaurant(Restaurants restaurant);
	
	public boolean deleteRestaurant(int rest_id);
	
	public boolean updateRestaurant(Restaurants restaurant);
	
	public Restaurants findRestaurantById(int rest_id);
	
	public ArrayList<Restaurants> findRestaurantWithCategory();
}
