package com.ssafy.switon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.Board;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.BoardService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("study")
@Api(value="StudyRestController", description="스터디 생성, 삭제, 정보 조회 등")
public class StudyRestController {
	
	@Autowired
	private StudyService studyService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private JoinService joinService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@ApiOperation(value = "스터디 리스트를 반환한다.", response = List.class)
	@GetMapping("/")
	public List<Study> showAll(){
		System.out.println("스터디 리스트 출력");
		return studyService.searchAll();
	}
	
	@ApiOperation(value = "스터디 id로 스터디 상세정보를 반환한다.", response = Study.class)
	@GetMapping("/{id}")
	public Study search(@PathVariable("id") int id) {
		System.out.println(id + "번 스터디 조회");
		return studyService.search(id);
	}
	
	@ApiOperation(value = "새로운 스터디를 생성한다. 로그인한 사용자가 모임장이 된다. (로그인 필요)")
	@PostMapping("/create")
	public Object create(Study study, HttpServletRequest request) {
		// 토큰에서 유저 ID 읽어와서 스터디의 모임장 id로 등록
		int userId = jwtUtil.getUserPK(request.getHeader("Token"));
		study.setUser_id(userId);
		
		int studyId = studyService.create(study, userId);
		if(studyId > 0) {
			boardService.create(new Board(studyId, 1));
			boardService.create(new Board(studyId, 2));
			boardService.create(new Board(studyId, 3));
			System.out.println("스터디 게시판(공지, QnA, 자료실) 생성 성공!");
			
			Join join = new Join();
			join.setStudy_id(studyId);
			join.setUser_id(userId);
			joinService.create(join);
			System.out.println("모임장의 스터디 가입 성공!");
			
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		System.out.println("** 스터디 생성 실패 - 서버 에러");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디를 삭제한다. 해당 소모임의 모임장이 아닌 사람이 시도할 경우 실패한다.")
	@DeleteMapping("/{id}/delete")
	public Object delete(@PathVariable("id") int id, HttpServletRequest request) {
		int user_id = jwtUtil.getUserPK(request.getHeader("Token"));
		Study study = studyService.search(id);
		
		if(study.getUser_id() != user_id) {
			System.out.println("** 스터디 삭제 실패 - 권한 없음");
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		if(studyService.delete(id)) {
			System.out.println("스터디 삭제 성공");
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		System.out.println("** 스터디 삭제 실패 - 서버 에러");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{id}/notice")
	public Object showNotice(@PathVariable("id") int id, HttpServletRequest request) {
		int noticeBoardId = boardService.findNoticeBoardId(id);
		if(noticeBoardId != 0) {
			List<Article> articles = articleService.searchBoardArticles(noticeBoardId);
			System.out.println(id + "번 스터디의 자료실 글 리스트 반환");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		}
		System.out.println("** " + id + "번 스터디의 자료실 글 리스트 반환 실패");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{id}/qna")
	public Object showQna(@PathVariable("id") int id, HttpServletRequest request) {
		int qnaBoardId = boardService.findQnABoardId(id);
		if(qnaBoardId != 0) {
			List<Article> articles = articleService.searchBoardArticles(qnaBoardId);
			System.out.println(id + "번 스터디의 QnA 게시판 글 리스트 반환");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		}
		System.out.println("** " + id + "번 스터디의 QnA 게시판 글 리스트 반환 실패");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{id}/repository")
	public Object showRepository(@PathVariable("id") int id, HttpServletRequest request) {
		int repoBoardId = boardService.findRepoBoardId(id);
		if(repoBoardId != 0) {
			List<Article> articles = articleService.searchBoardArticles(repoBoardId);
			System.out.println(id + "번 스터디의 자료실 글 리스트 반환");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		}
		System.out.println("** " + id + "번 스터디의 자료실 글 리스트 반환 실패");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
