package com.ssafy.switon.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.Board;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.Join;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyReturnDTO;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.BoardService;
import com.ssafy.switon.service.CommentService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("study")
@Api(value="StudyRestController", description="소모임과 관련된 모든 것")
public class StudyRestController {
	
	String baseDirectory = "C:\\SSAFY\\spring_workspace\\switon\\img";
	
	@Autowired
	private StudyService studyService;
	
	@Autowired 
	private BoardService boardService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private JoinService joinService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@ApiOperation(value = "스터디 리스트를 반환한다.", response = List.class)
	@GetMapping("/")
	public List<Study> showAllStudies(){
		System.out.println("스터디 리스트 출력");
		return studyService.searchAll();
	}
	
	@ApiOperation(value = "스터디 id로 스터디 상세정보를 반환한다.", response = Study.class)
	@GetMapping("/{studyId}")
	public Object searchStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		String auth = request.getHeader("Authentication");
		System.out.println("auth : " + auth);
		boolean isJoined = false;
		boolean isLeader = false;
		if(auth != null) {	// 로그인한 경우
			auth = auth.substring("Bearer ".length());	
			int userId = getUserPK(request);
			if(joinService.isMember(studyId, userId)) { // 가입한 회원인 경우 가입여부 변경
				isJoined = true;
			}
			if(userId == studyService.search(studyId).getUser_id()) { // 리더인 경우 리더 여부 변경
				isLeader = true;
			}
		}
		Study study = studyService.search(studyId);
		System.out.println(study.getCreated_at());
		StudyReturnDTO dto = new StudyReturnDTO(study, isJoined, isLeader);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 스터디를 생성한다. 로그인한 사용자가 모임장이 된다. (로그인 필요)")
	@PostMapping("/")
	public Object createStudy(@RequestParam(value = "img", required = false) MultipartFile img, Study study, HttpServletRequest request) {
		
		if(img != null) {
			String filePath = baseDirectory + "\\" + img.getOriginalFilename();
			try {
				img.transferTo(new File(filePath));
				study.setLogo(filePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 토큰에서 유저 ID 읽어와서 스터디의 모임장 id로 등록
		int userId = getUserPK(request);
		study.setUser_id(userId);
		int studyId = studyService.create(study, userId);
		if(studyId > 0) {
			boardService.create(new Board(studyId, 1));
			boardService.create(new Board(studyId, 2));
			boardService.create(new Board(studyId, 3));
			System.out.println("스터디 게시판(공지, QnA, 자료실) 생성 성공!");
			
			joinService.join(studyId, userId);
			System.out.println("모임장의 스터디 가입 성공!");
			
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** 스터디 생성 실패 - 서버 에러");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
	
	@ApiOperation(value = "스터디를 수정한다. 해당 소모임의 모임장이 아닌 사람이 시도할 경우 실패한다. (로그인 필요)")
	@PutMapping("/{studyId}")
	public Object modifyStudy(@RequestParam(value = "img", required = false) MultipartFile img,
							Study study, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		// 토큰에서 유저 ID 읽어와서 스터디의 모임장 id로 등록
		if(img != null) {
			String filePath = baseDirectory + "\\" + img.getOriginalFilename();
			try {
				img.transferTo(new File(filePath));
				study.setLogo(filePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int userId = getUserPK(request);
		Study originalStudy = studyService.search(studyId);
		study.setId(studyId);
		study.setUser_id(userId);
		
		if(originalStudy == null) {
			System.out.println("** 스터디 수정 실패 - 없는 스터디입니다.");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(study.getLogo() == null) {
			if(originalStudy.getLogo() != null) {
				study.setLogo(originalStudy.getLogo());
			}
		}
		if(study.getName() == null) {
			study.setName(originalStudy.getName());
		}
		if(study.getDescription() == null) {
			study.setDescription(originalStudy.getDescription());
		}
		
		if(originalStudy.getUser_id() != userId) {
			System.out.println("** 스터디 수정 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		
		study.setId(studyId);
		study.setUser_id(userId);
		studyService.modify(study);
		return new ResponseEntity<>("success", HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "스터디를 삭제한다. 해당 소모임의 모임장이 아닌 사람이 시도할 경우 실패한다. (로그인 필요)")
	@DeleteMapping("/{studyId}")
	public Object deleteStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = getUserPK(request);
		Study study = studyService.search(studyId);
		
		if(study.getUser_id() != userId) {
			System.out.println("** 스터디 삭제 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if(studyService.delete(studyId)) {
			System.out.println("스터디 삭제 성공");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** 스터디 삭제 실패 - 서버 에러");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ApiOperation(value = "스터디의 공지사항 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{studyId}/notice")
	public Object showNotice(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int noticeBoardId = boardService.findNoticeBoardId(studyId);
		if(noticeBoardId != 0) {
			List<ArticleReturnDTO> articles = articleService.searchArticles(studyId, noticeBoardId);
//			List<Article> articles = articleService.searchBoardArticles(noticeBoardId);
			System.out.println(studyId + "번 스터디의 공지사항 글 리스트 반환");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		}
		System.out.println("** " + studyId + "번 스터디의 공지사항 글 리스트 반환 실패");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{studyId}/qna")
	public Object showQnas(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** qna 게시판 글리스트 조회 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		int qnaBoardId = boardService.findQnABoardId(studyId);
		if(qnaBoardId != 0) {
			List<ArticleReturnDTO> articles = articleService.searchArticles(studyId, qnaBoardId);
//			List<Article> articles = articleService.searchBoardArticles(qnaBoardId);
			System.out.println(studyId + "번 스터디의 QnA 게시판 글 리스트 반환");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		}
		System.out.println("** " + studyId + "번 스터디의 QnA 게시판 글 리스트 반환 실패");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{studyId}/repository")
	public Object showRepositories(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 자료실 게시판 글 조회 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		int repoBoardId = boardService.findRepoBoardId(studyId);
		if(repoBoardId != 0) {
			List<ArticleReturnDTO> articles = articleService.searchArticles(studyId, repoBoardId);
//			List<Article> articles = articleService.searchBoardArticles(repoBoardId);
			System.out.println(studyId + "번 스터디의 자료실 글 리스트 반환");
			return new ResponseEntity<>(articles, HttpStatus.OK);
		}
		System.out.println("** " + studyId + "번 스터디의 자료실 글 리스트 반환 실패");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "공지글 작성 (모임장 로그인 필요)")
	@PostMapping("/{studyId}/notice")
	public Object showNotice(Article article, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = getUserPK(request);
		Study study = studyService.search(studyId);
		
		if(userId != study.getUser_id()) {
			System.out.println("** 공지글 작성 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		
		int noticeBoardId = boardService.findNoticeBoardId(studyId);
		article.setBoard_id(noticeBoardId);
		article.setUser_id(userId);
		
		if(articleService.create(article)) {
			System.out.println("공지글 작성 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** 공지글 작성 실패 - 서버 오류...");
		return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판에 글을 작성한다. (로그인 필요)")
	@PostMapping("/{studyId}/qna")
	public Object writeQna(@RequestParam(value = "img", required = false) MultipartFile img, Article article, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		if(img != null) {
			String filePath = baseDirectory + "\\" + img.getOriginalFilename();
			try {
				img.transferTo(new File(filePath));
				article.setFile(filePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int userId = getUserPK(request);
		if(joinService.isMember(studyId, userId)) {
			article.setBoard_id(boardService.findQnABoardId(studyId));
			article.setUser_id(userId);
			articleService.create(article);
			System.out.println(studyId + "번 스터디의 qna 게시판에 글 작성 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** " + studyId + "번 스터디의 qna 게시판에 글 작성 실패...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판에 글을 작성한다. (로그인 필요)")
	@PostMapping("/{studyId}/repository")
	public Object writeRepo(@RequestParam(value = "img", required = false) MultipartFile img, Article article, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		if(img != null) {
			String filePath = baseDirectory + "\\" + img.getOriginalFilename();
			try {
				img.transferTo(new File(filePath));
				article.setFile(filePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		int userId = getUserPK(request);
		if(joinService.isMember(studyId, userId)) {
			article.setBoard_id(boardService.findRepoBoardId(studyId));
			article.setUser_id(userId);
			articleService.create(article);
			System.out.println(studyId + "번 스터디의 자료실 게시판에 글 작성 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** " + studyId + "번 스터디의 자료실 게시판에 글 작성 실패...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 공지사항 글 조회")
	@GetMapping("/{studyId}/notice/{articleId}")
	public Object readNotice(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		
		Article article = articleService.search(articleId);
		if(article != null) {
			System.out.println(articleId + "번 글 조회 성공!");
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		System.out.println("** " + articleId + "번 글 조회 실패..");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판 글 조회")
	@GetMapping("/{studyId}/qna/{articleId}")
	public Object readQna(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** qna 게시판 글 조회 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		Article article = articleService.search(articleId);
		if(article != null) {
			System.out.println(articleId + "번 글 조회 성공!");
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		System.out.println("** " + articleId + "번 글 조회 실패..");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 조회")
	@GetMapping("/{studyId}/repository/{articleId}")
	public Object readRepo(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 자료실 게시판 글 조회 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		Article article = articleService.search(articleId);
		if(article != null) {
			System.out.println(articleId + "번 글 조회 성공!");
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		System.out.println("** " + articleId + "번 글 조회 실패..");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 공지사항 글 수정 (로그인 필요)")
	@PutMapping("/{studyId}/notice/{articleId}")
	public Object modifyNotice(Article article, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		
		Article originalArticle = articleService.search(articleId);
		
		if(originalArticle == null) {
			System.out.println("** " + articleId + "번 글 수정 실패 - 찾을 수 없는 게시글");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		int userId = getUserPK(request);
		if(originalArticle.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		
		article.setId(articleId);
		article.setUser_id(userId);
		article.setBoard_id(boardService.findQnABoardId(studyId));
		
		articleService.modify(article);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디의 QnA 게시판 글 수정 (로그인 필요)")
	@PutMapping("/{studyId}/qna/{articleId}")
	public Object modifyQna(@RequestParam(value = "img", required = false) MultipartFile img, 
						Article article, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		if(img != null) {
			String filePath = baseDirectory + "\\" + img.getOriginalFilename();
			try {
				img.transferTo(new File(filePath));
				article.setFile(filePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Article originalArticle = articleService.search(articleId);
		
		if(originalArticle == null) {
			System.out.println("** " + articleId + "번 글 수정 실패 - 찾을 수 없는 게시글");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		int userId = getUserPK(request);
		if(originalArticle.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		
		article.setId(articleId);
		article.setUser_id(userId);
		article.setBoard_id(boardService.findQnABoardId(studyId));
		
		articleService.modify(article);
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 수정 (로그인 필요)")
	@PutMapping("/{studyId}/repository/{articleId}")
	public Object modifyRepo(@RequestParam(value = "img", required = false) MultipartFile img, 
							Article article, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		if(img != null) {
			String filePath = baseDirectory + "\\" + img.getOriginalFilename();
			try {
				img.transferTo(new File(filePath));
				article.setFile(filePath);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Article originalArticle = articleService.search(articleId);
		
		if(originalArticle == null) {
			System.out.println("** " + articleId + "번 글 수정 실패 - 찾을 수 없는 게시글");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		int userId = getUserPK(request);
		if(originalArticle.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		
		article.setId(articleId);
		article.setUser_id(userId);
		article.setBoard_id(boardService.findRepoBoardId(studyId));
		
		articleService.modify(article);
		System.out.println(articleId + "번 글 수정 성공!");
		return new ResponseEntity<>("success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디의 공지사항 글 삭제 (로그인 필요)")
	@DeleteMapping("/{studyId}/notice/{articleId}")
	public Object deleteNotice(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Article article = articleService.search(articleId);
		int userId = getUserPK(request);
		
		if(article == null) {
			System.out.println("** " + articleId + "번 글 삭제 실패 - 찾을 수 없는 게시글");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(article.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			System.out.println("** " + articleId + "번 글 삭제 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if(articleService.delete(articleId)) {
			System.out.println(articleId + "번 글 삭제 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** " + articleId + "번 글 삭제 실패 - 서버 에러");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판 글 삭제 (로그인 필요)")
	@DeleteMapping("/{studyId}/qna/{articleId}")
	public Object deleteQna(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Article article = articleService.search(articleId);
		int userId = getUserPK(request);
		
		if(article == null) {
			System.out.println("** " + articleId + "번 글 삭제 실패 - 찾을 수 없는 게시글");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(article.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			System.out.println("** " + articleId + "번 글 삭제 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if(articleService.delete(articleId)) {
			System.out.println(articleId + "번 글 삭제 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** " + articleId + "번 글 삭제 실패 - 서버 에러");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 삭제 (로그인 필요)")
	@DeleteMapping("/{studyId}/repository/{articleId}")
	public Object deleteRepo(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Article article = articleService.search(articleId);
		int userId = getUserPK(request);
		
		if(article == null) {
			System.out.println("** " + articleId + "번 글 삭제 실패 - 찾을 수 없는 게시글");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(article.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			System.out.println("** " + articleId + "번 글 삭제 실패 - 권한 없음");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if(articleService.delete(articleId)) {
			System.out.println(articleId + "번 글 삭제 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** " + articleId + "번 글 삭제 실패 - 서버 에러");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna 게시글의 댓글 리스트 반환")
	@GetMapping("/{studyId}/qna/{articleId}/comments")
	public Object showQnaComments(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId) {
		List<Comment> comments = commentService.searchArticleComments(articleId);
		System.out.println("qna 게시글의 댓글 리스트 반환 성공!");
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 리스트 반환")
	@GetMapping("/{studyId}/repository/{articleId}/comments")
	public Object showRepoComments(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId) {
		List<Comment> comments = commentService.searchArticleComments(articleId);
		System.out.println("자료실 게시글의 댓글 리스트 반환 성공!");
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "qna 게시글의 댓글 작성")
	@PostMapping("/{studyId}/qna/{articleId}/comments")
	public Object createQnaComment(Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = getUserPK(request);
		comment.setArticle_id(articleId);
		comment.setUser_id(userId);
		
		if(commentService.create(comment)) {
			System.out.println("댓글 작성 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** 댓글 작성 실패 - 서버 오류...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 작성")
	@PostMapping("/{studyId}/repository/{articleId}/comments")
	public Object createRepoComment(Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = getUserPK(request);
		comment.setArticle_id(articleId);
		comment.setUser_id(userId);
		
		if(commentService.create(comment)) {
			System.out.println("댓글 작성 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** 댓글 작성 실패 - 서버 오류...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna 게시글의 댓글 수정")
	@PutMapping("/{studyId}/qna/{articleId}/comments/{commentId}")
	public Object modifyQnaComment(Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = getUserPK(request);
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			System.out.println("** 댓글 수정 실패 - 댓글이 존재하지 않음...");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			System.out.println("** 댓글 수정 실패 - 권한 없음...");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		comment.setId(commentId);
		
		if(commentService.update(comment)) {
			System.out.println("댓글 수정 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** 댓글 수정 실패 - 서버 오류...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 수정")
	@PutMapping("/{studyId}/repository/{articleId}/comments/{commentId}")
	public Object modifyRepoComment(Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = getUserPK(request);
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			System.out.println("** 댓글 수정 실패 - 댓글이 존재하지 않음...");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			System.out.println("** 댓글 수정 실패 - 권한 없음...");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		comment.setId(commentId);
		
		if(commentService.update(comment)) {
			 System.out.println("댓글 수정 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** 댓글 수정 실패 - 서버 오류...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "QnA 게시글의 댓글 삭제")
	@DeleteMapping("/{studyId}/qna/{articleId}/comments/{commentId}")
	public Object deleteQnaComment(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = getUserPK(request);
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			System.out.println("** 댓글 삭제 실패 - 댓글이 존재하지 않음...");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			System.out.println("** 댓글 삭제 실패 - 권한 없음...");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if(commentService.delete(commentId)) {
			System.out.println("댓글 삭제 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);			
		}
		System.out.println("** 댓글 삭제 실패 - 서버 오류...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 삭제")
	@DeleteMapping("/{studyId}/repository/{articleId}/comments/{commentId}")
	public Object deleteRepoComment(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = getUserPK(request);
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			System.out.println("** 댓글 삭제 실패 - 댓글이 존재하지 않음...");
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			System.out.println("** 댓글 삭제 실패 - 권한 없음...");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if(commentService.delete(commentId)) {
			System.out.println("댓글 삭제 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** 댓글 삭제 실패 - 서버 오류...");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "소모임에 가입한다.")
	@PostMapping("/{studyId}/join")
	public Object joinStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(joinService.isMember(studyId, userId)) {
			System.out.println("** 소모임 가입 실패 - 이미 가입한 회원...");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		
		if(joinService.join(studyId, userId)) {
			System.out.println("소모임 가입 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** 스터디 생성 실패 - 서버 에러");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "소모임을 탈퇴한다.")
	@DeleteMapping("/{studyId}/leave")
	public Object leaveStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = getUserPK(request);
		if(!joinService.isMember(studyId, userId)) {
			System.out.println("** 소모임 탈퇴 실패 - 이미 탈퇴했거나 가입하지 않은 소모임...");
			return new ResponseEntity<>("fail", HttpStatus.UNAUTHORIZED);
		}
		if(joinService.leave(studyId, userId)) {
			System.out.println("소모임 탈퇴 성공!");
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		System.out.println("** 소모임 탈퇴 실패 - 서버 에러");
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
