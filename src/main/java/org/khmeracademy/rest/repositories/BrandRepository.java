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
			+" B.date_added,"
			+" B.date_modify,"
			+" R.rest_id,"
			+" R.rest_name,"
			+" R.contact,"
			+" R.about,"
			+" R.open_close,"
			+" R.location,"
			+" A.address_id,"
			+" A.street,"
			+" A.district,"
			+" A.communce,"
			+" A.province"
			+" FROM brands B "
			+" INNER JOIN restaurants R"
			+" ON B.rest_id = R.rest_id"
			+" INNER JOIN addresses A"
			+" ON A.address_id = B.address_id";
	@Select(R_BRAND)
	@Results(value={
			@Result(property="rest.rest_id", column = "rest_id"),
			@Result(property="rest.rest_name", column = "rest_name"),
			@Result(property="rest.contact", column = "contact"),
			@Result(property="rest.about", column = "about"),
			@Result(property="rest.open_close", column = "open_close"),
			@Result(property="rest.location", column = "location"),
			@Result(property = "address.address_id", column = "address_id"),
			@Result(property = "address.street", column = "street"),
			@Result(property = "address.district", column = "district"),
			@Result(property = "address.communce", column = "communce"),
			@Result(property = "address.province", column = "province")
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
			+ "	address_id=#{address.address_id},"
			+ " date_modify=CURRENT_TIMESTAMP "
			+ "WHERE "
			+ "	brand_id=#{brand_id}";
	@Update(U_BRAND)
	public boolean updateBrand(Brands brand);
	
	
	String F_BRAND = "SELECT"
			+" B.brand_id,"
			+" B.contact,"
			+" B.date_added,"
			+" B.date_modify,"
			+" R.rest_id,"
			+" R.rest_name,"
			+" R.contact,"
			+" R.about,"
			+" R.open_close,"
			+" R.location,"
			+" A.address_id,"
			+" A.street,"
			+" A.district,"
			+" A.communce,"
			+" A.province"
			+" FROM brands B "
			+" INNER JOIN restaurants R"
			+" ON B.rest_id = R.rest_id"
			+" INNER JOIN addresses A"
			+" ON A.address_id = B.address_id"
			+" WHERE"
			+" B.brand_id = #{brand_id}";
	@Select(F_BRAND)
	@Results(value={
			@Result(property="rest.rest_id", column = "rest_id"),
			@Result(property="rest.rest_name", column = "rest_name"),
			@Result(property="rest.contact", column = "contact"),
			@Result(property="rest.about", column = "about"),
			@Result(property="rest.open_close", column = "open_close"),
			@Result(property="rest.location", column = "location"),
			@Result(property = "address.address_id", column = "address_id"),
			@Result(property = "address.street", column = "street"),
			@Result(property = "address.district", column = "district"),
			@Result(property = "address.communce", column = "communce"),
			@Result(property = "address.province", column = "province")
	})
	public ArrayList<Brands>  findBrandById(int brand_id);
}
