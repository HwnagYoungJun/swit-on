package com.ssafy.switon.service;

import java.util.List;

import com.ssafy.switon.dto.Conference;

public interface ConferenceService {
	
	Conference searchConferenceById(int id);
	List<Conference> searchConference(int studyId);
	boolean createConference(Conference conference);
	boolean deleteConference(int id);
}
