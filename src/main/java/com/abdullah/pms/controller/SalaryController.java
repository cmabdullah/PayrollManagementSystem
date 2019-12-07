package com.abdullah.pms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abdullah.pms.service.SalaryService;

@RestController
public class SalaryController {
	
	@Autowired
	SalaryService salaryService;
	
	@ResponseBody
	@RequestMapping(value = "/salary", method=RequestMethod.GET)
	public String processSalary() {
		salaryService.calculateSalary();
		return "success";//test message
	}
}
