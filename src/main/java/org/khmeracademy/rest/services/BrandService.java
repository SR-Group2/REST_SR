package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Brands;
import org.springframework.stereotype.Service;
@Service
public interface BrandService {

	public ArrayList<Brands> getAllBrand();
	public boolean insertBrand(Brands brand);
	public boolean deleteBrand(int brand_id);
	public boolean updateBrand(Brands brand);
	public Brands findBrandById(int brand_id);
}
