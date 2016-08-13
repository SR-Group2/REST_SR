package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.Restypes;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {

	String R_RESTAURANT = "SELECT"
			+ " R.rest_id,"
			+ " R.rest_name,"
			+ " R.contact,"
			+ " R.about,"
			+ " R.open_close,"
			+ " R.location,"
			+ " M.restype_id AS menu_restype_id,"
			+ " A.address_id AS address_address_id, "
			+ " A.street, "
			+ " A.district, "
			+ " A.communce, "
			+ " A.province, "
			+ " U.user_id,"
			+ " U.first_name, "
			+ " U.last_name,"
			+ " U.username,"
			+ " U.email,"
			+ " U.password,"
			+ " U.salt, "
			+ " U.dob, "
			+ " U.joined, "
			+ " U.picture,"
			+ " RT.restype_id,"
			+ " RT.restype_name,"
			+ " RT.parentid_restypeid,"
			+ " RT.restype_name_kh"
			+ " FROM"
			+ " restaurants R"
			+ " INNER JOIN"
			+ " menus M"
			+ " ON M.rest_id = R.rest_id "
			+ " INNER JOIN restypes RT ON RT.restype_id = M.restype_id"
			+ " INNER JOIN Addresses A"
			+ " ON R.address_id = A.address_id"
			+ " INNER JOIN users U"
			+ " ON R.user_id = U.user_id";
	@Select(R_RESTAURANT)
	@Results(value={
			@Result(property = "restypes.restype_id", column = "restype_restype_id"),
			@Result(property = "address.address_id", column = "address_address_id"),
			@Result(property = "address.user_id", column = "user_user_id"),
			@Result(property = "restypes.restype_id", column = "restype_id"),
			@Result(property = "restypes.restype_name", column = "restype_name"),
			@Result(property = "address.street", column = "street"),
			@Result(property = "restypes.parentid_restypeid", column = "parentid_restypeid"),
			@Result(property = "restypes.restype_name_kh", column = "restype_name_kh"),
			@Result(property = "address.district", column = "district"),
			@Result(property = "address.communce", column = "communce"),
			@Result(property = "address.province", column = "province"),
			@Result(property = "user.user_id", column = "user_id"),
			@Result(property = "user.first_name", column = "first_name"),
			@Result(property = "user.last_name", column = "last_name"),
			@Result(property = "user.email", column = "email"),
			@Result(property = "user.salt", column = "salt"),
			@Result(property = "user.dob", column = "dob"),
			@Result(property = "user.joined", column = "joined"),
			@Result(property = "user.picture", column = "picture"),
			@Result(property = "user.username", column = "username"),
			@Result(property = "user.password", column = "password")
	})
	public ArrayList<Restaurants> getAllRestaurant();
	
	String C_RESTAURANT = "INSERT INTO restaurants(rest_name,contact,about,open_close,location,restype_id,address_id,user_id)"
			+ " VALUES(#{rest_name} , #{contact} , #{about},#{open_close},#{location},#{restypes.restype_id},#{address.address_id},#{user.user_id})";
	@Insert(C_RESTAURANT)
	public boolean insertRestaurant(Restaurants restaurant);
	
	String D_RESTAURANT = "DELETE"
			+ " FROM"
			+ " restaurants"
			+ " WHERE"
			+ " rest_id = #{rest_id}";
	@Delete(D_RESTAURANT)
	public boolean deleteRestaurant(int rest_id);
	
	String U_RESTAURANT = "UPDATE restaurants SET "
			+ "	rest_name=#{rest_name} , "
			+ "	contact=#{contact} , "
			+ "	about=#{about},"
			+ " open_close = #{open_close},"
			+ " location = #{location},"
			+ " restype_id = #{restypes.restype_id},"
			+ " address_id = #{address.address_id},"
			+ " user_id = #{user.user_id} "
			+ "WHERE "
			+ "	rest_id = #{rest_id}";
	@Update(U_RESTAURANT)
	public boolean updateRestaurant(Restaurants restaurant);
	
	String F_RESTAURANT = "SELECT DISTINCT"
	+ " R.rest_id,"
	+ " R.rest_name,"
	+ " R.contact,"
	+ " R.about,"
	+ " R.open_close,"
	+ " R.location,"
	+ " A.address_id AS address_address_id,"
	+ " U.user_id AS user_user_id,"
	+ " U.username AS user_username"
	+ " FROM"
	+ " restaurants R"
	+ " INNER JOIN menus M ON R.rest_id = M.rest_id"
	+ " INNER JOIN restypes rs ON rs.restype_id = M.restype_id"
	+ " INNER JOIN Addresses A ON R.address_id = A.address_id"
	+ " INNER JOIN users U ON R.user_id = U.user_id"
	+ " WHERE R.rest_id = #{rest_id} ";
	@Select(F_RESTAURANT)
	@Results(value={
			@Result(property = "address.address_id", column = "address_address_id"),
			@Result(property = "user.user_id", column = "user_user_id"),
			@Result(property = "user.username", column = "user_username"),
			@Result(property = "menus.restype_id", column = "user_user_id")
	})
	public Restaurants  findRestaurantById(int rest_id);
	
	
}
