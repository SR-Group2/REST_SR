package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.utils.Pagination;
import org.springframework.stereotype.Service;

@Service
public interface RestypeService {

	public int countRestype();
	
	public ArrayList<Restypes> getAllRestype(Pagination pagination);
	
	public boolean insertRestype(Restypes restype);
	
	public boolean deleteRestype(int restype_id);
	
	public boolean updateRestype(Restypes restype);
	
	public ArrayList<Restypes>  findRestypeById(int restype_id);
	
	public ArrayList<Restypes> findRestypeByKeyword(String keyword);
}
