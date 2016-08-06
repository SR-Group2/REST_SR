package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Categories;
import org.khmeracademy.rest.repositories.CategoryRepository;
import org.khmeracademy.rest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public boolean insertCategory(Categories category) {
	
		return categoryRepository.insertCategory(category);
	}

	@Override
	public ArrayList<Categories> getCategory() {
		
		return categoryRepository.getCategory();
	}
	
	@Override
	public ArrayList<Categories> findCategoryById(int category_id) {
		
		return categoryRepository.findCategoryById(category_id);
	}

	@Override
	public boolean updateCategory(Categories category) {
		
		return categoryRepository.updateCategory(category);
	}

	@Override
	public boolean deleteCategory(int category_id) {
		
		return categoryRepository.deleteCategory(category_id);
	}
	
	
	
}
