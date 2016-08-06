package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.repositories.RestypeRepository;
import org.khmeracademy.rest.services.RestypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

@Service
public class RestypeServiceImpl implements RestypeService {

	@Autowired
	private RestypeRepository restypeRepository;
	@Override
	public ArrayList<Restypes> getAllRestype() {
		return restypeRepository.getAllRestype();
	}

	@Override
	public boolean insertRestype(Restypes restype) {
		return restypeRepository.insertRestype(restype);
	}

	@Override
	public boolean deleteRestype(int restype_id) {
		return restypeRepository.deleteRestype(restype_id);
	}

	@Override
	public boolean updateRestype(Restypes restype) {
		return restypeRepository.updateRestype(restype);
	}

	@Override
	public ArrayList<Restypes> findRestypeById(int restype_id) {
		return restypeRepository.findRestypeById(restype_id);
	}
	
}
