package com.e.commerce.global.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CorsFilter implements Filter {
	private static final String CLIENT_URL = "http://localhost:3000";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
		IOException,
		ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		// 요청을 허용하는 출처
		res.setHeader("Access-Control-Allow-Origin", CLIENT_URL);
		// 요청을 허용하는 HTTP 메소드
		res.setHeader("Access-Control-Allow-Methods", "*");
		// 쿠키 요청 허용
		res.setHeader("Access-Control-Allow-Credentials", "true");
		// 요청을 허용하는 헤더 이름
		res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization");
		// 클라이언트에서 preflight 요청 결과를 저장할 시간
		res.setHeader("Access-Control-Max-Age", "3600");

		if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}
}
