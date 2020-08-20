package com.ssafy.switon.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ssafy.switon.dto.Article;
import com.ssafy.switon.dto.ArticleFavReturnDTO;
import com.ssafy.switon.dto.ArticleWithStudyDTO;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.Schedule;
import com.ssafy.switon.dto.Study;
import com.ssafy.switon.dto.StudyProfileDTO;
import com.ssafy.switon.dto.StudyWithRate;
import com.ssafy.switon.dto.UserDTO;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserInfoWithMedals;
import com.ssafy.switon.dto.UserScheduleReturnDTO;
import com.ssafy.switon.dto.UserScheduleSimpleDTO;
import com.ssafy.switon.dto.UserStudyInfoDTO;
import com.ssafy.switon.service.ArticleFavService;
import com.ssafy.switon.service.ArticleService;
import com.ssafy.switon.service.JoinService;
import com.ssafy.switon.service.ScheduleService;
import com.ssafy.switon.service.StudyService;
import com.ssafy.switon.service.UserScheduleService;
import com.ssafy.switon.service.UserService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accounts")
@Api(value="AccountsRestController", description="유저 프로필 관련")
public class ProfileRestController {
	
	@Autowired
	StudyService studyService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	JoinService joinService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	ArticleFavService articleFavService;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	UserScheduleService userScheduleService;
	
	@ApiOperation(value = "유저 정보를 반환한다.", response = UserInfoDTO.class)
	@GetMapping("{name}")
	public Object userInfo(@PathVariable("name") String name) {
		System.out.println("유저 정보 반환");
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		try {
			UserInfoDTO originalUser = userService.search(userId);
			UserInfoWithMedals user = userService.addMedalsToUserInfo(originalUser);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("유저 정보를 받아올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "유저 정보를 수정한다.")
	@PutMapping("{username}")
	public Object modifyUser(@RequestParam(value = "img", required = false) MultipartFile img, 
							@PathVariable("username") String name, UserInfoDTO userInfoDTO, HttpServletRequest request) {
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		int userIdFromToken;
		try {
			userIdFromToken = getUserPK(request);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(userIdFromToken != userId) {
			System.out.println("** 유저 정보 수정 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("유저 정보 수정에 실패했습니다."), HttpStatus.NOT_FOUND);			
		}
		
		if(img != null) {
			String RealPath = getUploadRealPath(request, userIdFromToken, img);
			String path = getUploadPath(userIdFromToken, img);
			try {
				img.transferTo(new File(RealPath));
				userInfoDTO.setProfile_image(path);
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("파일 업로드 중 에러가 발생했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		
		UserInfoDTO originalUserDTO = userService.search(userIdFromToken);
		if(originalUserDTO == null) {
			return new ResponseEntity<>(new ReturnMsg("유저 정보 수정에 실패했습니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(img == null && userInfoDTO.getProfile_image() != null) {
			userInfoDTO.setProfile_image(originalUserDTO.getProfile_image());
		}
		userInfoDTO.setId(userIdFromToken);
		userInfoDTO.setEmail(originalUserDTO.getEmail());
		if(userInfoDTO.getName() != null) {
			String userName = userInfoDTO.getName();
			if(userName.contains(" ") || userName.contains("#") || userName.contains("&") || userName.contains("/")
					|| userName.contains("\"") || userName.contains("\\") || userName.contains("^")
					|| userName.contains("*") || userName.contains("!") || userName.contains("'")
					|| userName.contains("(") || userName.contains(")") || userName.contains("=")
					|| userName.contains("-") || userName.contains("+") || userName.contains("@")
					|| userName.contains("$") || userName.contains("%") || userName.contains("`")
					|| userName.contains("~") || userName.contains("|") || userName.contains("?")) {
				return new ResponseEntity<>(new ReturnMsg("이름에는 공백이나 특수문자를 입력하실 수 없습니다."), HttpStatus.BAD_REQUEST);
			}
			if(userName.length() > "닉네임입니다".length()) {
				return new ResponseEntity<>(new ReturnMsg("이름이 너무 깁니다. 다른 이름을 입력해주세요."), HttpStatus.BAD_REQUEST);
			}
			if(userService.nameAlreadyExist(userInfoDTO.getId(), userName)){
				return new ResponseEntity<>(new ReturnMsg("다른 사용자가 사용하고 있는 이름입니다. 다른 이름을 입력해주세요."), HttpStatus.BAD_REQUEST);
			};
		}
		if(userInfoDTO.getName() == null) {
			userInfoDTO.setName(originalUserDTO.getName());
		}
		if(userInfoDTO.getIntroduce() == null) {
			userInfoDTO.setIntroduce(originalUserDTO.getIntroduce());
		}
		if(img == null && userInfoDTO.getProfile_image() == null) {
			userInfoDTO.setProfile_image(null);
		}
		if(userInfoDTO.getName_legacy() == null) {
			if(originalUserDTO.getName_legacy() != null) {
				userInfoDTO.setName_legacy(originalUserDTO.getName_legacy());				
			}
		}
		try {
			if(userService.modify(userInfoDTO)) {
				System.out.println("유저 정보 수정");
				return new ResponseEntity<>(new ReturnMsg("유저 정보를 수정하였습니다."), HttpStatus.OK);			
			}			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("** 유저 정보 수정 실패 - 서버 에러");			
		}
		return new ResponseEntity<>(new ReturnMsg("서버 에러로 유저 정보 수정에 실패했습니다. 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);			
	}
	
	@ApiOperation(value = "유저가 가입한 스터디 목록을 반환한다.")
	@GetMapping("{name}/mystudy")
	public Object myStudyList(@PathVariable("name") String name) {
		if(name == null) {
			System.out.println("스터디 목록 반환 실패 - 유저 닉네임에 널값");
			return new ResponseEntity<>(new ReturnMsg("유저 닉네임을 받아올 수 없었습니다."), HttpStatus.BAD_REQUEST);
		}
		Integer userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		try {
			List<UserStudyInfoDTO> dtos = studyService.searchUserStudies(userId);
			System.out.println("유저 스터디 리스트 반환");			
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("유저 정보를 가져올 수 없었습니다."), HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "유저가 작성한 QnA 글 목록을 반환한다. ")
	@GetMapping("{name}/myqna")
	public Object myQnaList(@PathVariable("name") String name, HttpServletRequest request) {
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
//		int userIdFromToken;
//		try {
//			userIdFromToken = getUserPK(request);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
//		}
//		if(userId != 0 && userId != userIdFromToken) {
//			System.out.println("유저 QnA글 리스트 반환 실패 - 권한 없음");
//			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
//		}
		try {
			List<ArticleWithStudyDTO> qnas = articleService.searchUserQnAs(userId);
			System.out.println("유저 QnA글 리스트 반환");
			return new ResponseEntity<>(qnas, HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("글을 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "유저가 작성한 자료실 글 목록을 반환한다. ")
	@GetMapping("/{name}/myrepository")
	public Object myRepositoryList(@PathVariable("name") String name, HttpServletRequest request) {
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
//		int userIdFromToken;
//		try {
//			userIdFromToken = getUserPK(request);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
//		}
//		if(userId != userIdFromToken) {
//			System.out.println("유저 자료실 글 리스트 반환 실패 - 권한 없음");
//			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
//		}
		try {
			List<ArticleWithStudyDTO> repos = articleService.searchUserRepositories(userId);
			System.out.println("유저 자료실 글 리스트 반환");
			return new ResponseEntity<>(repos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ReturnMsg("글을 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@ApiOperation(value = "회원탈퇴를 한다.", response = UserInfoDTO.class)
	@DeleteMapping("/{name}")
	public Object userDelete(@PathVariable("name") String name, HttpServletRequest request) {
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		int userIdFromToken;
		try {
			userIdFromToken = getUserPK(request);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
		}
		if(userIdFromToken != userId) {
			System.out.println("** 회원 탈퇴 실패 - 권한 없음");
			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.FORBIDDEN);
		}
		try {
			if(userService.signOut(userIdFromToken)) {
				System.out.println("유저 탈퇴 성공!");
				return new ResponseEntity<>(new ReturnMsg("정상적으로 탈퇴되었습니다. 이용해주셔서 감사합니다."), HttpStatus.OK);
			}			
		} catch(Exception e) {
			System.out.println("** 회원 탈퇴 실패 - 서버 문제");
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("탈퇴 중 문제가 발생했습니다. 시스템 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ApiOperation(value = "유저가 참여한 스케쥴들만 모아서 반환", response = List.class)
	@GetMapping("/{name}/myschedule")
	public Object userSchedule(@PathVariable("name") String name, HttpServletRequest request) {
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
//		int userIdFromToken;
//		try {
//			userIdFromToken = getUserPK(request);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
//		}
//		if(userIdFromToken != userId) {
//			System.out.println("** 스케줄 조회 실패 - 권한이 없습니다.");
//			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
//		}
		try {
			List<UserScheduleReturnDTO> schedules = userScheduleService.getUserSchedules(userId);
			return new ResponseEntity<>(schedules, HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("스케줄 정보를 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@ApiOperation(value = "유저가 즐겨찾기한 글 목록 반환", response = List.class)
//	@GetMapping("/{name}/fav")
//	public Object userFavorite(@PathVariable("name") String name, HttpServletRequest request) {
//		int userId = userService.searchUserIdByName(name);
//		if(userId == 0) {
//			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.OK);
//		}
//		int userIdFromToken = getUserPK(request);
//		if(userIdFromToken != userId) {
//			System.out.println("** 즐겨찾기 조회 실패 - 권한이 없습니다.");
//			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
//		}
//		List<ArticleFavReturnDTO> list = articleFavService.searchFavList(userId);
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}
	
	@ApiOperation(value = "유저가 즐겨찾기한 글 목록 반환", response = List.class)
	@GetMapping("/{name}/fav")
	public Object userFavorite(@PathVariable("name") String name, HttpServletRequest request) {
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
//		int userIdFromToken;
//		try {
//			userIdFromToken = getUserPK(request);
//		} catch(Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(new ReturnMsg("잘못된 접근입니다. 다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
//		}
//		if(userIdFromToken != userId) {
//			System.out.println("** 즐겨찾기 조회 실패 - 권한이 없습니다.");
//			return new ResponseEntity<>(new ReturnMsg("권한이 없습니다."), HttpStatus.UNAUTHORIZED);
//		}
		try {
			List<ArticleWithStudyDTO> list = articleFavService.searchFavArticles(userId);
			return new ResponseEntity<>(list, HttpStatus.OK);			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("즐겨찾기 정보를 불러올 수 없었습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "유저아이디로 종료된 스터디를 조회한다", response = List.class)
	@GetMapping("/{name}/endstudy")
	public Object searchEndStudyByUserId(@PathVariable("name") String name, HttpServletRequest request){
		int userId = userService.searchUserIdByName(name);
		if(userId == 0) {
			return new ResponseEntity<>(new ReturnMsg("유저가 존재하지 않습니다."), HttpStatus.NOT_FOUND);
		}
		System.out.println("유저아이디로 종료된 스터디 조회");
		try {
			List<StudyWithRate> list1 = studyService.searchEndStudyByUserId(userId);
			List<StudyWithRate> list2 = studyService.searchNotEndStudyByUserId(userId);
			
			StudyProfileDTO list = new StudyProfileDTO(list1, list2);
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ReturnMsg("유저의 스터디를 불러오는데 실패했습니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private String getUploadRealPath(HttpServletRequest request, int userId, MultipartFile img) {
		String fileName = img.getOriginalFilename();
		int pos = fileName.lastIndexOf(".");
		String ext = fileName.substring(pos);
		return request.getServletContext().getRealPath("static"+ File.separator + "upload")
				+ File.separator + userId + "_" + System.currentTimeMillis() + ext;
	}
	
	private String getUploadPath(int userId, MultipartFile img) {
		String fileName = img.getOriginalFilename();
		int pos = fileName.lastIndexOf(".");
		String ext = fileName.substring(pos);
		return "upload/" + userId + "_" + System.currentTimeMillis() + ext;
	}
	
	// Token(Authentication)에서 유저 id 정보를 뽑아내는 메소드
	private int getUserPK(HttpServletRequest request) {
		return jwtUtil.getUserPK(request.getHeader("Authentication").substring("Bearer ".length()));
	}
}
