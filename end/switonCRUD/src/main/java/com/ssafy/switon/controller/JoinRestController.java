package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Join;
import com.ssafy.switon.service.JoinService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/join")
public class JoinRestController {

	@Autowired
	JoinService joinService;
	
	@ApiOperation(value = "소모임 가입 정보 전체 조회한다", response = List.class)
	@GetMapping("/list")
	public List<Join> searchAll() {
		System.out.println("소모임 가입 정보 전체 조회");
		return joinService.searchAll();
	}
	
	@ApiOperation(value = "소모임 가입 정보 상세 조회한다", response = Join.class)
	@GetMapping("/{id}")
	public Join search(@PathVariable("id") int id) {
		System.out.println("소모임 가입 정보 상세 조회");
		return joinService.search(id);
	}
	
	@ApiOperation(value = "소모임 가입 정보 등록한다", response = String.class)
	@PostMapping("/write")
	public String write(Join join) {
		System.out.println("소모임 가입 정보 등록");
		if(joinService.create(join)) {
			System.out.println("소모임 가입 정보 등록 성공!!!");
			return "success";
		} else {
			System.out.println("소모임 가입 정보 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "소모임 가입 정보 삭제한다", response = String.class)
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		System.out.println("소모임 가입 정보 삭제");
		if(joinService.delete(id)) {
			System.out.println("소모임 가입 정보 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("소모임 가입 정보 삭제 실패...");
			return "fail";
		}
	}

}
