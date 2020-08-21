package com.ssafy.switon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Awake;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.service.CommentService;
import com.ssafy.switon.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="createQnaComment", description="알람 테스트용")
public class AlertTestController {

	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	UserService userService;
	////////////
	
	
	
	@ApiOperation(value = "댓글이 달렸다는 알림 보내기 테스트")
	@PostMapping("alert/{userName}")
	public Object createQnaComment(@PathVariable("userName") String userName, HttpServletRequest request) {
		int userId = userService.searchUserIdByName(userName);
		int studyId = 1;
		int boardId = 2;
		int articleId = 1;
		String type = "qna";
		
		try {
			Awake awake = new Awake(studyId, boardId, articleId, type);
			// awake.setStudyId("~~~~")
			// 나머지 등록
/////////////////////////////////////////////////////////////// 추가 ///////////////////////////////////////////
			template.convertAndSend("/topic/notification/" + userId, awake);
///////////////////////////////////////////////////////////////////////////////////////
			return new ResponseEntity<>(new ReturnMsg("댓글을 성공적으로 작성했습니다."), HttpStatus.OK);				
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("** 댓글 작성 실패 - 서버 오류...");
			return new ResponseEntity<>(new ReturnMsg("작성에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
//	@ApiOperation(value = "qna 게시글의 댓글 작성")
//	@PostMapping("study/{studyId}/qna/{articleId}/commentAlertTest")
//	public Object createQnaComment(@RequestBody Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
//		comment.setArticle_id(articleId);
//		comment.setUser_id(userId);
//		
//		if(commentService.create(comment)) {
//			Awake awake = new Awake();
//			// awake.setStudyId("~~~~")
//			// 나머지 등록
//			System.out.println("댓글 작성 성공!");
//			
///////////////////////////////////////////////////////////////// 추가 ///////////////////////////////////////////
//			template.convertAndSend("/topic/notification/" + {글작성자 아이디}, awake);
/////////////////////////////////////////////////////////////////////////////////////////
//			return new ResponseEntity<>(new ReturnMsg("댓글을 성공적으로 작성했습니다."), HttpStatus.OK);			
//		}
//		System.out.println("** 댓글 작성 실패 - 서버 오류...");
//		return new ResponseEntity<>(new ReturnMsg("작성에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
