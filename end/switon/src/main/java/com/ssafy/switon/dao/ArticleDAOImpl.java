package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Article;

@Repository
public class ArticleDAOImpl implements ArticleDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Article> selectArticles() {
		return sqlSession.selectList("selectArticles");
	}

	@Override
	public List<Article> selectArticlesByBoardId(int boardId) {
		return sqlSession.selectList("selectArticlesByBoardId", boardId);
	}

	@Override
	public List<Article> selectArticlesByUserId(int userId) {
		return sqlSession.selectList("selectArticlesByUserId", userId);
	}

	@Override
	public Article selectArticleById(int id) {
		return sqlSession.selectOne("selectArticleById", id);
	}

	@Override
	public int insertArticle(Article article) {
		return sqlSession.insert("article.insertArticle", article);
	}

	@Override
	public int updateArticle(Article article) {
		return sqlSession.update("article.updateArticle", article);
	}

	@Override
	public int deleteArticle(int id) {
		return sqlSession.delete("article.deleteArticle", id);
	}

	@Override
	public int getRecentArticleIdByUserId(int userId) {
		Integer articleId = sqlSession.selectOne("article.getRecentArticleIdByUserId", userId);
		return articleId = articleId == null ? 0 : articleId;
	}

	@Override
	public List<Article> selectQnasByUserId(int userId) {
		return sqlSession.selectList("article.selectQnAsByUserId", userId);
	}

	@Override
	public List<Article> selectRepositoriesByUserId(int userId) {
		return sqlSession.selectList("article.selectReposByUserId", userId);
	}

}
