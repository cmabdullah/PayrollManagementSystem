package com.abdullah.PayrollManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller 
public class AttendanceController {
	
	@RequestMapping("/attendance")
	public String giveAttendence() {
		return "disable_enable_user_success";
	}
	
	
	@RequestMapping("/start")
	public String startJob() {
		System.out.println("job started");
		return "disable_enable_user_success";
	}
}
