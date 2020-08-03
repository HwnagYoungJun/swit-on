package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<Board> selectBoards() {
		return sqlSession.selectList("board.selectBoards");
	}

	@Override
	public List<Board> selectBoardsByStudyId(int studyId) {
		return sqlSession.selectList("board.selectBoardsByStudyId", studyId);
	}

	@Override
	public Board selectBoardById(int id) {
		return sqlSession.selectOne("board.selectBoardById", id);
	}

	@Override
	public int insertBoard(Board board) {
		return sqlSession.insert("board.insertBoard", board);
	}

	@Override
	public int deleteBoard(int id) {
		return sqlSession.delete("board.deleteBoard", id);
	}

	@Override
	public int findQnABoardId(int studyId) {
		Integer boardId = sqlSession.selectOne("board.findQnABoardId", studyId);
		return boardId = boardId == null ? 0 : boardId;
	}

	@Override
	public int findRepoBoardId(int studyId) {
		Integer boardId = sqlSession.selectOne("board.findRepoBoardId", studyId);
		return boardId = boardId == null ? 0 : boardId;
	}

	@Override
	public int findNoticeBoardId(int studyId) {
		Integer boardId = sqlSession.selectOne("board.findNoticeBoardId", studyId);
		return boardId = boardId == null ? 0 : boardId;
	}
}
