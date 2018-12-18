package com.abdullah.PayrollManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abdullah.PayrollManagementSystem.dao.AttendanceVisualizer;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.service.AttendanceService;
import com.abdullah.PayrollManagementSystem.service.LeaveService;
import com.abdullah.PayrollManagementSystem.service.LoanService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;

@Controller
public class HomeController {
	private static Logger logger = Logger.getLogger(HomeController.class);

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

	@Autowired
	LeaveService leaveService;

	public void setLeaveService(LeaveService leaveService) {
		this.leaveService = leaveService;
	}

	LoanService loanService;

	@Autowired
	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}

	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {
		logger.info("Showing home.....");
		Userinfo usersinfo = userinfoService.getCurrent(principal.getName());
		model.addAttribute("role", usersinfo.getAuthority());

		if (usersinfo.getAuthority().equals("ROLE_ADMIN")) {
			List<AttendanceVisualizer> attendanceVisualizer = attendanceService.getAllAttendanceOfThisYear();
			for (AttendanceVisualizer attendanceVisualizer2 : attendanceVisualizer) {
				System.out.println(attendanceVisualizer2);
			}
			model.addAttribute("attendanceVisualizer", attendanceVisualizer);
			
			List<Leave> allLeaveListSize = leaveService.getAllLeaveRequests();
			model.addAttribute("allLeaveListSize", allLeaveListSize.size());

			List<Loan> loanAllPendingRequestsSize = loanService.getAllPendingRequests();
			model.addAttribute("loanAllPendingRequestsSize",loanAllPendingRequestsSize.size());

		}
		
		if (usersinfo.getAuthority().equals("ROLE_EMPLOYEE")) {
			List<AttendanceVisualizer> attendanceVisualizer = attendanceService.getSingleUserAttendanceOfThisYear();
			model.addAttribute("attendanceVisualizer", attendanceVisualizer);
		}

		String x = "2018-10-16";

		model.addAttribute("myTime", x);

		int y = 2018;
		model.addAttribute("y", y);

		return "home";
	}

}
