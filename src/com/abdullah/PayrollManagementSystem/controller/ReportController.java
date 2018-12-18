package com.abdullah.PayrollManagementSystem.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.Report;
import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.abdullah.PayrollManagementSystem.dao.SearchData;
import com.abdullah.PayrollManagementSystem.service.AttendanceService;
import com.abdullah.PayrollManagementSystem.service.LeaveService;
import com.abdullah.PayrollManagementSystem.service.LoanService;
import com.abdullah.PayrollManagementSystem.service.SalaryService;
import com.abdullah.PayrollManagementSystem.service.UserinfoService;

//defining beans [PdfRevenueSummary]
@Controller
public class ReportController extends AbstractController {
	private static Logger logger = Logger.getLogger(ReportController.class);

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

	@Autowired
	SalaryService salaryService;

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	LoanService loanService;

	@Autowired
	public void setLoanService(LoanService loanService) {
		this.loanService = loanService;
	}

	UserinfoService userinfoService;

	@Autowired
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}

	@RequestMapping("/report")
	public String showReport(Model model) {
		List<Leave> leaveStatusGroupBy = leaveService.getLeaveStatusGroupBy();
		System.out.println(leaveStatusGroupBy.size());
		model.addAttribute("leaveStatusGroupBy", leaveStatusGroupBy);
		return "report";

	}

	@RequestMapping("/search_by_id")
	public String showThreeTypesofReport(Model model, SearchData searchData, HttpServletRequest request,
			HttpServletResponse response) {

		LocalDateTime entryfrom = searchData.getEntryfrom();

		LocalDateTime entryto = searchData.getEntryto();

		String username = searchData.getUsername();
		String reportType = request.getParameter("reportType");

		boolean inValidData = false;
		if (!userinfoService.exists(username)) {
			System.out.println("else........");
			inValidData = true;
			model.addAttribute("inValidData", inValidData);
			return "data_not_found";
		}

		int userId = userinfoService.getUserIdFromName(username).getId();

		System.out.println(
				"username : " + username + " entryfrom : " + entryfrom + " entryto " + entryto + " userId : " + userId);
		boolean wrongpattern = false;
		if (entryfrom.isAfter(entryto)) {
			wrongpattern = true;
			return "redirect:report";
		}

		List<Attendance> attendanceListByUser;
		if (reportType.equals("attendance")) {
			System.out.println("Working on attendance");
			LocalDateTime today = LocalDateTime.now();
			LocalDateTime sevenDaysAgo = today.minusWeeks(1);
			attendanceListByUser = attendanceService.getAllAttendanceBetween(entryto.toLocalDate(),
					entryfrom.toLocalDate(), userId);
			// attendanceListByUser =
			// attendanceService.getAllAttendanceBetween(today.toLocalDate(),sevenDaysAgo.toLocalDate(),
			// userId);
			System.out.println("Size : " + attendanceListByUser.size());
			model.addAttribute("attendanceListByUser", attendanceListByUser);
			return "search_by_id_report";
		}

		List<Salary> salaryListByUser;
		if (reportType.equals("salary")) {
			System.out.println("Working on salary");
			salaryListByUser = salaryService.getAllSalaryBetween(entryfrom.toLocalDate(), entryto.toLocalDate(),
					userId);
			System.out.println("salaryListByUser : " + salaryListByUser.size());
			model.addAttribute("salaryListByUser", salaryListByUser);
			return "search_by_id_report";
		}

		List<Loan> loanListByUser;
		if (reportType.equals("loan")) {
			System.out.println("Working on Loan");
			loanListByUser = loanService.getAllLoanBetween(entryfrom.toLocalDate(), entryto.toLocalDate() , userId);
			System.out.println("Size : " + loanListByUser.size());
			for (Loan loan : loanListByUser) {
				System.out.println("loanobject details : " + loan);
			}

			model.addAttribute("loanListByUser", loanListByUser);
			return "search_by_id_report";

		}

		return "search_by_id_report";

	}

	@RequestMapping("/leaveGroupBy/{leavetype}")
	public String showleaveGroupBy(@PathVariable String leavetype, Model model) {
		List<Leave> leaveStatusGroupByLeavetype = leaveService.getLeaveStatusGroupByLeavetype(leavetype);
		model.addAttribute("leaveStatusGroupByLeavetype", leaveStatusGroupByLeavetype);
		model.addAttribute("leaveReportFlag", leavetype);
		return "leave_report";

	}

	// @RequestMapping("/report")
	// protected ModelAndView handleRequestInternal(HttpServletRequest request,
	// HttpServletResponse response)
	// throws Exception {
	//
	// // dummy data
	// Map<String, String> revenueData = new HashMap<String, String>();
	// revenueData.put("1/20/2010", "$100,000");
	// revenueData.put("1/21/2010", "$200,000");
	// revenueData.put("1/22/2010", "$300,000");
	// revenueData.put("1/23/2010", "$400,000");
	// revenueData.put("1/24/2010", "$500,000");
	// return new ModelAndView("PdfRevenueSummary", "revenueData", revenueData);
	// //return new ModelAndView("report", "revenueData", revenueData);
	//
	// }

	@RequestMapping(value = "/report_process", method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// String entryfrom = request.getParameter("entryfromString");
		LocalDateTime entryfrom = LocalDateTime.parse(request.getParameter("entryfromString").concat(" 00:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.println("Hi HI " + entryfrom);

		LocalDateTime entryto = LocalDateTime.parse(request.getParameter("entrytoString").concat(" 00:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		String reportType = request.getParameter("reportType");

		boolean wrongpattern = false;
		if (entryfrom.isAfter(entryto)) {
			wrongpattern = true;
			return new ModelAndView("redirect:report", "wrongpattern", wrongpattern);

		}

		List<Attendance> attendanceList;
		if (reportType.equals("attendance")) {
			System.out.println("Working on attendance");
			attendanceList = attendanceService.getAllAttendanceBetween(entryfrom.toLocalDate(), entryto.toLocalDate());
			System.out.println("Size : " + attendanceList.size());
			return new ModelAndView("AttendancePdfSummary", "attendanceList", attendanceList);

		}
		List<Salary> salaryList;
		if (reportType.equals("salary")) {
			System.out.println("Working on salary");
			salaryList = salaryService.getAllSalaryBetween(entryfrom.toLocalDate(), entryto.toLocalDate());
			// System.out.println("Size : "+salaryList.size());
			//
			//
			// for (Salary salary : salaryList) {
			// System.out.println("Salaryobject details : "+ salary);
			// }
			return new ModelAndView("SalaryPdfSummary", "salaryList", salaryList);

		}

		List<Loan> loanList;
		if (reportType.equals("loan")) {
			System.out.println("Working on Loan");
			loanList = loanService.getAllLoanBetween(entryfrom.toLocalDate(), entryto.toLocalDate());
			System.out.println("Size : " + loanList.size());
			for (Loan loan : loanList) {
				System.out.println("loanobject details : " + loan);
			}
			return new ModelAndView("LoanPdfSummary", "loanList", loanList);

		}

		// 2018-12-14
		// dummy data
		Map<String, String> revenueData = new HashMap<String, String>();
		revenueData.put("1/20/2010", "$100,000");
		revenueData.put("1/21/2010", "$200,000");
		revenueData.put("1/22/2010", "$300,000");
		revenueData.put("1/23/2010", "$400,000");
		revenueData.put("1/24/2010", "$500,000");
		return new ModelAndView("PdfRevenueSummary", "revenueData", revenueData);
		// return new ModelAndView("report", "revenueData", revenueData);

	}

	// @RequestMapping(value = "/report_process", method = RequestMethod.POST)
	// public String leaveRequestProcess(Model model, @Valid Report report,
	// Principal principal) {
	//
	// boolean wrongpattern = false;
	// if (report.getEntryfrom().isAfter(report.getEntryto())) {
	// report.setEntryfrom(null);
	// report.setEntryto(null);
	// wrongpattern = true;
	// model.addAttribute("wrongpattern", wrongpattern);
	// return "redirect:report";
	// }
	// List<Attendance> attendanceList ;
	// if (report.getReportType().equals("attendance")) {
	// System.out.println("Working on attendance");
	// attendanceList =
	// attendanceService.getAllAttendanceBetween(report.getEntryfrom().toLocalDate(),
	// report.getEntryto().toLocalDate());
	// System.out.println(attendanceList.size());
	//
	// }
	//
	// return "disable_enable_user_success";
	// }

}
