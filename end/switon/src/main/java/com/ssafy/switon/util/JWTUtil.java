package com.ssafy.switon.util;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

<<<<<<< HEAD
import com.ssafy.switon.dto.UserDTO;
=======
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
import com.ssafy.switon.dto.UserInfoDTO;
import com.ssafy.switon.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtil {
	
	private String secretString = "DksSud+SkDkRkvkEkrahsSkwl+sGhkAhtGo=DNpWlRka";
	private Key key;
<<<<<<< HEAD
	private UserService userService;
=======
>>>>>>> f75d2f70fe3641e3a587de80511421d44e46e6bf
	
	public String createToken(UserInfoDTO user) {
		Claims claims = Jwts.claims().setSubject(Integer.toString(user.getId()));
		String jws = Jwts.builder()
				.setClaims(claims)
				.claim("id", user.getId())
				.claim("name", user.getName())
				.claim("email", user.getEmail())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1시간
				.signWith(key)
				.compact();
		return jws;
	}
	
	public JWTUtil() {
		key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));
		System.out.println(Encoders.BASE64.encode(key.getEncoded()));
	}
	
	public Key getKey() {
		return this.key;
	}
	
//	public Authentication getAuthentication(String token) {
//		User user = userService.search(this.getUserPK(token));
//		return 
//	}
	
	public int getUserPK(String token) {
		return Integer.parseInt(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject());
	}

}
