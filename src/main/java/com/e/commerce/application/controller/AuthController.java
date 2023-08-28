package com.e.commerce.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auths")
public class AuthController {
	@GetMapping("/login-form")
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login")
	public String login() {
		return "home";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
}
