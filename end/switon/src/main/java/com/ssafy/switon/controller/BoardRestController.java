package com.ssafy.switon.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Board;
import com.ssafy.switon.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/board")
@Api(value="BoardRestController", description="(테스트용) 게시판 테스트용 컨트롤러")
public class BoardRestController {
	
	@Autowired
	BoardService boardService;
	
	@ApiOperation(value = "(테스트용) 게시판 리스트를 반환", response = List.class)
	@GetMapping("/")
	public List<Board> showAll(){
		System.out.println("게시판 리스트 출력");
		return boardService.searchAll();
	}
	
	@ApiOperation(value = "스터디의 게시판 리스트를 반환 테스트", response = List.class)
	@GetMapping("/{studyId}")
	public List<Board> showStudyBoards(@PathVariable("studyId") int studyId){
		System.out.println("게시판 리스트 출력");
		return boardService.searchStudyBoards(studyId);
	}
	
	@ApiOperation(value = "(테스트용) 게시판을 생성")
	@PostMapping("/create")
	public Object create(Board board, HttpServletRequest request) {
		if(boardService.create(board)) {
			System.out.println("게시판 생성 성공!");
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		System.out.println("**게시판 생성 실패...");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "(테스트용) 게시판을 삭제")
	@GetMapping("/{id}/delete")
	public Object delete(@PathVariable("id") int id, HttpServletRequest request) {
		if(boardService.delete(id)) {
			System.out.println("게시판 삭제 성공!");
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		System.out.println("**게시판 삭제 실패...");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
