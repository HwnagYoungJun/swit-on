package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.ArticleWithLikesDTO;
import com.ssafy.switon.dto.ArticleWithStudyDTO;
import com.ssafy.switon.dto.BestArticles;

public interface ArticleService {
	
	// 모든 글 조회
	List<Article> searchAll();
	// 게시판 아이디에 맞는 게시글들만 반환
	List<Article> searchBoardArticles(int boardId);
	// 유저 아이디에 맞는 게시글들 반환
	List<Article> searchUserArticles(int userId);
	// 유저 아이디에 맞는 QnA 게시글들 반환
	List<ArticleWithStudyDTO> searchUserQnAs(int userId);
	// 유저 아이디에 맞는 자료실 게시글들 반환
	List<ArticleWithStudyDTO> searchUserRepositories(int userId);
	// id로 게시글 상세 조회
	Article search(int id);
	// 유저 아이디에 맞는 최신 게시글의 id 반환
	int searchUserRecentArticleId(int userId);
	// 게시글 생성
	int create(Article article, int studyId);
	// 게시글 수정
	boolean modify(Article article);
	// 게시글 삭제
	boolean delete(int id);
	// 게시판의 게시글 반환 new type
	List<ArticleReturnDTO> searchArticlesByBoardId(int studyId, int boardId, int type, int userId);
	// 게시판의 게시글 반환 new type 최신순
	List<ArticleReturnDTO> searchArticlesByBoardIdOrdered(int studyId, int boardId, int type, int userId);
	// 내가 가입한 스타디들의 게시글 반환 new type 최신순
	List<ArticleReturnDTO> searchFeeds(int userId, int startIdx, int endIdx);
	List<ArticleReturnDTO> searchArticlesByBoardIdLimit5(int studyId, int boardId, int type, int userId);
	// 인덱스로 아티클 반환
	List<ArticleReturnDTO> searchArticlesWithIndex(int boardId, int userId, int index);
	// qna랑 repo 각각 게시글 인기순 3개 반환
	BestArticles searchTopArticles(int studyId);
}
