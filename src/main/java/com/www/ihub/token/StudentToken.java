package com.www.ihub.token;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class StudentToken {

	
	private final SecretKey TOKEN=Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	
	public String generateToken(String name) {
		
		return Jwts.builder()
				 .setSubject(name)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
	                .signWith(TOKEN, SignatureAlgorithm.HS256)
	                .compact();
	}
	
}
