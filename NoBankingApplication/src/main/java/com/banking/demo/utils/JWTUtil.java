package com.banking.demo.utils;


import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component //marking this class as a spring component
public class JWTUtil {
	
	private final String SECRET_KEY_STRING = "my_super_secure_key_12345678901234567890";
	
	//CONVERTING into secretKey object
	private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_KEY_STRING.getBytes());
	
	
	//generating token with the given user name
	public String generateToken(String username){
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
				.signWith(SECRET_KEY, SignatureAlgorithm.HS256)
				.compact();
	}
	
	//extract user_name from token 
	public String extractUsername(String token){
		
		return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
				.parseClaimsJws(token).getBody().getSubject();
		
	}
	
	
	//validating JWT TOEKN 
	public boolean isTokenValid(String token){
		
		try {
			Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			return true;
		}catch (JwtException e) {
			return false;
		}
		
	}

} 
