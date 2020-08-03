package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.BoardDAO;
import com.ssafy.switon.dto.Board;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDao;
	
	@Override
	public List<Board> searchAll() {
		return boardDao.selectBoards();
	}

	@Override
	public List<Board> searchStudyBoards(int studyId) {
		return boardDao.selectBoardsByStudyId(studyId);
	}

	@Override
	public Board search(int id) {
		return boardDao.selectBoardById(id);
	}

	@Override
	public boolean create(Board board) {
		return boardDao.insertBoard(board) == 1;
	}

	@Override
	public boolean delete(int id) {
		return boardDao.deleteBoard(id) == 1;
	}

	@Override
	public int findQnABoardId(int studyId) {
		return boardDao.findQnABoardId(studyId);
	}

	@Override
	public int findRepoBoardId(int studyId) {
		return boardDao.findRepoBoardId(studyId);
	}

	@Override
	public int findNoticeBoardId(int studyId) {
		return boardDao.findNoticeBoardId(studyId);
	}


}
