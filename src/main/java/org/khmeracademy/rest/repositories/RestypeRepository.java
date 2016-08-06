package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Restypes;
import org.springframework.stereotype.Repository;

@Repository
public interface RestypeRepository {
	
	String R_RESTYPE = "SELECT "
			+ " restype_id, "
			+ " restype_name, "
			+ " parentid_restypeid"
			+ " FROM restypes";
	@Select(R_RESTYPE)
	public ArrayList<Restypes> getAllRestype();
	
	String C_RESTYPE = "INSERT INTO restypes(restype_name,parentid_restypeid) "
			+ " VALUES(#{restype_name}, #{parentid_restypeid})";
	@Insert(C_RESTYPE)
	public boolean insertRestype(Restypes restype);
	
	String U_RESTYPE = "UPDATE restypes SET "
			+ "	restype_name=#{restype_name} , "
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
			+ " restype_name"
			+ " FROM"
			+ " restypes"
			+ " WHERE"
			+ " restype_id = #{restype_id}";
	@Select(F_RESTYPE)
	public ArrayList<Restypes>  findRestypeById(int restype_id);
}
