   package com.ssafy.switon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyCardDTO;
import com.ssafy.switon.dto.UserReturnDTO;
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
	
	@ApiOperation(value = "상위카테고리 id 아래에 있는 하위 카테고리 id, 이름을 반환한다.")
	@GetMapping("/category/{id}")
	public Object showLowerCategory(@PathVariable("id") int id) {
		List<LowerCategory> list = categoryService.searchUp_Low(id);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@ApiOperation(value = "유저가 가입한 스터디의 게시물들을 반환한다.")
	@GetMapping("/feeds")
	public Object showFeeds(HttpServletRequest request) {
		int userId = getUserPK(request);
		try {
			List<ArticleReturnDTO> articles = articleService.searchFeeds(userId);			
			System.out.println("뉴스피드 생성 성공");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("뉴스피드 불러오기를 실패했습니다."), HttpStatus.OK);
		}
	}
	
	@ApiOperation(value = "스터디 카드 리스트를 반환한다.", response = List.class)
	@GetMapping("/studyCard")
	public List<StudyCardDTO> showAllStudyCards(@RequestParam(value="lowercategory_id", required = false) String lowercategory_id){
		System.out.println(lowercategory_id);
		if(lowercategory_id != null) {
			return studyService.searchStudyCardsByLowercategoryId(Integer.parseInt(lowercategory_id));
		}
		System.out.println("스터디 리스트 출력");
		return studyService.searchStudyCards();
	}
	
// Token(Authentication)에서 유저 id 정보를 뽑아내는 메소드
	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}

	

}
