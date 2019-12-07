package com.abdullah.pms.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.abdullah.pms.cash.service.MessageService;
import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.service.LeaveService;
import com.abdullah.pms.service.UserInfoService;

@Controller
public class LeaveController {

	@Autowired
	LeaveService leaveService;

	@Autowired
	UserInfoService userInfoService;
	
	@Autowired
	MessageService messageService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping("/leavereq")
	public String leaveRequest(Model model, Principal principal) {
		Optional<UserInfo> userInfo = userInfoService.exists(principal.getName());
		boolean isPandingRequest = false;
		// boolean isLeaveRequestOutOfLimit = false;
		isPandingRequest = leaveService.isPandingRequest(userInfo.get());
		System.out.println("isPandingRequest Status : "+isPandingRequest);

		
//			//if (leave.getLeavetype() == "regular") {
//				isLeaveRequestOutOfLimit = leaveService.isLeaveRequestOutOfLimit(leave.getUserinfo_id());
//				model.addAttribute("isLeaveRequestOutOfLimit",isPandingRequest);
//			//}

		//this 3 lines are side effect, implement notification controller
//		String mapkey = "pendingleave";
//		String leaveMessage = messageService.getPendingLeaveMessage(userInfo.get(), mapkey);
//		System.out.println("leaveMessage : " + leaveMessage);
		
		model.addAttribute("isPandingRequest", isPandingRequest);
		model.addAttribute("leave", new Leave());
		return "leavereq";
	}

	@RequestMapping(value = "/leavereq", method = RequestMethod.POST)
	public String leaveRequestProcess(Model model, @Valid Leave leave, Principal principal) {
		System.out.println(leave.toString());
		// convert date to local date
		LocalDate entryFrom = leave.getEntryFrom().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate entryTo = leave.getEntryTo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println("localDate : " + entryFrom);
		boolean wrongpattern = false;
		if ((entryTo.compareTo(entryFrom)) < 0) {
			// enter a valid date
			// print error message through js
			wrongpattern = true;
			model.addAttribute("wrongpattern", wrongpattern);
			System.out.println("Parsing date not valid ");
			return "leavereq";
		}
		Optional<UserInfo> userInfo = userInfoService.exists(principal.getName());
		if (userInfo.isPresent()) {
			// implement user auth first
			leaveService.postLeaveApplication(leave, entryFrom, entryTo, userInfo.get());
		}
		return "redirect:/leavereq";
	}
	
	@RequestMapping("/ad_leave")
	public String adLeaveRequest(Model model) {
		List<Leave> leaveList = leaveService.getAllLeaveRequests();
		System.out.println("Leave status"+leaveList);
		model.addAttribute("leaveList",leaveList);
		return "ad_leave";
	}
	
	@RequestMapping(value="/acceptLeave/{id}",method = RequestMethod.GET)  
	public String acceptLeave(@PathVariable int id){
		Optional<Leave> leave = leaveService.findById(id);
		if (leave.isPresent()) {
			Leave leaveAcceptStatusUpdate = leave.get();
			leaveAcceptStatusUpdate.setStatus(1);
			//show message js
			Leave leavesaveRes = leaveService.save(leaveAcceptStatusUpdate);
			//messageService.postLeaveAcceptionMessage(leavesaveRes);
		}
		return "redirect:/ad_leave";
	}
	
	
	@RequestMapping(value="/deleteLeave/{id}",method = RequestMethod.GET)  
	public String deleteLeave(@PathVariable int id) {

		Optional<Leave> leave = leaveService.findById(id);
		if (leave.isPresent()) {
			Leave leaveRejectStatusUpdate = leave.get();
			leaveRejectStatusUpdate.setStatus(2);//status 2 for reject
			//show message js
			leaveService.save(leaveRejectStatusUpdate);
		}
		return "redirect:/ad_leave";
	}

}