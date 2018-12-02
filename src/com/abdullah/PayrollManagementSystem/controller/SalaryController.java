package com.abdullah.PayrollManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDate;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.abdullah.PayrollManagementSystem.messageBrokerService.SendMessageService;
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
	
	@Autowired
	SendMessageService sendMessageService;
	
	public void setSendMessageService(SendMessageService sendMessageService) {
		this.sendMessageService = sendMessageService;
	}
	
	@RequestMapping("/permission_for_pay_salary")
	public String permissionForPaySalary() {
		logger.info("Showing salary.....");
		return "permission_for_pay_salary";
	}
	@RequestMapping("/permission_for_pay_salary_accept_by_admin")
	public String permissionForPaySalaryProcess(Model model,@Valid Salary salary, Principal principal) {
		logger.info("permission given for pay salary by admin.....");
		
		if(salary.getBonus() != null) {
			sendMessageService.givePermissionToPayBonus();
		}
		
		sendMessageService.givePermissionToPaySalary();
		return "accept_loan_request";//test message
	}
	@RequestMapping("/give_salary")
	public String paySalary(Model model) {
		logger.info("Showing salary.....");
		
		String isMenagerSalaryPermissionGiven = sendMessageService.isMenagerPermissionGiven("y");
		
		boolean isSalaryPermissionGiven = false;
		if ( isMenagerSalaryPermissionGiven != null ){
			isSalaryPermissionGiven = true;
			model.addAttribute("isSalaryPermissionGiven",isSalaryPermissionGiven);
		}
		
		String isMenagerBonusPermissionGiven = sendMessageService.isMenagerBonusPermissionGiven("y");
		boolean isBonusPermissionGiven = false;
		
		if ( isMenagerBonusPermissionGiven != null ){
			isBonusPermissionGiven = true;
			model.addAttribute("isBonusPermissionGiven",isBonusPermissionGiven);
		}
		
		
		
		return "give_salary";
	}
	@RequestMapping(value = "/prcess_salary", method=RequestMethod.POST)
	public String processSalary(Model model,@Valid Salary salary, Principal principal) {
		logger.info("Showing salary....."+ salary);
		
		String isMenagerBonusPermissionGiven1 = sendMessageService.isMenagerBonusPermissionGiven("y");
		
		if (isMenagerBonusPermissionGiven1 != null) {
			if(salary.getBonus() == null) {
				return "redirect:give_salary";
			}
		}
		
		
		
		//deque from redis
		String isMenagerSalaryPermissionGiven = sendMessageService.isMenagerPermissionGiven("n");
		String isMenagerBonusPermissionGiven = sendMessageService.isMenagerBonusPermissionGiven("n");
		salaryService.calculateSalary(salary.getBonus());
		return "accept_loan_request";//test message
	}
	/// give_salary
}
