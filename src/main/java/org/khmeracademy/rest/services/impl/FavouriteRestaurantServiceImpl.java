package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.FavouriteRestaurants;
import org.khmeracademy.rest.repositories.FavouriteRestaurantRepository;
import org.khmeracademy.rest.services.FavouriteRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavouriteRestaurantServiceImpl implements FavouriteRestaurantService {
	@Autowired
	private FavouriteRestaurantRepository favouriteRestaurantRepository;

	public ArrayList<FavouriteRestaurants> getFavouriteRestaurant() {
		return favouriteRestaurantRepository.getFavouriteRestaurant();
	}

	@Override
	public FavouriteRestaurants findFavouriteRestaurantById(int favrest_id) {
		return favouriteRestaurantRepository.findFavouriteRestaurantById(favrest_id);
	}

	@Override
	public boolean updateFavouriteRestaurant(FavouriteRestaurants favouriteRestaurant) {
		return favouriteRestaurantRepository.updateFavouriteRestaurant(favouriteRestaurant);
	}

	@Override
	public boolean deleteFavouriteRestaurant(int favrest_id) {

		return favouriteRestaurantRepository.deleteFavouriteRestaurant(favrest_id);
	}

	@Override
	public boolean insertFavouriteRestaurant(FavouriteRestaurants favouriteRestaurant) {
		return favouriteRestaurantRepository.insertFavouriteRestaurant(favouriteRestaurant);
	}

	@Override
	public ArrayList<FavouriteRestaurants> getFavouriteRestaurantByUserId(int user_id) {
		return favouriteRestaurantRepository.getFavouriteRestaurantByUserId(user_id);
	}

	@Override
	public int totalFavourite(int user_id) {
		
		return favouriteRestaurantRepository.totalFavourite(user_id);
	}
	
	
 
}
