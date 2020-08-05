package com.ssafy.switon.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.UserDTO;
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
	
	@ApiOperation(value = "유저 정보를 수정한다.(만드는중)", response = UserInfoDTO.class)
	@PutMapping("{id}")
	public Object modifyUser(@RequestParam(value = "img", required = false) MultipartFile img, 
							@PathVariable int id, UserInfoDTO userInfoDTO, MultipartHttpServletRequest request) {
		int userId = getUserPK(request);
		if(userId != id) {
			System.out.println("** 유저 정보 수정 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("유저 정보 수정에 실패했습니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);			
		}
		if(img != null) {
			String filePath = getUploadPath(request, userId);
			try {
				img.transferTo(new File(filePath));
				userInfoDTO.setProfile_image(filePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		UserInfoDTO originalUserDTO = userService.search(userId);
		userInfoDTO.setId(userId);
		userInfoDTO.setEmail(originalUserDTO.getEmail());
		if(userInfoDTO.getName() == null) {
			userInfoDTO.setName(originalUserDTO.getName());
		}
		if(userInfoDTO.getIntroduce() == null) {
			userInfoDTO.setIntroduce(originalUserDTO.getIntroduce());
		}
		if(userInfoDTO.getProfile_image() == null) {
			userInfoDTO.setProfile_image(originalUserDTO.getProfile_image());
		}
		if(userInfoDTO.getName_legacy() == null) {
			if(originalUserDTO.getName_legacy() != null) {
				userInfoDTO.setName_legacy(originalUserDTO.getName_legacy());				
			}
		}
		if(userService.modify(userInfoDTO)) {
			System.out.println("유저 정보 수정");
			return new ResponseEntity<>(new ReturnMsg("유저 정보를 수정하였습니다."), HttpStatus.OK);			
		}
		System.out.println("** 유저 정보 수정 실패 - 서버 에러");
		return new ResponseEntity<>(new ReturnMsg("서버 에러로 유저 정보 수정에 실패했습니다. 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);			
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
		int userId = getUserPK(request);
		if(id == userId) {
			List<Article> qnas = articleService.searchUserQnAs(id);
			System.out.println("유저 QnA글 리스트 반환");
			return new ResponseEntity<>(qnas, HttpStatus.OK);
		}
		System.out.println("유저 QnA글 리스트 반환 실패 - 권한 없음");
		return new ResponseEntity<>(new ReturnMsg("가입하지 않은 소모임입니다."), HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "유저가 작성한 자료실 글 목록을 반환한다. (로그인 필요)")
	@GetMapping("/{id}/myrepository")
	public Object myRepositoryList(@PathVariable int id, HttpServletRequest request) {
		System.out.println(request.getHeader("Authentication"));
		int userId = getUserPK(request);
		System.out.println(userId);
		if(id == userId) {
			List<Article> repos = articleService.searchUserRepositories(id);
			System.out.println("유저 자료실 글 리스트 반환");
			return new ResponseEntity<>(repos, HttpStatus.OK);
		}		
		System.out.println("유저 자료실 글 리스트 반환 실패 - 권한 없음");
		return new ResponseEntity<>(new ReturnMsg("가입하지 않은 소모임입니다."), HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "회원탈퇴를 한다.", response = UserInfoDTO.class)
	@DeleteMapping("{id}")
	public Object userDelete(@PathVariable int id, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(userId != id) {
			System.out.println("** 회원 탈퇴 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		if(userService.signOut(userId)) {
			System.out.println("유저 탈퇴 성공!");
			return new ResponseEntity<>(new ReturnMsg("정상적으로 탈퇴되었습니다. 이용해주셔서 감사합니다."), HttpStatus.OK);
		}
		System.out.println("** 회원 탈퇴 실패 - 서버 문제");
		return new ResponseEntity<>(new ReturnMsg("탈퇴 중 문제가 발생했습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.OK);
	}
	
	// 파일 저장 경로와 파일 저장명을 반환해주는 메소드
	private String getUploadPath(MultipartHttpServletRequest request, int userId) {
		return request.getServletContext().getRealPath("static"+ File.separator + "upload") + File.separator + userId + "_" + System.currentTimeMillis() + ".PNG";
	}
	
	// Token(Authentication)에서 유저 id 정보를 뽑아내는 메소드
	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
}
