package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.CommentLike;

public interface CommentLikeDAO {
	List<CommentLike> selectCommentLikes();
	CommentLike selectCommentLike(int id);
	CommentLike selectCommentLikeByUser_Comment(int user_id, int comment_id);
	int selectLikeCount(int comment_id);
	int insertCommentLike(CommentLike commentlike);
	int deleteCommentLike(int id);
	int deleteCommentLikeByUser(int user_id, int comment_id);
}
