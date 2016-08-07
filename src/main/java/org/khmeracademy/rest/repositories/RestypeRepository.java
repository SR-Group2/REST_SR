package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Restypes;
import org.springframework.stereotype.Repository;

@Repository
public interface RestypeRepository {
	
	final String R_RESTYPE = 
			  "SELECT restype_id, "
			+ "	  	  restype_name, "
			+ "	  	  restype_name_kh, "
			+ "	  	  restype_picture, "
			+ "	  	  date_added, "
			+ "		  date_modify, "
			+ "		  parentid_restypeid "
			+ "FROM  restypes "
			+ "WHERE restype_name LIKE #{keyword} "
			+ "ORDER BY date_added DESC "
			+ "LIMIT #{limit}  ";
	@Select(R_RESTYPE)
	public ArrayList<Restypes> getAllRestype(@Param("keyword") String keyword, @Param("limit") int limit, @Param("offset") int offset);
	
	String C_RESTYPE = "INSERT INTO restypes(restype_name,restype_name_kh,"
			+ "restype_picture, parentid_restypeid) "
			+ " VALUES(#{restype_name}, #{restype_name_kh}, #{restype_picture},"
			+ " #{parentid_restypeid})";
	@Insert(C_RESTYPE)
	public boolean insertRestype(Restypes restype);
	
	String U_RESTYPE = "UPDATE restypes SET "
			+ "	restype_name=#{restype_name} , restype_name_kh=#{restype_name_kh}, "
			+ "date_modify=CURRENT_TIMESTAMP , restype_picture=#{restype_picture},"
			+ " parentid_restypeid = #{parentid_restypeid} "
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
	
	String F_RESTYPE = "SELECT"
			+ " restype_id,"
			+ " restype_name, restype_name_kh, date_added, date_modify, restype_picture"
			+ " FROM"
			+ " restypes"
			+ " WHERE"
			+ " restype_id = #{restype_id}";
	@Select(F_RESTYPE)
	public ArrayList<Restypes>  findRestypeById(int restype_id);
	
	String COUNT_RESTYPE = "SELECT COUNT(restype_id) FROM restypes WHERE restype_name LIKE '%'||#{keyword}||'%' ";
	@Select(COUNT_RESTYPE)
	public int countRestype(String keyword);
	
	String FIND_RESTYPE = "SELECT restype_id, restype_name, restype_name_kh, restype_picture,"
			+" date_added, date_modify, parentid_restypeid "
			+" FROM  restypes  WHERE restype_name LIKE '%' || #{keyword} || '%' ORDER BY date_added DESC";
	@Select(FIND_RESTYPE)
	public ArrayList<Restypes> findRestypeByKeyword(String keyword);
}
