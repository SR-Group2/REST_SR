package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Restaurants;
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
			+ " RT.restype_id,"
			+ " A.address_id,"
			+ " U.user_id"
			+ " FROM"
			+ " restaurants R"
			+ " INNER JOIN"
			+ " restypes RT"
			+ " ON R.restype_id = RT.restype_id"
			+ " INNER JOIN Addresses A"
			+ " ON R.address_id = A.address_id"
			+ " INNER JOIN users U"
			+ " ON R.user_id = U.user_id";
	@Select(R_RESTAURANT)
	@Results(value={
			@Result(property = "restype.restype_id", column = "restype_id"),
			@Result(property = "address.address_id", column = "address_id"),
			@Result(property = "user.user_id", column = "user_id")
	})
	public ArrayList<Restaurants> getAllRestaurant();
	
	String C_RESTAURANT = "INSERT INTO restaurants(rest_name,contact,about,open_close,location,restype_id,address_id,user_id)"
			+ " VALUES(#{rest_name} , #{contact} , #{about},#{open_close},#{location},#{restype.restype_id},#{address.address_id},#{user.user_id})";
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
			+ " restype_id = #{restype.restype_id},"
			+ " address_id = #{address.address_id},"
			+ " user_id = #{user.user_id} "
			+ "WHERE "
			+ "	rest_id = #{rest_id}";
	@Update(U_RESTAURANT)
	public boolean updateRestaurant(Restaurants restaurant);
	
	String F_RESTAURANT = "SELECT"
			+ " rest_id,"
			+ " rest_name,"
			+ " contact,"
			+ " about,"
			+ " open_close,"
			+ " location"
			+ " FROM"
			+ " restaurants "
			+ " WHERE rest_id = #{rest_id}";
	@Select(F_RESTAURANT)
	public ArrayList<Restaurants>  findRestaurantById(int rest_id);
}
