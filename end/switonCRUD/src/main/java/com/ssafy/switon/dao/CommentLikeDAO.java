package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.CommentLike;

public interface CommentLikeDAO {
	// 댓글 좋아요 전체 조회
	List<CommentLike> selectCommentLikes();
	// 댓글 좋아요 상세 조회
	CommentLike selectCommentLike(int id);
	// 해당 댓글 좋아요수 조회
	int selectLikeCount(int comment_id);
	// 댓글 좋아요 등록
	int insertCommentLike(CommentLike commentlike);
	// 댓글 좋아요 삭제
	int deleteCommentLike(int id);
}
