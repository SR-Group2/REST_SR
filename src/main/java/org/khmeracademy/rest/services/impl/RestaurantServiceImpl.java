package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.repositories.RestaurantRepository;
import org.khmeracademy.rest.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public ArrayList<Restaurants> getAllRestaurant() {
		return restaurantRepository.getAllRestaurant();
	}

	@Override
	public boolean insertRestaurant(Restaurants restaurant) {
		return restaurantRepository.insertRestaurant(restaurant);
	}

	@Override
	public boolean deleteRestaurant(int rest_id) {
		return restaurantRepository.deleteRestaurant(rest_id);
	}

	@Override
	public boolean updateRestaurant(Restaurants restaurant) {
		return restaurantRepository.updateRestaurant(restaurant);
	}

	@Override
	public Restaurants findRestaurantById(int rest_id) {
		return restaurantRepository.findRestaurantById(rest_id);
	}

	@Override
	public ArrayList<Restaurants> findRestaurantWithCategory() {
		// TODO Auto-generated method stub
		return restaurantRepository.findRestaurantWithCategory();
	}

	

}
