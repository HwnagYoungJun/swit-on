package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Comment;

public interface CommentDAO {
	
	List<Comment> selectComments();
	Comment selectComment(int id);
	int insertComment(Comment comment);
	int deleteComment(int id);
	int updateComment(Comment comment);
	List<Comment> selectCommentsByArticleId(int articleId);
}
