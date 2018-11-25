package com.abdullah.PayrollManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.service.AttendanceService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;

@Controller 
public class AttendanceController {
	UserinfoService userinfoService;
	
	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}
	
	AttendanceService attendanceService;
	
	@Autowired
	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}

	

	@RequestMapping("/attendance")
	public String giveAttendence( Model model, HttpServletRequest request, Principal principal) {
		
		
		boolean hasLogin = false;
		if ( principal != null ){
			hasLogin = attendanceService.hasLogin(principal.getName() , userinfoService.getUserIdFromName(principal.getName()).getId(), LocalDate.now());
		}
		System.out.println("Login status : " + hasLogin);
		model.addAttribute("hasLogin",hasLogin);
		
		boolean hasLogout = false;
		if ( principal != null ){	
			hasLogout = attendanceService.hasLogout(principal.getName() , userinfoService.getUserIdFromName(principal.getName()).getId(), LocalDate.now());
		}
		System.out.println("Logout status : " + hasLogout);
		model.addAttribute("hasLogout",hasLogout);
		
		if(hasLogin == false && hasLogout == true) {
			//block user
			
			Userinfo userinfo = new Userinfo();
			userinfo.setUsername(principal.getName());
			userinfo.setEnabled(false);
			System.out.println("F"+ userinfo);
			userinfoService.updateUserEnabledStatus(userinfo);
			
			
			return "block";
		}

		return "attendance";
	}
	
//	0:0:0:0:0:0:0:1
//	2001:0db8:0000:0000:0000:ff00:0042:8329
//	2001:db8::ff00:42:8329
	
	@RequestMapping("/start")
	public String startJob(Model model,@Valid Attendance attendance, HttpServletRequest request, Principal principal, @RequestParam(value="away", required=false) String away) {
		boolean hasLogin = false;
		boolean hasLogout = true;
		
		if( principal != null ) {
			hasLogin = attendanceService.hasLogin(principal.getName() , userinfoService.getUserIdFromName(principal.getName()).getId(), LocalDate.now());
			hasLogout =  attendanceService.hasLogout(principal.getName() , userinfoService.getUserIdFromName(principal.getName()).getId(), LocalDate.now());
		}
		
		
		if(hasLogin == false && hasLogout == false) {//give permission to login
			System.out.println("job started");
			System.out.println(request.getRemoteAddr());
			System.out.println(attendance);
			String username = "";
			if( principal != null ) {
				username = principal.getName();
				Userinfo userinfo = userinfoService.getUserIdFromName(username);
		    	System.out.println("Single Notice : "+userinfo);
		    	attendance.setLogintime(LocalDateTime.now());
		    	attendance.setUserinfo_id(userinfo.getId());
		    	attendance.setIpaddress(request.getRemoteAddr());
		    	attendance.setWorkinghours(0);
				System.out.println("Attendence object test : "+attendance);
				
				attendanceService.create(attendance);
			}
		}
		
		
		
		if (away != null) {
			System.out.println("Logout operation");
			if(hasLogin == true && hasLogout == false) {
				//give logout permission
				attendanceService.giveLogoutPermissionAndCalculateWorkingHour(principal.getName() , userinfoService.getUserIdFromName(principal.getName()).getId(), LocalDate.now());
				
			}	
		}
		
		
		
//		boolean hasLogin = false;
//		
//		if ( principal != null ){
//			
//			hasLogin = attendanceService.hasLogin(principal.getName() , userinfoService.getUserIdFromName(principal.getName()).getId(), LocalDate.now());
//		}
//		
//		System.out.println("Login status : " + hasLogin);
//		
//		model.addAttribute("hasLogin",hasLogin);
//		
//		
//		boolean hasLogout = false;
//		
//		if ( principal != null ){
//			
//			hasLogout = attendanceService.hasLogout(principal.getName() , userinfoService.getUserIdFromName(principal.getName()).getId(), LocalDate.now());
//		}
//		
//		model.addAttribute("hasLogout",hasLogout);
//		
//		
//		System.out.println("Logout status : " + hasLogout);
		
		
		//return "disable_enable_user_success";
		return "redirect:/attendance";
	}
}
