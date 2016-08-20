package org.khmeracademy.rest.repositories;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Restpictures;
import org.springframework.stereotype.Repository;

@Repository
public interface RestPictureRepository {

	String R_RESTPICTURE = "SELECT"
			+" RP.picture_id,"
			+" RP.path_name,"
			+" R.rest_id,"
			+" RP.date_added,"
			+" RP.date_modify"
			+" FROM restpictures RP "
			+" INNER JOIN restaurants R"
			+" ON RP.rest_id = R.rest_id";
	@Select(R_RESTPICTURE)
	@Results(value={
			@Result(property = "rest.rest_id", column = "rest_id")
	})
	public ArrayList<Restpictures> getAllRestpicture();
	
	String C_RESTPICTURE = "INSERT INTO restpictures (path_name,rest_id)"
			+ " VALUES(#{path_name}, #{rest.rest_id})";
	@Insert(C_RESTPICTURE)
	public boolean insertRestpicture(Restpictures restpicture);
	
	String U_RESTPICTURE = "UPDATE restpictures SET "
			+ "	path_name=#{path_name} , "
			+ "	rest_id=#{rest.rest_id}  "
			+ " WHERE "
			+ "	picture_id=#{picture_id}";
	@Update(U_RESTPICTURE)
	public boolean updateRestpicture(Restpictures restpicture);
	
	String D_RESTPICTURE = "DELETE"
			+ " FROM"
			+ " restpictures"
			+ " WHERE"
			+ " picture_id=#{picture_id}";
	@Delete(D_RESTPICTURE)
	public boolean deleteRestpicture(int picture_id);
	
	String F_RESTPICTURE = "SELECT"
			+ " picture_id,"
			+ " path_name,"
			+ " rest_id,"
			+ " date_added,"
			+ " date_modify"
			+ " FROM"
			+ " restpictures"
			+ " WHERE"
			+ " picture_id = #{picture_id}";
	@Select(F_RESTPICTURE)
	public ArrayList<Restpictures>  findRestpictureById(int picture_id);
	

	String C_BATCH_RESTPICTURE =  "<script>INSERT INTO restpictures (path_name, rest_id)"
			+ " VALUES "
			+ " <foreach collection='path_names' item='path_name' separator=','>"
			+ " 	(#{path_name} , #{rest_id})"
			+ " </foreach>"
			+ " </script>";
	@Insert(C_BATCH_RESTPICTURE)
	public boolean inertBatchRestpicture(@Param("path_names") List<String> path_names , @Param("rest_id") int rest_id);
}
