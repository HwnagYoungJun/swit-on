package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Article;

public interface ArticleDAO {
	
	// 모든 글 조회
	List<Article> selectArticles();
	// 게시판 아이디로 모든 글 조회
	List<Article> selectArticlesByBoardId(int boardId);
	// 유저 아이디로 모든 글 조회
	List<Article> selectArticlesByUserId(int userId);
	// id로 글 하나 조회
	Article selectArticleById(int id);
	// 유저 아이디로 최신글 아이디 하나 반환
	int getRecentArticleIdByUserId(int userId);
	// 글 작성
	int insertArticle(Article article);
	// 글 수정
	int updateArticle(Article article);
	// 글 삭제 (글 id로)
	int deleteArticle(int id);
	// 유저 아이디로 qna 게시글 모두 반환
	List<Article> selectQnasByUserId(int userId);
	// 유저 아이디로 repository 게시글 모두 반환
	List<Article> selectRepositoriesByUserId(int userId);

}
