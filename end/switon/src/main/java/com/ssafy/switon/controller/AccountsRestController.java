package com.ssafy.switon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(value="AccountsRestController", description="회원가입, 로그인, 회원정보 조회 등")
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
			UserInfoDTO uservo = userService.login(loginDTO);
			if(uservo != null) {
				System.out.println("로그인 성공!");
				System.out.println(uservo);
				
				User user = new User(uservo.getEmail(), uservo.getName());
				String token = jwtUtil.createToken(uservo);
				UserReturnDTO returnDTO = new UserReturnDTO(user, token);
				System.out.println(returnDTO);
				
				return new ResponseEntity<>(returnDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			System.out.println("** 로그인 실패!!");
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@ApiOperation(value = "(테스트용) 헤더의 토큰을 읽어서 해당하는 유저 정보를 반환한다.", response = UserInfoDTO.class)
	@GetMapping("/info")
	public Object info(HttpServletRequest request) {
		String userToken = request.getHeader("Token");
		System.out.println("유저 정보 반환");
		UserInfoDTO user = userService.search(jwtUtil.getUserPK(userToken));
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 가입을 한다. 가입 성공시 로그인을 자동으로 수행하여 토큰을 반환한다.", response = UserReturnDTO.class)
	@PostMapping("/register")
	public Object register(@RequestBody UserRegisterDTO registerDTO) {
		System.out.println("회원가입 시작");
		// 회원가입 성공시 바로 로그인하기 위한 로그인 DTO
		UserLoginDTO loginDTO = new UserLoginDTO(registerDTO.getEmail(), registerDTO.getPassword());
		System.out.println(registerDTO);
		try {
			if(userService.register(registerDTO)) {
				System.out.println("회원가입 성공!!");
				UserInfoDTO uservo = userService.login(loginDTO);
				if(uservo != null) {
					System.out.println("로그인 성공");
					String token = jwtUtil.createToken(uservo);
					if(token != null) System.out.println("토큰 발행 성공");
					UserReturnDTO dto = new UserReturnDTO(new User(uservo.getEmail(), uservo.getName()), token);
					return new ResponseEntity<>(dto, HttpStatus.OK);
				} else {
					System.out.println("** 로그인 실패");
					return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
				}
			} else {
				System.out.println("** 회원가입 실패");
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			System.out.println("** 서버 에러!!");
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
