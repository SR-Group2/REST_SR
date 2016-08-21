package org.khmeracademy.rest.repositories;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.RestaurantLocation;

public interface RestaurantLocationRepository {

	String C_REST_LOCATION = "INSERT "
			+ " INTO restaurant_locations("
			 + " restaurant_id, "
				 + " longitude, "
				 + " latitude,"
				 + " province,"
				 + " district, "
				 + " commune, "
				 + " village, "
				 + " street, "
				 + " no, "
				 + " status)"
				 	+ " VALUES(#{restaurant_id}, #{longitude}, #{latitude}, #{province}, #{district}, #{commune}, #{village}, #{street}, #{no}, #{status})";
	@Insert(C_REST_LOCATION)
	public boolean InsertRestaurantLocations(RestaurantLocation location);
	
	String U_REST_LOCATION = "UPDATE "
			+ " restaurant_locations "
			 + " SET "
			 + " district = #{district}, "
			 + " commune = #{commune}, "
			 + " street = #{street}, "
			 + " no = #{no}, "
			 + " branch = #{brand}, "
			 + " village = #{village}, "
			 + " status = #{status} "
			 + " WHERE restaurant_id = #{restaurant_id}";
	@Update(U_REST_LOCATION)
	public boolean UpdateRestaurantLocation(RestaurantLocation location);
}
