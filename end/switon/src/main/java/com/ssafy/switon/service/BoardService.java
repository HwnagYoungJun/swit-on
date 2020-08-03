package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Board;

public interface BoardService {

	// 모든 게시판 리스트 반환(필요해...?)
	List<Board> searchAll();
	// 스터디 아이디에 맞는 게시판들만 반환 (QnA랑 자료실이 반환될 듯)
	List<Board> searchStudyBoards(int studyId);
	// 아이디로 게시판 반환
	Board search(int id);
	// 새로운 게시판 생성
	boolean create(Board board);
	// 게시판 삭제
	boolean delete(int id);
	// 스터디 아이디로 공지게시판 id 찾기
	int findNoticeBoardId(int studyId);
	// 스터디 아이디로 QnA게시판 id 찾기
	int findQnABoardId(int studyId);
	// 스터디 아이디로 자료실 게시판 id 찾기
	int findRepoBoardId(int studyId);
}
