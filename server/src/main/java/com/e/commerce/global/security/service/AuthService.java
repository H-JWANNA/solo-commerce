package com.e.commerce.global.security.service;

import static com.e.commerce.global.exception.ExceptionCode.*;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.e.commerce.global.exception.BusinessLogicException;
import com.e.commerce.global.security.jwt.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final RedisTemplate<String, String> redisTemplate;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public void reissue(HttpServletRequest request, HttpServletResponse response) {
		String refreshToken = request.getHeader("Refresh");
		String username = jwtTokenProvider.getSub(refreshToken);
		String redisRefreshToken = redisTemplate.opsForValue().get(username);

		// 토큰 검증
		if (!refreshToken.equals(redisRefreshToken)) {
			throw new BusinessLogicException(NOT_EXIST_REFRESH_TOKEN);
		}

		Authentication auth = jwtTokenProvider.getAuth(refreshToken);
		String newAccessToken = jwtTokenProvider.generateAccessToken(auth);

		// 토큰 재발행
		response.setHeader("Authorization", "Bearer " + newAccessToken);
	}

	@Transactional
	public void logout(HttpServletRequest request) {
		String accessToken = request.getHeader("Authorization");
		String username = jwtTokenProvider.getSub(accessToken);

		if(accessToken == null || !accessToken.startsWith("Bearer ")){
			throw new BusinessLogicException(INVALID_JWT);
		}

		// Delete Refresh Token
		if (redisTemplate.opsForValue().get(username) != null) {
			redisTemplate.delete(username);
		}
	}
}
