package com.e.commerce.global.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e.commerce.global.security.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;

	@GetMapping("/reissue")
	public ResponseEntity reissue(HttpServletRequest request, HttpServletResponse response) {
		authService.reissue(request, response);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/logout")
	public ResponseEntity logout(HttpServletRequest request) {
		authService.logout(request);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
