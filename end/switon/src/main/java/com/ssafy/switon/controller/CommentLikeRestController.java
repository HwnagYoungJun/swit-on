package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.CommentLike;
import com.ssafy.switon.service.CommentLikeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/commentlike")
public class CommentLikeRestController {

	@Autowired
	CommentLikeService commentlikeService;
	
	@ApiOperation(value = "댓글좋아요 전체 목록을 반환한다" , response = List.class)
	@GetMapping("/list")
	public List<CommentLike> searchAll() {
		System.out.println("댓글좋아요 전체 목록 조회");
		return commentlikeService.searchAll();
	}
	
	@ApiOperation(value = "댓글좋아요 상세 목록을 반환한다", response = CommentLike.class)
	@GetMapping("/{id}")
	public CommentLike searchOne(@PathVariable("id") int id) {
		System.out.println(id + "번을 상세 조회");
		return commentlikeService.search(id);
	}
	
	@ApiOperation(value = "댓글좋아요 등록한다", response = String.class)
	@PostMapping("/write")
	public String create(CommentLike commentlike) {
		System.out.println("댓글좋아요 등록");
		if(commentlikeService.create(commentlike)) {
			System.out.println("댓글좋아요 등록성공!!!!");
			return "success";
		} else {
			System.out.println("댓글좋아요 등록실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "댓글좋아요 삭제한다", response = String.class)
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		System.out.println( id + "번을 삭제");
		if(commentlikeService.delete(id)) {
			System.out.println("댓글좋아요 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("댓글좋아요 삭제 실패...");
			return "fail";
		}
	}
	
}
