package com.e.commerce.global.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.e.commerce.global.security.handler.CustomAuthenticationFailureHandler;
import com.e.commerce.global.security.handler.CustomAuthenticationSuccessHandler;
import com.e.commerce.global.security.jwt.JwtAccessDeniedHandler;
import com.e.commerce.global.security.jwt.JwtAuthenticationEntryPoint;
import com.e.commerce.global.security.jwt.JwtAuthenticationFilter;
import com.e.commerce.global.security.jwt.JwtFilter;
import com.e.commerce.global.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
	private final JwtTokenProvider jwtTokenProvider;

	// 정적 자원에 대한 Security Ignoring 처리
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring()
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations())
			.requestMatchers(HttpMethod.POST, "/registers");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.headers(header -> header
				.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

		// csrf 보안 disable & 특정 리퀘스트 권한 부여
		http
			.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> {
				auth.requestMatchers("members/**").hasRole("USER");
				auth.requestMatchers("auths/**").permitAll();
				auth.requestMatchers("search/**").permitAll();
				auth.requestMatchers("/**").permitAll();
			});

		// JWT 인증으로 인한 세션 불필요
		http
			.sessionManagement(session -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// 비인증 시 자동으로 Login Form Redirect 되는 것 disable
		http
			.formLogin(FormLoginConfigurer::disable)
			.httpBasic(HttpBasicConfigurer::disable);

		// Exception Handler for JWT
		http
			.exceptionHandling(handle -> handle
				.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
				.accessDeniedHandler(new JwtAccessDeniedHandler()));

		// JWT 적용
		http
			.apply(new JwtSecurityConfig());

		return http.build();
	}

	public class JwtSecurityConfig extends AbstractHttpConfigurer<JwtSecurityConfig, HttpSecurity> {
		@Override
		public void configure(HttpSecurity builder) throws Exception {
			AuthenticationManager am = builder.getSharedObject(AuthenticationManager.class);

			JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(am, jwtTokenProvider);
			jwtAuthFilter.setFilterProcessesUrl("/auth/login");
			jwtAuthFilter.setAuthenticationSuccessHandler(new CustomAuthenticationSuccessHandler());
			jwtAuthFilter.setAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());

			JwtFilter jwtFilter = new JwtFilter(jwtTokenProvider);
			builder
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.addFilter(jwtAuthFilter);
		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
