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
			+ " C.comment_id,"
			+ " C.comment,"
			+ " U.user_id,"
			+ " U.first_name,"
			+ " U.last_name,"
			+ " U.salt,"
			+ " U.dob,"
			+ " U.joined,"
			+ " U.picture,"
			+ " U.username,"
			+ " U.email,"
			+ " U.password,"
			+ " F.food_id,"
			+ " F.food_name,"
			+ " F.price,"
			+ " F.description"
			+ "	FROM comments C"
			+ " INNER JOIN users U"
			+ " ON C.user_id = U.user_id"
			+ " INNER JOIN foods F"
			+ " ON f.food_id = C.food_id";
	@Select(R_COMMENT)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.salt", column="salt"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.joined", column="joined"),
			@Result(property="user.picture", column="picture"),
			@Result(property="user.username", column="username"),
			@Result(property="user.password", column="password"),
			@Result(property="user.email", column="email"),
			@Result(property="food.food_id", column="food_id"),
			@Result(property="food.food_name", column="food_name"),
			@Result(property="food.price", column="price"),
			@Result(property="food.description", column="description")
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
	
	String F_COMMENT = "SELECT "
			+ " C.comment_id,"
			+ " C.comment,"
			+ " U.user_id,"
			+ " U.first_name,"
			+ " U.last_name,"
			+ " U.salt,"
			+ " U.dob,"
			+ " U.joined,"
			+ " U.picture,"
			+ " U.username,"
			+ " U.email,"
			+ " U.password,"
			+ " F.food_id,"
			+ " F.food_name,"
			+ " F.price,"
			+ " F.description"
			+ "	FROM comments C"
			+ " INNER JOIN users U"
			+ " ON C.user_id = U.user_id"
			+ " INNER JOIN foods F"
			+ " ON f.food_id = C.food_id"
			+ " WHERE"
			+ " C.comment_id = #{comment_id}";
	@Select(F_COMMENT)
	@Results(value={
			@Result(property="user.user_id", column="user_id"),
			@Result(property="user.first_name", column="first_name"),
			@Result(property="user.last_name", column="last_name"),
			@Result(property="user.salt", column="salt"),
			@Result(property="user.dob", column="dob"),
			@Result(property="user.joined", column="joined"),
			@Result(property="user.picture", column="picture"),
			@Result(property="user.username", column="username"),
			@Result(property="user.password", column="password"),
			@Result(property="user.email", column="email"),
			@Result(property="food.food_id", column="food_id"),
			@Result(property="food.food_name", column="food_name"),
			@Result(property="food.price", column="price"),
			@Result(property="food.description", column="description")
	})
	public ArrayList<Comments> getCommentById(int id);

}
