package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Addresses;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository {

	String R_ADDRESS = "SELECT"
			+ " address_id,"
			+ " street,"
			+ " district,"
			+ " communce,"
			+ " province"
			+ " FROM"
			+ " addresses";
	@Select(R_ADDRESS)
	public ArrayList<Addresses> getAllAddress();
	
	String C_ADDRESS = "INSERT INTO"
			+ " addresses(street,district,communce,province)"
			+ " VALUES(#{street},#{district},#{communce},#{province})";
	@Insert(C_ADDRESS)
	public boolean insertAddress(Addresses address);
	
	String D_ADDRESS = "DELETE "
			+ " FROM"
			+ " addresses"
			+ " WHERE"
			+ " address_id = #{address_id}";
	@Delete(D_ADDRESS)
	public boolean deleteAddress(int address_id);
	
	String U_ADDRESS = "UPDATE"
			+ " addresses"
			+ " SET"
			+ " street = #{street},"
			+ " district = #{district},"
			+ " communce = #{communce},"
			+ " province = #{province}"
			+ " WHERE"
			+ " address_id = #{address_id}";
	@Update(U_ADDRESS)
	public boolean updateAddress(Addresses address);
	
	String F_ADDRESS = "SELECT"
			+ " address_id,"
			+ " street,"
			+ " district,"
			+ " communce,"
			+ " province"
			+ " FROM"
			+ " addresses "
			+ " WHERE address_id = #{address_id}";
	@Select(F_ADDRESS)
	public ArrayList<Addresses>  findAddressById(int address_id);
}
