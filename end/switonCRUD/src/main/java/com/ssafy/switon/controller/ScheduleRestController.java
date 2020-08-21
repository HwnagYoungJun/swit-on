package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.service.ScheduleService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/schedule")
public class ScheduleRestController {

	@Autowired
	ScheduleService scheduleService;
	
	@ApiOperation(value = "모든 스케쥴 조회한다", response = List.class)
	@GetMapping("/list")
	public List<Schedule> searchAll() {
		System.out.println("모든 스케쥴 조회");
		return scheduleService.selectAll();
	}
	
	@ApiOperation(value = "스터디 아이디로 스케쥴 조회한다", response = Schedule.class)
	@GetMapping("/studyschedule/{studyId}")
	public Schedule searchByStudyId(@PathVariable("studyId") int studyId) {
		System.out.println("스터디 아이디로 스케쥴 조회");
		return scheduleService.selectScheduleByStudyId(studyId);
	}
	
	@ApiOperation(value = "아이디로 스케쥴 조회한다", response = Schedule.class)
	@GetMapping("/{id}")
	public Schedule searchById(@PathVariable("id") int id) {
		System.out.println("아이디로 스케쥴 조회");
		return scheduleService.selectScheduleById(id);
	}
	
	@ApiOperation(value = "스케쥴 등록한다", response = String.class)
	@PostMapping("/write")
	public String writeSchedule(Schedule schedule) {
		System.out.println("스케쥴 등록");
		if(scheduleService.createSchedule(schedule)) {
			System.out.println("스케쥴 등록 성공!!!");
			return "success";
		} else {
			System.out.println("스케쥴 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "스케쥴 삭제한다", response = String.class)
	@DeleteMapping("/delete/{id}")
	public String deleteSchedule(@PathVariable("id") int id) {
		System.out.println("스케쥴 삭제");
		if(scheduleService.deleteSchedule(id)) {
			System.out.println("스케쥴 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("스케쥴 삭제 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "스케쥴 수정한다", response = String.class)
	@PostMapping("/update")
	public String updateSchedule(Schedule schedule) {
		System.out.println("스케쥴 수정");
		if(scheduleService.updateSchedule(schedule)) {
			System.out.println("스케쥴 수정 성공!!!");
			return "success";
		} else {
			System.out.println("스케쥴 수정 실패...");
			return "fail";
		}
	}
}
