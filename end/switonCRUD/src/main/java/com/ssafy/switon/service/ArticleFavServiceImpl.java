package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ArticleFavDAO;
import com.ssafy.switon.dto.ArticleFav;

@Service
public class ArticleFavServiceImpl implements ArticleFavService {

	@Autowired
	ArticleFavDAO articlefavDAO;
	
	@Override
	public List<ArticleFav> searchAll() {
		return articlefavDAO.selectArticleFavs();
	}

	@Override
	public ArticleFav search(int id) {
		return articlefavDAO.selectArticleFav(id);
	}

	@Override
	public boolean create(ArticleFav articlefav) {
		return articlefavDAO.insertArticleFav(articlefav)==1;
	}

	@Override
	public boolean delete(int id) {
		return articlefavDAO.deleteArticleFav(id)==1;
	}

	@Override
	public List<ArticleFav> searchByUser(int user_id) {
		return articlefavDAO.selectArticleFavByUser(user_id);
	}

}
