package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.CommentReturnDTO;

public interface CommentService {

	// 댓글 전체 조회
	List<Comment> searchAll();
	// 댓글 상세 조회
	Comment search(int id);
	// 댓글 등록
	boolean create(Comment comment);
	// 댓글 삭제
	boolean delete(int id);
	// 댓글 수정
	boolean update(Comment comment);
	// 게시글의 댓글 조회
	List<Comment> searchArticleComments(int articleId);
	
	List<CommentReturnDTO> searchArticleCommentsIncludingProfile(int articleId, int readerId);
}
