package com.ssafy.switon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserStudyInfoDTO;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.service.UserService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accounts")
@Api(value="AccountsRestController", description="유저 프로필 관련")
public class ProfileRestController {
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JoinService joinService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@ApiOperation(value = "유저 정보를 반환한다.", response = UserInfoDTO.class)
	@GetMapping("{id}")
	public Object userInfo(@PathVariable int id) {
		System.out.println("유저 정보 반환");
		UserInfoDTO user = userService.search(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저가 가입한 스터디 목록을 반환한다.")
	@GetMapping("{id}/mystudy")
	public Object myStudyList(@PathVariable int id) {
		List<UserStudyInfoDTO> dtos = studyService.searchUserStudies(id);
		System.out.println("유저 스터디 리스트 반환");
		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저가 작성한 QnA 글 목록을 반환한다. (로그인 필요)")
	@GetMapping("{id}/myqna")
	public Object myQnaList(@PathVariable int id, HttpServletRequest request) {
		String userToken = request.getHeader("Token");
		if(id == jwtUtil.getUserPK(userToken)) {
			List<Article> qnas = articleService.searchUserQnAs(id);
			System.out.println("유저 QnA글 리스트 반환");
			return new ResponseEntity<>(qnas, HttpStatus.OK);
		}
		System.out.println("유저 QnA글 리스트 반환 실패 - 권한 없음");
		return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "유저가 작성한 자료실 글 목록을 반환한다. (로그인 필요)")
	@GetMapping("/{id}/myrepository")
	public Object myRepositoryList(@PathVariable int id, HttpServletRequest request) {
		String userToken = request.getHeader("Token");
		if(id == jwtUtil.getUserPK(userToken)) {
			List<Article> repos = articleService.searchUserRepositories(id);
			System.out.println("유저 자료실 글 리스트 반환");
			return new ResponseEntity<>(repos, HttpStatus.OK);
		}		
		System.out.println("유저 자료실 글 리스트 반환 실패 - 권한 없음");
		return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
	}

}
