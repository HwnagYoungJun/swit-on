package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.service.CommentService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comment")
public class CommentRestController {

	@Autowired
	CommentService commentService;
	
	@ApiOperation(value = "댓글 전체 조회한다", response = List.class)
	@GetMapping("/list")
	public List<Comment> searchAll() {
		System.out.println("댓글 전체 조회");
		return commentService.searchAll();
	}
	
	@ApiOperation(value = "댓글 상세 조회한다", response = Comment.class)
	@GetMapping("/{id}")
	public Comment search(@PathVariable("id") int id) {
		System.out.println("댓글 상세 조회");
		return commentService.search(id);
	}
	
	@ApiOperation(value = "댓글 등록한다", response = String.class)
	@PostMapping("/write")
	public String write(Comment comment) {
		System.out.println("댓글 등록");
		if(commentService.create(comment)) {
			System.out.println("댓글 등록 성공!!!");
			return "success";
		} else {
			System.out.println("댓글 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "댓글 삭제한다", response = String.class)
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		System.out.println("댓글 삭제");
		if(commentService.delete(id)) {
			System.out.println("댓글 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("댓글 삭제 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "댓글 수정한다", response = String.class)
	@PostMapping("/update")
	public String update(Comment comment) {
		System.out.println("댓글 수정");
		if(commentService.update(comment)) {
			System.out.println("댓글 수정 성공!!!");
			return "success";
		} else {
			System.out.println("댓글 수정 실패...");
			return "fail";
		}
	}
}
