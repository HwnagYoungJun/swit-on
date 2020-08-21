package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.StudyDAO;
import com.ssafy.switon.dto.Study;

@Service
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyDAO studyDAO;
	
	@Override
	public List<Study> searchStudyByKeyword(String keyword) {
		return studyDAO.selectStudyByKeyword(keyword);
	}

}
