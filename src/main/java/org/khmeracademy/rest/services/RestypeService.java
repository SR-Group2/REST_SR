package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Restypes;
import org.springframework.stereotype.Service;

@Service
public interface RestypeService {
	public ArrayList<Restypes> getAllRestype();
	public boolean insertRestype(Restypes restype);
	public boolean deleteRestype(int restype_id);
	public boolean updateRestype(Restypes restype);
	public ArrayList<Restypes>  findRestypeById(int restype_id);
}
