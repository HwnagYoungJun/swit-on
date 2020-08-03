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
<<<<<<< HEAD
		return sqlSession.selectList("selectArticles");
=======
		return sqlSession.selectList("article.selectArticles");
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
	}

	@Override
	public List<Article> selectArticlesByBoardId(int boardId) {
<<<<<<< HEAD
		return sqlSession.selectList("selectArticlesByBoardId", boardId);
=======
		return sqlSession.selectList("article.selectArticlesByBoardId", boardId);
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
	}

	@Override
	public List<Article> selectArticlesByUserId(int userId) {
<<<<<<< HEAD
		return sqlSession.selectList("selectArticlesByUserId", userId);
=======
		return sqlSession.selectList("article.selectArticlesByUserId", userId);
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
	}

	@Override
	public Article selectArticleById(int id) {
<<<<<<< HEAD
		return sqlSession.selectOne("selectArticleById", id);
=======
		return sqlSession.selectOne("article.selectArticle", id);
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
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
