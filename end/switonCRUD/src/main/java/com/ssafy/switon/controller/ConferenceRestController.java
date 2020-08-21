package com.ssafy.switon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Conference;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.service.ConferenceService;

import io.swagger.annotations.ApiOperation;

@RestController
public class ConferenceRestController {

	@Autowired
	ConferenceService conferenceService;
	
	@ApiOperation(value = "스터디 아이디로 회의 상세 조회한다", response = List.class)
	@GetMapping("/study/{study_id}/room")
	public Object searchConferenceByStudyId(@PathVariable("study_id") int study_id, HttpServletRequest request) {
		try {
			
			return new ResponseEntity<>(conferenceService.searchConference(study_id), HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("회의를 찾을 수 없습니다."), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@ApiOperation(value = "회의를 등록한다")
	@PostMapping("/study/{study_id}/room/{roomCode}")
	public Object createConference(@PathVariable("study_id") int study_id, @PathVariable("roomCode") String roomCode, HttpServletRequest request) {
		Conference conference = new Conference();
		
		conference.setRoomCode(roomCode);
		conference.setStudyId(study_id);
		
		return new ResponseEntity<>(conference, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회의를 삭제한다")
	@DeleteMapping("/study/{study_id}/room/{id}")
	public Object deleteConference(@PathVariable("study_id") int study_id, @PathVariable("id") int id, HttpServletRequest request) {
		Conference conference = conferenceService.searchConferenceById(id);
		if(conference == null) {
			return new ResponseEntity<>(new ReturnMsg("회의가 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
		}
		try {
			if(conferenceService.deleteConference(id)) {
				return new ResponseEntity<>(new ReturnMsg("회의를 삭제하였습니다."), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("회의를 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
