package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Foods;
import org.khmeracademy.rest.repositories.FoodRepository;
import org.khmeracademy.rest.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Override
	public boolean insertFood(Foods food) {
	
		return foodRepository.insertFood(food);
	}

	@Override
	public ArrayList<Foods> getFood() {
		return foodRepository.getFood();
	}

	@Override
	public boolean updateFood(Foods food) {
		return foodRepository.updateFood(food);
	}

	@Override
	public boolean deleteFood(int food_id) {
		return foodRepository.deleteFood(food_id);
	}

	@Override
	public ArrayList<Foods> findFoodById(int food_id) {
		return foodRepository.findFoodById(food_id);
	}

	
}
