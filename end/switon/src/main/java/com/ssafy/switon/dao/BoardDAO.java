package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Board;

public interface BoardDAO {
	
	// 게시판 전체 조회
	List<Board> selectBoards();
	// 스터디 아이디로 게시판 리스트 검색
	List<Board> selectBoardsByStudyId(int studyId);
	// 아이디로 게시판 상세 조회 필요한가...? 일단 만들어봄
	Board selectBoardById(int id);
	// 스터디 아이디로 공지 게시판 아이디 찾기
	int findNoticeBoardId(int studyId);
	// 스터디 아이디로 QnA 게시판 아이디 찾기
	int findQnABoardId(int studyId);
	// 스터디 아이디 + 게시판 타입(QnA)으로 게시판 아이디 찾기
	int findRepoBoardId(int studyId);
	// 게시판 추가
	int insertBoard(Board board);
	// 게시판 삭제
	int deleteBoard(int id);

}
