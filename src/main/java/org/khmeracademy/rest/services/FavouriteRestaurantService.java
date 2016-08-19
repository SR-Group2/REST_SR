package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.khmeracademy.rest.entities.FavouriteRestaurants;
import org.springframework.stereotype.Service;

@Service
public interface FavouriteRestaurantService {
	public boolean insertFavouriteRestaurant(FavouriteRestaurants favouriteRestaurant);
	public ArrayList<FavouriteRestaurants> getFavouriteRestaurant();
	public FavouriteRestaurants findFavouriteRestaurantById(@Param("favrest_id") int favrest_id);
	public boolean updateFavouriteRestaurant(FavouriteRestaurants favouriteRestaurant);
	public boolean deleteFavouriteRestaurant(@Param("favrest_id") int favrest_id);
}
