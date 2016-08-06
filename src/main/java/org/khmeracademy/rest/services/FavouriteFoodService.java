package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.FavouriteFoods;

public interface FavouriteFoodService {
	
	public boolean insertFavouriteFood(FavouriteFoods favouriteFoods);
	
	public ArrayList<FavouriteFoods> getFavouriteFood();
	
	public ArrayList<FavouriteFoods> findFavouriteFoodsById(int fav_id);
	
	public boolean updateFavouriteFood(FavouriteFoods favouriteFoods);
	
	public boolean deleteFavouriteFood(int fav_id);
}
