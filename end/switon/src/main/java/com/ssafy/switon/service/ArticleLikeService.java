package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.ArticleLike;

public interface ArticleLikeService {
	
	List<ArticleLike> searchAll();
	ArticleLike search(int id);
	int searchLikeCount(int article_id);
	boolean createArticleLike(ArticleLike articlelike);
	boolean deleteArticleLike(int id);
	boolean deleteArticleLikeByUser(int user_id, int article_id);
	ArticleLike searchByUser_Article(int userId, int articleId);
}
