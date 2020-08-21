package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.CommentLike;

public interface CommentLikeService {
	List<CommentLike> searchAll();
	CommentLike search(int id);
	int searchLikeCount(int comment_id);
	boolean create(CommentLike commentlike);
	boolean delete(int id);
	boolean deleteByUser(int user_id, int comment_id);
	CommentLike searchByUser_Comment(int userId, int commentId);
}
