package com.e.commerce.global.security.jwt;

import static com.e.commerce.global.exception.ExceptionCode.*;

import java.io.IOException;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.e.commerce.global.exception.BusinessLogicException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {

		String token = jwtTokenProvider.resolveToken(request);
		try {
			// 정상적인 토큰이면 SecurityContextHolder에 저장
			if (token != null && jwtTokenProvider.validateToken(token)) {
				Authentication auth = jwtTokenProvider.getAuth(token);
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (RedisConnectionFailureException e) {
			SecurityContextHolder.clearContext();
			throw new BusinessLogicException(REDIS_ERROR);
		} catch (Exception e) {
			throw new BusinessLogicException(INVALID_JWT);
		}

		filterChain.doFilter(request, response);
	}
}
