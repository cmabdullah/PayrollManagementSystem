package com.abdullah.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.pms.cash.service.MessageService;
import com.abdullah.pms.service.SalaryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SalaryController {
	
	@Autowired
	SalaryService salaryService;
	
	@Autowired
	MessageService messageService;
	
//	@ResponseBody
//	@RequestMapping(value = "/salary", method=RequestMethod.GET)
//	public String processSalary() {
//		salaryService.calculateSalary();
//		return "success";//test message
//	}
	
	@RequestMapping(value = "/payment_permission", method=RequestMethod.GET)
	public String paymentPermission() {
		return "payment_permission";
	}
	
	@RequestMapping(value = "/payment_permission", method=RequestMethod.POST)
	public String paymentPermissionPost(String bonus) {
		log.info(bonus);
		if(bonus != null) 
			messageService.givePermissionToPayBonus();
		messageService.givePermissionToPaySalary();
		return "redirect:/payment_permission";//test message
	}
	
	@RequestMapping(value = "/give_salary" , method=RequestMethod.GET)
	public String paySalary(Model model) {
		log.info("Showing salary.....");
		String isMenagerSalaryPermissionGiven = messageService.isMenagerPermissionGiven("y");
		log.info(isMenagerSalaryPermissionGiven);
		boolean isSalaryPermissionGiven = false;
		if ( isMenagerSalaryPermissionGiven != null ){
			isSalaryPermissionGiven = true;
			model.addAttribute("isSalaryPermissionGiven",isSalaryPermissionGiven);
		}
		
		String isMenagerBonusPermissionGiven = messageService.isMenagerBonusPermissionGiven("y");
		log.info(isMenagerBonusPermissionGiven);
		boolean isBonusPermissionGiven = false;
		if ( isMenagerBonusPermissionGiven != null ){
			isBonusPermissionGiven = true;
			model.addAttribute("isBonusPermissionGiven",isBonusPermissionGiven);
		}
		return "give_salary";
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/give_salary", method=RequestMethod.POST)
	public String paySalaryPost(String bonus) {
		log.info(bonus);
		String isMenagerBonusPermissionGiven = messageService.isMenagerBonusPermissionGiven("y");
		if ( isMenagerBonusPermissionGiven != null ){
			if (bonus == null) {
				return "redirect:give_salary";
			}
		}
		String delMenagerSalaryPermissionGiven = messageService.isMenagerPermissionGiven("n");
		String delMenagerBonusPermissionGiven  = messageService.isMenagerBonusPermissionGiven("n");
		salaryService.calculateSalary(bonus);
		return "give_salary";
	}
}
