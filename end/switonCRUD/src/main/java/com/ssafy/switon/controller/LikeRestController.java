package com.ssafy.switon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.ArticleFav;
import com.ssafy.switon.dto.ArticleLike;
import com.ssafy.switon.dto.CommentLike;
import com.ssafy.switon.dto.StudyLike;
import com.ssafy.switon.service.ArticleFavService;
import com.ssafy.switon.service.ArticleLikeService;
import com.ssafy.switon.service.CommentLikeService;
import com.ssafy.switon.service.StudyLikeService;

import io.swagger.annotations.ApiOperation;

@RestController
public class LikeRestController {

	@Autowired
	ArticleFavService articlefavService;
	
	@Autowired
	ArticleLikeService articlelikeService;
	
	@Autowired
	CommentLikeService commentlikeService;
	
	@Autowired
	StudyLikeService studylikeService;
	
	@Autowired
	SimpMessagingTemplate template;
	
	///////////////////////ArticleFavRetController///////////////////////
	@ApiOperation(value = "글 즐겨찾기 목록을 전체 반환한다", response = List.class)
	@GetMapping("/articlefav/list")
	public List<ArticleFav> searchAllArticleFav() {
		System.out.println("글 즐겨찾기 목록 전체 반환");
		return articlefavService.searchAll();
	}
	
	@ApiOperation(value = "글 즐겨찾기 목록을 상세 반환한다", response = ArticleFav.class)
	@GetMapping("/articlefav/{articlefavid}")
	public ArticleFav searchArticleFav(@PathVariable("articlefavid") int id) {
		System.out.println("글 즐겨찾기 목록 상세 반환");
		return articlefavService.search(id);
	}
	
	@ApiOperation(value = "사용자가 즐겨찾기한 글 목록을 반환", response = List.class)
	@GetMapping("/articlefav/list/{user_id}")
	public List<ArticleFav> searchArticleFavByUser(@PathVariable("user_id") int user_id) {
		System.out.println("사용자가 즐겨찾기한 목록 반환");
		return articlefavService.searchByUser(user_id);
	}
	
	@ApiOperation(value = "글 즐겨찾기 등록한다", response = String.class)
	@PostMapping("/articlefav/write")
	public String writeArticleFav(ArticleFav articlefav) {
		
		
		System.out.println("글 즐겨찾기 등록");
		if(articlefavService.create(articlefav)) {
			System.out.println("글 즐겨찾기 등록 성공!!!");
			return "success";
		} else {
			System.out.println("글 즐겨찾기 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "글 즐겨찾기 삭제한다", response = String.class)
	@GetMapping("/articlefav/delete/{articlefavid}")
	public String deleteArticleFav(@PathVariable("articlefavid") int id) {
		System.out.println("글 즐겨찾기 삭제");
		if(articlefavService.delete(id)) {
			System.out.println("글 즐겨찾기 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("글 즐겨찾기 삭제 실패...");
			return "fail";
		}
	}
	
	///////////////////////////ArticleLikeRestController////////////////
	
	// 글좋아요 목록 반환
	@ApiOperation(value = "글좋아요 전체 목록을 반환한다.", response = List.class)
	@GetMapping("/articlelike/list")
	public List<ArticleLike> searchAllArticleLike() {
		System.out.println("글좋아요 목록 조회");
		return articlelikeService.searchAll();
	}
	// 글좋아요 목록 상세반환
	@ApiOperation(value = "글좋아요 목록을 반환한다.", response = ArticleLike.class)
	@GetMapping("/articlelike/{articlelikeid}")
	public ArticleLike searchArticleLike(@PathVariable("articlelikeid") int id) {
		System.out.println(id + "번을 상세 조회");
		return articlelikeService.search(id);
	}
	
	// 해당 글 좋아요 수 반환
	@ApiOperation(value = "해당 글 좋아요 수 반환한다.", response = Integer.class)
	@GetMapping("/articlelike/articlelikecount/{article_id}")
	public int articleLikeCount(@PathVariable("article_id") int article_id) {
		System.out.println(article_id + "번 글의 좋아요수 조회");
		return articlelikeService.searchLikeCount(article_id);
	}
	
	// 글좋아요 등록 반환
	@ApiOperation(value = "글좋아요 작성한다.", response = String.class)
	@PostMapping("/articlelike/write")
	public String writeArticleLike(ArticleLike articlelike) {
		System.out.println("글좋아요를 등록");
		if(articlelikeService.createArticleLike(articlelike)) {
			System.out.println("글좋아요 등록 성공!!!");
			//객체를 생성하고 필요한 값을 다넣고 그객체를 보내준다
			template.convertAndSend("/topic/notification/" + 1, articlelike);
			return "success";
		} else {
			System.out.println("글좋아요 등록 실패...");
			return "fail";
		}
	}
	// 글좋아요 삭제 반환
	@ApiOperation(value = "글좋아요 삭제한다.", response = String.class)
	@GetMapping("/articlelike/delete/{articlelikeid}")
	public String deleteArticleLike(@PathVariable("articlelikeid") int id) {
		System.out.println(id + "번 삭제");
		if(articlelikeService.deleteArticleLike(id)) {
			System.out.println("글좋아요 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("글좋아요 삭제 실패...");
			return "fail";
		}
	}
	
	////////////////////////////CommentLikeRestController///////////////
	
	@ApiOperation(value = "댓글좋아요 전체 목록을 반환한다" , response = List.class)
	@GetMapping("/commentlike/list")
	public List<CommentLike> searchAllCommentLike() {
		System.out.println("댓글좋아요 전체 목록 조회");
		return commentlikeService.searchAll();
	}
	
	@ApiOperation(value = "댓글좋아요 상세 목록을 반환한다", response = CommentLike.class)
	@GetMapping("/commentlike/{commentlikeid}")
	public CommentLike searchCommentLike(@PathVariable("commentlikeid") int id) {
		System.out.println(id + "번을 상세 조회");
		return commentlikeService.search(id);
	}
	
	// 해당 댓글 좋아요 수 반환
	@ApiOperation(value = "해당 댓글 좋아요 수 반환한다.", response = Integer.class)
	@GetMapping("/commentlike/commentlikecount/{comment_id}")
	public int commentLikeCount(@PathVariable("comment_id") int comment_id) {
		System.out.println(comment_id + "번 댓글의 좋아요수 조회");
		return commentlikeService.searchLikeCount(comment_id);
	}
	
	@ApiOperation(value = "댓글좋아요 등록한다", response = String.class)
	@PostMapping("/commentlike/write")
	public String writeCommentLike(CommentLike commentlike) {
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
	@GetMapping("/commentlike/delete/{commentlikeid}")
	public String deleteCommentLike(@PathVariable("commentlikeid") int id) {
		System.out.println( id + "번을 삭제");
		if(commentlikeService.delete(id)) {
			System.out.println("댓글좋아요 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("댓글좋아요 삭제 실패...");
			return "fail";
		}
	}

	////////////////////////////StudyLikeRestController//////////////////
	
	@ApiOperation(value = "소모임좋아요 전체 목록을 반환한다.", response = List.class)
	@GetMapping("/studylike/list")
	public List<StudyLike> searchAllStudyLike() {
		System.out.println("소모임 좋아요 전체 조회");
		return studylikeService.searchAll();
	}
	
	@ApiOperation(value = "소모임좋아요 목록을 반환한다.", response = ArticleLike.class)
	@GetMapping("/studylike/{studylikeid}")
	public StudyLike searchStudyLike(@PathVariable("studylikeid") int id) {
		System.out.println(id + "번을 상세 조회");
		return studylikeService.search(id);
	}
	
	// 해당 소모임 좋아요 수 반환
	@ApiOperation(value = "해당 소모임 좋아요 수 반환한다.", response = Integer.class)
	@GetMapping("/studylike/studylikecount/{study_id}")
	public int studyLikeCount(@PathVariable("study_id") int study_id) {
		System.out.println(study_id + "번 소모임의 좋아요수 조회");
		return studylikeService.searchLikeCount(study_id);
	}
	
	@ApiOperation(value = "소모임좋아요 작성한다.", response = String.class)
	@PostMapping("/studylike/write")
	public String writeStudyLike(StudyLike studylike) {
		System.out.println("소모임 좋아요 등록");
		if(studylikeService.createStudyLike(studylike)) {
			System.out.println("소모임 좋아요 등록 성공!!!");
			return "success";
		} else {
			System.out.println("소모임 좋아요 등록 실패...");
			return "fail";
		}
	}
	
	@ApiOperation(value = "소모임좋아요 삭제한다.", response = String.class)
	@GetMapping("/studylike/delete/{studylikeid}")
	public String deleteStudyLike(@PathVariable("studylikeid") int id) {
		System.out.println(id + "번을 삭제");
		if(studylikeService.deleteStudyLike(id)) {
			System.out.println("소모임 삭제 성공!!!");
			return "success";
		} else {
			System.out.println("소모임 삭제 실패...");
			return "fail";
		}
	}
	
}
