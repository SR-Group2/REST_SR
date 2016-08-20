package org.khmeracademy.rest.services.impl;


import java.util.ArrayList;
import java.util.List;

import org.khmeracademy.rest.entities.Addresses;
import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.UploadedFileInfo;
import org.khmeracademy.rest.filters.RestypeFilter;
import org.khmeracademy.rest.form.RestaurantForm2;
import org.khmeracademy.rest.form.RestaurantForm2.RestaurantUpdateForm2;
import org.khmeracademy.rest.repositories.AddressRepository;
import org.khmeracademy.rest.repositories.CategoryRepository;
import org.khmeracademy.rest.repositories.RestPictureRepository;
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
	private RestPictureRepository restPictureRepository; 
	
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

	/*@Override
	public boolean updateRestaurant(RestaurantUpdateForm2 restaurantUpdateForm2) {
		return restaurantRepository.updateRestaurant(restaurantUpdateForm2);
	}*/

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
			
			UploadedFileInfo menuPath = fileUploadService.upload(restaurantForm.getMenu_files(), "menu");
			UploadedFileInfo restaurantPath = fileUploadService.upload(restaurantForm.getRestaurant_files(), "restaurant");
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
			//Categories cate = new Categories();
			categoryRepository.inertBatchCategories(menuPath.getNames() , restaurantForm.getRest_id());
			
			
			//Restpictures restpicture = new Restpictures();
			restPictureRepository.inertBatchRestpicture(restaurantPath.getNames(), restaurantForm.getRest_id());
			
			
			//4. Insert Rest Type ID
			restType.insertBatchRestypeId(restaurantForm.getRestypes_id(), restaurantForm.getRest_id());
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

	@Override
	public int countRestOwner() {
		
		return restaurantRepository.countRestOwner();
	}
	
	//=============================== update image restaurant ============================
	@Override
	@Transactional
	public boolean updateRestaurant(RestaurantUpdateForm2 restaurantUpdateForm2){
		
		// ============== Beloved Teacher Pirang =======================
		try{
			
			UploadedFileInfo menu_urls = fileUploadService.upload(restaurantUpdateForm2.getMenu_files(), "menu");
			List<String> deletemenuPath = restaurantUpdateForm2.getDeletedMenuImageUrl();
			System.out.println(deletemenuPath);
			// fileInfo.getNames() : List of FIle Path
			//1. Upload File
			//2. Get Url
			//3. Insert Restaurant -> Return ID
			//4. Insert Menu
			//==================
			
			// 1. Update address -> return address id (table name : addresses)
			//Addresses address = restaurantUpdateForm2.getAddress();
			//addressRepository.insertAddress(address);
			
			//System.out.println("ADDRESS_ID ==> " + address.getAddress_id());
			
			// 2. Update Restaurant -> return rest_id (table name : restaurants)
			//restaurantUpdateForm2.setAddress(address);
			restaurantRepository.updateRestaurant(restaurantUpdateForm2);
			
			System.out.println("REST ID ======= > " + restaurantUpdateForm2.getRest_id());
			
			//3. Update Many Categories -> return category ID (table name : categories)
		
			
			//	categoryRepository.inertBatchCategories(menu_urls.getNames(), restaurantUpdateForm2.getRest_id());
				
			
				//categoryRepository.deleteBatchCategories(deletemenuPath, restaurantUpdateForm2.getRest_id());
			System.out.println(deletemenuPath);
			fileUploadService.delete(deletemenuPath, "/resources/NhamEy/upload/menu");
			
			
			//Restpictures restpicture = new Restpictures();
		//	restPictureRepository.inertBatchRestpicture(restaurantPath.getNames(), restaurantUpdateForm2.getRest_id());
			
			
			//4. Insert Rest Type ID
			//restType.insertBatchRestypeId(restaurantUpdateForm2.getRestypes_id(), restaurantUpdateForm2.getRest_id());
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;

	}

	

}



