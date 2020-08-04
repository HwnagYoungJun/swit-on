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

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleFav;
import com.ssafy.switon.dto.ArticleLike;
import com.ssafy.switon.dto.Board;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.CommentLike;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.LowerCategory;
import com.ssafy.switon.dto.ResultStrObject;
import com.ssafy.switon.dto.StudyLike;
import com.ssafy.switon.dto.UpperCategory;
import com.ssafy.switon.service.ArticleFavService;
import com.ssafy.switon.service.ArticleLikeService;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.BoardService;
import com.ssafy.switon.service.CategoryService;
import com.ssafy.switon.service.CommentLikeService;
import com.ssafy.switon.service.CommentService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.StudyLikeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="ArticleRestController", description="(테스트용) 각종 CRUD 테스트용 컨트롤러...")
public class TestRestController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	ArticleService articleService;
	
	@ApiOperation(value = "게시글 리스트 전체 반환", response = List.class)
	@GetMapping("/article/")
	public List<Article> showAll(){
		System.out.println("게시글 리스트 출력");
		return articleService.searchAll();
	}
	
	@ApiOperation(value = "게시판 아이디로 게시글 리스트 조회", response = List.class)
	@GetMapping("/article/board/{boardId}")
	public List<Article> showBoardArticles(@PathVariable int boardId){
		System.out.println("게시판 아이디로 게시글 리스트 출력");
		return articleService.searchBoardArticles(boardId);
	}
	
	@ApiOperation(value = "유저 아이디로 게시글 리스트 조회, 후에 토큰으로 구현할것", response = List.class)
	@GetMapping("/article/user/{userId}")
	public List<Article> showUserArticles(@PathVariable int userId){
		System.out.println("유저 아이디로 게시글 리스트 출력");
		return articleService.searchUserArticles(userId);
	}
	
	@ApiOperation(value = "유저 아이디로 게시글 조회, 후에 토큰으로 구현할 것", response = Article.class)
	@GetMapping("/article/{id}")
	public Article showArticle(@PathVariable int id){
		System.out.println("유저 아이디로 게시글 리스트 출력");
		return articleService.search(id);
	}
	
	@ApiOperation(value = "게시글 작성, 후에 토큰으로 구현할 것")
	@PostMapping("/article/create")
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
	@PostMapping("/article/update")
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
	@PostMapping("/article/delete")
	public Object deleteArticle(int id) {
		if(articleService.delete(id)) {
			System.out.println("게시글 삭제 성공!");
			return new ResponseEntity<>(null, HttpStatus.OK);
		} else {
			System.out.println("** 게시글 삭제 실패...");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	@ApiOperation(value = "(테스트용) 게시판 리스트를 반환", response = List.class)
	@GetMapping("/board/")
	public List<Board> showAllBoards(){
		System.out.println("게시판 리스트 출력");
		return boardService.searchAll();
	}
	
	@ApiOperation(value = "스터디의 게시판 리스트를 반환 테스트", response = List.class)
	@GetMapping("/board/{studyId}")
	public List<Board> showStudyBoards(@PathVariable("studyId") int studyId){
		System.out.println("게시판 리스트 출력");
		return boardService.searchStudyBoards(studyId);
	}
	
	@ApiOperation(value = "(테스트용) 게시판을 생성")
	@PostMapping("/board/create")
	public Object create(Board board, HttpServletRequest request) {
		if(boardService.create(board)) {
			System.out.println("게시판 생성 성공!");
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		System.out.println("**게시판 생성 실패...");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "(테스트용) 게시판을 삭제")
	@GetMapping("/board/{id}/delete")
	public Object delete(@PathVariable("id") int id, HttpServletRequest request) {
		if(boardService.delete(id)) {
			System.out.println("게시판 삭제 성공!");
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		System.out.println("**게시판 삭제 실패...");
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Autowired
	CategoryService categoryService;
	
	@ApiOperation(value = "대분류 정보 조회한다", response = List.class)
	@GetMapping("/category/up")
	public List<UpperCategory> searchUp() {
		System.out.println("대분류 정보 조회");
		return categoryService.searchUp();
	}
	
	@ApiOperation(value = "소분류 정보 조회한다", response = List.class)
	@GetMapping("/category/low")
	public List<LowerCategory> searchLow() {
		System.out.println("소분류 정보 조회");
		return categoryService.searchLow();
	}
	
	@ApiOperation(value = "대분류에 해당하는 소분류 조회한다", response = List.class)
	@GetMapping("/category/up_low/{up_id}")
	public List<LowerCategory> searchUp_Low(@PathVariable("up_id") int uppercategory_id) {
		System.out.println("대분류에 해당하는 소분류 정보 조회");
		return categoryService.searchUp_Low(uppercategory_id);
	}
	
	@Autowired
	JoinService joinService;
	
	@ApiOperation(value = "소모임 가입 정보 전체 조회한다", response = List.class)
	@GetMapping("/join/list")
	public List<Join> searchAllJoins() {
		System.out.println("소모임 가입 정보 전체 조회");
		return joinService.searchAll();
	}
	
	@ApiOperation(value = "소모임 가입 정보 상세 조회한다", response = Join.class)
	@GetMapping("/join/{id}")
	public Join searchJoin(@PathVariable("id") int id) {
		System.out.println("소모임 가입 정보 상세 조회");
		return joinService.search(id);
	}
	
//	@ApiOperation(value = "소모임 가입 정보 등록한다", response = String.class)
//	@PostMapping("/join/write")
//	public String write(Join join) {
//		System.out.println("소모임 가입 정보 등록");
//		if(joinService.create(join)) {
//			System.out.println("소모임 가입 정보 등록 성공!!!");
//			return "success";
//		} else {
//			System.out.println("소모임 가입 정보 등록 실패...");
//			return "fail";
//		}
//	}
	
//	@ApiOperation(value = "소모임 가입 정보 삭제한다", response = String.class)
//	@GetMapping("/join/delete/{id}")
//	public String deleteJoin(@PathVariable("id") int id) {
//		System.out.println("소모임 가입 정보 삭제");
//		if(joinService.delete(id)) {
//			System.out.println("소모임 가입 정보 삭제 성공!!!");
//			return "success";
//		} else {
//			System.out.println("소모임 가입 정보 삭제 실패...");
//			return "fail";
//		}
//	}
	
	@Autowired
	CommentService commentService;
	
	@ApiOperation(value = "댓글 전체 조회한다", response = List.class)
	@GetMapping("/comment/list")
	public List<Comment> searchAllComments() {
		System.out.println("댓글 전체 조회");
		return commentService.searchAll();
	}
	
	@ApiOperation(value = "댓글 상세 조회한다", response = Comment.class)
	@GetMapping("/comment/{id}")
	public Comment searchComment(@PathVariable("id") int id) {
		System.out.println("댓글 상세 조회");
		return commentService.search(id);
	}
	
	@ApiOperation(value = "댓글 등록한다", response = String.class)
	@PostMapping("/comment/write")
	public String writeComment(Comment comment) {
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
	@GetMapping("/comment/delete/{id}")
	public String deleteComment(@PathVariable("id") int id) {
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
	@PostMapping("/comment/update")
	public String updateComment(Comment comment) {
		System.out.println("댓글 수정");
		if(commentService.update(comment)) {
			System.out.println("댓글 수정 성공!!!");
			return "success";
		} else {
			System.out.println("댓글 수정 실패...");
			return "fail";
		}
	}
	
	
	@Autowired
	ArticleFavService articlefavService;
	
	@Autowired
	ArticleLikeService articlelikeService;
	
	@Autowired
	CommentLikeService commentlikeService;
	
	@Autowired
	StudyLikeService studylikeService;
	
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
	// 글좋아요 등록 반환
	@ApiOperation(value = "글좋아요 작성한다.", response = String.class)
	@PostMapping("/articlelike/write")
	public String writeArticleLike(ArticleLike articlelike) {
		System.out.println("글좋아요를 등록");
		if(articlelikeService.createArticleLike(articlelike)) {
			System.out.println("글좋아요 등록 성공!!!");
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
