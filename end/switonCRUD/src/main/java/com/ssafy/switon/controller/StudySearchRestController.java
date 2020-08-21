package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Study;
import com.ssafy.switon.service.StudyService;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudySearchRestController {

	@Autowired
	StudyService studyService;
	
	@ApiOperation(value ="키워드로 스터디 검색한다", response = List.class)
	@GetMapping("/study/search/{keyword}")
	public List<Study> searchStudyByKeyword (@PathVariable("keyword") String keyword) {
		System.out.println("키워드로 스터디 검색");
		return studyService.searchStudyByKeyword(keyword);
	}
}
