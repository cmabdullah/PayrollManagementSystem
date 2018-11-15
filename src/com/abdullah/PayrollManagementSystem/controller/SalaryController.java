package com.abdullah.PayrollManagementSystem.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.abdullah.PayrollManagementSystem.service.SalaryService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;


@Controller
public class SalaryController {
	private static Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	SalaryService salaryService;
	
	
	
	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}
	@RequestMapping("/give_salary")
	public String paySalary() {
		logger.info("Showing salary.....");
		return "give_salary";
	}
	@RequestMapping(value = "/prcess_salary", method=RequestMethod.POST)
	public String processSalary(Model model,@Valid Salary salary, Principal principal) {
		logger.info("Showing salary....."+ salary);
		
		salaryService.calculateSalary(salary.getBonus());
		return "accept_loan_request";
	}
	/// give_salary
}
