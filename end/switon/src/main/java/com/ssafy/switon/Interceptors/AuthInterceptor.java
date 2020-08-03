package com.ssafy.switon.Interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.service.UserService;
import com.ssafy.switon.util.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	UserService userService;
	
	
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("--- AuthInterceptor 시작 ------------");
		if(!request.getMethod().equals("OPTIONS")) {
			Jws<Claims> jws;
			try {
				String jwsString = request.getHeader("token");
//				String jwsString = request.getHeader("Authorization").substring("Bearer ".length());
				System.out.println("토큰: " + jwsString);
//				System.out.println(jwtUtil.getUserPK(jwsString));
				if (jwsString == null) {
					System.out.println("토큰이 없습니다.");
					return false;
				}
				jws = Jwts.parserBuilder()
						.setSigningKey(jwtUtil.getKey())
						.build()
						.parseClaimsJws(jwsString);
				System.out.println("token validated");
				Claims claims = jws.getBody();
				
				int id = (Integer) claims.get("id");
				String name = (String) claims.get("name");
				String email = (String) claims.get("email");
				
//				System.out.println(id + "," + name + "," + email);
				UserInfoDTO user = userService.search(id);
				
				if(user != null && user.getName() != null) {
					System.out.println(name + " 유저 토큰 확인 완료");
					System.out.println("----------------------------------");
				} else {
					System.out.println("미등록 유저 토큰");
					return false;
				}
			} catch (NullPointerException ne) {
				System.out.println("** 토큰이 없음");
				response.getWriter().print("request must have a token");
				return false;
			} catch (JwtException je) {
				System.out.println("** 유효하지 않은 토큰");
				System.out.println(je.getMessage());
				response.sendRedirect("http://127.0.0.1:80/");
				return false;
			} catch (Exception e) {
				System.out.println("** 토큰을 받아올 수 없었습니다.");
				System.out.println(e.getMessage());
			}
		}
		
		return super.preHandle(request, response, handler);
	}
	
	
	
	

}
