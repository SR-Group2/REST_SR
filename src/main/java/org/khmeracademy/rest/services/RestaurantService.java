package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.filters.RestypeFilter;
import org.khmeracademy.rest.form.RestaurantForm2;
import org.khmeracademy.rest.form.RestaurantForm2.RestaurantUpdateForm2;
import org.khmeracademy.rest.utils.Pagination;
import org.springframework.stereotype.Service;

@Service
public interface RestaurantService {

	public ArrayList<Restaurants> getAllRestaurant();
	
//	public boolean addNewRestaurant(RestaurantForm restaurantForm);
	
	public boolean addNewRestaurant(RestaurantForm2 restaurantForm);
	
	public boolean deleteRestaurant(int rest_id);
	
	//public boolean updateRestaurant(Restaurants restaurant);
	
	public Restaurants findRestaurantById(int rest_id);
	
	public ArrayList<Restaurants> findRestaurantWithCategory(Pagination pagination);
	
	public ArrayList<Restaurants> searchRest(Pagination pagination, RestypeFilter filter);
	
	public int countRestById(String keyword);
	
	public int countRestOwner();
	
	public boolean updateRestaurant(RestaurantUpdateForm2 restaurantUpdateForm2);
	
}
