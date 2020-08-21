package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Board;

public interface BoardDAO {
	
	List<Board> selectBoards();
	List<Board> selectBoardsByStudyId(int studyId);
	Board selectBoardById(int id);
	int findNoticeBoardId(int studyId);
	int findQnABoardId(int studyId);
	int findRepoBoardId(int studyId);
	int insertBoard(Board board);
	int deleteBoard(int id);
	int findBoardId(int studyId, int type);
	int findStudyIdById(int id);

}
