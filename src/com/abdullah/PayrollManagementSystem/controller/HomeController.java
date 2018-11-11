package com.abdullah.PayrollManagementSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

		return "notification";
	}
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
	public String putNotification(@RequestBody String message) {
		logger.info("Showing Notification....." + message);
		return "notification";
	}
	
}
