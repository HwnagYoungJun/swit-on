package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Comment;

public interface CommentDAO {
	
	// 댓글 전체 조회
	List<Comment> selectComments();
	// 댓글 상세 조회
	Comment selectComment(int id);
	// 댓글 등록
	int insertComment(Comment comment);
	// 댓글 삭제
	int deleteComment(int id);
	// 댓글 수정
	int updateComment(Comment comment);
	// 게시글 아이디로 댓글 조회
	List<Comment> selectCommentsByArticleId(int articleId);
}
