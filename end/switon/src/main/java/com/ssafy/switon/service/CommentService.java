package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.CommentReturnDTO;

public interface CommentService {

	List<Comment> searchAll();
	Comment search(int id);
	boolean create(Comment comment);
	boolean create(Comment comment, int studyId, int type, String type_name);
	boolean delete(int id);
	boolean update(Comment comment);
	List<Comment> searchArticleComments(int articleId);
	List<CommentReturnDTO> searchArticleCommentsIncludingProfile(int articleId, int readerId);
}
