package com.ssafy.switon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleFav;
import com.ssafy.switon.dto.ArticleLike;
import com.ssafy.switon.dto.Comment;
import com.ssafy.switon.dto.CommentLike;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.StudyLike;
import com.ssafy.switon.service.ArticleFavService;
import com.ssafy.switon.service.ArticleLikeService;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.CommentLikeService;
import com.ssafy.switon.service.CommentService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.StudyLikeService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.ApiOperation;

@RestController
public class LikeRestController {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	StudyLikeService studylikeService;
	
	@Autowired
	ArticleLikeService articlelikeService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	CommentLikeService commentlikeService;
	
	@Autowired
	ArticleFavService articlefavService;
	
	@Autowired
	JoinService joinService;
	
	@ApiOperation(value = "소모임에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/like")
	public Object studyLike(@PathVariable("studyId") int studyId, HttpServletRequest request) {
			System.out.println("소모임 좋아요 누르기");
			int userId = 0;
			try {
				userId = getUserPK(request);
			} catch(Exception e) {
				return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
			}
			if(!joinService.isMember(studyId, userId)) {
				return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
			}
			StudyLike studylike = studylikeService.searchByUser_Study(userId, studyId);
			
			if(studylike != null) {
				System.out.println(studyId + "에 내가 이미 좋아요 누름!!");
				return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 눌렀습니다."), HttpStatus.UNAUTHORIZED);
			}
			try {
				studylike = new StudyLike();
				studylike.setStudy_id(studyId);
				studylike.setUser_id(userId);
				if(studylikeService.createStudyLike(studylike)) {
					return new ResponseEntity<>("success", HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			
	}
	
	@ApiOperation(value = "소모임에 눌럿던 좋아요를 취소한다.")
	@DeleteMapping("/study/{studyId}/like")
	public Object studyUnLike(@PathVariable("studyId") int studyId, HttpServletRequest request) {
		System.out.println("소모임 좋아요 취소");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		StudyLike studylike = studylikeService.searchByUser_Study(userId, studyId);
		
		if(studylike == null) {
			System.out.println(studyId + "에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 취소했습니다."), HttpStatus.UNAUTHORIZED);
		}
		try {
			if(studylikeService.deleteStudyLikeByUser(userId, studyId)) {
				return new ResponseEntity<>("success", HttpStatus.OK);								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 취소 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/qna/{articleId}/like")
	public Object studyQnaLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
			System.out.println("qna게시판 글에 좋아요 누르기");
			int userId = 0;
			try {
				userId = getUserPK(request);
			} catch(Exception e) {
				return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
			}
			if(!joinService.isMember(studyId, userId)) {
				return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
			}
			ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
			
			if(articlelike != null) {
				System.out.println(articleId + "번 글에 내가 이미 좋아요 누름!!");
				return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 눌렀습니다."), HttpStatus.UNAUTHORIZED);
			}
			try {
				articlelike = new ArticleLike();
				articlelike.setArticle_id(articleId);
				articlelike.setUser_id(userId);
				if(articlelikeService.createArticleLike(articlelike)) {
					return new ResponseEntity<>("success", HttpStatus.OK);
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/qna/{articleId}/like")
	public Object studyQnaUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		System.out.println("qna게시판 글에 눌렀던 좋아요 취소");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
		
		if(articlelike == null) {
			System.out.println(articleId + "번 글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 취소했습니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			if(articlelikeService.deleteArticleLikeByUser(userId, articleId)) {
				return new ResponseEntity<>("success", HttpStatus.OK);			
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 취소 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/repository/{articleId}/like")
	public Object studyRepoLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
			System.out.println("자료실게시판 글에 좋아요 누르기");
			int userId = 0;
			try {
				userId = getUserPK(request);
			} catch(Exception e) {
				return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
			}
			if(!joinService.isMember(studyId, userId)) {
				return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
			}
			ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
			if(articlelike != null) {
				System.out.println(articleId + "번 글에 내가 이미 좋아요 누름!!");
				return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 눌렀습니다."), HttpStatus.UNAUTHORIZED);
			}
			try {
				articlelike = new ArticleLike();
				articlelike.setArticle_id(articleId);
				articlelike.setUser_id(userId);
				if(articlelikeService.createArticleLike(articlelike)) {
					Article article = articleService.search(articleId);
					// 글쓴이에게 2점 부여
					joinService.givePoint(article.getUser_id(), studyId, 2);
					return new ResponseEntity<>("success", HttpStatus.OK);
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/repository/{articleId}/like")
	public Object studyRepoUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		System.out.println("자료실게시판 글에 눌렀던 좋아요 취소");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		ArticleLike articlelike = articlelikeService.searchByUser_Article(userId, articleId);
		
		if(articlelike == null) {
			System.out.println(articleId + "번 글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 취소했습니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			if(articlelikeService.deleteArticleLikeByUser(userId, articleId)) {
				return new ResponseEntity<>("success", HttpStatus.OK);								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 취소 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글 댓글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/qna/{articleId}/{commentId}/like")
	public Object studyQnaCommentLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("qna게시판 글 댓글에 좋아요 누르기");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		Comment comment = commentService.search(commentId);
		if(comment == null) {
			return new ResponseEntity<>(new ReturnMsg("댓글이 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
		}
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike != null) {
			System.out.println(commentId + "번 댓글에 내가 이미 좋아요 누름!!");
			return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 눌렀습니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			commentlike = new CommentLike();
			commentlike.setComment_id(commentId);
			commentlike.setUser_id(userId);
			if(commentlikeService.create(commentlike)) {
				// qna 답글쓴 사람에게 2점 부여
				joinService.givePoint(comment.getUser_id(), studyId, 2);
				System.out.println(comment.getUser_id() + " " + studyId + " " + 2);
				return new ResponseEntity<>("success", HttpStatus.OK);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna게시판 글 댓글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/qna/{articleId}/{commentId}/like")
	public Object studyQnaCommentUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("qna게시판 글 댓글에 눌렀던 좋아요 취소");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(!joinService.isMember(studyId, userId)) {
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
		}
		Comment comment = commentService.search(commentId);
		if(comment == null) {
			return new ResponseEntity<>(new ReturnMsg("댓글이 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
		}
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike == null) {
			System.out.println(commentId + "번 댓글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 취소했습니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			if(commentlikeService.deleteByUser(userId, commentId)) {
				return new ResponseEntity<>("success", HttpStatus.OK);								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 취소 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글 댓글에 좋아요 누른다.")
	@PostMapping("/study/{studyId}/repository/{articleId}/{commentId}/like")
	public Object studyRepoCommentLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("자료실게시판 글 댓글에 좋아요 누르기");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment comment = commentService.search(commentId);
		if(comment == null) {
			return new ResponseEntity<>(new ReturnMsg("댓글이 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
		}
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike != null) {
			System.out.println(commentId + "번 댓글에 내가 이미 좋아요 누름!!");
			return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 눌렀습니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			commentlike = new CommentLike();
			commentlike.setComment_id(commentId);
			commentlike.setUser_id(userId);
			if(commentlikeService.create(commentlike)) {
				return new ResponseEntity<>("success", HttpStatus.OK);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실게시판 글 댓글에 눌렀던 좋아요를 취소한다")
	@DeleteMapping("/study/{studyId}/repository/{articleId}/{commentId}/like")
	public Object studyRepoCommentUnLike(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, @PathVariable("commentId") int commentId, HttpServletRequest request) {
		System.out.println("자료실게시판 글 댓글에 눌렀던 좋아요 취소");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		Comment comment = commentService.search(commentId);
		if(comment == null) {
			return new ResponseEntity<>(new ReturnMsg("댓글이 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
		}
		CommentLike commentlike = commentlikeService.searchByUser_Comment(userId, commentId);
		
		if(commentlike == null) {
			System.out.println(commentId + "번 댓글에 좋아요를 누르지 않았음!!");
			return new ResponseEntity<>(new ReturnMsg("이미 좋아요를 취소했습니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			if(commentlikeService.deleteByUser(userId, commentId)) {
				return new ResponseEntity<>("success", HttpStatus.OK);								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 취소 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna 글에 즐겨찾기 누른다.")
	@PostMapping("/study/{studyId}/qna/{articleId}/fav")
	public Object articleQnAFav(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
			System.out.println("글에 즐겨찾기 누르기");
			int userId = 0;
			try {
				userId = getUserPK(request);
			} catch(Exception e) {
				return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
			}
			ArticleFav articlefav = articlefavService.searchByUser_Article(userId, articleId);
			
			if(articlefav != null) {
				System.out.println(articleId + "번 글에 내가 이미 즐겨찾기 누름!!");
				return new ResponseEntity<>(new ReturnMsg("이미 즐겨찾기한 글입니다."), HttpStatus.OK);
			}
			
			try {
				articlefav = new ArticleFav();
				articlefav.setArticle_id(articleId);
				articlefav.setUser_id(userId);
				if(articlefavService.create(articlefav)) {
					return new ResponseEntity<>("success", HttpStatus.OK);
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 글에 즐겨찾기 누른다.")
	@PostMapping("/study/{studyId}/repository/{articleId}/fav")
	public Object articleRepoFav(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
			System.out.println("글에 즐겨찾기 누르기");
			int userId = 0;
			try {
				userId = getUserPK(request);
			} catch(Exception e) {
				return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
			}
			ArticleFav articlefav = articlefavService.searchByUser_Article(userId, articleId);
			
			if(articlefav != null) {
				System.out.println(articleId + "번 글에 내가 이미 즐겨찾기 누름!!");
				return new ResponseEntity<>(new ReturnMsg("이미 즐겨찾기한 글입니다."), HttpStatus.UNAUTHORIZED);
			}
			try {
				articlefav = new ArticleFav();
				articlefav.setArticle_id(articleId);
				articlefav.setUser_id(userId);
				if(articlefavService.create(articlefav)) {
					return new ResponseEntity<>("success", HttpStatus.OK);
				}				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "qna 글에 눌렀던 즐겨찾기를 취소한다")
	@DeleteMapping("/study/{studyId}/qna/{articleId}/fav")
	public Object articleQnAUnFav(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		System.out.println("글에 눌렀던 즐겨찾기를 취소");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		ArticleFav articlefav = articlefavService.searchByUser_Article(userId, articleId);
		
		if(articlefav == null) {
			System.out.println(articleId + "번 글에 즐겨찾기를 누르지 않았음!!");
			return new ResponseEntity<>(new ReturnMsg("이미 즐겨찾기를 해제한 글입니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			if(articlefavService.deleteByUser(userId, articleId)) {
				return new ResponseEntity<>("success", HttpStatus.OK);								
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 취소 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "자료실 글에 눌렀던 즐겨찾기를 취소한다")
	@DeleteMapping("/study/{studyId}/repository/{articleId}/fav")
	public Object articleRepoUnFav(@PathVariable("studyId") int studyId, @PathVariable("articleId") int articleId, HttpServletRequest request) {
		System.out.println("글에 눌렀던 즐겨찾기를 취소");
		int userId = 0;
		try {
			userId = getUserPK(request);
		} catch(Exception e) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		ArticleFav articlefav = articlefavService.searchByUser_Article(userId, articleId);
		
		if(articlefav == null) {
			System.out.println(articleId + "번 글에 즐겨찾기를 누르지 않았음!!");
			return new ResponseEntity<>(new ReturnMsg("이미 즐겨찾기를 해제한 글입니다."), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			if(articlefavService.deleteByUser(userId, articleId)) {
				return new ResponseEntity<>("success", HttpStatus.OK);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("좋아요 누르기 도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
	
}
