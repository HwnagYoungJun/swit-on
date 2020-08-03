package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Comment;

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
<<<<<<< HEAD
	
=======
	// 게시글의 댓글 조회
	List<Comment> searchArticleComments(int articleId);
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
}
