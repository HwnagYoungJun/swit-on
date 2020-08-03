package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ArticleDAO;
import com.ssafy.switon.dto.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDAO articleDao;
	
	@Override
	public List<Article> searchAll() {
		return articleDao.selectArticles();
	}

	@Override
	public List<Article> searchBoardArticles(int boardId) {
		return articleDao.selectArticlesByBoardId(boardId);
	}

	@Override
	public List<Article> searchUserArticles(int userId) {
		return articleDao.selectArticlesByUserId(userId);
	}

	@Override
	public Article search(int id) {
		return articleDao.selectArticleById(id);
	}

	@Override
	public boolean create(Article article) {
		return articleDao.insertArticle(article) == 1;
	}

	@Override
	public boolean modify(Article article) {
		return articleDao.updateArticle(article) == 1;
	}

	@Override
	public boolean delete(int id) {
		return articleDao.deleteArticle(id) == 1;
	}

	@Override
	public int searchUserRecentArticleId(int userId) {
		return articleDao.getRecentArticleIdByUserId(userId);
	}

	@Override
	public List<Article> searchUserQnAs(int userId) {
		return articleDao.selectQnasByUserId(userId);
	}

	@Override
	public List<Article> searchUserRepositories(int userId) {
		return articleDao.selectRepositoriesByUserId(userId);
	}

}
