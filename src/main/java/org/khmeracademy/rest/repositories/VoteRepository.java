package org.khmeracademy.rest.repositories;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.khmeracademy.rest.entities.Votes;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository {
	
	String R_VOTE= "SELECT vote_id, vote_number, rest_id, food_id, user_id"
			+ "	FROM votes";
	@Select(R_VOTE)
	@Results(value={
			@Result(property="rest.rest_id", column="rest_id"),
			@Result(property="food.food_id", column="food_id" ),
			@Result(property="user.user_id", column="user_id")
	})
	public ArrayList<Votes> getAllVotes();
	
	String C_VOTE="INSERT INTO"
			+ " votes (vote_number, rest_id, food_id, user_id)"
			+ " VALUES(#{vote_number},#{rest.rest_id}, #{food.food_id},#{user.user_id})";
	
	@Insert(C_VOTE)
	public boolean insertVote(Votes vote);
	
	String U_VOTE="UPDATE votes"
			+ " SET vote_number=#{vote_number},"
			+ " rest_id=#{rest.rest_id},"
			+ " food_id=#{food.food_id},"
			+ " user_id=#{user.user_id}";

	@Update(U_VOTE)
	public boolean  updateVote(Votes vote);
	
	String D_VOTE="DELETE"
				+ " FROM"
				+ " votes "
				+ " WHERE "
				+ " vote_id=#{vote_id} ";
	@Delete(D_VOTE)
	public boolean deleteVote(int id);
	
	String F_VOTE="SELECT vote_id, vote_number "
			+ " FROM votes"
			+ " WHERE"
			+ " vote_id=#{vote_id} ";
	@Select(F_VOTE)
	public ArrayList<Votes> getVoteById(int id);

}
