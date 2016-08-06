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
	
	String R_COMMENT= "SELECT comment_id, user_id, food_id, comment"
			+ "	FROM comments";
	@Select(R_COMMENT)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="food.food_id", column="food_id" )
	})
	public ArrayList<Comments> getAllComments();
	
	String C_COMMENT="INSERT INTO"
			+ " comments (user_id, food_id, comment)"
			+ " VALUES(#{user.user_id},#{food.food_id},#{comment})";
	
	@Insert(C_COMMENT)
	public boolean insertComment(Comments comment);
	
	String U_COMMENT="UPDATE comments"
			+ " SET user_id=#{user.user_id},"
			+ " food_id=#{food.food_id},"
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
	
	String F_COMMENT="SELECT comment_id, comment "
			+ " FROM comments"
			+ " WHERE"
			+ " comment_id=#{comment_id}";
	@Select(F_COMMENT)
	public ArrayList<Comments> getCommentById(int id);

}
