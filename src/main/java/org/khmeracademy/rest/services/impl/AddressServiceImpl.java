package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Addresses;
import org.khmeracademy.rest.repositories.AddressRepository;
import org.khmeracademy.rest.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public ArrayList<Addresses> getAllAddress() {
		return addressRepository.getAllAddress();
	}

	@Override
	public boolean insertAddress(Addresses address) {
		return addressRepository.insertAddress(address);
	}

	@Override
	public boolean deleteAddress(int address_id) {
		return addressRepository.deleteAddress(address_id);
	}

	@Override
	public boolean updateAddress(Addresses address) {
		return addressRepository.updateAddress(address);
	}

	@Override
	public ArrayList<Addresses> findAddressById(int address_id) {
		return addressRepository.findAddressById(address_id);
	}
	
}
