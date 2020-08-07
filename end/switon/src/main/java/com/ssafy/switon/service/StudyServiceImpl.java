package com.ssafy.switon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.CategoryDAO;
import com.ssafy.switon.dao.JoinDAO;
import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dao.UserDAO;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyCardDTO;
import com.ssafy.switon.dto.StudyReturnDTO;
import com.ssafy.switon.dto.UpperCategory;
import com.ssafy.switon.dto.UserStudyInfoDTO;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyDAO studyDao;
	
	@Autowired
	JoinDAO joinDao;
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	CategoryDAO categoryDao;
 	
	@Override
	public List<Study> searchAll() {
		return studyDao.selectStudies();
	}

	@Override
	public Study search(int id) {
		return studyDao.selectStudyById(id);
	}
	
	@Override
	public StudyReturnDTO search(int id, boolean isJoined, boolean isLeader) {
		Study study = studyDao.selectStudyById(id);
		LowerCategory lowerCategory = categoryDao.selectLowOne(study.getLowercategory_id());
		UpperCategory upperCategory = categoryDao.selectUpOne(lowerCategory.getUppercategory_id());
		study.setUppercategory_id(upperCategory.getId());
		study.setUppercategory_name(upperCategory.getName());
		return new StudyReturnDTO(study, isJoined, isLeader);
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

	@Override
	public List<Study> searchStudiesByLowercategory(int lowercategory_id) {
		return studyDao.selectStudiesByLowercategoryId(lowercategory_id);
	}

	@Override
	public List<StudyCardDTO> searchStudyCardsByLowercategoryId(int lowercategory_id) {
		List<Study> studies = studyDao.selectStudiesByLowercategoryId(lowercategory_id);
		List<StudyCardDTO> studyCards = new ArrayList<StudyCardDTO>();
		for(Study study : studies) {
			studyCards.add(new StudyCardDTO(study.getId(), study.getName(), 
					study.getStart_time(), study.getEnd_time(), study.getWeek(), 
					study.getEnd_term(), study.getUsers_current(), study.getUsers_limit()));
		}
		return studyCards;
	}

	@Override
	public List<StudyCardDTO> searchStudyCards() {
		List<Study> studies = studyDao.selectStudies();
		List<StudyCardDTO> studyCards = new ArrayList<StudyCardDTO>();
		for(Study study : studies) {
			studyCards.add(new StudyCardDTO(study.getId(), study.getName(), 
					study.getStart_time(), study.getEnd_time(), study.getWeek(), 
					study.getEnd_term(), study.getUsers_current(), study.getUsers_limit()));
		}
		return studyCards;
	}

	

}
