package com.example.demo.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.domain.Users;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(Users user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("auth-api")
					.withSubject(user.getUsername())
					.withExpiresAt(genExpirationDate())
					.sign(algorithm);
			return token;
		}catch (JWTCreationException exception){
			throw new RuntimeException("Erro na geração de token",exception);
		}
	}
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			return " ";
		}
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-04:00"));
	}
}
