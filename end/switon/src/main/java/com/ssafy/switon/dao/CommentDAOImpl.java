package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<Comment> selectComments() {
		return sqlsession.selectList("comment.selectComments");
	}

	@Override
	public Comment selectComment(int id) {
		return sqlsession.selectOne("comment.selectComment", id);
	}

	@Override
	public int insertComment(Comment comment) {
		return sqlsession.insert("comment.insertComment", comment);
	}

	@Override
	public int deleteComment(int id) {
		return sqlsession.delete("comment.deleteComment", id);
	}

	@Override
	public int updateComment(Comment comment) {
		return sqlsession.update("comment.updateComment", comment);
	}

}
