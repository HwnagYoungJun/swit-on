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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.ParticipateInfo;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.ScheduleReturnDTO;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.UserSchedule;
import com.ssafy.switon.dto.UserScheduleReturnDTO;
import com.ssafy.switon.dto.UserSimpleDTO;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.ScheduleService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.service.UserScheduleService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/study")
@Api(value="UserScheduleRestController", description="스케줄과 관련된 모든 것들")
public class ScheduleRestController {

	@Autowired
	UserScheduleService userscheduleService;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	JoinService joinService;
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@ApiOperation(value = "해당 스터디의 스케줄들 반환", response = List.class)
	@GetMapping("/{studyId}/schedule")
	private Object searchSchedules(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 스터디 스케줄 조회 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		try {
			List<ScheduleReturnDTO> schedules = scheduleService.selectSchedulesByStudyId(studyId);
			System.out.println(studyId + "번 스터디의 스케줄 리스트 반환 성공");			
			return new ResponseEntity<>(schedules, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("스케줄을 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "새로운 스케줄 생성")
	@PostMapping("/{studyId}/schedule")
	private Object createSchedule(@RequestBody Schedule schedule, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Study study = studyService.search(studyId);
		if(userId != study.getUser_id()) {
			System.out.println("** 스케줄 생성 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		schedule.setStudy_id(studyId);
		schedule.setUser_id(userId);
		String result = scheduleService.createSchedule(schedule);
		if(result.equals("success")) {
			System.out.println(studyId + "번 스터디에 스케줄 생성 성공!");
			return new ResponseEntity<>(new ReturnMsg("스케줄을 성공적으로 생성했습니다."), HttpStatus.OK);
		}
		if(result.equals("fail")) {
			System.out.println("** 스케줄 생성 실패 - 서버 에러");
			return new ResponseEntity<>(new ReturnMsg("스케줄을 생성할 수 없었습니다. 서버 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		System.out.println("스케줄 생성 실패 - " + result);
		return new ResponseEntity<>(new ReturnMsg(result), HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스케줄 조회", response = Schedule.class)
	@GetMapping("/{studyId}/schedule/{scheduleId}")
	private Object searchSchedule(@PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 스케줄 조회 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		Schedule schedule = scheduleService.selectScheduleById(scheduleId);
		if(schedule == null) {
			return new ResponseEntity<>(new ReturnMsg("스케줄을 찾을 수 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		ScheduleReturnDTO dto = new ScheduleReturnDTO(schedule);
		if(studyId != schedule.getStudy_id()) {
			System.out.println("** 스케줄 조회 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		Study study = studyService.search(studyId);
		if(study == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 스터디입니다."), HttpStatus.UNAUTHORIZED);
		}
		dto.setStudy_name(study.getName());
		try {
			List<UserSimpleDTO> members = userscheduleService.searchParticipants(scheduleId);
			dto.setMembers(members);
			System.out.println(scheduleId + "번 스케줄 조회 성공");
			return new ResponseEntity<>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("스케줄 정보를 가져올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
	}
	
	@ApiOperation(value = "스케줄 수정")
	@PutMapping("/{studyId}/schedule/{scheduleId}")
	private Object modifySchedule(@RequestBody Schedule schedule, @PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		Schedule originalSchedule = scheduleService.selectScheduleById(scheduleId);
		if(originalSchedule == null) {
			return new ResponseEntity<>(new ReturnMsg("스케줄이 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
		}
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(userId != originalSchedule.getUser_id()) {
			System.out.println("** 스케줄 수정 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		// 수정되지 않은 부분은 예전 스케줄에서 가져오기
		if(schedule.getBg_color() == null) {schedule.setBg_color(originalSchedule.getBg_color());}
		if(schedule.getStart() == null) {schedule.setStart(originalSchedule.getStart());}
		if(schedule.getEnd() == null) {schedule.setEnd(originalSchedule.getEnd());}
		if(schedule.getTitle() == null) {schedule.setTitle(originalSchedule.getTitle());}
		schedule.setStudy_id(studyId);
		schedule.setUser_id(userId);
		schedule.setId(scheduleId);
		try {
			if(scheduleService.updateSchedule(schedule)){
				System.out.println(scheduleId + "번 스케줄 수정 성공!");
				return new ResponseEntity<>(new ReturnMsg("스케줄을 성공적으로 수정했습니다."), HttpStatus.OK);
			}			
		} catch (Exception e) {
			System.out.println("** 스케줄 수정 실패 - 서버 에러");
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("스케줄을 수정할 수 없었습니다. 서버 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스케줄 삭제")
	@DeleteMapping("/{studyId}/schedule/{scheduleId}")
	private Object deleteSchedule( @PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		Schedule schedule = scheduleService.selectScheduleById(scheduleId);
		if(schedule == null) {
			return new ResponseEntity<>(new ReturnMsg("스케줄이 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
		}
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(userId != schedule.getUser_id()) {
			System.out.println("** 스케줄 삭제 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		try {
			if(scheduleService.deleteSchedule(scheduleId)){
				System.out.println(scheduleId + "번 스케줄 삭제 성공!");
				return new ResponseEntity<>(new ReturnMsg("스케줄을 삭제하였습니다."), HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println("** 스케줄 삭제 실패 - 서버 에러");			
		}
		return new ResponseEntity<>(new ReturnMsg("스케줄을 삭제할 수 없었습니다. 서버 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "유저의 스케줄 참가 여부 및 상태 보기")
	@GetMapping("/{studyId}/schedule/{scheduleId}/participate")
	private Object userParticipateCheck( @PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 스케줄 참가여부 조회 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		ParticipateInfo info = userscheduleService.getParticipateInfo(userId, scheduleId);
		if(info == null) {
			return new ResponseEntity<>(new ParticipateInfo(), HttpStatus.OK);
		}
		System.out.println(scheduleId + "번 스케줄 참가 여부 및 상태 반환");
		return new ResponseEntity<>(info, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저의 스케줄 참가")
	@PostMapping("/{studyId}/schedule/{scheduleId}/participate")
	private Object userParticipate( @PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 스케줄 참가 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		try {
			String result = userscheduleService.createUserSchedule(new UserSchedule(userId, scheduleId));
			if(result.equals("success")) {
				System.out.println(scheduleId + "번 스케줄 참가 성공!!");
				return new ResponseEntity<>(new ReturnMsg("스케줄에 참가신청을 하였습니다."), HttpStatus.OK);
			}
			if(result.equals("fail")) {
				System.out.println("** 스케줄 참가 실패 - 서버 오류 발생");
				return new ResponseEntity<>(new ReturnMsg("스케줄에 참여할 수 없었습니다. 서버 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<>(new ReturnMsg(result), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("스케줄에 참여할 수 없었습니다. 서버 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "유저의 스케줄 참가 취소")
	@DeleteMapping("/{studyId}/schedule/{scheduleId}/participate")
	private Object cancelUserParticipate( @PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		};
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 스케줄 참가 취소 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		if(userscheduleService.cancelParticipate(userId, scheduleId)) {
			System.out.println(scheduleId + "번 스케줄 참가 취소 성공!!");
			return new ResponseEntity<>(new ReturnMsg("스케줄에 참가신청을 취소하였습니다."), HttpStatus.OK);
		}
		System.out.println("** 스케줄 참가 실패 - 서버 오류 발생");
		return new ResponseEntity<>(new ReturnMsg("스케줄에 참여할 수 없었습니다. 서버 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "출석체크(입실)")
	@PutMapping("/{studyId}/schedule/{scheduleId}/checkin")
	private Object checkIn( @PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 입실 출석체크 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		UserSchedule userSchedule = userscheduleService.getParticipate(userId, scheduleId);
		if(userSchedule == null) {
			System.out.println("** 입실 출석체크 실패 - 참여하지 않은 스케줄");
			return new ResponseEntity<>(new ReturnMsg("참여하지 않은 스케줄입니다."), HttpStatus.UNAUTHORIZED);
		}
		if(userscheduleService.changeStatus(userId, scheduleId, 1)) {
			System.out.println(userId + "번 유저 " + scheduleId + "번 스케줄 입실 성공!");
			return new ResponseEntity<>(new ReturnMsg("정상적으로 출석 시작하였습니다."), HttpStatus.OK);
		}
		System.out.println("** 입실 출석체크 - 서버 오류 발생");
		return new ResponseEntity<>(new ReturnMsg("출석체크 도중 오류가 발생했습니다. 서버 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "출석체크(퇴실)")
	@PutMapping("/{studyId}/schedule/{scheduleId}/checkout")
	private Object checkOut( @PathVariable("studyId") int studyId, @PathVariable("scheduleId") int scheduleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 입실 퇴실체크 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		UserSchedule userSchedule = userscheduleService.getParticipate(userId, scheduleId);
		if(userSchedule == null) {
			System.out.println("** 입실 퇴실체크 실패 - 참여하지 않은 스케줄");
			return new ResponseEntity<>(new ReturnMsg("참여하지 않은 스케줄입니다."), HttpStatus.UNAUTHORIZED);
		}
		if(userscheduleService.changeStatus(userId, scheduleId, 2)) {
			System.out.println(userId + "번 유저 " + scheduleId + "번 스케줄 퇴실 성공!");
			return new ResponseEntity<>(new ReturnMsg("정상적으로 출석 완료하였습니다."), HttpStatus.OK);
		}
		System.out.println("** 입실 퇴실체크 - 서버 오류 발생");
		return new ResponseEntity<>(new ReturnMsg("출석체크 도중 오류가 발생했습니다. 서버 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// Token(Authentication)에서 유저 id 정보를 뽑아내는 메소드
	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
	
}
