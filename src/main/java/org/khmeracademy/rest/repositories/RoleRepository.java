package org.khmeracademy.rest.repositories;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Roles;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository {
	
	String R_ROLE= "SELECT role_id, role_name"
			+ "	FROM roles";
	@Select(R_ROLE)
	public ArrayList<Roles> getAllRoles();
	
	String C_ROLE="INSERT INTO"
			+ " roles (role_name)"
			+ " VALUES(#{role_name})";
	
	@Insert(C_ROLE)
	public boolean insertRole(Roles role);
	
	String U_ROLE="UPDATE roles"
			+ " SET role_name=#{role_name}"
			+ " WHERE"
			+ " role_id=#{role_id}";

	@Update(U_ROLE)
	public boolean  updateRole(Roles role);
	
	String D_ROLE="DELETE"
				+ " FROM"
				+ " roles "
				+ " WHERE "
				+ "	role_id=#{role_id}";
	@Delete(D_ROLE)
	public boolean deleteRole(int id);
	
	String F_ROLE="SELECT role_id, role_name "
			+ " FROM roles"
			+ " WHERE"
			+ " role_id=#{role_id}";
	@Select(F_ROLE)
	public ArrayList<Roles> getRoleById(int id);


}
