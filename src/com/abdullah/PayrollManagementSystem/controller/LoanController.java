package com.abdullah.PayrollManagementSystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	//@RequestMapping("/loanreq_process")
	@RequestMapping(value = "/loanreq_process", method=RequestMethod.POST)
	public String requestForLoanProcess(Model model, @Valid Loan loan , BindingResult result) {
		logger.info("Showing loan....."+loan);
		
		
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error: errors) {
				System.out.println(error.getDefaultMessage());
			}
		}else {
			System.out.println("Form is validet");
		}
		
		
		return "loanreq";
	}
	
}
