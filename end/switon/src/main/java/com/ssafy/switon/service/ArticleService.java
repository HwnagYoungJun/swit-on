package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleReturnDTO;

public interface ArticleService {
	
	// 모든 글 조회
	List<Article> searchAll();
	// 게시판 아이디에 맞는 게시글들만 반환
	List<Article> searchBoardArticles(int boardId);
	// 유저 아이디에 맞는 게시글들 반환
	List<Article> searchUserArticles(int userId);
	// 유저 아이디에 맞는 QnA 게시글들 반환
	List<Article> searchUserQnAs(int userId);
	// 유저 아이디에 맞는 자료실 게시글들 반환
	List<Article> searchUserRepositories(int userId);
	// id로 게시글 상세 조회
	Article search(int id);
	// 유저 아이디에 맞는 최신 게시글의 id 반환
	int searchUserRecentArticleId(int userId);
	// 게시글 생성
	boolean create(Article article);
	// 게시글 수정
	boolean modify(Article article);
	// 게시글 삭제
	boolean delete(int id);
	// 게시글 반환 new type
	List<ArticleReturnDTO> searchArticles(int studyId, int boardId);

}
