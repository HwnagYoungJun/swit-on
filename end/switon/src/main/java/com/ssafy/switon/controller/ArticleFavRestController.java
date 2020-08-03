package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.ArticleFav;
import com.ssafy.switon.service.ArticleFavService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/articlefav")
public class ArticleFavRestController {

	@Autowired
	ArticleFavService articlefavService;
	
	@ApiOperation(value = "글 즐겨찾기 목록을 전체 반환한다", response = List.class)
	@GetMapping("/list")
	public List<ArticleFav> searchAll() {
		System.out.println("글 즐겨찾기 목록 전체 반환");
		return articlefavService.searchAll();
	}
	
	@ApiOperation(value = "글 즐겨찾기 목록을 상세 반환한다", response = ArticleFav.class)
	@GetMapping("/{id}")
	public ArticleFav search(@PathVariable("id") int id) {
		System.out.println("글 즐겨찾기 목록 상세 반환");
		return articlefavService.search(id);
	}
	
	@ApiOperation(value = "글 즐겨찾기 등록한다", response = String.class)
	@PostMapping("/write")
	public String write(ArticleFav articlefav) {
		System.out.println("글 즐겨찾기 등록");
		if(articlefavService.create(articlefav)) {
			System.out.println("글 즐겨찾기 등록 성공!!!");
			return "success";
		} else {
			System.out.println("글 즐겨찾기 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "글 즐겨찾기 삭제한다", response = String.class)
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		System.out.println("글 즐겨찾기 삭제");
		if(articlefavService.delete(id)) {
			System.out.println("글 즐겨찾기 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("글 즐겨찾기 삭제 실패...");
			return "fail";
		}
	}
	
}
