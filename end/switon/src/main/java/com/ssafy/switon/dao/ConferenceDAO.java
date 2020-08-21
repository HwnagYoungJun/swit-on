package com.ssafy.switon.dao;

import java.util.List;

import com.ssafy.switon.dto.Conference;

public interface ConferenceDAO {
	
	Conference selectConferenceById(int id);
	List<Conference> selectConferenceByStudyId(int studyId);
	int insertConference(Conference conference);
	int deleteConference(int id);
	
}
