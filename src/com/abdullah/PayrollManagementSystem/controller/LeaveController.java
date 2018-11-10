package com.abdullah.PayrollManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.service.LeaveService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;
@Controller
public class LeaveController {
	
	UserinfoService userinfoService;
	private static Logger logger = Logger.getLogger(LoanController.class);
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
		boolean isPandingRequest = false;
	//	boolean isLeaveRequestOutOfLimit  = false;
		isPandingRequest = leaveService.isPandingRequest(leave.getUserinfo_id());
		
//		//if (leave.getLeavetype() == "regular") {
//			isLeaveRequestOutOfLimit = leaveService.isLeaveRequestOutOfLimit(leave.getUserinfo_id());
//			model.addAttribute("isLeaveRequestOutOfLimit",isPandingRequest);
//		//}
		
		
		model.addAttribute("isPandingRequest",isPandingRequest);
		return "leavereq";
	}
	
	@RequestMapping(value = "/leavereq_process", method=RequestMethod.POST)
	public String leaveRequestProcess(Model model,@Valid Leave leave, Principal principal) {
		
		
		boolean wrongpattern = false;
		if(leave.getEntryfrom().isAfter(leave.getEntryto())) {
			leave.setEntryfrom(null);
			leave.setEntryto(null);
			wrongpattern = true;
			model.addAttribute("wrongpattern",wrongpattern);
			return "leavereq";
		}
		System.out.println("tostring Leave object : "+leave);
		leave.setUserinfo_id(userinfoService.getUserIdFromName(principal.getName()).getId());
		
		
		boolean isPandingRequest = false;
		isPandingRequest = leaveService.isPandingRequest(leave.getUserinfo_id());
		
		boolean isLeaveRequestOutOfLimit  = false;
		isPandingRequest = leaveService.isPandingRequest(leave.getUserinfo_id());
		
		if (leave.getLeavetype().equals("regular")) {
			isLeaveRequestOutOfLimit = leaveService.isLeaveRequestOutOfLimit(leave.getUserinfo_id());
		}
		System.out.println("isLeaveRequestOutOfLimit : "+isLeaveRequestOutOfLimit);
		model.addAttribute("isLeaveRequestOutOfLimit",isLeaveRequestOutOfLimit);
		if(isPandingRequest || isLeaveRequestOutOfLimit) {
			model.addAttribute("isPandingRequest",isPandingRequest);
			
			return "leavereq";
		} else {
			leaveService.postLeaveApplication(leave);
			logger.info("Showing leave info....."+leave);
		}
		
		return "disable_enable_user_success";
	}
	
	//deleteleave
	
	@RequestMapping(value="/deleteleave/{id}",method = RequestMethod.GET)  
	public String deleteLeave(@PathVariable int id) {
		
		logger.info("deleted id is : "+id);
			leaveService.ignorePendingApplicationId(id);
			//loanService.deletePendingLoanApplication(id);
		return "disable_enable_user_success";
	}
	
	@RequestMapping(value="/acceptleave/{id}",method = RequestMethod.GET)  
	public String acceptLeave(@PathVariable int id) {
		
		logger.info("accept id is : "+id);
		leaveService.acceptPendingApplicationId(id);
		return "disable_enable_user_success";
	}
	
	
	
	//admin panel
	@RequestMapping("/ad_leave")
	public String adLeaveRequest(Model model) {
		
		List<Leave> leaveInit = leaveService.getAllLeaveRequests();
		
		//first usersinfo is key, last usersinfo is value
		model.addAttribute("leaveInit",leaveInit);
		
		return "ad_leave";
	}
	
	//admin panel
	@RequestMapping(value = "/ad_leave_req_process",  method=RequestMethod.POST)
	public String adLeaveRequestProcess(Model model,@Valid Leave leave, BindingResult result, Principal principal) {
		
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
		
		//check user id has any pending request or not
		List<Leave> leaveInit = leaveService.getAllLeaveRequests();
		int userInfoId = leave.getUserinfo_id();
		boolean userIdPendingRequestExist = false;
		for (Leave leave1 : leaveInit) {
			
			if (leave1.getUserinfo_id() == userInfoId) {
				userIdPendingRequestExist = true;
			}
		}
		if(!userIdPendingRequestExist)
			return "ad_leave";
		
		//if user id has pending request
		int leaveId = leaveService.getLeaveApplicationIdBasedOnUserinfo_id(leave.getUserinfo_id());
		leave.setId(leaveId);
		
		System.out.println("Show leave info : "+leave);
		leaveService.performADRequest(leave);
		return "disable_enable_user_success";
	}
}
