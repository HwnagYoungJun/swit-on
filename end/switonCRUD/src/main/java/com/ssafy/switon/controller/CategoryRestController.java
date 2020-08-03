package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.UpperCategory;
import com.ssafy.switon.service.CategoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

	@Autowired
	CategoryService categoryService;
	
	@ApiOperation(value = "대분류 정보 조회한다", response = List.class)
	@GetMapping("/up")
	public List<UpperCategory> searchUp() {
		System.out.println("대분류 정보 조회");
		return categoryService.searchUp();
	}
	
	@ApiOperation(value = "소분류 정보 조회한다", response = List.class)
	@GetMapping("/low")
	public List<LowerCategory> searchLow() {
		System.out.println("소분류 정보 조회");
		return categoryService.searchLow();
	}
	
	@ApiOperation(value = "대분류에 해당하는 소분류 조회한다", response = List.class)
	@GetMapping("/up_low/{up_id}")
	public List<LowerCategory> searchUp_Low(@PathVariable("up_id") int uppercategory_id) {
		System.out.println("대분류에 해당하는 소분류 정보 조회");
		return categoryService.searchUp_Low(uppercategory_id);
	}
	
}
