package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.ArticleWithStudyDTO;
import com.ssafy.switon.dto.BestArticles;

public interface ArticleService {
	
	List<Article> searchAll();
	List<Article> searchBoardArticles(int boardId);
	List<Article> searchUserArticles(int userId);
	List<ArticleWithStudyDTO> searchUserQnAs(int userId);
	List<ArticleWithStudyDTO> searchUserRepositories(int userId);
	Article search(int id);
	int searchUserRecentArticleId(int userId);
	int create(Article article, int studyId);
	boolean modify(Article article);
	boolean delete(int id);
	List<ArticleReturnDTO> searchArticlesByBoardId(int studyId, int boardId, int type, int userId);
	List<ArticleReturnDTO> searchArticlesByBoardIdOrdered(int studyId, int boardId, int type, int userId);
	List<ArticleReturnDTO> searchFeeds(int userId, int startIdx, int endIdx);
	List<ArticleReturnDTO> searchArticlesByBoardIdLimit5(int studyId, int boardId, int type, int userId);
	List<ArticleReturnDTO> searchArticlesWithIndex(int boardId, int userId, int index);
	BestArticles searchTopArticles(int studyId);
	Article search(int articleId, int studyId, int type);
}
