package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Board;

public interface BoardService {

	List<Board> searchAll();
	List<Board> searchStudyBoards(int studyId);
	Board search(int id);
	boolean create(Board board);
	boolean delete(int id);
	int findNoticeBoardId(int studyId);
	int findQnABoardId(int studyId);
	int findRepoBoardId(int studyId);
}
