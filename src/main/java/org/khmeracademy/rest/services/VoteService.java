package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Votes;
import org.springframework.stereotype.Service;
@Service
public interface VoteService {
	public ArrayList<Votes> getAllVotes();
	public boolean insertVote(Votes vote);
	public boolean  updateVote(Votes vote);
	public boolean deleteVote(int id);
	public Votes getVoteById(int id);
}
