package org.khmeracademy.rest.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.khmeracademy.rest.entities.Menus;
import org.khmeracademy.rest.form.RestTypeId;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository {

	String C_BATCH_Menus =  " <script>INSERT INTO menus(  rest_id , restype_id ) " 
							+ " 	VALUES "
							+ " 	<foreach collection='restypes_id' item='restype_id' separator=','>" 
							+ " 		( #{rest_id}, #{restype_id.restype_id})"
							+ " 	</foreach>" 
							+ " </script>";

	@Insert(C_BATCH_Menus)
	public boolean inertBatchMenus(@Param("restypes_id") List<RestTypeId> restypes_id, 
			@Param("rest_id") int rest_id);
	
	String D_C_BATCH_Menus = "DELETE FROM menus WHERE rest_id = #{rest_id}";
	@Delete(D_C_BATCH_Menus)
	public boolean deleteMenus(@Param("rest_id") int rest_id);

}
