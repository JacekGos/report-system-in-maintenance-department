package com.jacekg.reportSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showLoginPage")
	public String showLoginPage() {
		return "navigation/login-page";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		return "navigation/access-denied";
	}
}
