package com.abdullah.pms.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abdullah.pms.cash.service.MessageService;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NotificationController {

	@Autowired
	MessageService messageService;

	@Autowired
	UserInfoService userInfoService;

	@RequestMapping("/notification")
	public String showNotification(Model model, Principal principal) {

		Optional<UserInfo> userInfo = userInfoService.exists(principal.getName());
		log.info("userInfo : "+userInfo.get().toString());
		if (userInfo.isPresent()) {
			// not dequeue
			String isMenagerBonusPermissionGiven = messageService.isMenagerBonusPermissionGiven("y");
			String isMenagerSalaryPermissionGiven = messageService.isMenagerPermissionGiven("y");
			model.addAttribute("isMenagerSalaryPermissionGiven", isMenagerSalaryPermissionGiven);
			model.addAttribute("isMenagerBonusPermissionGiven", isMenagerBonusPermissionGiven);

			log.info(isMenagerSalaryPermissionGiven);
			String leaveMessage = messageService.getPendingLeaveMessage(userInfo.get(), "pendingLeave");// mapkey
			String loanMessage = messageService.getPendingLoanMessage(userInfo.get(), "pendingLoan");
			String salaryNotice = messageService.getPendingSalaryMessage(userInfo.get(), "pendingSalary");
			String queueName = String.valueOf(userInfo.get().getId());
			
			//delete
			messageService.delete( queueName, "pendingLeave");
			messageService.delete( queueName, "pendingLoan");
			messageService.delete( queueName, "pendingSalary");
			
			model.addAttribute("salaryNotice", salaryNotice);
			model.addAttribute("leaveMessage", leaveMessage);
			model.addAttribute("loanMessage", loanMessage);
		}

		return "/notification";
	}
}
