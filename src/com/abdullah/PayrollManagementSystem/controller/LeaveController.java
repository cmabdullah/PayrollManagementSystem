package com.abdullah.PayrollManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.service.LeaveService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;
@Controller
public class LeaveController {
	
	UserinfoService userinfoService;
	
	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}
	@Autowired
	LeaveService leaveService;

	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

	@RequestMapping("/leavereq")
	public String leaveRequest(Model model, @Valid Leave leave, Principal principal) {

		return "leavereq";
	}
	
	@RequestMapping(value = "/leavereq_process", method=RequestMethod.POST)
	public String leaveRequestProcess(Model model,@Valid Leave leave, Principal principal) {
		System.out.println(leave);
		leave.setUserinfo_id(userinfoService.getUserIdFromName(principal.getName()).getId());
		
		
		boolean isPandingRequest = false;
		isPandingRequest = leaveService.isPandingRequest(leave.getUserinfo_id());
		
		if(isPandingRequest) {
			model.addAttribute("isPandingRequest",isPandingRequest);
			return "leavereq";
		} else {
			leaveService.postLeaveApplication(leave);
		}
		
		
		
		//leaveService.postLeaveApplication(leave);
		
		return "leavereq";
	}
	
	
	
	//admin panel
	@RequestMapping("/ad_leave")
	public String adLeaveRequest(Model model) {
		model.addAttribute(new Leave());
		return "ad_leave";
	}
	
	//admin panel
	@RequestMapping(value = "/ad_leave_req_process",  method=RequestMethod.POST)
	public String adLeaveRequestProcess(Model model,@Valid Leave leave, BindingResult result, Principal principal) {
		System.out.println("Show leave info : "+leave);
		boolean wrongpattern = false;
		boolean wrongid = false;
		
		if (!userinfoService.existsUserId(leave.getUserinfo_id())) {
			wrongid = true;
			model.addAttribute("wrongid",wrongid);
			//result.rejectValue("userinfo_id", "DuplicateKey.leave.userinfo_id", "this userinfo_id is not exist yet");
			return "ad_leave";
		}
		
		if(leave.getEntryfrom().isAfter(leave.getEntryto())) {
			leave.setEntryfrom(null);
			leave.setEntryto(null);
			wrongpattern = true;
			model.addAttribute("wrongpattern",wrongpattern);
			return "ad_leave";
		}
		
		
		
		leaveService.performADRequest(leave);
		return "disable_enable_user_success";
	}
	
}
