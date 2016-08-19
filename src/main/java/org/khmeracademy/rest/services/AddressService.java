package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Addresses;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

	public ArrayList<Addresses> getAllAddress();
	
	public boolean insertAddress(Addresses address);
	
	public boolean deleteAddress(int address_id);
	
	public boolean updateAddress(Addresses address);
	
	public ArrayList<Addresses> findAddressById(int address_id);
}
