package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.ArticleFav;

@Repository
public class ArticleFavDAOImpl implements ArticleFavDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<ArticleFav> selectArticleFavs() {
		return sqlsession.selectList("articlefav.selectArticleFavs");
	}

	@Override
	public ArticleFav selectArticleFav(int id) {
		return sqlsession.selectOne("articlefav.selectArticleFav", id);
	}

	@Override
	public int insertArticleFav(ArticleFav articlefav) {
		return sqlsession.insert("articlefav.insertArticleFav", articlefav);
	}

	@Override
	public int deleteArticleFav(int id) {
		return sqlsession.delete("articlefav.deleteArticleFav", id);
	}

	@Override
	public List<ArticleFav> selectArticleFavByUser(int user_id) {
		return sqlsession.selectList("articlefav.selectArticleFavByUser", user_id);
	}

}
