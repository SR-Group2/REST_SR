package org.khmeracademy.rest.services;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Comments;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
	public ArrayList<Comments> getAllComments();
	public boolean insertComment(Comments comment);
	public boolean  updateComment(Comments comment);
	public boolean deleteComment(int id);
	public ArrayList<Comments> getCommentById(int id);
}
