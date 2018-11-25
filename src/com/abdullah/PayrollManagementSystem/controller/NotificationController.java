package com.abdullah.PayrollManagementSystem.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.LeaveMessage;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.messageBrokerService.SendMessageService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;
@Controller
public class NotificationController {
	private static Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	SendMessageService sendMessageService;
	
	public void setSendMessageService(SendMessageService sendMessageService) {
		this.sendMessageService = sendMessageService;
	}
	
	UserinfoService userinfoService;
	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}
	
	@RequestMapping("/notification")
	public String showNotification(Model model, Principal principal) throws IOException, TimeoutException {

		
		
		int userId = userinfoService.getUserIdFromName(principal.getName()).getId();
		
		String queueName = String.valueOf(userId);
		
		String leaveMessage = sendMessageService.getPendingLeaveMessage(queueName);
		String loanMessage = sendMessageService.getPendingLoanMessage(queueName);
		
		//first usersinfo is key, last usersinfo is value
		model.addAttribute("leaveMessage",leaveMessage);
		model.addAttribute("loanMessage",loanMessage);
		
		return "notification";
	}


}
