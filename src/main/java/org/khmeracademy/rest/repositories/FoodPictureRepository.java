package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.FoodPictures;
import org.springframework.stereotype.Service;

@Service
public interface FoodPictureRepository {
	
	/* =================  INSERT DATA   =================== */
	String C_FP = "INSERT INTO foodpictures (path_name, food_id, date_added, date_modify)"
			+ "VALUES(#{path_name}, #{food.food_id}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
	@Insert(C_FP)
	@Results(value={
		@Result(property="food.food_id", column="food_id")
	})
	public boolean insertFoodPicture(FoodPictures foodPictures);
	
	/* =================  GET DATA   =================== */
	String R_FP = "SELECT fp.picture_id, fp.path_name, fp.food_id, f.food_name, fp.date_added, fp.date_modify"
			+ " FROM FOODPICTURES fp INNER JOIN FOODS f ON fp.food_id = f.food_id";
	@Select(R_FP)
	@Results(value={
			@Result(property="food.food_id", column="food_id"),
			@Result(property="food.food_name", column="food_name")
		})
	public ArrayList<FoodPictures> getFoodPictures();
	
	/* =================  GET DATA  BY ID =================== */
	String F_FP = "SELECT fp.picture_id, fp.path_name, fp.food_id, f.food_name, fp.date_added, fp.date_modify"
			+ " FROM FOODPICTURES fp INNER JOIN FOODS f ON fp.food_id = f.food_id WHERE fp.picture_id = #{picture_id}";
	@Select(F_FP)
	@Results(value={
			@Result(property="food.food_id", column="food_id"),
			@Result(property="food.food_name", column="food_name")
		})
	public ArrayList<FoodPictures> findFoodPictureById(@Param("picture_id") int picture_id);
	
	/* =================  UPDATE DATA   =================== */
	String U_FP = "UPDATE FOODPICTURES set path_name=#{path_name}, food_id=#{food.food_id},date_modify=CURRENT_TIMESTAMP"
			+ " WHERE picture_id = #{picture_id}";
	@Update(U_FP)
	@Results(value={
		@Result(property="food.food_id", column="food_id")
	})
	public boolean updateFoodPicture(FoodPictures foodPictures);
	
	/* =================  DELETE DATA   =================== */
	String D_FP = "DELETE FROM FOODPICTURES WHERE picture_id = #{picture_id}";
	@Delete(D_FP)
	public boolean deleteFoodPictures(@Param("picture_id") int picture_id);
}
