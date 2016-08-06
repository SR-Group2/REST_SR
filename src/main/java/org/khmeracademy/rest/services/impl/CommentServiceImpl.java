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
	private CommentReopository commmentRepository;
	@Override
	public ArrayList<Comments> getAllComments() {
		return commmentRepository.getAllComments();
	}

	@Override
	public boolean insertComment(Comments comment) {
		return commmentRepository.insertComment(comment);
	}

	@Override
	public boolean updateComment(Comments comment) {
		return commmentRepository.updateComment(comment);
	}

	@Override
	public boolean deleteComment(int id) {
		return commmentRepository.deleteComment(id);
	}

	@Override
	public ArrayList<Comments> getCommentById(int id) {
		return commmentRepository.getCommentById(id);
	}

}
