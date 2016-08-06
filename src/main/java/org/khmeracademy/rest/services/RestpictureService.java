package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restpictures;
import org.springframework.stereotype.Service;

@Service
public interface RestpictureService {

	public ArrayList<Restpictures> getAllRestpicture();
	public boolean insertRestpicture(Restpictures restpicture);
	public boolean deleteRestpicture(int picture_id);
	public boolean updateRestpicture(Restpictures restpicture);
	public ArrayList<Restpictures>  findRestpictureById(int picture_id);
}
