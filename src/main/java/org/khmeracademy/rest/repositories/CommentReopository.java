package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Comments;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentReopository {
	
	String R_COMMENT= "SELECT "
			+ " C.comment_id, "
			+ " U.user_id, "
			+ " U.picture, "
			+ " R.rest_id, "
			+ " comment, "
			+ " R.rest_name, "
			+ " U.first_name, "
			+ " U.last_name "
			+ "	FROM comments C"
			+ " INNER JOIN restaurants R"
			+ " ON C.rest_id = R.rest_id"
			+ " INNER JOIN users U"
			+ " ON C.user_id = U.user_id WHERE R.rest_id = #{rest_id} ORDER BY C.rest_id";
	@Select(R_COMMENT)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.picture", column="picture"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="rest.rest_id", column="rest_id"),
			@Result(property="rest.rest_name", column="rest_name")
	})
	public ArrayList<Comments> getAllComments(int rest_id);
	
	String C_COMMENT="INSERT INTO"
			+ " comments(rest_id, user_id,comment)"
			+ " VALUES(#{rest.rest_id},#{user.user_id},#{comment})";
	
	@Insert(C_COMMENT)
	public boolean insertComment(Comments comment);
	
	String U_COMMENT="UPDATE comments"
			+ " SET "
			+ " comment=#{comment}"
			+ " WHERE"
			+ " comment_id=#{comment_id}";

	@Update(U_COMMENT)
	public boolean  updateComment(Comments comment);
	
	String D_COMMENT="DELETE"
				+ " FROM"
				+ " comments "
				+ " WHERE "
				+ " comment_id=#{comment_id}";
	@Delete(D_COMMENT)
	public boolean deleteComment(int id);
	
	String F_COMMENT= "SELECT "
			+ " comment_id, "
			+ " U.user_id, "
			+ " R.rest_id, "
			+ " comment, "
			+ " R.rest_name, "
			+ " U.first_name, "
			+ " U.last_name "
			+ "	FROM comments C"
			+ " INNER JOIN restaurants R"
			+ " ON C.rest_id = R.rest_id"
			+ " INNER JOIN users U"
			+ " ON C.user_id = U.user_id"
			+ " WHERE"
			+ " comment_id=#{comment_id}";
	@Select(F_COMMENT)
	public Comments getCommentById(int id);
	
	@Select("SELECT COUNT(*) FROM comments")
	public int countComment();
	

}
