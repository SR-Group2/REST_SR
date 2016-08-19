
package org.khmeracademy.rest.repositories;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.FavouriteFoods;
import org.khmeracademy.rest.entities.FavouriteRestaurants;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteFoodRepository {
	
	
	/* =================  INSERT DATA   =================== */
	String C_FFOOD = "INSERT INTO favouritefoods (user_id, food_id)"
			+ "VALUES(#{user.user_id}, #{food.food_id})";
	@Insert(C_FFOOD)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="food.food_id", column="food_id")
	})
	public boolean insertFavouriteFood(FavouriteFoods favouriteFoods);
	
	/* =================  GET DATA   =================== */
	String R_FFOOD = "SELECT fav_id, user_id, food_id FROM favouritefoods";
	@Select(R_FFOOD)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="food.food_id", column="food_id")
	})
	public ArrayList<FavouriteFoods> getFavouriteFoods();
	
	/* =================  GET DATA  BY ID =================== */
	String F_FFOOD = "SELECT fav_id, user_id, food_id FROM favouritefoods WHERE fav_id = #{fav_id}";
	@Select(F_FFOOD)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="food.food_id", column="food_id")
	})
	public ArrayList<FavouriteFoods> findFavouriteFoodById(@Param("fav_id") int fav_id);
	
	/* =================  UPDATE DATA   =================== */
	String U_FFOOD = "UPDATE favouritefoods set user_id=#{user.user_id},"
			+ "food_id=#{food.food_id} WHERE fav_id = #{fav_id}";
	@Update(U_FFOOD)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="food.food_id", column="food_id")
	})
	public boolean updateFavouriteFood(FavouriteFoods favouriteFoods);
	
	/* =================  DELETE DATA   =================== */
	String D_FFOOD = "DELETE FROM favouritefoods WHERE fav_id = #{fav_id}";
	@Delete(D_FFOOD)
	public boolean deleteFavouriteFood(@Param("fav_id") int fav_id);
	
}

