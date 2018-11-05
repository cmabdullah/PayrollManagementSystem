package com.abdullah.PayrollManagementSystem.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class LoanController {
	private static Logger logger = Logger.getLogger(LoanController.class);
	@RequestMapping("/loanreq")
	public String showLoanInfo() {
		logger.info("Showing loan.....");
		return "loanreq";
	}
	
}
