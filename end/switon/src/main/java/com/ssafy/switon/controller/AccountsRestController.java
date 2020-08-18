package com.ssafy.switon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.switon.dto.PwFindDTO;
import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.Token;
import com.ssafy.switon.dto.User;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.dto.UserLoginDTO;
import com.ssafy.switon.dto.UserRegisterDTO;
import com.ssafy.switon.dto.UserReturnDTO;
import com.ssafy.switon.service.UserService;
import com.ssafy.switon.util.JWTUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/accounts")
@Api(value="AccountsRestController", description="회원가입, 로그인")
public class AccountsRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@ApiOperation(value = "로그인 정보가 일치하면 토큰을 발행한다.", response = UserReturnDTO.class)
	@PostMapping("/login")
	public Object login(@RequestBody UserLoginDTO loginDTO) {
		System.out.println("로그인 시도");
		System.out.println(loginDTO);
		try {
			if(!userService.emailAlreadyExists(loginDTO.getEmail())){
				return new ResponseEntity<>(new ReturnMsg("등록되지 않은 유저입니다. 회원가입을 해주세요."), HttpStatus.UNAUTHORIZED);
			}
			UserInfoDTO uservo = userService.login(loginDTO);
			if(uservo != null) {
				System.out.println("로그인 성공!");
				System.out.println(uservo);
				
				User user = new User(userService.findUserIdByEmail(uservo.getEmail()),
						uservo.getEmail(), uservo.getName());
				String token = jwtUtil.createToken(uservo);
				UserReturnDTO returnDTO = new UserReturnDTO(user, token);
				System.out.println(returnDTO);
				
				return new ResponseEntity<>(returnDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ReturnMsg("비밀번호가 일치하지 않습니다. 다시 한번 확인해주세요."), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			System.out.println("** 로그인 실패!!");
		}
		return new ResponseEntity<>(new ReturnMsg("입력하신 정보를 다시 한번                                          확확인해주세요."), HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "(테스트용) 헤더의 토큰을 읽어서 해당하는 유저 정보를 반환한다.", response = UserInfoDTO.class)
	@GetMapping("/")
	public Object info(HttpServletRequest request) {
		String userToken = request.getHeader("Authentication").substring("Bearer ".length());
		System.out.println("유저 정보 반환");
		UserInfoDTO user = userService.search(jwtUtil.getUserPK(userToken));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "이메일, 이름 중복 검사", response = boolean.class)
	@GetMapping("/check")
	public boolean checkDuplicate(@RequestParam(value="name", required = false) String name,
								@RequestParam(value="email", required = false) String email) {
		if(name != null) { // 이름에 뭔가 입력해서 날아올 경우...
			// true false만 반환해주기
			// if ~~~ return true; 이런 식
			if(userService.nameAlreadyExists(name)) {
				return false;
			} else {
				return true;
			}
		}
		if(email != null) { // 이메일에 뭔가 입력해서 날아올 경우...
			if(userService.emailAlreadyExists(email)) {
				return false;
			} else {
				return true;
			}
		}
		return false;
		
	}
	
	
	@ApiOperation(value = "회원 가입을 한다. 가입 성공시 로그인을 자동으로 수행하여 토큰을 반환한다.", response = UserReturnDTO.class)
	@PostMapping("/register")
	public Object register(@RequestBody UserRegisterDTO registerDTO) {
		System.out.println("회원가입 시작");
		// 회원가입 성공시 바로 로그인하기 위한 로그인 DTO
		UserLoginDTO loginDTO = new UserLoginDTO(registerDTO.getEmail(), registerDTO.getPassword());
		System.out.println(registerDTO);
		try {
			if(userService.nameAlreadyExists(registerDTO.getName())) {
				return new ResponseEntity<>(new ReturnMsg("중복된 이름입니다. 다른 이름을 입력해주세요."), HttpStatus.UNAUTHORIZED);
			}
			if(userService.emailAlreadyExists(registerDTO.getEmail())) {
				return new ResponseEntity<>(new ReturnMsg("중복된 이메일입니다. 다른 이메일을 입력해주세요."), HttpStatus.UNAUTHORIZED);
			}
			if(userService.register(registerDTO)) {
				System.out.println("회원가입 성공!!");
				UserInfoDTO uservo = userService.login(loginDTO);
				if(uservo != null) {
					System.out.println("로그인 성공");
					String token = jwtUtil.createToken(uservo);
					if(token != null) System.out.println("토큰 발행 성공");
					UserReturnDTO dto = new UserReturnDTO(new User(userService.findUserIdByEmail(uservo.getEmail())
																			,uservo.getEmail(), uservo.getName()), token);
					return new ResponseEntity<>(dto, HttpStatus.OK);
				} else {
					System.out.println("** 로그인 실패");
					return new ResponseEntity<>(new ReturnMsg("다시 로그인해주세요."), HttpStatus.UNAUTHORIZED);
				}
			} else {
				System.out.println("** 회원가입 실패");
				return new ResponseEntity<>(new ReturnMsg("회원가입에 실패했습니다. 입력한 정보를 확인해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			System.out.println("** 서버 에러!!");
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("회원가입에 실패했습니다. 시스템 관리자에게 문의해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "비밀번호 찾기를 시작하여 유저 이메일로 토큰을 발행한다.")
	@GetMapping("/pwfind")
	public Object giveTokenByEmail(@RequestParam(required = false) String email, @RequestParam(required = false) String token) {
		if(email != null) {
			// 존재하지 않는 메일인 경우 오류
			if(!userService.emailAlreadyExists(email)) {
				return new ResponseEntity<>(new ReturnMsg("입력하신 이메일로 가입한 계정이 존재하지 않습니다."), HttpStatus.UNAUTHORIZED);
			}
			String newToken = jwtUtil.createEmailToken(email);
			return newToken;
		}
		if(token != null) {
			try {
				String validEmail = jwtUtil.getFindPwEmail(token);
				User user = userService.findUserByEmail(validEmail);
				return user;
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ReturnMsg("변경 가능 시간이 만료되었습니다. 다시 비밀번호 찾기를 시도해주세요."), HttpStatus.UNAUTHORIZED);
			}
		}
		return new ResponseEntity<>(new ReturnMsg("잘못된 요청입니다."), HttpStatus.BAD_REQUEST);
	}
	
	@ApiOperation(value = "비밀번호 찾기를 시작하여 유저 이메일로 토큰을 발행한다.")
	@PostMapping("/pwfind")
	public Object pwChange(@RequestBody PwFindDTO pwFindDTO) {
		String password = pwFindDTO.getPassword();
		if(password == null) {
			return new ResponseEntity<>(new ReturnMsg("잘못된 요청입니다."), HttpStatus.BAD_REQUEST);
		}
		if(!password.equals(pwFindDTO.getPassword2())) {
			return new ResponseEntity<>(new ReturnMsg("비밀번호가 일치하지 않습니다."), HttpStatus.I_AM_A_TEAPOT);
		}
		try {
			String validEmail = jwtUtil.getFindPwEmail(pwFindDTO.getToken());
			int userId = userService.findUserIdByEmail(validEmail);
			if(userService.pwChange(userId, password)) {
				return new ResponseEntity<>(new ReturnMsg("비밀번호를 성공적으로 변경하였습니다."), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ReturnMsg("변경 가능 시간이 만료되었습니다. 다시 비밀번호 찾기를 시도해주세요."), HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(new ReturnMsg("비밀번호 변경하지 못했습니다. 서버 관리자에게 문의 바랍니다."), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
