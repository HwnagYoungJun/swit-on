package com.ssafy.switon.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

import com.ssafy.switon.dao.ArticleDAO;
import com.ssafy.switon.dto.Alarm;
import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleDetailReturnDTO;
import com.ssafy.switon.dto.ArticleReturnDTO;
import com.ssafy.switon.dto.BestUsersReturnDTO;
import com.ssafy.switon.dto.Board;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.CommentReturnDTO;
import com.ssafy.switon.dto.DashBoardReturnDTO;
import com.ssafy.switon.dto.Like;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyReturnDTO;
import com.ssafy.switon.dto.StudySimple;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserRate;
import com.ssafy.switon.dto.UserSimpleDTO;
import com.ssafy.switon.service.AlarmService;
import com.ssafy.switon.service.ArticleFavService;
import com.ssafy.switon.service.ArticleLikeService;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.BoardService;
import com.ssafy.switon.service.CategoryService;
import com.ssafy.switon.service.CommentService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.service.UserScheduleService;
import com.ssafy.switon.service.UserService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("study")
@Api(value="StudyRestController", description="소모임과 관련된 모든 것")
public class StudyRestController {
	
	String baseDirectory = "C:\\SSAFY\\spring_workspace\\switon\\img";
	
	@Autowired
	private AlarmService alarmService;
	
	@Autowired
	private UserScheduleService userScheduleService;
	
	@Autowired
	private ArticleLikeService articleLikeService;
	
	@Autowired
	private CategoryService categoryService;
	
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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleFavService articleFavService;
	
	@Autowired
	ArticleDAO articleDAO;
	
	@Autowired
	SimpMessagingTemplate template;
	
	@ApiOperation(value = "스터디 검색 결과를 반환한다.", response = List.class)
	@GetMapping("/search")
	public Object searchStudy(@RequestParam(value="keyword", required = false) String keyword){
		List<Study> dto = new ArrayList<Study>();
		dto = studyService.searchStudiesByKeywordEnter(keyword);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디 리스트를 반환한다.", response = List.class)
	@GetMapping("")
	public Object showAllStudies(@RequestParam(value="lowercategory_id", required = false) String lowercategory_id,
									@RequestParam(value="uppercategory_id", required = false) String uppercategory_id,
									@RequestParam(value="keyword", required = false) String keyword){
		Object dto;
		if(lowercategory_id != null) {
			int low_id = Integer.parseInt(lowercategory_id);
			if(!categoryService.lowerCategoryIdExists(low_id)) {
				return new ResponseEntity<>(new ReturnMsg("존재하지 않는 카테고리입니다."), HttpStatus.NOT_FOUND);
			}
			dto = studyService.searchStudiesByLowercategory(low_id);
		} else if(uppercategory_id != null) {
			int upper_id = Integer.parseInt(uppercategory_id);
			if(!categoryService.upperCategoryIdExists(upper_id)) {
				return new ResponseEntity<>(new ReturnMsg("존재하지 않는 카테고리입니다."), HttpStatus.NOT_FOUND);
			}
			dto = studyService.searchStudiesByUppercategory(upper_id);
		} else if(keyword != null) {
			dto = studyService.searchStudyByKeyword(keyword);
		} else {
			dto = studyService.searchAll();			
		}
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디 id로 스터디 상세정보를 반환한다.", response = Study.class)
	@GetMapping("/{studyId}")
	public Object searchStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		String auth = request.getHeader("Authentication");
		boolean isJoined = false;
		boolean isLeader = false;
		int userId = 0;
		Study study = studyService.search(studyId);
		if(study == null) {
			return new ResponseEntity<>(new ReturnMsg("스터디가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		if(auth != null && auth.contains("Bearer ") && auth.length() > 15) {
			auth = auth.substring("Bearer ".length());	
			userId = getUserPK(request);
			if(joinService.isMember(studyId, userId)) {
				isJoined = true;
			}
			if(userId == studyService.search(studyId).getUser_id()) {
				isLeader = true;
			}
		}
		try {
			StudyReturnDTO dto = studyService.search(studyId, isJoined, isLeader, userId);	
			return new ResponseEntity<>(dto, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("스터디를 찾을 수 없습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "새로운 스터디를 생성한다. 로그인한 사용자가 모임장이 된다. (로그인 필요)")
	@PostMapping("/")
	public Object createStudy(@RequestParam(value = "img", required = false) MultipartFile img, Study study, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		int startTime = Integer.parseInt((study.getStart_time().replace(":", "")));
		int endTime = Integer.parseInt(study.getEnd_time().replace(":", ""));
		if(endTime - startTime <= 0) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 시간을 입력했습니다."), HttpStatus.BAD_REQUEST);
		}
		if(study.getStart_term().after(study.getEnd_term())) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 기간을 입력했습니다."), HttpStatus.BAD_REQUEST);
		}
		if(study.getUsers_limit() == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저수 제한은 0명이 될 수 없습니다."), HttpStatus.BAD_REQUEST);
		}
		if(img != null) {
			long time = System.currentTimeMillis();
			String RealPath = getUploadRealPath(request, userId, img, time);
			String path = getUploadPath(userId, img, time);
			try {
				img.transferTo(new File(RealPath));
				study.setLogo(path);
			} catch (IOException e) {
				return new ResponseEntity<>(new ReturnMsg("파일 업로드 중 에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		// 토큰에서 유저 ID 읽어와서 스터디의 모임장 id로 등록
		study.setUser_id(userId);
		try {
			int studyId = studyService.create(study, userId);
			if(studyId > 0) {
				boardService.create(new Board(studyId, 1));
				boardService.create(new Board(studyId, 2));
				boardService.create(new Board(studyId, 3));
				
				joinService.join(studyId, userId);
				
				return new ResponseEntity<>(studyId, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("소모임 생성을 실패했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ApiOperation(value = "스터디를 수정한다. 해당 소모임의 모임장이 아닌 사람이 시도할 경우 실패한다. (로그인 필요)")
	@PutMapping("/{studyId}")
	public Object modifyStudy(@RequestParam(value = "img", required = false) MultipartFile img,
							Study study, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		int startTime = Integer.parseInt((study.getStart_time().replace(":", "")));
		int endTime = Integer.parseInt(study.getEnd_time().replace(":", ""));
		if(endTime - startTime <= 0) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 시간을 입력했습니다."), HttpStatus.BAD_REQUEST);
		}
		if(study.getStart_term().after(study.getEnd_term())) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 기간을 입력했습니다."), HttpStatus.BAD_REQUEST);
		}
		
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(img != null) {
			long time = System.currentTimeMillis();
			String RealPath = getUploadRealPath(request, userId, img, time);
			String path = getUploadPath(userId, img, time);
			try {
				img.transferTo(new File(RealPath));
				study.setLogo(path);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("파일 업로드 중 에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		Study originalStudy = studyService.search(studyId);
		study.setId(studyId);
		study.setUser_id(userId);
		
		if(originalStudy == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 스터디입니다."), HttpStatus.NOT_FOUND);
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
			return new ResponseEntity<>(new ReturnMsg("수정 권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		
		study.setId(studyId);
		study.setUser_id(userId);
		try {
			studyService.modify(study);
			return new ResponseEntity<>(new ReturnMsg("스터디를 성공적으로 수정하였습니다."), HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("스터디 수정에 실패했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ApiOperation(value = "스터디를 삭제한다. 해당 소모임의 모임장이 아닌 사람이 시도할 경우 실패한다. (로그인 필요)")
	@DeleteMapping("/{studyId}")
	public Object deleteStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Study study = studyService.search(studyId);
		if(study == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 스터디입니다."), HttpStatus.NOT_FOUND);
		}
		try {
			if(study.getUser_id() != userId) {
				return new ResponseEntity<>(new ReturnMsg("삭제 권한이 없습니다."), HttpStatus.FORBIDDEN);
			}
			if(studyService.delete(studyId)) {
				return new ResponseEntity<>(new ReturnMsg("스터디를 성공적으로 삭제하였습니다."), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("스터디 삭제에 실패했습니다. 시스템 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 대쉬보드를 반환한다. (최신글순)(공지5, qna5, 자료실5)", response = List.class)
	@GetMapping("/{studyId}/dashboard")
	public Object showDashboard(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("가입하지 않은 소모임입니다."), HttpStatus.FORBIDDEN);
		}
		int noticeBoardId = boardService.findNoticeBoardId(studyId);
		int qnaBoardId = boardService.findQnABoardId(studyId);
		int repoBoardId = boardService.findRepoBoardId(studyId);
		try {
			if(noticeBoardId != 0) {
				List<ArticleReturnDTO> notice = articleService.searchArticlesByBoardIdLimit5(studyId, noticeBoardId, 1, userId);
				List<ArticleReturnDTO> qna = articleService.searchArticlesByBoardIdLimit5(studyId, qnaBoardId, 2, userId);
				List<ArticleReturnDTO> repository = articleService.searchArticlesByBoardIdLimit5(studyId, repoBoardId, 3, userId);
				DashBoardReturnDTO dto = new DashBoardReturnDTO(notice, qna, repository);
				return new ResponseEntity<>(dto, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("대쉬보드 글들을 받아올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 공지사항 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{studyId}/notice")
	public Object showNotice(@PathVariable("studyId") int studyId, @RequestParam int index, HttpServletRequest request) {
		Study study = studyService.search(studyId);
		if(study == null) {
			return new ResponseEntity<>(new ReturnMsg("없는 스터디입니다."), HttpStatus.NOT_FOUND);
		}
		int userId = 0;
		String auth = request.getHeader("Authentication");
		if(auth != null && auth.contains("Bearer ") && auth.length() > 15) {
			userId = getUserPK(request);
		}
		int noticeBoardId = boardService.findNoticeBoardId(studyId);
		if(noticeBoardId != 0) {
			try {
				List<ArticleReturnDTO> articles = articleService.searchArticlesWithIndex(noticeBoardId, userId, index);
				return new ResponseEntity<>(articles, HttpStatus.OK);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(new ReturnMsg("공지사항을 받아올 수 없었습니다. 시스템 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{studyId}/qna")
	public Object showQnas(@PathVariable("studyId") int studyId, @RequestParam int index, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Study study = studyService.search(studyId);
		if(study == null) {
			return new ResponseEntity<>(new ReturnMsg("없는 스터디입니다."), HttpStatus.NOT_FOUND);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		int qnaBoardId = boardService.findQnABoardId(studyId);
		if(qnaBoardId != 0) {
			try {
				List<ArticleReturnDTO> articles = articleService.searchArticlesWithIndex(qnaBoardId, userId, index);
				return new ResponseEntity<>(articles, HttpStatus.OK);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(new ReturnMsg("QnA를 받아올 수 없었습니다. 시스템 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 리스트를 반환한다.", response = List.class)
	@GetMapping("/{studyId}/repository")
	public Object showRepositories(@PathVariable("studyId") int studyId, @RequestParam int index, HttpServletRequest request) {
		Study study = studyService.search(studyId);
		if(study == null) {
			return new ResponseEntity<>(new ReturnMsg("없는 스터디입니다."), HttpStatus.NOT_FOUND);
		}
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		int repoBoardId = boardService.findRepoBoardId(studyId);
		if(repoBoardId != 0) {
			try {
				List<ArticleReturnDTO> articles = articleService.searchArticlesWithIndex(repoBoardId, userId, index);
				return new ResponseEntity<>(articles, HttpStatus.OK);				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(new ReturnMsg("자료실 글을 받아올 수 없었습니다. 시스템 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "공지글 작성 (모임장 로그인 필요)")
	@PostMapping("/{studyId}/notice")
	public Object showNotice(Article article, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Study study = studyService.search(studyId);
		
		if(userId != study.getUser_id()) {
			return new ResponseEntity<>(new ReturnMsg("작성 권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		
		int noticeBoardId = boardService.findNoticeBoardId(studyId);
		article.setBoard_id(noticeBoardId);
		article.setUser_id(userId);
		try {
			int articleId = articleService.create(article, studyId);
			if(articleId > 0) {
				return new ResponseEntity<>(articleId, HttpStatus.OK);
			}
		} catch (Exception e) {
		}
		return new ResponseEntity<>(new ReturnMsg("공지글 작성을 실패했습니다. 서버 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판에 글을 작성한다. (로그인 필요)")
	@PostMapping("/{studyId}/qna")
	public Object writeQna(@RequestParam(value = "upload", required = false) MultipartFile img, Article article, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("작성 권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(img != null) {
			long time = System.currentTimeMillis();
			String RealPath = getUploadRealPath(request, userId, img, time);
			String path = getUploadPath(userId, img, time);
			try {
				img.transferTo(new File(RealPath));
				article.setFile(path);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("파일 업로드 중 에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		if(joinService.isMember(studyId, userId)) {
			article.setBoard_id(boardService.findQnABoardId(studyId));
			article.setUser_id(userId);
			int articleId = articleService.create(article, studyId);
			joinService.givePoint(userId, studyId, 1);
			return new ResponseEntity<>(articleId, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ReturnMsg("글 작성을 실패하였습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판에 글을 작성한다. (로그인 필요)")
	@PostMapping("/{studyId}/repository")
	public Object writeRepo(@RequestParam(value = "upload", required = false) MultipartFile img, Article article, @PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("작성 권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(img != null) {
			long time = System.currentTimeMillis();
			String RealPath = getUploadRealPath(request, userId, img, time);
			String path = getUploadPath(userId, img, time);
			try {
				img.transferTo(new File(RealPath));
				article.setFile(path);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("파일 업로드 중 에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		if(joinService.isMember(studyId, userId)) {
			article.setBoard_id(boardService.findRepoBoardId(studyId));
			article.setUser_id(userId);
			int articleId = articleService.create(article, studyId);
			joinService.givePoint(userId, studyId, 5);
			return new ResponseEntity<>(articleId, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ReturnMsg("글 작성을 실패하였습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 공지사항 글 조회")
	@GetMapping("/{studyId}/notice/{articleId}")
	public Object readNotice(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Study std = studyService.search(studyId);
		if(std == null) {
			return new ResponseEntity<>(new ReturnMsg("없는 스터디입니다."), HttpStatus.NOT_FOUND);
		}
		int userId = 0;
		String auth = request.getHeader("Authentication");
		if(auth != null && auth.contains("Bearer ") && auth.length() > 15) {
			userId = getUserPK(request);
		}
		Article originalArticle = articleService.search(articleId, studyId, 1);
		if(originalArticle == null) {
			return new ResponseEntity<>(new ReturnMsg("글이 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		if(originalArticle != null) {
			List<CommentReturnDTO> comments = commentService.searchArticleCommentsIncludingProfile(articleId, userId);
			Like like = new Like(articleLikeService.searchLikeCount(articleId),
					articleLikeService.searchByUser_Article(userId, articleId) != null);
			ArticleDetailReturnDTO article = new ArticleDetailReturnDTO(originalArticle, comments, like);
			
			UserInfoDTO userInfo = userService.search(originalArticle.getUser_id());
			UserSimpleDTO user = new UserSimpleDTO();
			user.setId(userInfo.getId());
			user.setName(userInfo.getName());
			user.setProfile_image(userInfo.getProfile_image());
			article.setUser(user);
			
			Study studyInfo = studyService.search(studyId);
			StudySimple study = new StudySimple(studyInfo);
			article.setStudy(study);
			
			article.setIsfavorite(articleFavService.searchByUser_Article(userId, articleId) != null);
			
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ReturnMsg("글을 불러올 수 없습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ApiOperation(value = "스터디의 qna 게시판 글 조회")
	@GetMapping("/{studyId}/qna/{articleId}")
	public Object readQna(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Study std = studyService.search(studyId);
		if(std == null) {
			return new ResponseEntity<>(new ReturnMsg("없는 스터디입니다."), HttpStatus.NOT_FOUND);
		}
		int userId = getUserPK(request);
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		Article originalArticle = articleService.search(articleId, studyId, 2);
		if(originalArticle == null) {
			return new ResponseEntity<>(new ReturnMsg("글이 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		if(originalArticle != null) {
			List<CommentReturnDTO> comments = commentService.searchArticleCommentsIncludingProfile(articleId, userId);
			Like like = new Like(articleLikeService.searchLikeCount(articleId),
					articleLikeService.searchByUser_Article(userId, articleId) != null);
			ArticleDetailReturnDTO article = new ArticleDetailReturnDTO(originalArticle, comments, like);
			
			UserInfoDTO userInfo = userService.search(originalArticle.getUser_id());
			UserSimpleDTO user = new UserSimpleDTO();
			user.setId(userInfo.getId());
			user.setName(userInfo.getName());
			user.setProfile_image(userInfo.getProfile_image());
			article.setUser(user);
			
			Study studyInfo = studyService.search(studyId);
			StudySimple study = new StudySimple(studyInfo);
			article.setStudy(study);
			
			article.setIsfavorite(articleFavService.searchByUser_Article(userId, articleId) != null);

			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ReturnMsg("글을 불러올 수 없습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 조회")
	@GetMapping("/{studyId}/repository/{articleId}")
	public Object readRepo(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Study std = studyService.search(studyId);
		if(std == null) {
			return new ResponseEntity<>(new ReturnMsg("없는 스터디입니다."), HttpStatus.NOT_FOUND);
		}
		int userId = getUserPK(request);
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		Article originalArticle = articleService.search(articleId, studyId, 3);
		if(originalArticle == null) {
			return new ResponseEntity<>(new ReturnMsg("글이 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		if(originalArticle != null) {
			List<CommentReturnDTO> comments = commentService.searchArticleCommentsIncludingProfile(articleId, userId);
			Like like = new Like(articleLikeService.searchLikeCount(articleId),
					articleLikeService.searchByUser_Article(userId, articleId) != null);
			ArticleDetailReturnDTO article = new ArticleDetailReturnDTO(originalArticle, comments, like);
			
			UserInfoDTO userInfo = userService.search(originalArticle.getUser_id());
			UserSimpleDTO user = new UserSimpleDTO();
			user.setId(userInfo.getId());
			user.setName(userInfo.getName());
			user.setProfile_image(userInfo.getProfile_image());
			article.setUser(user);
			
			Study studyInfo = studyService.search(studyId);
			StudySimple study = new StudySimple(studyInfo);
			article.setStudy(study);

			article.setIsfavorite(articleFavService.searchByUser_Article(userId, articleId) != null);
			
			return new ResponseEntity<>(article, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ReturnMsg("글을 불러올 수 없습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 공지사항 글 수정 (로그인 필요)")
	@PutMapping("/{studyId}/notice/{articleId}")
	public Object modifyNotice(Article article, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		
		Article originalArticle = articleService.search(articleId);
		
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		
		if(originalArticle == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 글입니다."), HttpStatus.NOT_FOUND);
		}
		if(originalArticle.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		
		article.setId(articleId);
		article.setUser_id(userId);
		article.setBoard_id(boardService.findQnABoardId(studyId));
		
		articleService.modify(article);
		return new ResponseEntity<>(new ReturnMsg("성공적으로 수정하였습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디의 QnA 게시판 글 수정 (로그인 필요)")
	@PutMapping("/{studyId}/qna/{articleId}")
	public Object modifyQna(@RequestParam(value = "upload", required = false) MultipartFile img, 
						Article article, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(img != null) {
			long time = System.currentTimeMillis();
			String RealPath = getUploadRealPath(request, userId, img, time);
			String path = getUploadPath(userId, img, time);
			try {
				img.transferTo(new File(RealPath));
				article.setFile(path);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("파일 업로드 중 에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		Article originalArticle = articleService.search(articleId);
		
		if(originalArticle == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 글입니다."), HttpStatus.NOT_FOUND);
		}
		
		if(originalArticle.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		
		article.setId(articleId);
		article.setUser_id(userId);
		article.setBoard_id(boardService.findQnABoardId(studyId));
		
		articleService.modify(article);
		return new ResponseEntity<>(new ReturnMsg("성공적으로 수정하였습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 수정 (로그인 필요)")
	@PutMapping("/{studyId}/repository/{articleId}")
	public Object modifyRepo(@RequestParam(value = "upload", required = false) MultipartFile img, 
							Article article, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(img != null) {
			long time = System.currentTimeMillis();
			String RealPath = getUploadRealPath(request, userId, img, time);
			String path = getUploadPath(userId, img, time);
			try {
				img.transferTo(new File(RealPath));
				article.setFile(path);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("파일 업로드 중 에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		Article originalArticle = articleService.search(articleId);
		
		if(originalArticle == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 글입니다."), HttpStatus.NOT_FOUND);
		}
		
		if(originalArticle.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		
		article.setId(articleId);
		article.setUser_id(userId);
		article.setBoard_id(boardService.findRepoBoardId(studyId));
		
		articleService.modify(article);
		return new ResponseEntity<>(new ReturnMsg("성공적으로 수정하였습니다."), HttpStatus.OK);
	}
	
	@ApiOperation(value = "스터디의 공지사항 글 삭제 (로그인 필요)")
	@DeleteMapping("/{studyId}/notice/{articleId}")
	public Object deleteNotice(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Article article = articleService.search(articleId);
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		
		if(article == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 글입니다."), HttpStatus.NOT_FOUND);
		}
		if(article.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(articleService.delete(articleId)) {
			return new ResponseEntity<>(new ReturnMsg("성공적으로 삭제하였습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("삭제에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 qna 게시판 글 삭제 (로그인 필요)")
	@DeleteMapping("/{studyId}/qna/{articleId}")
	public Object deleteQna(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Article article = articleService.search(articleId);
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		
		if(article == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 글입니다."), HttpStatus.NOT_FOUND);
		}
		if(article.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(articleService.delete(articleId)) {
			return new ResponseEntity<>(new ReturnMsg("성공적으로 삭제하였습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("삭제에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "스터디의 자료실 게시판 글 삭제 (로그인 필요)")
	@DeleteMapping("/{studyId}/repository/{articleId}")
	public Object deleteRepo(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		Article article = articleService.search(articleId);
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		
		if(article == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 글입니다."), HttpStatus.NOT_FOUND);
		}
		if(article.getUser_id() != userId) { // 내가 쓴 글이 아니면 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(articleService.delete(articleId)) {
			return new ResponseEntity<>(new ReturnMsg("성공적으로 삭제하였습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("삭제에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "notice 게시글의 댓글 리스트 반환")
	@GetMapping("/{studyId}/notice/{articleId}/comments")
	public Object showNoticeComments(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId) {
		List<Comment> comments = commentService.searchArticleComments(articleId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "qna 게시글의 댓글 리스트 반환")
	@GetMapping("/{studyId}/qna/{articleId}/comments")
	public Object showQnaComments(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId) {
		List<Comment> comments = commentService.searchArticleComments(articleId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 리스트 반환")
	@GetMapping("/{studyId}/repository/{articleId}/comments")
	public Object showRepoComments(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId) {
		List<Comment> comments = commentService.searchArticleComments(articleId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "notice 게시글의 댓글 작성")
	@PostMapping("/{studyId}/notice/{articleId}/comments")
	public Object createNoticeComment(@RequestBody Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		comment.setArticle_id(articleId);
		comment.setUser_id(userId);
		
		if(commentService.create(comment)) {			
			String studyName = studyService.search(studyId).getName();
			Article article = articleService.search(articleId);
			Alarm alarm = new Alarm(article.getUser_id(), 2, studyName +" 글에 새 댓글", studyId, articleId, 1);
			alarmService.createAlarm(alarm);
			return new ResponseEntity<>(new ReturnMsg("댓글을 성공적으로 작성했습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("작성에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna 게시글의 댓글 작성")
	@PostMapping("/{studyId}/qna/{articleId}/comments")
	public Object createQnaComment(@RequestBody Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		comment.setArticle_id(articleId);
		comment.setUser_id(userId);
		
		if(commentService.create(comment)) {
			
			String studyName = studyService.search(studyId).getName();
			Article article = articleService.search(articleId);
			Alarm alarm = new Alarm(article.getUser_id(), 2, studyName +" 글에 새 댓글", studyId, articleId, 2);
			//template.convertAndSend("/topic/notification/" + articleDAO.selectArticleById(articleId).getUser_id(), alarm);
			alarmService.createAlarm(alarm);
			
			joinService.givePoint(userId, studyId, 5);
			return new ResponseEntity<>(new ReturnMsg("댓글을 성공적으로 작성했습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("작성에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 작성")
	@PostMapping("/{studyId}/repository/{articleId}/comments")
	public Object createRepoComment(@RequestBody Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		comment.setArticle_id(articleId);
		comment.setUser_id(userId);
		
		if(commentService.create(comment)) {			
			String studyName = studyService.search(studyId).getName();
			Article article = articleService.search(articleId);
			Alarm alarm = new Alarm(article.getUser_id(), 2, studyName +" 글에 새 댓글", studyId, articleId, 3);
			//template.convertAndSend("/topic/notification/" + articleDAO.selectArticleById(articleId).getUser_id(), alarm);
			alarmService.createAlarm(alarm);
			
			joinService.givePoint(userId, studyId, 1);
			return new ResponseEntity<>(new ReturnMsg("댓글을 성공적으로 작성했습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("작성에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "notice 게시글의 댓글 수정")
	@PutMapping("/{studyId}/notice/{articleId}/comments/{commentId}")
	public Object modifyNoticeComment(Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 댓글입니다."), HttpStatus.NOT_FOUND);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		comment.setId(commentId);
		
		if(commentService.update(comment)) {
			return new ResponseEntity<>(new ReturnMsg("댓글을 성공적으로 수정했습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("수정에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna 게시글의 댓글 수정")
	@PutMapping("/{studyId}/qna/{articleId}/comments/{commentId}")
	public Object modifyQnaComment(Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 댓글입니다."), HttpStatus.NOT_FOUND);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		comment.setId(commentId);
		
		if(commentService.update(comment)) {
			return new ResponseEntity<>(new ReturnMsg("댓글을 성공적으로 수정했습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("수정에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 수정")
	@PutMapping("/{studyId}/repository/{articleId}/comments/{commentId}")
	public Object modifyRepoComment(Comment comment, @PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 댓글입니다."), HttpStatus.NOT_FOUND);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		comment.setId(commentId);
		
		if(commentService.update(comment)) {
			return new ResponseEntity<>(new ReturnMsg("댓글을 수정하였습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("수정에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "notice 게시글의 댓글 삭제")
	@DeleteMapping("/{studyId}/notice/{articleId}/comments/{commentId}")
	public Object deleteNoticeComment(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 댓글입니다."), HttpStatus.NOT_FOUND);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(commentService.delete(commentId)) {
			return new ResponseEntity<>(new ReturnMsg("댓글을 삭제했습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("삭제에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "QnA 게시글의 댓글 삭제")
	@DeleteMapping("/{studyId}/qna/{articleId}/comments/{commentId}")
	public Object deleteQnaComment(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 댓글입니다."), HttpStatus.NOT_FOUND);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(commentService.delete(commentId)) {
			return new ResponseEntity<>(new ReturnMsg("댓글을 삭제했습니다."), HttpStatus.OK);			
		}
		return new ResponseEntity<>(new ReturnMsg("삭제에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 게시글의 댓글 삭제")
	@DeleteMapping("/{studyId}/repository/{articleId}/comments/{commentId}")
	public Object deleteRepoComment(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment originalComment = commentService.search(commentId);
		if(originalComment == null) {
			return new ResponseEntity<>(new ReturnMsg("존재하지 않는 댓글입니다."), HttpStatus.NOT_FOUND);
		}
		if(originalComment.getUser_id() != userId) {	// 내가 쓴 댓글이 아닐 경우 실패
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		if(commentService.delete(commentId)) {
			return new ResponseEntity<>(new ReturnMsg("댓글을 삭제했습니다."), HttpStatus.OK);
		}
		return new ResponseEntity<>(new ReturnMsg("삭제에 실패했습니다. 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "소모임에 가입한다.")
	@PostMapping("/{studyId}/join")
	public Object joinStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		try {
			if(joinService.isMember(studyId, userId)) {
				return new ResponseEntity<>(new ReturnMsg("이미 가입한 소모임입니다."), HttpStatus.FORBIDDEN);
			}
			if(joinService.isFull(studyId)) {
				return new ResponseEntity<>(new ReturnMsg("최대 인원수에 도달하여 더 이상 가입할 수 없습니다."), HttpStatus.FORBIDDEN);
			}
			
			if(joinService.join(studyId, userId)) {
				Study study = studyService.search(studyId);
				
				Alarm alarm = new Alarm(study.getUser_id(), 4, study.getName() +" 새 멤버 가입", studyId, 0, 1);
				alarmService.createAlarm(alarm);
				return new ResponseEntity<>(new ReturnMsg("소모임에 성공적으로 가입했습니다."), HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("소모임 가입에 실패했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);			
	}
	
	@ApiOperation(value = "소모임을 탈퇴한다.")
	@DeleteMapping("/{studyId}/leave")
	public Object leaveStudy(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("이미 탈퇴한 소모임입니다."), HttpStatus.FORBIDDEN);
		}
		try {
			if(joinService.leave(studyId, userId)) {
				return new ResponseEntity<>(new ReturnMsg("소모임을 탈퇴했습니다."), HttpStatus.OK);
			}
		} catch (Exception e) {
		}
		return new ResponseEntity<>(new ReturnMsg("탈퇴에 실패했습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "베스트 유저들을 반환한다.")
	@GetMapping("/{studyId}/best")
	public Object getBestUsers(@PathVariable("studyId") int studyId) {
		try {
			BestUsersReturnDTO dto = studyService.findBestUsers(studyId);
			return new ResponseEntity<>(dto, HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("베스트 유저 정보를 받아올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "베스트 글들을 반환한다.")
	@GetMapping("/{studyId}/bestArticles")
	public Object getBestArticles(@PathVariable("studyId") int studyId) {
		try {
			return new ResponseEntity<>(articleService.searchTopArticles(studyId), HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("베스트 글 정보를 받아올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "참석율과 출석률을 반환한다.", response = UserRate.class)
	@GetMapping("/{studyId}/attend")
	public Object getUserRate(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		int userId = 0;
		String token = request.getHeader("Authentication");
		if(token != null) {
			userId = getUserPK(request);
		}
		if(userId != 0) {
			try {
				UserRate rate = userScheduleService.getUserParticipateRate(userId, studyId);
				return new ResponseEntity<>(rate, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(new ReturnMsg("출석률을 가져올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	
	private String getUploadRealPath(HttpServletRequest request, int userId, MultipartFile img, long time) {
		String fileName = img.getOriginalFilename();
		int pos = fileName.lastIndexOf(".");
		String ext = fileName.substring(pos);
		String result = request.getServletContext().getRealPath("static"+ File.separator + "upload")
				+ File.separator + userId + "_" + time + ext;
		return result;
	}
	private String getUploadPath(int userId, MultipartFile img, long time) {
		String fileName = img.getOriginalFilename();
		int pos = fileName.lastIndexOf(".");
		String ext = fileName.substring(pos);
		return "upload/" + userId + "_" + time + ext;
	}
	
	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
}
