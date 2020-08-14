package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ArticleDAO;
import com.ssafy.switon.dao.ArticleFavDAO;
import com.ssafy.switon.dao.BoardDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleFav;
import com.ssafy.switon.dto.ArticleFavReturnDTO;
import com.ssafy.switon.dto.ArticleWithStudyDTO;
import com.ssafy.switon.dto.Board;
import com.ssafy.switon.dto.StudySimple;
import com.ssafy.switon.dto.UserSimpleDTO;

@Service
public class ArticleFavServiceImpl implements ArticleFavService {

	@Autowired
	ArticleFavDAO articlefavDAO;
	
	@Autowired
	ArticleDAO articleDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	StudyDAO studyDAO;
	
	@Autowired
	BoardDAO boardDAO;
	
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

	@Override
	public ArticleFav searchByUser_Article(int userId, int articleId) {
		return articlefavDAO.selectArticleFavByUser_Article(userId, articleId);
	}

	@Override
	public boolean deleteByUser(int user_id, int article_id) {
		return articlefavDAO.deleteArticleFavByUser(user_id, article_id)==1;
	}

	@Override
	public List<ArticleWithStudyDTO> searchFavArticles(int userId) {
		List<Integer> ids = articlefavDAO.selectArticleFavIdsByUserId(userId);
		List<ArticleWithStudyDTO> articles = new ArrayList<ArticleWithStudyDTO>();
		if(ids.size() != 0) {
			for(int id : ids) {
				Article article = articleDAO.selectArticleById(id);
				Board board = boardDAO.selectBoardById(article.getBoard_id());
				
				String board_name = "repository";
				switch(board.getType()) {
				case 1:
					board_name = "notice";
					break;
				case 2:
					board_name = "qna";
					break;
				case 3:
					board_name = "repository";
					break;
				}
				
				int studyId = boardDAO.findStudyIdById(article.getBoard_id());				
				StudySimple study = new StudySimple(studyDAO.selectStudyById(studyId));
				articles.add(new ArticleWithStudyDTO(article, study, board_name));
			}
			return articles;
		}		
		return null;
	}

//	@Override
//	public List<ArticleFavReturnDTO> searchFavList(int userId) {
//		List<Integer> ids = articlefavDAO.selectArticleFavIdsByUserId(userId);
//		List<ArticleFavReturnDTO> favs = new ArrayList<ArticleFavReturnDTO>();
//		if(ids.size() != 0) {
//			for(int id : ids) {
//				Article article = articleDAO.selectArticleById(id);
//				UserSimpleDTO user = new UserSimpleDTO(userDAO.selectUserById(userId));
//				Board board = boardDAO.selectBoardById(article.getBoard_id());
//				String board_name = "repository";
//				switch(board.getType()) {
//				case 1:
//					board_name = "notice";
//					break;
//				case 2:
//					board_name = "qna";
//					break;
//				case 3:
//					board_name = "repository";
//					break;
//				}
//				StudySimple study = new StudySimple(studyDAO.selectStudyById(board.getStudy_id()));
//				ArticleFavReturnDTO fav = new ArticleFavReturnDTO(article, user, study, board_name);
//				favs.add(fav);
//			}
//			return favs;
//		}
//		return null;
//	}

}
