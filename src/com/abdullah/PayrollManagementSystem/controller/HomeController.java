package com.abdullah.PayrollManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String showHome() {
		return "home";
	}
	@RequestMapping("/admin")
	public String showAdmin() {
		return "admin";
	}
}
