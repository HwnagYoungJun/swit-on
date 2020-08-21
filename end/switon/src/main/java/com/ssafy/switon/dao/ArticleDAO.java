package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleWithLikesDTO;
import com.ssafy.switon.dto.BoardIndexDTO;
import com.ssafy.switon.dto.FeedsIndexDTO;

public interface ArticleDAO {
	
	List<Article> selectArticles();
	List<Article> selectArticlesByBoardId(int boardId);
	List<Article> selectArticlesByBoardIdLimit5(BoardIndexDTO boardIndexDTO);
	List<Article> selectArticlesByUserId(int userId);
	Article selectArticleById(int id);
	int getRecentArticleIdByUserId(int userId);
	int insertArticle(Article article);
	int updateArticle(Article article);
	int deleteArticle(int id);
	List<Article> selectQnasByUserId(int userId);
	List<Article> selectRepositoriesByUserId(int userId);
	List<Article> selectFeeds(FeedsIndexDTO feedsIndexDTO);
	int cntUserArticlesByStudyId(int user_id, int study_id);
	int selectRecentUserArticleId(int user_id, int study_id);
	List<ArticleWithLikesDTO> selectTopThreeArticles(int boardId);

}
