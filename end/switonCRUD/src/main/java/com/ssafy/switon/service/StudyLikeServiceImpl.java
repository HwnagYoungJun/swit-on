package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.StudyLikeDAO;
import com.ssafy.switon.dto.StudyLike;

@Service
public class StudyLikeServiceImpl implements StudyLikeService {

	@Autowired
	StudyLikeDAO studylikeDAO;
	
	@Override
	public List<StudyLike> searchAll() {
		return studylikeDAO.selectStudyLikes();
	}

	@Override
	public StudyLike search(int id) {
		return studylikeDAO.selectStudyLike(id);
	}

	@Override
	public boolean createStudyLike(StudyLike studylike) {
		return studylikeDAO.insertStudyLike(studylike)==1;
	}

	@Override
	public boolean deleteStudyLike(int id) {
		return studylikeDAO.deleteStudyLike(id)==1;
	}

	@Override
	public int searchLikeCount(int study_id) {
		return studylikeDAO.selectLikeCount(study_id);
	}

	@Override
	public StudyLike searchByUser_Study(int user_id, int study_id) {
		return studylikeDAO.selectStudyLikeByUser_Study(user_id, study_id);
	}

	@Override
	public boolean deleteStudyLikeByUser(int user_id, int study_id) {
		return studylikeDAO.deleteStudyLikeByUser(user_id, study_id)==1;
	}

}
