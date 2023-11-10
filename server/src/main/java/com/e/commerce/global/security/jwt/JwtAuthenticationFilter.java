package com.e.commerce.global.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.e.commerce.global.security.dto.LoginRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;

	@SneakyThrows
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		ObjectMapper om = new ObjectMapper();
		LoginRequestDto dto = om.readValue(request.getInputStream(), LoginRequestDto.class);

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
			dto.username(),
			dto.password());

		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		FilterChain chain, Authentication authResult) throws ServletException, IOException {

		String accessToken = jwtTokenProvider.generateAccessToken(authResult);
		String refreshToken = jwtTokenProvider.generateRefreshToken(authResult);

		response.setHeader("Authorization", "Bearer " + accessToken);
		response.setHeader("Refresh", refreshToken);

		this.getSuccessHandler().onAuthenticationSuccess(request, response, authResult);
	}
}
