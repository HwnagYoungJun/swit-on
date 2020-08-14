package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ArticleDAO;
import com.ssafy.switon.dao.ArticleLikeDAO;
import com.ssafy.switon.dao.BoardDAO;
import com.ssafy.switon.dao.JoinDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.ArticleWithStudyDTO;
import com.ssafy.switon.dto.Board;
import com.ssafy.switon.dto.FeedsIndexDTO;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.Like;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudySimple;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserSimpleDTO;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDAO articleDao;
	
	@Autowired
	StudyDAO studyDao;
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	JoinDAO joinDao;
	
	@Autowired
	BoardDAO boardDao;
	
	@Autowired
	ArticleLikeDAO articleLikeDao;
	
	@Autowired
	ArticleLikeService articleLikeService;
	
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
	public List<ArticleWithStudyDTO> searchUserQnAs(int userId) {
		List<Article> originalArticles = articleDao.selectQnasByUserId(userId);
		List<ArticleWithStudyDTO> articles = new ArrayList<ArticleWithStudyDTO>();
		if(originalArticles.size() != 0) {
			for(Article article : originalArticles) {
				Board board = boardDao.selectBoardById(article.getBoard_id());
				
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
				
				int studyId = boardDao.findStudyIdById(board.getId());
				StudySimple study = new StudySimple(studyDao.selectStudyById(studyId));
				articles.add(new ArticleWithStudyDTO(article, study, board_name));
			}
			return articles;
		}
		return null;
	}

	@Override
	public List<ArticleWithStudyDTO> searchUserRepositories(int userId) {
		List<Article> originalArticles = articleDao.selectRepositoriesByUserId(userId);
		List<ArticleWithStudyDTO> articles = new ArrayList<ArticleWithStudyDTO>();
		if(originalArticles.size() != 0) {
			for(Article article : originalArticles) {
				Board board = boardDao.selectBoardById(article.getBoard_id());
				
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
				
				int studyId = boardDao.findStudyIdById(board.getId());
				StudySimple study = new StudySimple(studyDao.selectStudyById(studyId));
				articles.add(new ArticleWithStudyDTO(article, study, board_name));
			}
			return articles;
		}
		return null;
	}

	@Override
	public List<ArticleReturnDTO> searchArticlesByBoardId(int studyId, int boardId, int type, int userId) {
		String board_name = "notice";
		switch(type) {
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
		List<Article> articles = articleDao.selectArticlesByBoardId(boardId);
		List<ArticleReturnDTO> articleReturnDTOs = new ArrayList<ArticleReturnDTO>();
		Study originalStudy = studyDao.selectStudyById(studyId);
			StudySimple study = new StudySimple();
			study.setId(originalStudy.getId());
			study.setLogo(originalStudy.getLogo());
			study.setName(originalStudy.getName());
		for(Article article : articles) {
			UserInfoDTO userInfoDTO = userDao.selectUserById(article.getUser_id());
			UserSimpleDTO user = new UserSimpleDTO();
			user.setId(userInfoDTO.getId());
			user.setName(userInfoDTO.getName());
			if(userInfoDTO.getProfile_image() != null) {
				user.setProfile_image(userInfoDTO.getProfile_image());				
			}
			int articleId = article.getId();
			boolean isLiked = articleLikeService.searchByUser_Article(userId, articleId) != null;
			Like like = new Like(articleLikeService.searchLikeCount(articleId),
					isLiked);
			System.out.println(like);
			ArticleReturnDTO articleReturnDTO = new ArticleReturnDTO();
			articleReturnDTO.setId(articleId);
			articleReturnDTO.setTitle(article.getTitle());
			articleReturnDTO.setContent(article.getContent());
			articleReturnDTO.setUser_id(article.getUser_id());			
			articleReturnDTO.setFile(article.getFile());			
			articleReturnDTO.setBoard_id(article.getBoard_id());			
			articleReturnDTO.setCreated_at(article.getCreated_at());			
			articleReturnDTO.setUpdated_at(article.getUpdated_at());			
			articleReturnDTO.setStudy(study);
			articleReturnDTO.setUser(user);
			articleReturnDTO.setBoard_name(board_name);
			articleReturnDTO.setLike(like);
			articleReturnDTOs.add(articleReturnDTO);
		}
		return articleReturnDTOs;
	}
	
	@Override
	public List<ArticleReturnDTO> searchArticlesByBoardIdLimit5(int studyId, int boardId, int type, int userId) {
		String board_name = "notice";
		switch(type) {
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
		List<Article> articles = articleDao.selectArticlesByBoardIdLimit5(boardId);
		List<ArticleReturnDTO> articleReturnDTOs = new ArrayList<ArticleReturnDTO>();
		Study originalStudy = studyDao.selectStudyById(studyId);
			StudySimple study = new StudySimple();
			study.setId(originalStudy.getId());
			study.setLogo(originalStudy.getLogo());
			study.setName(originalStudy.getName());
		for(Article article : articles) {
			UserInfoDTO userInfoDTO = userDao.selectUserById(article.getUser_id());
			UserSimpleDTO user = new UserSimpleDTO();
			user.setId(userInfoDTO.getId());
			user.setName(userInfoDTO.getName());
			if(userInfoDTO.getProfile_image() != null) {
				user.setProfile_image(userInfoDTO.getProfile_image());				
			}
			int articleId = article.getId();
			boolean isLiked = articleLikeService.searchByUser_Article(userId, articleId) != null;
			Like like = new Like(articleLikeService.searchLikeCount(articleId),
					isLiked);
			System.out.println(like);
			ArticleReturnDTO articleReturnDTO = new ArticleReturnDTO();
			articleReturnDTO.setId(articleId);
			articleReturnDTO.setTitle(article.getTitle());
			articleReturnDTO.setContent(article.getContent());
			articleReturnDTO.setUser_id(article.getUser_id());			
			articleReturnDTO.setFile(article.getFile());			
			articleReturnDTO.setBoard_id(article.getBoard_id());			
			articleReturnDTO.setCreated_at(article.getCreated_at());			
			articleReturnDTO.setUpdated_at(article.getUpdated_at());			
			articleReturnDTO.setStudy(study);
			articleReturnDTO.setUser(user);
			articleReturnDTO.setBoard_name(board_name);
			articleReturnDTO.setLike(like);
			articleReturnDTOs.add(articleReturnDTO);
		}
		return articleReturnDTOs;
	}

	@Override
	public List<ArticleReturnDTO> searchFeeds(int userId, int startIdx, int endIdx) {
		FeedsIndexDTO dto = new FeedsIndexDTO(userId, startIdx, endIdx);
		List<Article> originalArticles = articleDao.selectFeeds(dto);
		List<ArticleReturnDTO> articles = new LinkedList<ArticleReturnDTO>();
		if(originalArticles.size() != 0) {
			for(Article originalArticle : originalArticles) {
				String board_name = "repository";
				Board board = boardDao.selectBoardById(originalArticle.getBoard_id());
				switch(board.getId()) {
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
				StudySimple study = new StudySimple(studyDao.selectStudyById(board.getStudy_id()));
//				Like like = 
//				ArticleReturnDTO articleLikeDao = new ArticleReturnDTO();
			}
		}
		return articles;
	}

	@Override
	public List<ArticleReturnDTO> searchArticlesByBoardIdOrdered(int studyId, int boardId, int type, int userId) {
		List<ArticleReturnDTO> articles = searchArticlesByBoardId(studyId, boardId, type, userId);
		Collections.sort(articles, new Comparator<ArticleReturnDTO>() {
			@Override
			public int compare(ArticleReturnDTO article1, ArticleReturnDTO article2) {
				return article2.getCreated_at().compareTo(article1.getCreated_at());
			}
		});
		return articles;
	}
	


}
