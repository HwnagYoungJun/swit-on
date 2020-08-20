package com.ssafy.switon.Interceptors;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ssafy.switon.dto.ReturnMsg;
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.service.UserService;
import com.ssafy.switon.util.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.swagger.models.HttpMethod;

@Component
public class GetRequestInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(!request.getMethod().equals(HttpMethod.GET.name())) {
			System.out.println("(GET이 아닌 Method) AuthInterceptor 통과...");
			return super.preHandle(request, response, handler);
		}
		
		System.out.println("--- AuthInterceptor (GET) 시작 ------------");
		if(!request.getMethod().equals("OPTIONS")) {
			Jws<Claims> jws;
			try {
//				String jwsString = request.getHeader("token");
				String jwsString = request.getHeader("Authentication").substring("Bearer ".length());
				System.out.println("토큰: " + jwsString);
//				System.out.println(jwtUtil.getUserPK(jwsString));
				if (jwsString == null) {
					setResponseMsg(response, "로그인이 필요합니다.");
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
				
				if(user != null && user.getName() != null && !user.getName().equals("")) {
					System.out.println(name + " 유저 토큰 확인 완료");
					System.out.println("----------------------------------");
				} else {
					System.out.println("** 미등록 유저 토큰");
					setResponseMsg(response, "로그인이 필요합니다.");
					return false;
				}
			} catch (NullPointerException ne) {
				System.out.println("** 토큰이 없음");
				setResponseMsg(response, "로그인이 필요합니다.");
				return false;
			} catch (JwtException je) {
				System.out.println("** 유효하지 않은 토큰");
				System.out.println(je.getMessage());
				setResponseMsg(response, "로그인이 필요합니다.");
				return false;
			} catch (Exception e) {
				System.out.println("** 토큰을 받아올 수 없었습니다.");
				setResponseMsg(response, "로그인이 필요합니다.");
				System.out.println(e.getMessage());
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}

	private void setResponseMsg(HttpServletResponse response, String msg) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write((new ReturnMsg(msg).toString()));
		response.setStatus(401);
	}
}
