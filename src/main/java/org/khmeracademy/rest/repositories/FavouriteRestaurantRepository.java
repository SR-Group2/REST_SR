package org.khmeracademy.rest.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.FavouriteRestaurants;
import org.khmeracademy.rest.entities.Restpictures;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRestaurantRepository {
	/* =================  INSERT DATA   =================== */
	String C_FREST = "INSERT INTO favouriterestaurants (user_id, rest_id)"
			+ "VALUES(#{user.user_id}, #{rest.rest_id})";
	@Insert(C_FREST)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="rest.rest_id", column="rest_id")
	})
	public boolean insertFavouriteRestaurant(FavouriteRestaurants favouriteRestaurant);
	
	/* =================  GET DATA   =================== */
	String R_FREST = "SELECT F.favrest_id,"
			+ " R.rest_name,"
			+ " R.rest_name_kh, "
			+ " U.user_id,"
			+ " U.first_name,"
			+ " U.last_name"
			+ " From favouriterestaurants F"
			+ " INNER JOIN restaurants R"
			+ " ON F.rest_id=R.rest_id"
			+ " INNER JOIN users U "
			+ " ON U.user_id = F.user_id;"
			+ " SELECT COUNT(F.rest_id) As total,"
			+ " U.user_id"
			+ " From favouriterestaurants F"
			+ " INNER JOIN users U"
			+ " ON F.user_id = U.user_id"
			+ " GROUP BY U.user_id";
	@Select(R_FREST)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="user.first_name", column="first_name"),
		@Result(property="user.last_name", column="last_name"),
		@Result(property="rest.rest_id", column="rest_id"),
		@Result(property="rest.rest_name", column="rest_name"),
		@Result(property="fav_total", column="total")
		
		
		
	})
	public ArrayList<FavouriteRestaurants> getFavouriteRestaurant();
	
	/* =================  GET DATA  BY ID =================== */
	String F_FREST = "SELECT favrest_id, user_id, rest_id FROM favouriterestaurants WHERE favrest_id = #{favrest_id}";
	@Select(F_FREST)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="rest.rest_id", column="rest_id")
	})
	public FavouriteRestaurants findFavouriteRestaurantById(@Param("favrest_id") int favrest_id);
	
	/* =================  UPDATE DATA   =================== */
	String U_FREST = "UPDATE favouriterestaurants set user_id=#{user.user_id},"
			+ "rest_id=#{rest.rest_id} WHERE favrest_id = #{favrest_id}";
	@Update(U_FREST)
	@Results(value={
		@Result(property="user.user_id", column="user_id"),
		@Result(property="rest.rest_id", column="rest_id")
	})
	public boolean updateFavouriteRestaurant(FavouriteRestaurants favouriteRestaurant);
	
	/* =================  DELETE DATA   =================== */
	String D_FREST = "DELETE FROM favouriterestaurants WHERE favrest_id = #{favrest_id}";
	@Delete(D_FREST)
	public boolean deleteFavouriteRestaurant(@Param("favrest_id") int favrest_id);
	
	/*================== GET DATA BY USER ID======================================*/
	/*String G_FREST="SELECT"
			+ " R.rest_name,"
			+ " R.rest_name_kh, "
			+ " U.user_id,"
			+ " U.first_name,"
			+ " U.last_name"
			+ " From favouriterestaurants F"
			+ " INNER JOIN restaurants R"
			+ " ON F.rest_id=R.rest_id"
			+ " INNER JOIN users U "
			+ " ON U.user_id = F.user_id"
			+ " WHERE U.user_id=#{user_id};"
			+ " SELECT COUNT(F.rest_id) As total"
			+ " From favouriterestaurants F "
			+ " Where F.user_id=#{user_id}";
	
	@Select(G_FREST)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="rest.rest_id", column="rest_id"),
			@Result(property="rest.rest_name", column="rest_name"),
			@Result(property="fav_total", column="total")	
		})*/

	/*public ArrayList<FavouriteRestaurants> getFavouriteRestaurantByUserId(@Param("user_id") int user_id);*/
	
	String G_FREST="SELECT"
		+ " R.rest_id,"
		+ " F.favrest_id,"
		+ " R.rest_name,"	
		+ " R.rest_name_kh, "
		+ " U.user_id,"
		+ " U.first_name,"
		+ " U.last_name,"
		+ " U.dob,"
		+ " U.gender"
		+ " From favouriterestaurants F"
		+ " INNER JOIN restaurants R"
		+ " ON F.rest_id=R.rest_id"
		+ " INNER JOIN users U "
		+ " ON U.user_id = F.user_id"
		+ " WHERE U.user_id=#{user_id}";
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.gender", column="gender"),
			@Result(property="rest.rest_id", column="rest_id"),
			@Result(property="rest.rest_name", column="rest_name"),
			// fav_total got data from totalFavourite 
			// column user_id send parameter to totalFavourite(int user_id);
			@Result(property ="fav_total", column="user_id", many=@Many(select="totalFavourite")),
			@Result(property = "restpictures", javaType=List.class, column="rest_id", many=@Many(select="findRestyPicture")),
			
	})
	@Select(G_FREST)
	public ArrayList<FavouriteRestaurants> getFavouriteRestaurantByUserId(@Param("user_id") int user_id);
	
	@Select("SELECT  RP.picture_id, RP.path_name, RP.date_added, RP.date_modify FROM restaurants R"
			+ " INNER JOIN restpictures RP ON R.rest_id = RP.rest_id"
			+ " WHERE R.rest_id =#{rest_id} ")
	@Results(value={
			@Result(property = "rest_id", column = "rest_id"),
			@Result(property = "picture_id", column = "picture_id"),
			@Result(property = "path_name", column = "path_name")
	})
	public ArrayList<Restpictures> findRestyPicture(int rest_id);
	
	
	String C_USER_FAV = "SELECT COUNT(F.rest_id) As favtotal "
			+ " From favouriterestaurants F "
			+ " Where F.user_id= #{user_id} ";				 
	@Select(C_USER_FAV)
	public int totalFavourite(int user_id);
	
	@Select("select COUNT(*) from  favouriterestaurants where user_id=#{user_id} and rest_id=#{rest_id}")
	public int countFavByUserIdAndRestId(@Param("user_id") int userId , @Param("rest_id") int restId);
	
	@Select("SELECT COUNT(*) FROM favouriterestaurants")
	public int countFavoriteRest();
	
	
}
