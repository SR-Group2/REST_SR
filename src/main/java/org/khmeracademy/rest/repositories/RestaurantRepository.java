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
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Categories;
import org.khmeracademy.rest.entities.Restaurants;
import org.khmeracademy.rest.entities.Restypes;
import org.khmeracademy.rest.form.RestaurantForm2;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
	
	
	//=================== Restaurant Pagination with search ===============
	
	final String R_RESTYPE = 
			  "SELECT rest_id, "
			+ "	  	  rest_name, "
			+ "	  	  contact, "
			+ "	  	  about, "
			+ "		  open_close, "
			+ "		  location"
			+ " FROM  restaurants "
			+ " WHERE LOWER(rest_name) LIKE LOWER(#{keyword}) "
			+ " ORDER BY rest_id DESC "
			+ " LIMIT #{limit} OFFSET #{offset} ";
	@Select(R_RESTYPE)
	public ArrayList<Restaurants> searchRest(@Param("keyword") String keyword, 
			@Param("limit") int limit, @Param("offset") int offset);
	
	//==================COUNT Restaurant Detail  ================
	String COUNT_RESTBYID = "SELECT COUNT(rest_id) FROM restaurants WHERE LOWER(rest_name) LIKE '%'||#{keyword}||'%' ";
	@Select(COUNT_RESTBYID)
	public int countRestById(String keyword);
		
	//================== Restaurant Detail  ================
	String R_RESTAURANT = "SELECT"
			+ " DISTINCT ON(R.rest_id) R.rest_id,"
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
	
	//TODO: 2. ADD TO RESTAURANT RETURN ID
	String C_RESTAURANT = "INSERT INTO restaurants(rest_name,contact,about,"
			+ "open_close,location,address_id,user_id)"
			+ " VALUES(#{rest_name} , #{contact} , #{about},#{open_close},"
			+ "#{location},"
			+ "#{address.address_id},#{user_id})";
	@Insert(C_RESTAURANT)
	@SelectKey(
            keyProperty = "rest_id",
            before = false,
            resultType = Integer.class,
            statement = { "SELECT last_value FROM restaurants_rest_id_seq" })
	//public boolean insertRestaurant(RestaurantForm restaurantForm);
	public boolean insertRestaurant(RestaurantForm2 restaurantForm);
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
			@Result(property = "user.username", column = "user_username")
	})
	public Restaurants  findRestaurantById(int rest_id);
	
	/*======================DASHBOARD REQUIREMENT ==================*/
	/*======================GET Restaurant WITH CATEGORY ==================*/

	String F_RESTAURANT_C = "SELECT "
			+ " R.rest_id,"
			+ " R.rest_name,"
			+ " R.contact,"
			+ " R.about,"
			+ " R.open_close,"
			+ " R.location,"
			+ " A.address_id AS address_address_id,"
			+ " A.street,"
			+ " A.district, "
			+ " A.communce,"
			+ " A.province,"
			+ " U.user_id,"
			+ "  U.first_name,"
			+ "  U.last_name,"
			+ "  U.username,"
			+ "  U.email,"
			+ "  U.password,"
			+ "  U.salt,"
			+ "  U.dob,"
			+ "  U.joined,"
			+ "  U.picture,"
			+ "  RO.role_name"
			+ " FROM restaurants R"
			+ " INNER JOIN Addresses A  ON R.address_id = A.address_id"
			+ " INNER JOIN users U  ON R.user_id = U.user_id "
			+ " INNER JOIN roles RO ON U.role_id = RO.role_id WHEre RO.role_id=2";
	@Select(F_RESTAURANT_C)
	@Results(value={
			@Result(property = "rest_id", column = "rest_id"),
			@Result(property = "user.user_id", column = "user_id"),
			@Result(property = "user.first_name", column = "first_name"),
			@Result(property = "user.last_name", column = "last_name"),
			@Result(property = "user.email", column = "email"),
			@Result(property = "user.salt", column = "salt"),
			@Result(property = "user.dob", column = "dob"),
			@Result(property = "user.joined", column = "joined"),
			@Result(property = "user.picture", column = "picture"),
			@Result(property = "user.username", column = "username"),
			@Result(property = "user.password", column = "password"),
			@Result(property = "address.address_id", column = "address_address_id"),
			@Result(property = "address.district", column = "district"),
			@Result(property = "address.communce", column = "communce"),
			@Result(property = "address.province", column = "province"),
			@Result(property = "address.street", column = "street"),
			@Result(property="categories", javaType=List.class, column="rest_id", many=@Many(select="getCategoryByRestId")),
			@Result(property="restype", javaType=List.class, column="rest_id", many=@Many(select="findMenuByRestId"))
	})
	public ArrayList<Restaurants> findRestaurantWithCategory();
	
	/*====================== Get Category By Restaurant ID ==================*/
	/*String GC_BRID = "SELECT "
					+"		c.category_id, "
					+"		c.picture, "
					+"		c.category_name_kh, "
					+"		c.category_name"
					+"	FROM "
					+"	categories c "
					+"  INNER JOIN catrests cr ON c.category_id = cr.category_id "
					+"	WHERE cr.rest_id = #{rest_id}";*/
	String GC_BRID = "SELECT "
			+"		c.category_id, "
			+"		c.picture, "
			+"		c.category_name_kh, "
			+"		c.picture,"
			+"		c.url,"
			+"		c.category_name"
			+"	FROM "
			+"	categories c "
			+"  INNER JOIN restaurants r ON c.rest_id = r.rest_id "
			+"	WHERE c.rest_id = #{rest_id}";
	@Select(GC_BRID)
	public ArrayList<Categories> getCategoryByRestId(int rest_id);
	
	@Select("SELECT  RT.restype_id, RT.restype_name , RT.restype_name_kh  FROM restaurants R"
			+ " INNER JOIN menus M ON R.rest_id = M.rest_id"
			+ " INNER JOIN restypes RT ON M.restype_id = RT.restype_id"
			+ " WHERE R.rest_id =#{rest_id} ")
	@Results(value={
			@Result(property = "rest_id", column = "rest_id"),
			@Result(property = "restype_id", column = "restype_id"),
			@Result(property = "user.user_id", column = "user_id"),
			@Result(property = "user.first_name", column = "first_name"),
			@Result(property = "user.last_name", column = "last_name"),
			@Result(property = "user.email", column = "email"),
	})
	public ArrayList<Restypes> findMenuByRestId(int rest_id);
	
}
