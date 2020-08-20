package com.ssafy.switon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Conference;
import com.ssafy.switon.dto.ConferenceReturnDTO;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.service.ConferenceService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.ApiOperation;

@RestController
public class ConferenceRestController {

	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	JoinService joinService;
	
	@Autowired
	ConferenceService conferenceService;
	
	@ApiOperation(value = "스터디 아이디로 회의 상세 조회한다", response = List.class)
	@GetMapping("/study/{study_id}/room")
	public Object searchConferenceByStudyId(@PathVariable("study_id") int study_id, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(!joinService.isMember(study_id, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		int leaderId = studyService.search(study_id).getUser_id();
		boolean isLeader = false;
		if(userId == leaderId) {
			isLeader = true;
		}
		try {
			List<Conference> conferences = conferenceService.searchConference(study_id);
			ConferenceReturnDTO confReturnDTO = new ConferenceReturnDTO(conferences, isLeader);
			return new ResponseEntity<>(confReturnDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("회의를 찾을 수 없습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "회의를 등록한다")
	@PostMapping("/study/{study_id}/room")
	public Object createConference(@PathVariable("study_id") int study_id, @RequestBody String roomCode, HttpServletRequest request) {
		int userId = getUserPK(request);
		int leaderId = studyService.search(study_id).getUser_id();
		roomCode = roomCode.replace("=", "");
		if(userId != leaderId) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		Conference conference = new Conference();
		
		conference.setRoomCode(roomCode);
		conference.setStudyId(study_id);
		try {
			boolean success = conferenceService.createConference(conference);
			return new ResponseEntity<>("success", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("회의를 등록하지 못했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "회의를 삭제한다")
	@DeleteMapping("/study/{study_id}/room/{id}")
	public Object deleteConference(@PathVariable("study_id") int study_id, @PathVariable("id") int id, HttpServletRequest request) {
		int userId = getUserPK(request);
		int leaderId = studyService.search(study_id).getUser_id();
		if(userId != leaderId) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		Conference conference = conferenceService.searchConferenceById(id);
		if(conference == null) {
			return new ResponseEntity<>(new ReturnMsg("회의가 존재하지 않습니다."), HttpStatus.BAD_REQUEST);
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
	
	// Token(Authentication)에서 유저 id 정보를 뽑아내는 메소드
	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
}
