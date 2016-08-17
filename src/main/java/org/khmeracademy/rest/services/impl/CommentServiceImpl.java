package org.khmeracademy.rest.services.impl;

import java.util.ArrayList;

import org.khmeracademy.rest.entities.Comments;
import org.khmeracademy.rest.repositories.CommentReopository;
import org.khmeracademy.rest.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentReopository commentRepository;
	@Override
	public ArrayList<Comments> getAllComments(int rest_id) {
		return commentRepository.getAllComments(rest_id);
	}

	@Override
	public boolean insertComment(Comments comment) {
		return commentRepository.insertComment(comment);
	}

	@Override
	public boolean updateComment(Comments comment) {
		return commentRepository.updateComment(comment);
	}

	@Override
	public boolean deleteComment(int id) {
		return commentRepository.deleteComment(id);
	}

	@Override
	public Comments getCommentById(int id) {
		return commentRepository.getCommentById(id);
	}

}
