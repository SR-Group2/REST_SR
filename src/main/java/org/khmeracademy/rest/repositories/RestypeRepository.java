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
import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.Restpictures;
import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.form.CategoryId;
import org.khmeracademy.rest.form.RestTypeId;
import org.springframework.stereotype.Repository;

@Repository
public interface RestypeRepository {
	
	final String ONLY_RESTYPE = "SELECT restype_id, "
			+ "	  	  restype_name, "
			+ "	  	  restype_name_kh, "
			+ "	  	  restype_picture, "
			+ "	  	  date_added, "
			+ "		  date_modify, "
			+ "		  parentid_restypeid,"
			+ "		  description "
			+ " FROM  restypes "
			+ " WHERE restype_id = #{keyword}";
	@Select(ONLY_RESTYPE)
	public Restypes getOnlyRestype(int restype_id);
	
	
	final String R_RESTYPE = "SELECT restype_id, "
			+ "	  	  restype_name, "
			+ "	  	  restype_name_kh, "
			+ "	  	  restype_picture, "
			+ "	  	  date_added, "
			+ "		  date_modify, "
			+ "		  parentid_restypeid,"
			+ "		  description "
			+ " FROM  restypes "
			+ " WHERE LOWER(restype_name) LIKE LOWER(#{keyword}) "
			+ " ORDER BY restype_id DESC "
			+ " LIMIT #{limit} OFFSET #{offset} ";
	@Select(R_RESTYPE)
	public ArrayList<Restypes> getAllRestype(@Param("keyword") String keyword, 
			@Param("limit") int limit, @Param("offset") int offset);
	
	
	
	String C_RESTYPE = "INSERT INTO restypes(restype_name,restype_name_kh,"
			+ "restype_picture, parentid_restypeid, description) "
			+ " VALUES(#{restype_name}, #{restype_name_kh}, #{restype_picture},"
			+ " #{parentid_restypeid}, #{description})";
	@Insert(C_RESTYPE)
	public boolean insertRestype(Restypes restype);
	
	String U_RESTYPE = "UPDATE restypes SET "
			+ "	restype_name=#{restype_name} , restype_name_kh=#{restype_name_kh}, "
			+ "date_modify=CURRENT_TIMESTAMP , restype_picture=#{restype_picture},"
			+ " parentid_restypeid = #{parentid_restypeid}, description = #{description} "
			+ "WHERE "
			+ "	restype_id=#{restype_id}";
	@Update(U_RESTYPE)
	public boolean updateRestype(Restypes restype);
	
	String D_RESTYPE = "DELETE"
			+ " FROM"
			+ " restypes"
			+ " WHERE"
			+ " restype_id = #{restype_id}";
	@Delete(D_RESTYPE)
	public boolean deleteRestype(int restype_id);
	

	String F_RESTYPE = "SELECT DISTINCT"
						+ "	r.rest_id AS restaurantid, r.rest_name,"
						+ " r.contact, r.about,"
						+ " r.open_close, r.latitude, r.longitude,"
						+ " rsp.restype_id AS rsprestype_id, "
						+ "	rsp.restype_name FROM restaurants r "
						+ " LEFT JOIN menus mn ON mn.rest_id = r.rest_id "
						+ " LEFT JOIN restypes rsp ON mn.restype_id = rsp.restype_id "
						+ " WHERE rsp.restype_id = #{restype_id}"
						+ " ORDER BY 1 "
						+ " OFFSET #{offset} LIMIT #{limit}  ";
	@Select(F_RESTYPE)
	@Results(value={
			@Result(property = "rest_id", column="restaurantid"),
			@Result(property = "restpictures", javaType=List.class, column="restaurantid", many=@Many(select="findRestyPicture"))
	})
	public ArrayList<Restaurants>  findRestypeById(@Param("restype_id") int restype_id,
			@Param("limit") int limit, @Param("offset") int offset);
	
	@Select("SELECT  RP.picture_id, RP.path_name, RP.date_added, RP.date_modify FROM restaurants R"
			+ " INNER JOIN restpictures RP ON R.rest_id = RP.rest_id"
			+ " WHERE R.rest_id =#{rest_id} ")
	@Results(value={
			@Result(property = "rest_id", column = "rest_id"),
			@Result(property = "picture_id", column = "picture_id"),
			@Result(property = "path_name", column = "path_name")
	})
	public ArrayList<Restpictures> findRestyPicture(int rest_id);
	
	String COUNT_RESTYPE = "SELECT COUNT(restype_id) FROM restypes WHERE LOWER(restype_name) LIKE '%'||#{keyword}||'%' ";
	@Select(COUNT_RESTYPE)
	public int countRestype(String keyword);
	
	String FIND_RESTYPE = "SELECT restype_id, restype_name, restype_name_kh, restype_picture,"
			+" date_added, date_modify, parentid_restypeid "
			+" FROM  restypes  WHERE restype_name LIKE '%' || #{keyword} || '%' ORDER BY date_added DESC";
	@Select(FIND_RESTYPE)
	public ArrayList<Restypes> findRestypeByKeyword(String keyword);
	
	//================= Count Number of Resturant in Restype FOR Pagination
	String COUNT_RESTAURANT = "SELECT count( DISTINCT r.rest_id) num_rest "
				+"	FROM restaurants r "
				+" INNER JOIN menus mn ON mn.rest_id = r.rest_id "
				+" INNER JOIN restypes rsp ON mn.restype_id = rsp.restype_id " 
				+" WHERE rsp.restype_id = #{restype_id}";
	@Select(COUNT_RESTAURANT)
	public int countRest(int restype_id);
	
	//TODO: 3. ADD TO CATE_REST WITH RESTAURANT ID
	/*String C_BATCH_CATREST =  "<script>INSERT INTO catrests( rest_id , category_id ) "
							+ " VALUES "
							+ " <foreach collection='categories_id' item='category_id' separator=','>"
							+ " 	(#{rest_id}, #{category_id.category_id})"
							+ " </foreach>"
							+ " </script>";
	@Insert(C_BATCH_CATREST)
	public boolean inertBatchCatRest(@Param("categories_id") List<CategoryId> categories_id ,@Param("rest_id") int rest_id);*/
	
	//TODO: 3. ADD TO CATE_REST WITH RESTAURANT ID
	String C_BATCH_REST_TYPE_ID =  "<script>INSERT INTO menus( rest_id , restype_id ) "
							+ " VALUES "
							+ " <foreach collection='restype_ids' item='restype' separator=','>"
							+ " 	(#{rest_id}, #{restype.restype_id})"
							+ " </foreach>"
							+ " </script>";
	@Insert(C_BATCH_REST_TYPE_ID)
	public boolean insertBatchRestypeId(@Param("restype_ids") List<RestTypeId> rest_type_ids ,@Param("rest_id") int rest_id);
	
	
	//TODO: 3. ADD TO CATE_REST WITH RESTAURANT ID
		String U_BATCH_REST_TYPE_ID =  "<script>"
								+ " <foreach collection='restype_ids' item='restype' separator=';'>"
								+ " 	UPDATE  menus SET  restype_id = #{restype.restype_id} "
								+ "		WHERE rest_id = #{rest_id}"
								+ " </foreach>"
								+ " </script>";
		@Insert(U_BATCH_REST_TYPE_ID)
		public boolean updateBatchRestypeId(@Param("restype_ids") List<RestTypeId> rest_type_ids ,@Param("rest_id") int rest_id);
}
