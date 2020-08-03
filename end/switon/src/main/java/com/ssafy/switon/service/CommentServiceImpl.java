package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.CommentDAO;
import com.ssafy.switon.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO commentDAO;
	
	@Override
	public List<Comment> searchAll() {
		return commentDAO.selectComments();
	}

	@Override
	public Comment search(int id) {
		return commentDAO.selectComment(id);
	}

	@Override
	public boolean create(Comment comment) {
		return commentDAO.insertComment(comment)==1;
	}

	@Override
	public boolean delete(int id) {
		return commentDAO.deleteComment(id)==1;
	}

	@Override
	public boolean update(Comment comment) {
		return commentDAO.updateComment(comment)==1;
	}

	@Override
	public List<Comment> searchArticleComments(int articleId) {
		return commentDAO.selectCommentsByArticleId(articleId);
	}

}
