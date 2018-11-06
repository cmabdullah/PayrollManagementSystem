package com.abdullah.PayrollManagementSystem.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abdullah.PayrollManagementSystem.dao.Loan;
@Controller
public class LoanController {
	private static Logger logger = Logger.getLogger(LoanController.class);
	@RequestMapping("/loanreq")
	public String requestForLoan(Model model) {
		model.addAttribute(new Loan());//add attribute into model
		logger.info("Showing loan.....");
		return "loanreq";
	}
	
	@RequestMapping("/loanreq_process")
	public String requestForLoanProcess(Model model, @Valid Loan loan) {
		logger.info("Showing loan....."+loan);
		return "loanreq";
	}
	
}
