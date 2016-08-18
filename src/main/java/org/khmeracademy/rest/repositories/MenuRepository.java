package org.khmeracademy.rest.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.khmeracademy.rest.entities.Menus;
import org.khmeracademy.rest.form.RestTypeId;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository {

	String C_BATCH_CATREST =  " <script>INSERT INTO menus(  rest_id , restype_id ) " 
							+ " 	VALUES "
							+ " 	<foreach collection='restypes_id' item='restype_id' separator=','>" 
							+ " 		( #{rest_id}, #{restype_id.restype_id})"
							+ " 	</foreach>" 
							+ " </script>";

	@Insert(C_BATCH_CATREST)
	public boolean inertBatchMenus(@Param("restypes_id") List<RestTypeId> restypes_id, @Param("rest_id") int rest_id);

}
