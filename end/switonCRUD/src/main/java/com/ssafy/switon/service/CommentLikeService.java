package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.ArticleLike;
import com.ssafy.switon.dto.CommentLike;

public interface CommentLikeService {
	// 댓글 좋아요 모두 조회
	List<CommentLike> searchAll();
	// 댓글 좋아요 상세 조회
	CommentLike search(int id);
	// 댓글좋아요 수 조회
	int searchLikeCount(int comment_id);
	// 댓글 좋아요 등록
	boolean create(CommentLike commentlike);
	// 댓글 좋아요 삭제
	boolean delete(int id);
	// 유저가 좋아요 누른 댓글 조회
	CommentLike searchByUser_Comment(int userId, int commentId);
}
