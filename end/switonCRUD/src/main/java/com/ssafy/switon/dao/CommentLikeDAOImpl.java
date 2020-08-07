package com.ssafy.switon.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.switon.dto.ArticleLike;
import com.ssafy.switon.dto.CommentLike;

@Repository
public class CommentLikeDAOImpl implements CommentLikeDAO {

	@Autowired
	SqlSession sqlsession;
	
	@Override
	public List<CommentLike> selectCommentLikes() {
		return sqlsession.selectList("commentlike.selectCommentLikes");
	}

	@Override
	public CommentLike selectCommentLike(int id) {
		return sqlsession.selectOne("commentlike.selectCommentLike", id);
	}

	@Override
	public int insertCommentLike(CommentLike commentlike) {
		return sqlsession.insert("commentlike.insertCommentLike", commentlike);
	}

	@Override
	public int deleteCommentLike(int id) {
		return sqlsession.delete("commentlike.deleteCommentLike", id);
	}

	@Override
	public int selectLikeCount(int comment_id) {
		return sqlsession.selectOne("commentlike.likeCount", comment_id);
	}

	@Override
	public CommentLike selectCommentLikeByUser_Comment(int user_id, int comment_id) {
		CommentLike commentlike = new CommentLike();
		commentlike.setUser_id(user_id);
		commentlike.setComment_id(comment_id);
		return sqlsession.selectOne("commentlike.selectCommentLikeByUser_Article_Comment", commentlike);
	}

	@Override
	public int deleteCommentLikeByUser(int user_id, int comment_id) {
		CommentLike commentlike = new CommentLike();
		commentlike.setUser_id(user_id);
		commentlike.setComment_id(comment_id);
		return sqlsession.delete("commentlike.deleteCommentLikeByUser", commentlike);
	}

}
