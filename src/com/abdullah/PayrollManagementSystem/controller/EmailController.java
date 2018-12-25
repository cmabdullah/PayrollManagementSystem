package com.abdullah.PayrollManagementSystem.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.Email;
import com.abdullah.PayrollManagementSystem.service.EmailService;

@Controller
public class EmailController {
	
	EmailService emailService;
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	@RequestMapping("/send_mail")
	public String sendMail(Model model) {
		
		model.addAttribute(new Email());
		
		return "send_mail";
	}
	@RequestMapping(value = "/process_send_mail", method = RequestMethod.GET)
	public String processSendMail(Model model,@Valid Email email,  BindingResult result) {
		if (result.hasErrors()) {
			return "send_mail";
		}
		
		emailService.sendEmail(email);
		
		System.out.println("Email object : "+email);
		
		return "disable_enable_user_success";
	}

}
