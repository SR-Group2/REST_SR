package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Addresses;
import org.khmeracademy.rest.entities.CatRest;
import org.khmeracademy.rest.entities.Menus;
import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.filters.RestypeFilter;
import org.khmeracademy.rest.repositories.AddressRepository;
import org.khmeracademy.rest.repositories.MenuRepository;
import org.khmeracademy.rest.repositories.RestaurantRepository;
import org.khmeracademy.rest.repositories.RestypeRepository;
import org.khmeracademy.rest.services.RestaurantService;
import org.khmeracademy.rest.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RestypeRepository restType;

	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public ArrayList<Restaurants> getAllRestaurant() {
		return restaurantRepository.getAllRestaurant();
	}

	@Override
	public boolean insertRestaurant(Restaurants restaurant) {
		//return restaurantRepository.insertRestaurant(restaurant);
		return addNewRestaurant(restaurant);
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
	
	@Transactional
	public boolean addNewRestaurant(Restaurants restaurant){
		try{
			
			//TODO: 1. ADDRESS WITH RESTAURANT ID
			Addresses address = restaurant.getAddress();
			addressRepository.insertAddress(address);
			
			System.out.println("ADDRESS_ID ==> " + address.getAddress_id());
			
			//TODO: 2. ADD TO RESTAURANT RETURN ID
			restaurant.setAddress(address);
			restaurantRepository.insertRestaurant(restaurant);
			
			//System.out.println("getRest_id() ======= > " + restaurant.getRest_id());
			
			//TODO: 3. ADD MANY REST_TYPES TO CATRESTS
			restType.inertBatchCatRest(restaurant.getCategories() , restaurant.getRest_id() );
			
			//TODO: 4. ADD MANY MENUS TO MENUS
			menuRepository.inertBatchMenus(restaurant.getRestype(), restaurant.getRest_id() );
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}



	@Override
	public int countRestById(String keyword) {
		
		return restaurantRepository.countRestById(keyword.toLowerCase());
	}

	@Override
	public ArrayList<Restaurants> searchRest(Pagination pagination, RestypeFilter filter) {
		return restaurantRepository.searchRest("%" + filter.getKeyword().toLowerCase() + "%", 
				pagination.getLimit(), pagination.getOffset());
	}

	

}
