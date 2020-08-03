package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.ArticleLike;
import com.ssafy.switon.dto.StudyLike;
import com.ssafy.switon.service.StudyLikeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/studylike")
public class StudyLikeRestController {
	
	@Autowired
	StudyLikeService studylikeService;
	
	@ApiOperation(value = "소모임좋아요 전체 목록을 반환한다.", response = List.class)
	@GetMapping("/list")
	public List<StudyLike> searchAll() {
		System.out.println("소모임 좋아요 전체 조회");
		return studylikeService.searchAll();
	}
	
	@ApiOperation(value = "소모임좋아요 목록을 반환한다.", response = ArticleLike.class)
	@GetMapping("/{id}")
	public StudyLike searchOne(@PathVariable("id") int id) {
		System.out.println(id + "번을 상세 조회");
		return studylikeService.search(id);
	}
	
	@ApiOperation(value = "소모임좋아요 작성한다.", response = String.class)
	@PostMapping("/write")
	public String write(StudyLike studylike) {
		System.out.println("소모임 좋아요 등록");
		if(studylikeService.createStudyLike(studylike)) {
			System.out.println("소모임 좋아요 등록 성공!!!");
			return "success";
		} else {
			System.out.println("소모임 좋아요 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "소모임좋아요 삭제한다.", response = String.class)
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		System.out.println(id + "번을 삭제");
		if(studylikeService.deleteStudyLike(id)) {
			System.out.println("소모임 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("소모임 삭제 실패...");
			return "fail";
		}
	}
	
}
