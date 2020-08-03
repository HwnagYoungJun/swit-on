package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ResultStrObject;
import com.ssafy.switon.service.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/article")
@Api(value="ArticleRestController", description="(테스트용) 게시글 작성, 수정, 삭제, 조회 등...")
public class ArticleRestController {
	
	@Autowired
	ArticleService articleService;
	
	@ApiOperation(value = "게시글 리스트 전체 반환", response = List.class)
	@GetMapping("/")
	public List<Article> showAll(){
		System.out.println("게시글 리스트 출력");
		return articleService.searchAll();
	}
	
	@ApiOperation(value = "게시판 아이디로 게시글 리스트 조회", response = List.class)
	@GetMapping("/board/{boardId}")
	public List<Article> showBoardArticles(@PathVariable int boardId){
		System.out.println("게시판 아이디로 게시글 리스트 출력");
		return articleService.searchBoardArticles(boardId);
	}
	
	@ApiOperation(value = "유저 아이디로 게시글 리스트 조회, 후에 토큰으로 구현할것", response = List.class)
	@GetMapping("/user/{userId}")
	public List<Article> showUserArticles(@PathVariable int userId){
		System.out.println("유저 아이디로 게시글 리스트 출력");
		return articleService.searchUserArticles(userId);
	}
	
	@ApiOperation(value = "유저 아이디로 게시글 조회, 후에 토큰으로 구현할 것", response = Article.class)
	@GetMapping("/{id}")
	public Article showArticle(@PathVariable int id){
		System.out.println("유저 아이디로 게시글 리스트 출력");
		return articleService.search(id);
	}
	
	@ApiOperation(value = "게시글 작성, 후에 토큰으로 구현할 것")
	@PostMapping("/create")
	public Object createArticle(Article article){
		if(articleService.create(article)) {
			System.out.println("게시글 작성 성공!");
			int recentArticleId = articleService.searchUserRecentArticleId(article.getUser_id());
			System.out.println(recentArticleId);
			return new ResponseEntity<>(new ResultStrObject(recentArticleId), HttpStatus.OK);
		} else {
			System.out.println("** 게시글 작성 실패...");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 수정, 후에 토큰으로 구현할 것")
	@PostMapping("/update")
	public Object editArticle(Article article) {
		if(articleService.modify(article)) {
			System.out.println("게시글 수정 성공!");
			return new ResponseEntity<>(new ResultStrObject(article.getId()), HttpStatus.OK);
		} else {
			System.out.println("** 게시글 수정 실패...");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "게시글 삭제, 후에 토큰으로 구현할 것")
	@PostMapping("/delete")
	public Object deleteArticle(int id) {
		if(articleService.delete(id)) {
			System.out.println("게시글 삭제 성공!");
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			System.out.println("** 게시글 삭제 실패...");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
