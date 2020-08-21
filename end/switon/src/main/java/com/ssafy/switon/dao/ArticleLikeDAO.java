package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.ArticleLike;

public interface ArticleLikeDAO {
	
	List<ArticleLike> selectArticleLikes();
	ArticleLike selectArticleLike(int id);
	ArticleLike selectArticleLikeByUser_Article(int user_id, int article_id);
	int selectLikeCount(int article_id);
	int insertArticleLike(ArticleLike articlelike);
	int deleteArticleLike(int id);
	int deleteArticleLikeByUser(int user_id, int article_id);
	int cntLikesUserGot (int user_id, int study_id);
	int selectRecentLikeId(int user_id, int study_id);
}
