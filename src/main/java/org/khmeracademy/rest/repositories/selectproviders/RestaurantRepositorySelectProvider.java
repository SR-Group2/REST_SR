package org.khmeracademy.rest.repositories.selectproviders;

import java.util.Map;

public class RestaurantRepositorySelectProvider {

	public String searchRestaurant(Map<String, Object> map){
		StringBuilder strBuilder = new StringBuilder();
		
		int categoryId = (int)map.get("category_id");
		
		strBuilder.append("SELECT DISTINCT	R.rest_id, " +
						  "					R.rest_name, " +
						  "					R.rest_name_kh, " +
						  "					R.contact, " + 
						  "					R.about, " +
						  "					R.latitude, " +
						  "					R.longitude " +
						  "FROM restaurants R ");
		if(categoryId!=0){
			strBuilder.append("LEFT JOIN menus M ON R.rest_id = M.rest_id ");
		}
						  
		strBuilder.append("WHERE LOWER (R.rest_name) LIKE LOWER (#{keyword}) ");
						  
		if(categoryId!=0){
			strBuilder.append("AND restype_id = #{category_id}");
		}
		
		strBuilder.append("ORDER BY R.rest_id DESC "
						+ "LIMIT #{limit} OFFSET #{offset} ");	
		
		return strBuilder.toString();
	}
	
	public String count(Map<String, Object> map){
		
		StringBuilder strBuilder = new StringBuilder();
		
		int categoryId = (int)map.get("category_id");
		
		strBuilder.append("SELECT COUNT(DISTINCT R.rest_id) " +
						  "FROM restaurants R ");
		if(categoryId!=0){
			strBuilder.append("LEFT JOIN menus M ON R.rest_id = M.rest_id ");
		}
						  
		strBuilder.append("WHERE LOWER (R.rest_name) LIKE '%'||#{keyword}||'%' ");
						  
		if(categoryId!=0){
			strBuilder.append("AND restype_id = #{category_id}");
		}
		
		return strBuilder.toString();
	}
}
