package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Brands;
import org.khmeracademy.rest.repositories.BrandRepository;
import org.khmeracademy.rest.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository brandRepositoy;
	
	@Override
	public ArrayList<Brands> getAllBrand() {
		return brandRepositoy.getAllBrand();
	}

	@Override
	public boolean insertBrand(Brands brand) {
		return brandRepositoy.insertBrand(brand);
	}

	@Override
	public boolean deleteBrand(int brand_id) {
		return brandRepositoy.deleteBrand(brand_id);
	}

	@Override
	public boolean updateBrand(Brands brand) {
		return brandRepositoy.updateBrand(brand);
	}

	@Override
	public Brands findBrandById(int brand_id) {
		return brandRepositoy.findBrandById(brand_id);
	}

}
