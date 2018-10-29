package com.abdullah.PayrollManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LeaveController {
	
	@RequestMapping("/leavereq")
	public String leaveRequest() {
		return "leavereq";
	}
	
}
