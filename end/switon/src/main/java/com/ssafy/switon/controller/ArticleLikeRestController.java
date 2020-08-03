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
import com.ssafy.switon.service.ArticleLikeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/articlelike")
public class ArticleLikeRestController {

	@Autowired
	ArticleLikeService articlelikeService;
	
	// 글좋아요 목록 반환
	@ApiOperation(value = "글좋아요 전체 목록을 반환한다.", response = List.class)
	@GetMapping("/list")
	public List<ArticleLike> searchAll() {
		System.out.println("글좋아요 목록 조회");
		return articlelikeService.searchAll();
	}
	// 글좋아요 목록 상세반환
	@ApiOperation(value = "글좋아요 목록을 반환한다.", response = ArticleLike.class)
	@GetMapping("/{id}")
	public ArticleLike searchOne(@PathVariable("id") int id) {
		System.out.println(id + "번을 상세 조회");
		return articlelikeService.search(id);
	}
	// 글좋아요 등록 반환
	@ApiOperation(value = "글좋아요 작성한다.", response = String.class)
	@PostMapping("/write")
	public String write(ArticleLike articlelike) {
		System.out.println("글좋아요를 등록");
		if(articlelikeService.createArticleLike(articlelike)) {
			System.out.println("글좋아요 등록 성공!!!");
			return "success";
		} else {
			System.out.println("글좋아요 등록 실패...");
			return "fail";
		}
	}
	// 글좋아요 삭제 반환
	@ApiOperation(value = "글좋아요 삭제한다.", response = String.class)
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		System.out.println(id + "번 삭제");
		if(articlelikeService.deleteArticleLike(id)) {
			System.out.println("글좋아요 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("글좋아요 삭제 실패...");
			return "fail";
		}
	}
}
