package com.abdullah.PayrollManagementSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
	private static Logger logger = Logger.getLogger(HomeController.class);
	@RequestMapping("/")
	public String showHome() {
		logger.info("Showing home.....");
		return "home";
	}
	
	@RequestMapping("/notification")
	public String showNotification() {
		logger.info("Showing Notification.....");
		return "notification";
	}
	
}
