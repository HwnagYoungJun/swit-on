package com.ssafy.switon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.switon.dao.ConferenceDAO;
import com.ssafy.switon.dto.Conference;

@Service
public class ConferenceServiceImpl implements ConferenceService {

	@Autowired
	ConferenceDAO conferenceDAO;
	
	@Override
	public Conference searchConferenceById(int id) {
		return conferenceDAO.selectConferenceById(id);
	}

	@Override
	public List<Conference> searchConference(int studyId) {
		return conferenceDAO.selectConferenceByStudyId(studyId);
	}

	@Override
	public boolean createConference(Conference conference) {
		return conferenceDAO.insertConference(conference)==1;
	}

	@Override
	public boolean deleteConference(int id) {
		return conferenceDAO.deleteConference(id)==1;
	}

}
