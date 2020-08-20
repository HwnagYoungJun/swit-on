   package com.ssafy.switon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.dto.AlarmReturnDTO;
import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.FeedsIndexDTO;
import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyCardDTO;
import com.ssafy.switon.dto.UserReturnDTO;
import com.ssafy.switon.service.AlarmService;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.CategoryService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="SwitonRestController", description = "스윗온의 기타 다양한 기능을 모아둔 컨트롤러")
public class SwitonRestController {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private StudyService studyService;
	
	@Autowired
	private AlarmService alarmService;
	
	@ApiOperation(value = "상위카테고리 id 아래에 있는 하위 카테고리 id, 이름을 반환한다.")
	@GetMapping("/category/{id}")
	public Object showLowerCategory(@PathVariable("id") int id) {
		List<LowerCategory> list = categoryService.searchUp_Low(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
//	@ApiOperation(value = "유저가 가입한 스터디의 게시물들을 반환한다.")
//	@GetMapping("/feeds")
//	public Object showFeeds(HttpServletRequest request) {
//		int userId = getUserPK(request);
//		try {
//			List<ArticleReturnDTO> articles = articleService.searchFeeds(userId);			
//			System.out.println("뉴스피드 생성 성공");
//			return new ResponseEntity<>(articles, HttpStatus.OK);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(new ReturnMsg("뉴스피드 불러오기를 실패했습니다."), HttpStatus.OK);
//		}
//	}
	
	@ApiOperation(value = "유저가 가입한 스터디의 게시물들을 반환한다. ")
	@GetMapping("/feeds")
	public Object showFeeds(@RequestParam int index, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		try {
			List<ArticleReturnDTO> articles = articleService.searchFeeds(userId, index, 5);
			System.out.println("뉴스피드 생성 성공");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("뉴스피드 불러오기를 실패했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "스터디 카드 리스트를 반환한다.", response = List.class)
	@GetMapping("/studyCard")
	public Object showAllStudyCards(@RequestParam(value="lowercategory_id", required = false) String lowercategory_id){
		System.out.println(lowercategory_id);
		List<StudyCardDTO> list = new ArrayList<>();
		if(lowercategory_id != null) {
			try {
				list = studyService.searchStudyCardsByLowercategoryId(Integer.parseInt(lowercategory_id));
				return new ResponseEntity<>(list, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("스터디를 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		try {
			list = studyService.searchStudyCards();
			System.out.println("스터디 리스트 출력");		
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("스터디를 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// userId로 알림 리스트 조회 
	@ApiOperation(value = "유저id로 알람 목록을 상세 반환한다", response = List.class)
	@GetMapping("/alarm")
	public Object searchAlarmByUserId(HttpServletRequest request) {
		System.out.println("/알람 시작");
		int userId = 0;
		String token = request.getHeader("Authentication");
		if(token != null) {
			userId = getUserPK(request);
		}
		if(userId != 0) {
			try {
				List<AlarmReturnDTO> alarms = alarmService.searchAlarmByUserId(userId, "board_name도");			
				System.out.println("알람 목록을 상세 조회");
				return new ResponseEntity<>(alarms, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("알림을 받아올 수 없습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		System.out.println("토큰을 못 읽어옴");
		return new ResponseEntity<>(new ReturnMsg("비로그인 유저입니다."), HttpStatus.OK);
	}
	
	// id로 알림 읽음으로 등록 
	@ApiOperation(value = "알람의 id로 알람을 읽음 처리한다", response = String.class)
	@PutMapping("/alarm/{id}")
	public String setReadById(@PathVariable("id") int id) {
		System.out.println("알람을 읽음 처리");
		if(alarmService.updateAlarmById(id)) {
			System.out.println("읽음 처리 성공!!!");
			return "success";
		} else {
			System.out.println("읽음 처리 실패...");
			return "fail";
		}
	}
	
// Token(Authentication)에서 유저 id 정보를 뽑아내는 메소드
	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}

}
