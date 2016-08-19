package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.filters.RestypeFilter;
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
	public ArrayList<Restypes> getAllRestype(Pagination pagination, RestypeFilter filter) {
		System.out.println(pagination.getOffset() + " " + pagination.getLimit());
		System.out.println(filter.getKeyword()) ;
		return restypeRepository.getAllRestype("%" + filter.getKeyword().toLowerCase() + "%", 
				pagination.getLimit(), pagination.getOffset());
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
	public ArrayList<Restaurants> findRestypeById(int restype_id, Pagination pagination) {
		
		return restypeRepository.findRestypeById(restype_id, pagination.getLimit(), pagination.offset());
		
	}

	@Override
	public int countRestype(String keyword) {
		return restypeRepository.countRestype(keyword);
	}

	@Override
	public ArrayList<Restypes> findRestypeByKeyword(String keyword) {
		return restypeRepository.findRestypeByKeyword(keyword);
	}

	@Override
	public int countRest(int restype_id) {
		
		return restypeRepository.countRest(restype_id);
	}
	
}
