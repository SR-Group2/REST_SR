package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restpictures;
import org.khmeracademy.rest.repositories.RestPictureRepository;
import org.khmeracademy.rest.services.RestpictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestpictureServiceImpl implements RestpictureService {

	@Autowired
	private RestPictureRepository restpicRepository;
	@Override
	public ArrayList<Restpictures> getAllRestpicture() {
		return restpicRepository.getAllRestpicture();
	}

	@Override
	public boolean insertRestpicture(Restpictures restpicture) {
		return restpicRepository.insertRestpicture(restpicture);
	}

	@Override
	public boolean updateRestpicture(Restpictures restpicture) {
		return restpicRepository.updateRestpicture(restpicture);
	}

	@Override
	public boolean deleteRestpicture(int picture_id) {
		return restpicRepository.deleteRestpicture(picture_id);
	}

	@Override
	public ArrayList<Restpictures> findRestpictureById(int picture_id) {
		return restpicRepository.findRestpictureById(picture_id);
	}

	
}
