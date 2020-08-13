package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.CommentDAO;
import com.ssafy.switon.dao.CommentLikeDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.CommentReturnDTO;
import com.ssafy.switon.dto.Like;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserSimpleDTO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	CommentLikeDAO commentLikeDAO;
	
	@Override
	public List<Comment> searchAll() {
		return commentDAO.selectComments();
	}

	@Override
	public Comment search(int id) {
		return commentDAO.selectComment(id);
	}

	@Override
	public boolean create(Comment comment) {
		return commentDAO.insertComment(comment)==1;
	}

	@Override
	public boolean delete(int id) {
		return commentDAO.deleteComment(id)==1;
	}

	@Override
	public boolean update(Comment comment) {
		return commentDAO.updateComment(comment)==1;
	}

	@Override
	public List<Comment> searchArticleComments(int articleId) {
		return commentDAO.selectCommentsByArticleId(articleId);
	}

	@Override
	public List<CommentReturnDTO> searchArticleCommentsIncludingProfile(int articleId, int readerId) {
		List<Comment> originalComments = commentDAO.selectCommentsByArticleId(articleId);
		List<CommentReturnDTO> comments = new ArrayList<CommentReturnDTO>();
		for(Comment originalComment : originalComments) {
			UserInfoDTO userInfo = userDAO.selectUserById(originalComment.getUser_id());
			UserSimpleDTO user = new UserSimpleDTO();
			user.setId(userInfo.getId());
			user.setName(userInfo.getName());
			user.setProfile_image(userInfo.getProfile_image());
			Like like = new Like(commentLikeDAO.selectLikeCount(originalComment.getId())
					, commentLikeDAO.selectCommentLikeByUser_Comment(readerId, originalComment.getId()) != null);
			CommentReturnDTO comment = new CommentReturnDTO(originalComment, user, like);
			comments.add(comment);
		}
		return comments;
	}

}
