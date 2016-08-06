package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Foods;

public interface FoodService {
	
	public boolean insertFood(Foods food);
	public ArrayList<Foods> getFood();
	public boolean updateFood(Foods food);
	public boolean deleteFood(int food_id);
	public ArrayList<Foods> findFoodById(int food_id);
	
}
