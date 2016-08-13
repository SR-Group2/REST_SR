package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Categories;

public interface CategoryService {
	
	public boolean insertCategory(Categories category);
	
	public ArrayList<Categories> getCategory();
	
	public ArrayList<Categories> findCategoryById(int category_id);
	
	public boolean updateCategory(Categories category);
	
	public boolean deleteCategory(int category_id);
	
	public ArrayList<Categories> getCategoryByRestId(int rest_id);
	
}
