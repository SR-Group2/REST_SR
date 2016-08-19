package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.khmeracademy.rest.entities.Addresses;
import org.khmeracademy.rest.entities.Categories;
import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.UploadedFileInfo;
import org.khmeracademy.rest.filters.RestypeFilter;
import org.khmeracademy.rest.form.RestaurantForm2;
import org.khmeracademy.rest.repositories.AddressRepository;
import org.khmeracademy.rest.repositories.CategoryRepository;
import org.khmeracademy.rest.repositories.RestaurantRepository;
import org.khmeracademy.rest.repositories.RestypeRepository;
import org.khmeracademy.rest.services.FileUploadService;
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
	private CategoryRepository categoryRepository; 
	

	@Autowired
	private FileUploadService fileUploadService;
	
	@Override
	public ArrayList<Restaurants> getAllRestaurant() {
		return restaurantRepository.getAllRestaurant();
	}

	/*@Override
	public boolean insertRestaurant(Restaurants restaurant) {
		//return restaurantRepository.insertRestaurant(restaurant);
		return addNewRestaurant(restaurant);
	}
*/
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
	public ArrayList<Restaurants> findRestaurantWithCategory(Pagination pagination) {
		// TODO Auto-generated method stub
		return restaurantRepository.findRestaurantWithCategory(pagination.getLimit(), pagination.getOffset());
	}
	
	@Override
	@Transactional
	public boolean addNewRestaurant(RestaurantForm2 restaurantForm){
		
		// ============== Belove Teacher Pirang
		try{
			
			UploadedFileInfo fileInfo = fileUploadService.upload(restaurantForm.getMenu_files(), "menu");
			// fileInfo.getNames() : List of FIle Path
			//1. Upload File
			//2. Get Url
			//3. Insert Restaurant -> Return ID
			//4. Insert Menu
			//==================
			
			// 1. Insert address -> return address id (table name : addresses)
			Addresses address = restaurantForm.getAddress();
			addressRepository.insertAddress(address);
			
			System.out.println("ADDRESS_ID ==> " + address.getAddress_id());
			
			// 2. Insert Restaurant -> return rest_id (table name : restaurants)
			restaurantForm.setAddress(address);
			restaurantRepository.insertRestaurant(restaurantForm);
			
			System.out.println("REST ID ======= > " + restaurantForm.getRest_id());
			
			//3. Insert Many Categories -> return category ID (table name : categories)
			Categories cate = new Categories();
			categoryRepository.inertBatchCategories(fileInfo.getNames() , restaurantForm.getRest_id());
			
			//4. Insert Rest Type ID
			restType.insertBatchRestypeId(restaurantForm.getRestypes_id(), restaurantForm.getRest_id());
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
		
		/* ======================= My Belove Teacher Tola
		try{
			
			// 1. Insert address -> return address id (table name : addresses)
			Addresses address = restaurantForm.getAddress();
			addressRepository.insertAddress(address);
			
			System.out.println("ADDRESS_ID ==> " + address.getAddress_id());
			
			// 2. Insert Restaurant -> return rest_id (table name : restaurants)
			restaurantForm.setAddress(address);
			restaurantRepository.insertRestaurant(restaurantForm);
			
			System.out.println("getRest_id() ======= > " + restaurantForm.getRest_id());
			
			//3. Insert Many Categories -> return category ID (table name : categories)
			Categories cate = new Categories();
			categoryRepository.inertBatchCategories(restaurantForm.getCategories() , cate);
			
			System.out.println("getCategories_id() ======= > " + cate.getCategory_id());
			
			//4. Insert Many Categories ID and Restaurants ID ( table name : catrests ) 
			System.out.println(restaurantForm.getCategories().size());
			
			List<CategoryId> categoryId = new ArrayList<CategoryId>();
			
			for(int i=0;i<restaurantForm.getCategories().size();i++){
				CategoryId cateId = new CategoryId();
				cateId.setCategory_id(cate.getCategory_id()-i);
				categoryId.add(cateId);
			}
			
			for(int i=0;i<categoryId.size();i++){
				System.out.println("getCategories_id" + categoryId.get(i).getCategory_id());
			}
			
			restType.inertBatchCatRest(categoryId , restaurantForm.getRest_id() );
			
			// 5. Get ID from table name restypes( Get from client ) 
			//    - > Insert only 3 Menus Id and Restaurants ID  ( table name : menus ) 
			menuRepository.inertBatchMenus(restaurantForm.getRestypes_id(), restaurantForm.getRest_id() );
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
		
		*/
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

	@Override
	public int countRestOwner() {
		
		return restaurantRepository.countRestOwner();
	}


	

}



