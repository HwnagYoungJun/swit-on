package com.ssafy.switon.service;

import java.sql.Timestamp;
import java.util.List;

import com.ssafy.switon.dto.BestUsersReturnDTO;
import com.ssafy.switon.dto.SearchReturnDTO;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyCardDTO;
import com.ssafy.switon.dto.StudyReturnDTO;
import com.ssafy.switon.dto.StudyWithRate;
import com.ssafy.switon.dto.UserStudyInfoDTO;

public interface StudyService {
	
	List<Study> searchAll();
	Study search(int id);
	StudyReturnDTO search(int id, boolean isJoined, boolean isLeader, int userId);
	int create(Study study, int userId);
	boolean modify(Study study);
	boolean delete(int id);
	List<UserStudyInfoDTO> searchUserStudies(int userId);
	List<Study> searchStudiesByLowercategory(int lowercategory_id);
	List<Study> searchStudiesByUppercategory(int uppercategory_id);
	List<StudyCardDTO> searchStudyCardsByLowercategoryId(int lowercategory_id);
	List<StudyCardDTO> searchStudyCardsByUppercategory(int uppercategory_id);
	List<StudyCardDTO> searchStudyCards();
	SearchReturnDTO searchStudyByKeyword(String keyword);
	String finishStudies(Timestamp timestamp);
	BestUsersReturnDTO findBestUsers(int studyId);
	List<Study> searchStudiesByKeywordEnter(String keyword);
	List<StudyWithRate> searchEndStudyByUserId(int user_id);
	List<StudyWithRate> searchNotEndStudyByUserId(int user_id);

}
