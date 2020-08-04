package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.CommentLikeDAO;
import com.ssafy.switon.dto.CommentLike;

@Service
public class CommentLikeServiceImpl implements CommentLikeService {

	@Autowired
	CommentLikeDAO commentlikeDAO;
	
	@Override
	public List<CommentLike> searchAll() {
		return commentlikeDAO.selectCommentLikes();
	}

	@Override
	public CommentLike search(int id) {
		return commentlikeDAO.selectCommentLike(id);
	}

	@Override
	public boolean create(CommentLike commentlike) {
		return commentlikeDAO.insertCommentLike(commentlike)==1;
	}

	@Override
	public boolean delete(int id) {
		return commentlikeDAO.deleteCommentLike(id)==1;
	}

	@Override
	public int searchLikeCount(int comment_id) {
		return commentlikeDAO.selectLikeCount(comment_id);
	}

	@Override
	public CommentLike searchByUser_Comment(int userId, int commentId) {
		return commentlikeDAO.selectCommentLikeByUser_Comment(userId, commentId);
	}

}
