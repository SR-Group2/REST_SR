package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.FoodPictures;
import org.khmeracademy.rest.repositories.FoodPictureRepository;
import org.khmeracademy.rest.services.FoodPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodPictureServiceImpl implements FoodPictureService{
	
	@Autowired
	private FoodPictureRepository foodPictureRepository;
	
	@Override
	public boolean insertFoodPictures(FoodPictures foodPicture) {
		
		return foodPictureRepository.insertFoodPicture(foodPicture);
	}

	@Override
	public ArrayList<FoodPictures> getFoodPictures() {
		
		return foodPictureRepository.getFoodPictures();
	}

	@Override
	public boolean updateFoodPictures(FoodPictures foodPictures) {
	
		return foodPictureRepository.updateFoodPicture(foodPictures);
	}

	@Override
	public boolean deleteFoodPictures(int picture_id) {
		
		return foodPictureRepository.deleteFoodPictures(picture_id);
	}

	@Override
	public ArrayList<FoodPictures> findFoodPicturesById(int picture_id) {
		
		return foodPictureRepository.findFoodPictureById(picture_id);
	}

}
