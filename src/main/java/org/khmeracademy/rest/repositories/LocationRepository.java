package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;
import org.khmeracademy.rest.entities.Locations;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository {
	//============ GET ALL LOCATION ============
	String R_LOCATION = "SELECT"
			+ " id,"
			+ " type,"
			+ " type_code,"
			+ " code,"
			+ " khmer_name,"
			+ " reference,"
			+ " official_note,"
			+ " khmer_name,"
			+ " checker_note,"
			+ " parent_id"
			+ " FROM "
			+ " locations";
	@Select(R_LOCATION)
	public ArrayList<Locations> getAlllocation();
	
	
}
