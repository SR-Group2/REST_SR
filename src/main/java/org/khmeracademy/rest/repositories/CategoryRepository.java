package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Categories;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {
	/* =================  INSERT DATA   =================== */
	String C_CAT = "INSERT INTO CATEGORIES (category_name, other, date_added, date_modify)"
			+ "VALUES(#{category_name}, #{other}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
	@Insert(C_CAT)
	public boolean insertCategory(Categories category);
	
	/* =================  GET DATA   =================== */
	String R_CAT = "SELECT category_id, category_name, other, date_added, date_modify FROM CATEGORIES";
	@Select(R_CAT)
	public ArrayList<Categories> getCategory();
	
	/* =================  GET DATA  BY ID =================== */
	String F_CAT = "SELECT category_id, category_name, other, date_added, date_modify FROM CATEGORIES"
			+ " WHERE category_id = #{category_id}";
	@Select(F_CAT)
	public ArrayList<Categories> findCategoryById(@Param("category_id") int category_id);
	
	/* =================  UPDATE DATA   =================== */
	String U_CAT = "UPDATE CATEGORIES SET category_name=#{category_name}, other=#{other},date_modify=CURRENT_TIMESTAMP"
			+ " WHERE category_id=#{category_id}";
	@Update(U_CAT)
	public boolean updateCategory(Categories category);
	
	/* =================  DELETE DATA   =================== */
	String D_CAT = "DELETE FROM CATEGORIES WHERE category_id = #{category_id}";
	@Delete(D_CAT)
	public boolean deleteCategory(@Param("category_id") int category_id);
}
