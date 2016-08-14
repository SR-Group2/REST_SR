package org.khmeracademy.rest.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.khmeracademy.rest.entities.Image;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@RestController
public class RestUploadController {

	@RequestMapping(value = "/api/upload", method = RequestMethod.POST)
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
}
