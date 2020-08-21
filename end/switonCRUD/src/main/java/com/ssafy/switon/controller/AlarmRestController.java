package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.service.AlarmService;

import io.swagger.annotations.ApiOperation;

@RestController
public class AlarmRestController {

	@Autowired
	AlarmService alarmService;
	
	@ApiOperation(value = "알람 목록을 전체 반환한다", response = List.class)
	@GetMapping("/alarm/list")
	public List<Alarm> searchAll(){
		System.out.println("알람 목록 전체 반환");
		return alarmService.searchAll();
	}
	
	// id로 상세 조회  
	@ApiOperation(value = "id로 알람 목록을 상세 반환한다", response = Alarm.class)
	@GetMapping("/alarm/{id}")
	public Alarm searchAlarmById(@PathVariable("id") int id) {
		System.out.println(id + "번 알람 목록을 상세 조회");
		return alarmService.searchAlarmById(id);
	}
	
	// 알림 생성 
	@ApiOperation(value = "알람을 등록한다", response = String.class)
	@PostMapping("/alarm/write")
	public String createAlarm(Alarm alarm) {
		System.out.println("알람을 등록");
		if(alarmService.createAlarm(alarm)) {
			System.out.println("알람 등록 성공!!!");
			return "success";
		} else {
			System.out.println("알람 등록 실패...");
			return "fail";
		}
	}
	
	// id로 삭제  
	@ApiOperation(value = "알람을 삭제한다", response = String.class)
	@DeleteMapping("/alarm/delete/{id}")
	public String deleteAlarm(@PathVariable("id") int id) {
		System.out.println(id + "번 알람을 삭제");
		if(alarmService.deleteAlarmById(id)) {
			System.out.println("알람 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("알람 삭제 실패...");
			return "fail";
		}
	}
	
	// userId로 알림 리스트 조회 
	@ApiOperation(value = "유저id로 알람 목록을 상세 반환한다", response = List.class)
	@GetMapping("/alarm/user/{userId}")
	public List<Alarm> searchAlarmByUserId(@PathVariable("userId") int userId) {
		System.out.println("알람 목록을 상세 조회");
		return alarmService.searchAlarmByUserId(userId);
	}
	
	// id로 알림 읽음으로 등록 
	@ApiOperation(value = "id로 알람을 읽음 처리한다", response = String.class)
	@PutMapping("/alarm/setread/{id}")
	public String setReadById(@PathVariable("id") int id) {
		System.out.println("알람을 읽음 처리");
		if(alarmService.updateAlarmById(id)) {
			System.out.println("읽음 처리 성공!!!");
			return "success";
		} else {
			System.out.println("읽음 처리 실패...");
			return "fail";
		}
	}
	
	// userId로 그 유저가 읽지않은 알림 갯수 조회
	@ApiOperation(value = "userId로 읽지 않은 알람 갯수 조회한다", response = Integer.class)
	@GetMapping("/alarm/cnt/{userId}")
	public int countNotReadByUserId(@PathVariable("userId") int userId) {
		System.out.println("읽지않은 알람 갯수 조회");
		return alarmService.searchCountNotReadByUserId(userId);
	}
	
	
}
