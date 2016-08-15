package org.khmeracademy.rest.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.khmeracademy.rest.entities.Menus;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository {

	String C_BATCH_CATREST =  " <script>INSERT INTO menus(  rest_id , restype_id ) " 
							+ " 	VALUES "
							+ " 	<foreach collection='menus' item='menu' separator=','>" 
							+ " 		( #{rest_id}, #{menu.restype_id})"
							+ " 	</foreach>" 
							+ " </script>";

	@Insert(C_BATCH_CATREST)
	public boolean inertBatchMenus(@Param("menus") List<Menus> catRests, @Param("rest_id") int rest_id);

}
