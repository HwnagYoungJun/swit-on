package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.ArticleFav;

public interface ArticleFavDAO {
	List<ArticleFav> selectArticleFavs();
	ArticleFav selectArticleFav(int id);
	List<ArticleFav> selectArticleFavByUser(int user_id);
	int insertArticleFav(ArticleFav articlefav);
	int deleteArticleFav(int id);
	int deleteArticleFavByUser(int user_id, int article_id);
	ArticleFav selectArticleFavByUser_Article(int user_id, int article_id);
	List<Integer> selectArticleFavIdsByUserId(int userId);
	
}
