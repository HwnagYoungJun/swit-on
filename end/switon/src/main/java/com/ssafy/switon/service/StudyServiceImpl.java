package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.switon.dao.JoinDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.UserStudyInfoDTO;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyDAO studyDao;
	
	@Autowired
	JoinDAO joinDao;
	
	@Autowired
	UserDAO userDao;
 	
	@Override
	public List<Study> searchAll() {
		return studyDao.selectStudies();
	}

	@Override
	public Study search(int id) {
		return studyDao.selectStudyById(id);
	}

	@Override
	public int create(Study study, int userId) {
		if(studyDao.insertStudy(study) == 1) {
			System.out.println("스터디 생성 성공");
			int studyId = studyDao.getRecentStudyIdByUserId(userId);
			return studyId;
		}
		return 0;
	}

	@Override
	public boolean modify(Study study) {
		return studyDao.updateStudy(study) == 1;
	}

	@Override
	public boolean delete(int id) {
		return studyDao.deleteStudy(id) == 1;
	}

	@Override
	public List<UserStudyInfoDTO> searchUserStudies(int userId) {
		List<UserStudyInfoDTO> list = new ArrayList<>();
		List<Join> joins = joinDao.selectJoinsByUserId(userId);
		for(Join join : joins) {
			UserStudyInfoDTO dto = new UserStudyInfoDTO();
			int studyId = join.getStudy_id();
			Study study = studyDao.selectStudyById(studyId);
			dto.setStudy(study);
			dto.setLeaderInfo(userDao.selectUserById(study.getUser_id()));
			dto.setUserCntCurrent(joinDao.countUsersByStudyId(studyId));
			list.add(dto);
		}
		return list;
	}

}
