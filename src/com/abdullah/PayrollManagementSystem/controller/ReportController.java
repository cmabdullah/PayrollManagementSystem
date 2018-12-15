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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.Report;
import com.abdullah.PayrollManagementSystem.service.AttendanceService;

//defining beans [PdfRevenueSummary]
@Controller
public class ReportController extends AbstractController {
	private static Logger logger = Logger.getLogger(ReportController.class);

	AttendanceService attendanceService;

	@Autowired
	public void setAttendanceService(AttendanceService attendanceService) {
		this.attendanceService = attendanceService;
	}
	
	@RequestMapping("/report")
	public String showReport() {
		return "report";

	}

//	@RequestMapping("/report")
//	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		// dummy data
//		Map<String, String> revenueData = new HashMap<String, String>();
//		revenueData.put("1/20/2010", "$100,000");
//		revenueData.put("1/21/2010", "$200,000");
//		revenueData.put("1/22/2010", "$300,000");
//		revenueData.put("1/23/2010", "$400,000");
//		revenueData.put("1/24/2010", "$500,000");
//		 return new ModelAndView("PdfRevenueSummary", "revenueData", revenueData);
//		//return new ModelAndView("report", "revenueData", revenueData);
//
//	}

	
	
	@RequestMapping(value = "/report_process", method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//String entryfrom = request.getParameter("entryfromString");
		LocalDateTime entryfrom = LocalDateTime.parse(request.getParameter("entryfromString").concat(" 00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		System.out.println("Hi HI "+entryfrom);
		
		LocalDateTime entryto = LocalDateTime.parse(request.getParameter("entrytoString").concat(" 00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		String reportType = request.getParameter("reportType");
		
		boolean wrongpattern = false;
		if (entryfrom.isAfter(entryto)) {
			wrongpattern = true;
			return new ModelAndView("redirect:report", "wrongpattern", wrongpattern);

		}
		
		List<Attendance> attendanceList ;
		if (reportType.equals("attendance")) {
			System.out.println("Working on attendance");
			attendanceList = attendanceService.getAllAttendanceBetween(entryfrom.toLocalDate(), entryto.toLocalDate());
			System.out.println("Size : "+attendanceList.size());
			return new ModelAndView("AttendancePdfSummary", "attendanceList", attendanceList);

		}
		
		
		
		
		
		//2018-12-14
		// dummy data
		Map<String, String> revenueData = new HashMap<String, String>();
		revenueData.put("1/20/2010", "$100,000");
		revenueData.put("1/21/2010", "$200,000");
		revenueData.put("1/22/2010", "$300,000");
		revenueData.put("1/23/2010", "$400,000");
		revenueData.put("1/24/2010", "$500,000");
		 return new ModelAndView("PdfRevenueSummary", "revenueData", revenueData);
		//return new ModelAndView("report", "revenueData", revenueData);

	}
	
	
	
//	@RequestMapping(value = "/report_process", method = RequestMethod.POST)
//	public String leaveRequestProcess(Model model, @Valid Report report, Principal principal) {
//
//		boolean wrongpattern = false;
//		if (report.getEntryfrom().isAfter(report.getEntryto())) {
//			report.setEntryfrom(null);
//			report.setEntryto(null);
//			wrongpattern = true;
//			model.addAttribute("wrongpattern", wrongpattern);
//			return "redirect:report";
//		}
//		List<Attendance> attendanceList ;
//		if (report.getReportType().equals("attendance")) {
//			System.out.println("Working on attendance");
//			attendanceList = attendanceService.getAllAttendanceBetween(report.getEntryfrom().toLocalDate(), report.getEntryto().toLocalDate());
//			System.out.println(attendanceList.size());
//
//		}
//
//		return "disable_enable_user_success";
//	}

}
