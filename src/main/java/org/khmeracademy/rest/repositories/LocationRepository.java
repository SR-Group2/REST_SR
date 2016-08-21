package org.khmeracademy.rest.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.khmeracademy.rest.entities.Locations;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository {
	//============ GET ALL LOCATION ============
	String R_LOCATION = "SELECT id, "
			   + " 		 type_code, "
			   + "       code, "
			   + "		 CONCAT(type, ' ', khmer_name) AS name, "
			   + "		 parent_id "
			   + "FROM locations "
			   + "WHERE parent_id = #{parent_id} "
			   + "AND type_code = #{type_code} "
			   + "ORDER BY 4";
	@Select(R_LOCATION)
	public List<Locations> getAllLocationByParentIdAndTypeCode(@Param("parent_id") int id, @Param("type_code")String typeCode);
}
