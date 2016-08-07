package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.repositories.RestypeRepository;
import org.khmeracademy.rest.services.RestypeService;
import org.khmeracademy.rest.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestypeServiceImpl implements RestypeService {

	@Autowired
	private RestypeRepository restypeRepository;
	@Override
	public ArrayList<Restypes> getAllRestype(Pagination pagination) {
		return restypeRepository.getAllRestype(pagination);
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

	@Override
	public int countRestype() {
		return restypeRepository.countRestype();
	}

	@Override
	public ArrayList<Restypes> findRestypeByKeyword(String keyword) {
		return restypeRepository.findRestypeByKeyword(keyword);
	}
	
}
