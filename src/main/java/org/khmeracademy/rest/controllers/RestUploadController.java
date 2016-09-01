package org.khmeracademy.rest.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.khmeracademy.rest.entities.Image;
import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.entities.Users;
import org.khmeracademy.rest.entities.Users.Users2;
import org.khmeracademy.rest.form.RestaurantForm2;
import org.khmeracademy.rest.form.RestaurantForm2.RestaurantUpdateForm2;
import org.khmeracademy.rest.repositories.UserRepository;
import org.khmeracademy.rest.services.RestaurantService;
import org.khmeracademy.rest.services.RestypeService;
import org.khmeracademy.rest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@RestController
@RequestMapping("/api/upload")
public class RestUploadController {
	
	@Autowired
	private RestaurantService restaurantService;
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestypeService restypeService;
	
	//================== Upload By RestController ==============
	@RequestMapping(value="/image", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> uploading(@RequestParam("file") CommonsMultipartFile file,
			HttpServletRequest request) {
		//Image image = new Image();
		System.out.println("FILE=" + file.getOriginalFilename());
		
		String filename = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (!file.isEmpty()) {
			
			String originalSavePath = "/resources/images/";
			String thumbnailSavePath = "/resources/images/thumbnails/";
			try {
				filename = file.getOriginalFilename();
				
				byte[] bytes = file.getBytes();
				
				UUID uuid = UUID.randomUUID();
				
				String randomUUIDFileName = uuid.toString();
				
				String extension = filename.substring(filename.lastIndexOf(".") + 1);
				// A.jpeg
				
				System.out.println(originalSavePath);
				
				File originalPath = new File(originalSavePath);
				
				if (!originalPath.exists()) {
					System.out.println("NOT EXISTS");
					originalPath.mkdir ();
				}
				
				File thumbnailPath = new File(thumbnailSavePath);
				
				if (!thumbnailPath.exists()) {
					System.out.println("NOT EXISTS");
					thumbnailPath.mkdirs();
				}
				
				filename = randomUUIDFileName + "." + extension;
				
				// "/opt/images/" + 123e4567-e89b-12d3-a456-426655440000.jpeg
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(originalSavePath + filename)));
				stream.write(bytes);
				stream.close();

				try {
					//TODO: USING THUMBNAILS TO RESIZE THE IMAGE
					Thumbnails.of(originalSavePath + filename)
						.forceSize(24, 24)
						.toFiles(originalPath, Rename.SUFFIX_HYPHEN_THUMBNAIL);					
					
				} catch (Exception ex) {
					stream = new BufferedOutputStream(new FileOutputStream(new File(originalPath +  filename)));
					stream.write(bytes);
					stream.close();
				}
				//image.setOriginalImage("http://localhost:9999" + "/files/images/" + filename);
				//image.setThumbnailImage("http://localhost:9999" + "/files/images/" + randomUUIDFileName + "-thumbnail" + "." + extension);
				System.out.println("You successfully uploaded " + originalSavePath + filename + "!");
				//return image;
				map.put("DATA", "http://localhost:9999" + "/files/images/" + filename);
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
				
			} catch (Exception e) {
				System.out.println("You failed to upload " + filename + " => " + e.getMessage());
				map.put("IMAGE_URL", originalSavePath + File.separator + filename);
				//return image;
				map.put("MESSAGE", "ERROR");
				map.put("STATUS", false);
			}
		} else {
			System.out.println("You failed to upload " + filename + " because the file was empty.");
			//return image;
			map.put("MESSAGE", "UNSUCCESS");
			map.put("STATUS", true);
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
	}
	
	//================== Upload Directly ==================

	@RequestMapping(method = RequestMethod.POST)
	public Image upload(@RequestParam("file") CommonsMultipartFile file) throws IOException {
		
		
		String originalSavePath = "/resources/images/";
		String thumbnailSavePath = "/resources/images/thumbnails/";
		
		String filename = file.getOriginalFilename();

		byte[] bytes = file.getBytes();

		//UNIQUE UNIVERSAL ID
		UUID uuid = UUID.randomUUID();

		String randomUUIDFileName = uuid.toString();

		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		// A.jpeg

		System.out.println(originalSavePath);

		File originalPath = new File(originalSavePath);

		if (!originalPath.exists()) {
			System.out.println("NOT EXISTS");
			originalPath.mkdirs();
		}

		File thumbnailPath = new File(thumbnailSavePath);

		if (!thumbnailPath.exists()) {
			System.out.println("NOT EXISTS");
			thumbnailPath.mkdirs();
		}

		filename = randomUUIDFileName + "." + extension;

		// "/opt/images/" + 123e4567-e89b-12d3-a456-426655440000.jpeg
		BufferedOutputStream stream = new BufferedOutputStream(
				new FileOutputStream(new File(originalSavePath + filename)));
		stream.write(bytes);
		stream.close();
		
		Thumbnails.of(originalSavePath + filename)
	    .size(240, 240)
	    .toFiles(thumbnailPath, Rename.NO_CHANGE);
		
		Image image = new Image();
		image.setOriginalImage("http://192.168.178.155:9999/images/"+ filename);
		image.setThumbnailImage("http://192.168.178.155:9999/images/thumbnails/"+ filename);
		return image;
	}
	
	@RequestMapping(value = "/api/upload/many", method = RequestMethod.POST)
	public List<Image> upload(@RequestParam("file") List<CommonsMultipartFile> files) throws IOException {
		
		
		String originalSavePath = "/resources/images/";
		String thumbnailSavePath = "/resources/images/thumbnails/";
		
		List<Image> images = new ArrayList<>();
		for(CommonsMultipartFile file : files){
			String filename = file.getOriginalFilename();
	
			byte[] bytes = file.getBytes();
	
			//UNIQUE UNIVERSAL ID
			UUID uuid = UUID.randomUUID();
	
			String randomUUIDFileName = uuid.toString();
	
			String extension = filename.substring(filename.lastIndexOf(".") + 1);
			// A.jpeg
	
			System.out.println(originalSavePath);
	
			File originalPath = new File(originalSavePath);
	
			if (!originalPath.exists()) {
				System.out.println("NOT EXISTS");
				originalPath.mkdirs();
			}
	
			File thumbnailPath = new File(thumbnailSavePath);
	
			if (!thumbnailPath.exists()) {
				System.out.println("NOT EXISTS");
				thumbnailPath.mkdirs();
			}
	
			filename = randomUUIDFileName + "." + extension;
	
			// "/opt/images/" + 123e4567-e89b-12d3-a456-426655440000.jpeg
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File(originalSavePath + filename)));
			stream.write(bytes);
			stream.close();
			
			Thumbnails.of(originalSavePath + filename)
		    .size(240, 240)
		    .toFiles(thumbnailPath, Rename.NO_CHANGE);
			
			Image image = new Image();
			image.setOriginalImage("http://192.168.178.155:9999/images/"+ filename);
			image.setThumbnailImage("http://192.168.178.155:9999/images/thumbnails/"+ filename);
			
			images.add(image);
			
		}
		
		return images;
	}
	
	@RequestMapping(value="/test", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> uploadTest(
			@RequestParam(value="json_data") String jsonData,
			@RequestParam(value="menu") List<MultipartFile> menu_files,
			@RequestParam(value="restaurant") List<MultipartFile> restaurant_files,
			HttpServletRequest request) {
		
		RestaurantForm2 form = new Gson().fromJson(jsonData, RestaurantForm2.class);
		form.setMenu_files(menu_files);
		form.setRestaurant_files(restaurant_files);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try{
			if (restaurantService.addNewRestaurant(form)){
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 
	}
	//======================= upload json data with many file ===============================
	@RequestMapping(value="/test/update", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> updateImage(
			@RequestParam(value="json_data") String jsonData,
			@RequestParam(value="menu_picture") List<MultipartFile> menu_picture,
			@RequestParam(value="delete_menu_picture") List<String> delete_menu_picture,
			@RequestParam(value="addRestFile") List<MultipartFile> addRestFile,
			@RequestParam(value="deletedImageRest") List<String> deletedImageRest,
			HttpServletRequest request) {
		
		System.out.println(jsonData);
		System.out.println("Menu = " + deletedImageRest.size());
		System.out.println("Rest = " + delete_menu_picture.size());
	
		System.out.println("delete_menu_picture =======>"+delete_menu_picture);
		
		System.out.println("menu_picture =======>" + menu_picture);
		
		RestaurantUpdateForm2 restUpdateForm2 = new Gson().fromJson(jsonData, RestaurantUpdateForm2.class);
		
		restUpdateForm2.setDeletedMenuImageUrl(delete_menu_picture);
		
		restUpdateForm2.setMenu_files(menu_picture);
		
		restUpdateForm2.setRestaurant_files(addRestFile);
		
		restUpdateForm2.setDeletedRestaurantImageUrl(deletedImageRest);
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if (restaurantService.updateRestaurant(restUpdateForm2)){
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 
		

	}
	
	//======================= upload User File and  data with  ===============================
		@RequestMapping(value="/user", method = RequestMethod.POST)
		public ResponseEntity<Map<String,Object>> uploadUser(
				@RequestParam(value="json_data") String jsonData,
				@RequestParam(value="picture") List<MultipartFile> picture,
				HttpServletRequest request) {
			
			System.out.println(jsonData);
			System.out.println("User File = " + picture.size());
		
			
			Users2 user2 = new Gson().fromJson(jsonData, Users2.class);
			
			user2.setUser_file(picture);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				if (userService.addUser(user2)){
					map.put("MESSAGE", "SUCCESS");
					map.put("STATUS", true);
				}else{
					map.put("MESSAGE", "UNSUCCESS");
					map.put("STATUS", true);
				}		
			} catch (Exception e) {
				e.printStackTrace();
				map.put("MESSAGE", "ERROR");
				map.put("STATUS", false);
			}
			
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 

		}
		
		//======================= Sign Up User File and  data with  ===============================
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> singUpUser(
			@RequestParam(value="json_data") String jsonData,
			@RequestParam(value="picture") List<MultipartFile> picture,
			HttpServletRequest request) {
		
		System.out.println(jsonData);
		System.out.println("User File = " + picture.size());
	
		
		Users2 user2 = new Gson().fromJson(jsonData, Users2.class);
		
		user2.setUser_file(picture);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if (userService.signUpUser(user2)){
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 

	}
	
	//======================= Update User File and  data with  ===============================
	@RequestMapping(value="/user/update", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> updateUser(
			@RequestParam(value="json_data") String jsonData,
			@RequestParam(value="picture") List<MultipartFile> picture,
			HttpServletRequest request) {
		
		System.out.println(jsonData);
		System.out.println("User File = " + picture.size());
	
		
		Users2 user2 = new Gson().fromJson(jsonData, Users2.class);
		
		user2.setUser_file(picture);
		
	
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if (userService.updateUser2(user2)){
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 

	}
	//======================= INSERT RESTYPE  ===============================
	@RequestMapping(value="/restype/add", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> insertRestype(
			@RequestParam(value="json_data") String jsonData,
			@RequestParam(value="picture") List<MultipartFile> picture,
			HttpServletRequest request) {
		
		System.out.println(jsonData);
		System.out.println("Restype File = " + picture.size());
	
		
		Restypes restype = new Gson().fromJson(jsonData, Restypes.class);
		
		restype.setRestype_files(picture);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			if (restypeService.insertRestype(restype)){
				map.put("MESSAGE", "SUCCESS");
				map.put("STATUS", true);
			}else{
				map.put("MESSAGE", "UNSUCCESS");
				map.put("STATUS", true);
			}		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("MESSAGE", "ERROR");
			map.put("STATUS", false);
		}
		
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 

	}
	
	//======================= UPDATE RESTYPE  ===============================
		@RequestMapping(value="/restype/update", method = RequestMethod.POST)
		public ResponseEntity<Map<String,Object>> updateRestype(
				@RequestParam(value="json_data") String jsonData,
				@RequestParam(value="picture") List<MultipartFile> picture,
				HttpServletRequest request) {
			
			System.out.println(jsonData);
			System.out.println("Restype File = " + picture.size());
		
			
			Restypes restype = new Gson().fromJson(jsonData, Restypes.class);
			
			restype.setRestype_files(picture);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			try{
				if (restypeService.updateRestype(restype)){
					map.put("MESSAGE", "SUCCESS");
					map.put("STATUS", true);
				}else{
					map.put("MESSAGE", "UNSUCCESS");
					map.put("STATUS", true);
				}		
			} catch (Exception e) {
				e.printStackTrace();
				map.put("MESSAGE", "ERROR");
				map.put("STATUS", false);
			}
			
			return new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK); 

		}
	
	
}

