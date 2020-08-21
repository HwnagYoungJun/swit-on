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
			return super.preHandle(request, response, handler);
		}
		if(!request.getMethod().equals("OPTIONS")) {
			Jws<Claims> jws;
			try {
				String jwsString = request.getHeader("Authentication").substring("Bearer ".length());
				if (jwsString == null) {
					setResponseMsg(response, "로그인이 필요합니다.");
					return false;
				}
				jws = Jwts.parserBuilder()
						.setSigningKey(jwtUtil.getKey())
						.build()
						.parseClaimsJws(jwsString);
				Claims claims = jws.getBody();
				
				int id = (Integer) claims.get("id");
				String name = (String) claims.get("name");
				String email = (String) claims.get("email");
				
				UserInfoDTO user = userService.search(id);
				
				if(user != null && user.getName() != null && !user.getName().equals("")) {
				} else {
					setResponseMsg(response, "로그인이 필요합니다.");
					return false;
				}
			} catch (NullPointerException ne) {
				setResponseMsg(response, "로그인이 필요합니다.");
				return false;
			} catch (JwtException je) {
				setResponseMsg(response, "로그인이 필요합니다.");
				return false;
			} catch (Exception e) {
				setResponseMsg(response, "로그인이 필요합니다.");
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
