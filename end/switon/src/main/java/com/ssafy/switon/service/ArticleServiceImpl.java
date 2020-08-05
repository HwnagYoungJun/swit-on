package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ArticleDAO;
import com.ssafy.switon.dao.BoardDAO;
import com.ssafy.switon.dao.JoinDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.Join;
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

	@Override
	public List<ArticleReturnDTO> searchArticlesByBoardId(int studyId, int boardId) {
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
			
			ArticleReturnDTO articleReturnDTO = new ArticleReturnDTO();
			articleReturnDTO.setId(article.getId());
			articleReturnDTO.setTitle(article.getTitle());
			articleReturnDTO.setContent(article.getContent());
			articleReturnDTO.setUser_id(article.getUser_id());			
			articleReturnDTO.setFile(article.getFile());			
			articleReturnDTO.setBoard_id(article.getBoard_id());			
			articleReturnDTO.setCreated_at(article.getCreated_at());			
			articleReturnDTO.setUpdated_at(article.getUpdated_at());			
			articleReturnDTO.setStudy(study);
			articleReturnDTO.setUser(user);;
			articleReturnDTOs.add(articleReturnDTO);
		}
		return articleReturnDTOs;
	}

	@Override
	public List<ArticleReturnDTO> searchFeeds(int userId) {
		List<Join> joins = joinDao.selectJoinsByUserId(userId);
		List<ArticleReturnDTO> articles = new LinkedList<ArticleReturnDTO>();
		for(Join join : joins) { // 유저가 가입한 스터디 id마다 boardId들을 추출해서 List 얻어내기
			int studyId = join.getStudy_id();
			for(int i = 1; i <= 3; i++) {
				List<ArticleReturnDTO> boardArticles = searchArticlesByBoardId(studyId, boardDao.findBoardId(studyId, i));
				articles.addAll(boardArticles);
			}
		}
		Collections.sort(articles, new Comparator<ArticleReturnDTO>() {
			@Override
			public int compare(ArticleReturnDTO article1, ArticleReturnDTO article2) {
				return article2.getCreated_at().compareTo(article1.getCreated_at());
			}
		});
		
		return articles;
	}

	@Override
	public List<ArticleReturnDTO> searchArticlesByBoardIdOrdered(int studyId, int boardId) {
		List<ArticleReturnDTO> articles = searchArticlesByBoardId(studyId, boardId);
		Collections.sort(articles, new Comparator<ArticleReturnDTO>() {
			@Override
			public int compare(ArticleReturnDTO article1, ArticleReturnDTO article2) {
				return article2.getCreated_at().compareTo(article1.getCreated_at());
			}
		});
		return articles;
	}
	


}
