package com.ssafy.switon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.ApiOperation;

@RestController
public class StudyRestController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	StudyLikeService studylikeService;
	
	@Autowired
	ArticleLikeService articlelikeService;
	
	@Autowired
	CommentLikeService commentlikeService;
	
	@Autowired
	ArticleFavService articlefavService;
	
	@ApiOperation(value = "소모임에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/like")
	public Object studyLike(@PathVariable("studyId") int studyId, HttpServletRequest request) {
			System.out.println("소모임 좋아요 누르기");
			
			int userId = getUserPK(request);
			StudyLike studylike = studylikeService.searchByUser_Study(userId, studyId);
			
			if(studylike != null) {
				System.out.println(studyId + "에 내가 이미 좋아요 누름!!");
				return new ResponseEntity<>("fail", HttpStatus.OK);
			}
			
			studylike = new StudyLike();
			studylike.setStudy_id(studyId);
			studylike.setUser_id(userId);
			if(studylikeService.createStudyLike(studylike)) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			}
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "소모임에 눌럿던 좋아요를 취소한다.")
	@DeleteMapping("/study/{studyId}/unlike")
	public Object studyUnLike(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		System.out.println("소모임 좋아요 취소");
		
		int userId = getUserPK(request);
		StudyLike studylike = studylikeService.searchByUser_Study(userId, studyId);
		
		if(studylike == null) {
			System.out.println(studyId + "에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		

		System.out.println(studyId + "에 내가 이미 좋아요 누름!!");
		if(studylikeService.deleteStudyLikeByUser(userId, studyId)) {
			return new ResponseEntity<>("success", HttpStatus.OK);				
			
		}
		
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/qna/{articleId}/like")
	public Object studyQnaLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
			System.out.println("qna게시판 글에 좋아요 누르기");
			
			int userId = getUserPK(request);
			ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
			
			if(articlelike != null) {
				System.out.println(articleId + "번 글에 내가 이미 좋아요 누름!!");
				return new ResponseEntity<>("fail", HttpStatus.OK);
			}
			
			articlelike = new ArticleLike();
			articlelike.setArticle_id(articleId);
			articlelike.setUser_id(userId);
			if(articlelikeService.createArticleLike(articlelike)) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			}
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/qna/{articleId}/unlike")
	public Object studyQnaUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		System.out.println("qna게시판 글에 눌렀던 좋아요 취소");
		
		int userId = getUserPK(request);
		ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
		
		if(articlelike == null) {
			System.out.println(articleId + "번 글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		
		System.out.println(articleId + "번 글에 내가 이미 좋아요 누름!!");
		if(articlelikeService.deleteArticleLikeByUser(userId, articleId)) {
			return new ResponseEntity<>("success", HttpStatus.OK);				
			
		}
		
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/repository/{articleId}/like")
	public Object studyRepoLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
			System.out.println("자료실게시판 글에 좋아요 누르기");
			
			int userId = getUserPK(request);
			ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
			
			if(articlelike != null) {
				System.out.println(articleId + "번 글에 내가 이미 좋아요 누름!!");
				return new ResponseEntity<>("fail", HttpStatus.OK);
			}
			
			articlelike = new ArticleLike();
			articlelike.setArticle_id(articleId);
			articlelike.setUser_id(userId);
			if(articlelikeService.createArticleLike(articlelike)) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			}
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/repository/{articleId}/unlike")
	public Object studyRepoUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		System.out.println("자료실게시판 글에 눌렀던 좋아요 취소");
		
		int userId = getUserPK(request);
		ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
		
		if(articlelike == null) {
			System.out.println(articleId + "번 글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		
		System.out.println(articleId + "번 글에 내가 이미 좋아요 누름!!");
		if(articlelikeService.deleteArticleLikeByUser(userId, articleId)) {
			return new ResponseEntity<>("success", HttpStatus.OK);				
			
		}
		
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글 댓글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/qna/{articleId}/{commentId}/like")
	public Object studyQnaCommentLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("qna게시판 글 댓글에 좋아요 누르기");
		
		int userId = getUserPK(request);
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike != null) {
			System.out.println(commentId + "번 댓글에 내가 이미 좋아요 누름!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		
		commentlike = new CommentLike();
		commentlike.setComment_id(commentId);
		commentlike.setUser_id(userId);
		if(commentlikeService.create(commentlike)) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글 댓글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/qna/{articleId}/{commentId}/unlike")
	public Object studyQnaCommentUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("qna게시판 글 댓글에 눌렀던 좋아요 취소");
		
		int userId = getUserPK(request);
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike == null) {
			System.out.println(commentId + "번 댓글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		
		System.out.println(commentId + "번 댓글에 내가 이미 좋아요 누름!!");
		if(commentlikeService.deleteByUser(userId, commentId)) {
			return new ResponseEntity<>("success", HttpStatus.OK);				
			
		}
		
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글 댓글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/repository/{articleId}/{commentId}/like")
	public Object studyRepoCommentLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("자료실게시판 글 댓글에 좋아요 누르기");
		
		int userId = getUserPK(request);
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike != null) {
			System.out.println(commentId + "번 댓글에 내가 이미 좋아요 누름!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		
		commentlike = new CommentLike();
		commentlike.setComment_id(commentId);
		commentlike.setUser_id(userId);
		if(commentlikeService.create(commentlike)) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		}
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글 댓글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/repository/{articleId}/{commentId}/unlike")
	public Object studyRepoCommentUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("자료실게시판 글 댓글에 눌렀던 좋아요 취소");
		
		int userId = getUserPK(request);
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike == null) {
			System.out.println(commentId + "번 댓글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		
		System.out.println(commentId + "번 댓글에 내가 이미 좋아요 누름!!");
		if(commentlikeService.deleteByUser(userId, commentId)) {
			return new ResponseEntity<>("success", HttpStatus.OK);				
			
		}
		
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "글에 즐겨찾기 누른다.")
	@PostMapping("/study/{studyId}/fav")
	public Object articleFav(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
			System.out.println("글에 즐겨찾기 누르기");
			
			int userId = getUserPK(request);
			ArticleFav articlefav = articlefavService.searchByUser_Article(userId, articleId);
			
			if(articlefav != null) {
				System.out.println(articleId + "번 글에 내가 이미 즐겨찾기 누름!!");
				return new ResponseEntity<>("fail", HttpStatus.OK);
			}
			
			articlefav = new ArticleFav();
			articlefav.setArticle_id(articleId);
			articlefav.setUser_id(userId);
			if(articlefavService.create(articlefav)) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			}
			return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "글에 눌렀던 즐겨찾기를 취소한다")
	@DeleteMapping("/study/{studyId}/unfav")
	public Object articleUnFav(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		System.out.println("글에 눌렀던 즐겨찾기를 취소");
		
		int userId = getUserPK(request);
		ArticleFav articlefav = articlefavService.searchByUser_Article(userId, articleId);
		
		if(articlefav == null) {
			System.out.println(articleId + "번 글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>("fail", HttpStatus.OK);
		}
		
		System.out.println(articleId + "번 글에 내가 이미 좋아요 누름!!");
		if(articlefavService.deleteByUser(userId, articleId)) {
			return new ResponseEntity<>("success", HttpStatus.OK);				
			
		}
		
		return new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
	
}
