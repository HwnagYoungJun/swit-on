package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.ArticleFav;
import com.ssafy.switon.dto.ArticleWithStudyDTO;

public interface ArticleFavService {
	
	List<ArticleFav> searchAll();
	ArticleFav search(int id);
	List<ArticleFav> searchByUser(int user_id);
	boolean create(ArticleFav articlefav);
	boolean delete(int id);
	boolean deleteByUser(int user_id, int article_id);
	ArticleFav searchByUser_Article(int userId, int articleId);
	List<ArticleWithStudyDTO> searchFavArticles(int userId);
}
