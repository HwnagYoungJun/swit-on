package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.ArticleLike;
import com.ssafy.switon.dto.StudyLike;

@Repository
public class ArticleLikeDAOImpl implements ArticleLikeDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ArticleLike> selectArticleLikes() {
		return sqlSession.selectList("articlelike.selectArticleLikes");
	}

	@Override
	public ArticleLike selectArticleLike(int id) {
		return sqlSession.selectOne("articlelike.selectArticleLike", id);
	}

	@Override
	public int insertArticleLike(ArticleLike articlelike) {
		return sqlSession.insert("articlelike.insertArticleLike", articlelike);
	}

	@Override
	public int deleteArticleLike(int id) {
		return sqlSession.delete("articlelike.deleteArticleLike", id);
	}

	@Override
	public int selectLikeCount(int article_id) {
		return sqlSession.selectOne("articlelike.likeCount", article_id);
	}

	@Override
	public ArticleLike selectArticleLikeByUser_Article(int user_id, int article_id) {
		ArticleLike articlelike = new ArticleLike();
		articlelike.setUser_id(user_id);
		articlelike.setArticle_id(article_id);
		return sqlSession.selectOne("articlelike.selectArticleLikeByUser_Article", articlelike);
	}

	@Override
	public int deleteArticleLikeByUser(int user_id, int article_id) {
		ArticleLike articlelike = new ArticleLike();
		articlelike.setUser_id(user_id);
		articlelike.setArticle_id(article_id);
		return sqlSession.delete("articlelike.deleteArticleLikeByUser", articlelike);
	}

}
