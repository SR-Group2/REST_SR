package org.khmeracademy.rest.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Categories;
import org.khmeracademy.rest.entities.Restypes;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository {
	/* =================  INSERT DATA   =================== */
	String C_CAT = "INSERT INTO CATEGORIES (category_name, other, date_added, date_modify, picture)"
			+ "VALUES(#{category_name}, #{other}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, #{picture})";
	@Insert(C_CAT)
	public boolean insertCategory(Categories category);
	
	/* =================  GET DATA   =================== */
	String R_CAT = "SELECT category_id, category_name, other, date_added, date_modify, picture FROM CATEGORIES";
	@Select(R_CAT)
	public ArrayList<Categories> getCategory();
	
	/* =================  GET DATA  BY ID =================== */
	String F_CAT = "SELECT category_id, category_name, other, date_added, date_modify, picture FROM CATEGORIES"
			+ " WHERE category_id = #{category_id}";
	@Select(F_CAT)
	public Categories findCategoryById(@Param("category_id") int category_id);
	
	/* =================  UPDATE DATA   =================== */
	String U_CAT = "UPDATE CATEGORIES SET category_name=#{category_name}, other=#{other},date_modify=CURRENT_TIMESTAMP"
			+ ", picture=#{picture} WHERE category_id=#{category_id}";
	@Update(U_CAT)
	public boolean updateCategory(Categories category);
	
	/* =================  DELETE DATA   =================== */
	String D_CAT = "DELETE FROM CATEGORIES WHERE category_id = #{category_id}";
	@Delete(D_CAT)
	public boolean deleteCategory(@Param("category_id") int category_id);
	
	/*====================== Get Category By Restaurant ID ==================*/
	String GC_BRID = "SELECT "
					+"		r.rest_id, "
					+"		r.rest_name, "
					+"		c.category_id, "
					+"		c.date_added, "
					+"		c.date_modify, "
					+"		c.picture, "
					+"		c.category_name"
					+"	FROM "
					+"	categories c INNER JOIN catrests cr ON c.category_id = cr.category_id "
					+"	INNER JOIN restaurants r ON r.rest_id = cr.rest_id WHERE r.rest_id = #{rest_id}";
	@Select(GC_BRID)
	public ArrayList<Categories> getCategoryByRestId(int rest_id);
	
	//TODO: 3. ADD TO CATE_REST WITH RESTAURANT ID
/*	String C_BATCH_CATEGORIES =  "<script>INSERT INTO CATEGORIES (category_name, other, date_added, date_modify, picture , category_name_kh)"
								+ " VALUES "
								+ " <foreach collection='categories' item='category' separator=','>"
								+ " 	(#{category.category_name}, #{category.other}, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, #{category.picture} , #{category.category_name_kh})"
								+ " </foreach>"
								+ " </script>";*/
	/*@Insert(C_BATCH_CATEGORIES)
	@SelectKey(
            keyProperty = "cate.category_id",
            before = false,
            resultType = Integer.class,
            statement = { "SELECT last_value FROM categories_category_id_seq" })
	public boolean inertBatchCategories(@Param("categories") List<Categories> categories , @Param("cate")Categories cate);*/
	
	
	
	String C_BATCH_CATEGORIES =  "<script>INSERT INTO CATEGORIES (url, rest_id)"
			+ " VALUES "
			+ " <foreach collection='menu_urls' item='url' separator=','>"
			+ " 	(#{url} , #{rest_id})"
			+ " </foreach>"
			+ " </script>";
	@Insert(C_BATCH_CATEGORIES)
	public boolean inertBatchCategories(@Param("menu_urls") List<String> menu_urls , @Param("rest_id") int rest_id);
		
		
}
