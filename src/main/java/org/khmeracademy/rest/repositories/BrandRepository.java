package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Brands;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository {

	String R_BRAND = "SELECT"
			+" B.brand_id,"
			+" B.contact,"
			+" R.rest_id,"
			+" A.address_id,"
			+" A.street,"
			+" B.date_added,"
			+" B.date_modify"
			+" FROM brands B "
			+" INNER JOIN restaurants R"
			+" ON B.rest_id = R.rest_id"
			+" INNER JOIN addresses A"
			+" ON A.address_id = B.address_id";

	@Select(R_BRAND)
	@Results(value={
			@Result(property="rest.rest_id", column = "rest_id"),
			@Result(property = "address.address_id", column = "address_id")
	})
	public ArrayList<Brands> getAllBrand();
		
	String C_BRAND = "INSERT INTO brands (contact,rest_id,address_id)"
			+ "VALUES ( #{contact} , #{rest.rest_id} , #{address.address_id})";
	@Insert(C_BRAND)
	public boolean insertBrand(Brands brand);
	
	String D_BRAND = "DELETE"
			+ " FROM"
			+ " brands"
			+ " WHERE"
			+ " brand_id = #{brand_id}";
	@Delete(D_BRAND)
	public boolean deleteBrand(int brand_id);
	
	String U_BRAND = "UPDATE brands SET "
			+ "	contact=#{contact} , "
			+ "	rest_id=#{rest.rest_id} , "
			+ "	address_id=#{address.address_id} "
			+ "WHERE "
			+ "	brand_id=#{brand_id}";
	@Update(U_BRAND)
	public boolean updateBrand(Brands brand);
	
	
	String F_BRAND = "SELECT"
			+ " brand_id,"
			+ " contact,"
			+ " rest_id,"
			+ " address_id,"
			+ " date_added,"
			+ " date_modify"
			+ " FROM"
			+ " brands"
			+ " WHERE"
			+ " brand_id = #{brand_id}";
	@Select(F_BRAND)
	public ArrayList<Brands>  findBrandById(int brand_id);
}
