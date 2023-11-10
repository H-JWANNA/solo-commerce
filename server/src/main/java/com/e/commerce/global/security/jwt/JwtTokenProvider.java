package com.e.commerce.global.security.jwt;

import static com.e.commerce.global.exception.ExceptionCode.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.e.commerce.global.exception.BusinessLogicException;
import com.e.commerce.global.security.service.MemberDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
	private final RedisTemplate<String, String> redisTemplate;

	@Value("${spring.jwt.secret}")
	private String secretKey;

	@Value("${spring.jwt.token.access-expiration-time}")
	private int accessExpirationTime;

	@Value("${spring.jwt.token.refresh-expiration-time}")
	private int refreshExpirationTime;

	private final MemberDetailsService memberDetailsService;

	// access token 생성
	public String generateAccessToken(Authentication auth) {
		Claims claims = Jwts.claims().setSubject(auth.getName());
		Date now = new Date();
		Date expiration = new Date(now.getTime() + accessExpirationTime);
		Key key = getKeyFrom(encodeBase64From(secretKey));

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(now)
			.setExpiration(expiration)
			.signWith(key)
			.compact();
	}

	// refresh token 생성
	public String generateRefreshToken(Authentication auth) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + refreshExpirationTime);
		Key key = getKeyFrom(encodeBase64From(secretKey));

		String refreshToken = Jwts.builder()
			.setSubject(auth.getName())
			.setIssuedAt(now)
			.setExpiration(expiration)
			.signWith(key)
			.compact();

		redisTemplate.opsForValue().set(
			auth.getName(),			// key
			refreshToken,			// value
			refreshExpirationTime,	// time
			TimeUnit.MILLISECONDS	// unit
		);

		return refreshToken;
	}

	// 토큰을 복호화해서 Claim 생성, Subject 가져오기
	public String getSub(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(getKeyFrom(encodeBase64From(secretKey)))
			.build()
			.parseClaimsJws(token)
			.getBody().getSubject();
	}

	// 이를 통해 Authentication 객체 반환
	public Authentication getAuth(String token) {
		String username = getSub(token);

		UserDetails userDetails = memberDetailsService.loadUserByUsername(username);

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	// HTTP 헤더로부터 bearer 토큰을 가져옴
	public String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}

		return null;
	}

	// access token 검증
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(getKeyFrom(encodeBase64From(secretKey)))
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			log.error(INCORRECT_SIGNATURE.getMessage());
			throw new BusinessLogicException(INCORRECT_SIGNATURE);
		} catch (ExpiredJwtException e) {
			log.error(EXPIRED_JWT.getMessage());
			throw new BusinessLogicException(EXPIRED_JWT);
		} catch (JwtException e) {
			log.error(INVALID_JWT.getMessage());
			throw new BusinessLogicException(INVALID_JWT);
		}
	}

	// Plain Text 형태인 Secret Key => Base64 문자열로 인코딩
	public String encodeBase64From(String secretKey) {
		return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
	}

	// JWT 서명에 사용할 Secret Key 생성
	private Key getKeyFrom(String base64EncodedSecretKey) {
		// Base64 형식으로 인코딩 된 Secret Key를 디코딩 한 후, byte[]로 변환
		byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);

		// byte[]를 기반으로 가장 적절한 HMAC 알고리즘을 적용한 Key 객체를 생성
		Key key = Keys.hmacShaKeyFor(keyBytes);

		return key;
	}
}
