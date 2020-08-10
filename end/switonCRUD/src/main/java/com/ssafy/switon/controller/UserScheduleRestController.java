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
import com.ssafy.switon.dto.UserSchedule;
import com.ssafy.switon.service.UserScheduleService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/userschedule")
public class UserScheduleRestController {

	@Autowired
	UserScheduleService userscheduleService;
	
	@ApiOperation(value = "모든 유저스케쥴 조회한다", response = List.class)
	@GetMapping("/list")
	public List<UserSchedule> searchAll() {
		System.out.println("모든 유저 스케쥴 조회");
		return userscheduleService.selectAll();
	}
	
	@ApiOperation(value = "유저 아이디로 유저 스케쥴 조회한다", response = UserSchedule.class)
	@GetMapping("/{userId}")
	public UserSchedule searchByUserId(@PathVariable("userId") int userId) {
		System.out.println("유저 아이디로 유저 스케쥴 조회");
		return userscheduleService.selectUserScheduleByUserId(userId);
	}
	
	@ApiOperation(value = "유저 스케쥴 등록한다", response = String.class)
	@PostMapping("/write")
	public String writeUserSchedule(UserSchedule userschedule) {
		System.out.println("유저 스케쥴 등록");
		if(userscheduleService.createUserSchedule(userschedule)) {
			System.out.println("유저 스케쥴 등록 성공!!!");
			return "success";
		} else {
			System.out.println("유저 스케쥴 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "유저 스케쥴 상태 변경한다", response = String.class)
	@PostMapping("/update")
	public String updateUserSchedule(int status) {
		System.out.println("유저 스케쥴 상태 변경");
		if(userscheduleService.updateUserSchedule(status)) {
			System.out.println("유저 스케쥴 상태 변경 성공!!!");
			return "success";
		} else {
			System.out.println("유저 스케쥴 상태 변경 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "유저 스케쥴 삭제한다", response = String.class)
	@DeleteMapping("/delete/{id}")
	public String deleteUserSchedule(int id) {
		System.out.println("유저 스케쥴 삭제");
		if(userscheduleService.deleteUserSchedule(id)) {
			System.out.println("유저 스케쥴 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("유저 스케쥴 삭제 실패...");
			return "fail";
		}
	}
}
