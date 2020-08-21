package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.AlarmDAO;
import com.ssafy.switon.dao.ArticleDAO;
import com.ssafy.switon.dao.CommentDAO;
import com.ssafy.switon.dao.CommentLikeDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.CommentReturnDTO;
import com.ssafy.switon.dto.Like;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserSimpleDTO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	StudyDAO studyDAO;
	
	@Autowired
	ArticleDAO articleDAO;
	
	@Autowired
	CommentDAO commentDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AlarmDAO alarmDAO;
	
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

	
	public boolean create(Comment comment) {
		return commentDAO.insertComment(comment) == 1;
	}
	
	@Override
	public boolean create(Comment comment, int studyId, int type, String type_name) {
		if(commentDAO.insertComment(comment) == 1) {
			Article article = articleDAO.selectArticleById(comment.getArticle_id());
			Study study = studyDAO.selectStudyById(studyId);
			Alarm alarm = new Alarm();
			alarm.setUser_id(article.getUser_id());
			alarm.setType(3); // comment: 3
			alarm.setMsg(study.getName() + " 스터디의 " + type_name + " 게시판에 쓴 글에 댓글이 달렸습니다.");
			alarm.setStudy_id(studyId);
			alarm.setArticle_id(comment.getArticle_id());
			alarm.setBoard_type(type);
			alarmDAO.insertAlarm(alarm);
		} else {
			return false;
		}
		return true;
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
