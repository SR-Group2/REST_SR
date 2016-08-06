package org.khmeracademy.rest.repositories;



import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Foods;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository {
	/* =================  INSERT DATA   =================== */
	String C_FOOD = "INSERT INTO FOODS (food_name, price, description, category_id)"
			+ "VALUES(#{food_name}, #{price}, #{description},#{category.category_id})";
	@Insert(C_FOOD)
	@Results(value={
		@Result(property="category.category_id", column="category_id")
	})
	public boolean insertFood(Foods food);
	
	/* =================  GET DATA   =================== */
	String R_FOOD = "SELECT f.food_id, f.food_name, f.price, f.description, c.category_id, c.category_name, f.date_added, f.date_modify"
			+ " FROM FOODS f INNER JOIN categories c ON f.category_id = c.category_id";
	@Select(R_FOOD)
	@Results(value={
			@Result(property="category.category_id", column="category_id"),
			@Result(property="category.category_name", column="category_name")
		})
	public ArrayList<Foods> getFood();
	
	/* =================  GET DATA  BY ID =================== */
	String F_FOOD = "SELECT f.food_id, f.food_name, f.price, f.description, c.category_id, c.category_name, f.date_added, f.date_modify"
			+ " FROM FOODS f INNER JOIN categories c ON f.category_id = c.category_id WHERE f.food_id = #{food_id}";
	@Select(F_FOOD)
	@Results(value={
			@Result(property="category.category_id", column="category_id"),
			@Result(property="category.category_name", column="category_name")
		})
	public ArrayList<Foods> findFoodById(@Param("food_id") int food_id);
	
	/* =================  UPDATE DATA   =================== */
	String U_FOOD = "UPDATE FOODS set food_name=#{food_name}, price=#{price},description=#{description}, "
			+ "category_id=#{category.category_id}, date_modify=CURRENT_TIMESTAMP WHERE food_id = #{food_id}";
	@Update(U_FOOD)
	@Results(value={
		@Result(property="category.category_id", column="category_id")
	})
	public boolean updateFood(Foods food);
	
	/* =================  DELETE DATA   =================== */
	String D_FOOD = "DELETE FROM FOODS WHERE food_id = #{food_id}";
	@Delete(D_FOOD)
	public boolean deleteFood(@Param("food_id") int food_id);
}
