package com.abdullah.PayrollManagementSystem.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.AttendanceDao;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.LoanDao;
import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.abdullah.PayrollManagementSystem.dao.SalaryDao;
import com.abdullah.PayrollManagementSystem.dao.UserDao;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoDao;



@Service("salaryService")
public class SalaryService {

	private SalaryDao salaryDao;
	private UserinfoDao userinfoDao;

	@Autowired
	public void setUserinfoDao(UserinfoDao userinfoDao) {
		this.userinfoDao = userinfoDao;
	}

	@Autowired
	public void setSalaryDao(SalaryDao salaryDao) {
		this.salaryDao = salaryDao;
	}

	private AttendanceDao attendanceDao;

	@Autowired
	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}
	
	private LeaveDao leaveDao;
	@Autowired
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}
	
	private LoanDao loanDao;

	@Autowired
	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}

	public void calculateSalary(String bonus) {
		List<Userinfo> userEnableInfo = userinfoDao.showAllEnabledUsers();
		
		List<Salary> salary1 = new ArrayList<>();
		
		System.out.println(userEnableInfo.size());
		for (Userinfo userinfo : userEnableInfo) {
			System.out.println(userinfo);
			Salary salary = new Salary();
			System.out.println("your ID : "+userinfo.getId()+"total Working hour : "+calculateTotalWorkingHour(userinfo.getId()));
			System.out.println("your usedLeaveThisMonth : "+userinfo.getId()+" total  leave days : "+calculateTotalLeaveDays(userinfo.getId()));
			System.out.println("your Id  : "+userinfo.getId()+" your running loan id : "+checkRunningLoan(userinfo.getId()));
			
			salary.setUserinfo_id(userinfo.getId());
			salary.setMonthlyWorkingHour(calculateTotalWorkingHour(userinfo.getId()));
			salary.setTotalLeaveDays(calculateTotalLeaveDays(userinfo.getId()));
			salary.setLoan_id(checkRunningLoan(userinfo.getId()));
			salary.setUsertype(userinfo.getAuthority());
			
			System.out.println("Salary Object : "+salary);
			
			salary1.add(salary);
			
		}
		
		System.out.println("Salary size: "+salary1.size());
		
		
		for (Salary salary2 : salary1) {
			System.out.println("salary1 Object : "+salary2);
		}
		
		
		
		
		 processLowLevelCalculation(salary1);
		
		
//		for(int i = 0 ; i< userEnableInfo.size(); i++) {
//			System.out.println("your ID : "+userEnableInfo.get(i).getId()+"total Working hour : "+calculateTotalWorkingHour(userEnableInfo.get(i).getId()));
//			System.out.println("your usedLeaveThisMonth : "+userEnableInfo.get(i).getId()+" total  leave days : "+calculateTotalLeaveDays(userEnableInfo.get(i).getId()));
//			System.out.println("your Id  : "+userEnableInfo.get(i).getId()+" your running loan id : "+checkRunningLoan(userEnableInfo.get(i).getId()));
//		}
		
		
//		int totalworkingHour = calculateTotalWorkingHour();
//		System.out.println("total working hour : " +totalworkingHour);
		

	}
	
	private void processLowLevelCalculation(List<Salary> salary) {
		
		List<Salary> AllUsersSalary = (List<Salary>) salary;
		
		for (Salary salary2 : AllUsersSalary) {
			System.out.println("Salary Datails "+salary2 );
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	private int checkRunningLoan(int userinfoId) {
		int loanId = 0;
		List<Loan> runningLoanInfo = loanDao.checkRunningLoan(userinfoId);
		for (Loan loan : runningLoanInfo) {
			//System.out.println(loan);
			loanId = loan.getId();
		}
		return loanId;
	}

	private int calculateTotalLeaveDays(int userinfoId) {
		int totalLeaveDays = 0;
		String currentMonthString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
		String StartOfCurrentMonththString = currentMonthString.substring(0, 8).concat("01 00:00");

		// convert string to Localdatetime
		LocalDateTime currentMonththLocalDateTime = LocalDateTime.parse(StartOfCurrentMonththString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime previousMonththLocalDateTime = currentMonththLocalDateTime.minusMonths(1);

		List<Leave> leavePendingRequest = leaveDao.checkRegularLeaveFromLastMonthToPresentMonth(currentMonththLocalDateTime, previousMonththLocalDateTime , userinfoId);;
		for (Leave leave : leavePendingRequest) {
			//System.out.println(leave);
			totalLeaveDays =totalLeaveDays + leave.getTotal_leave_days();
		}
		return totalLeaveDays;
	}

	public int calculateTotalWorkingHour(int userinfoId) {
		int totalWorkingHour = 0;
		String currentMonthString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
		String StartOfCurrentMonththString = currentMonthString.substring(0, 8).concat("01 00:00");

		// convert string to Localdatetime
		LocalDateTime currentMonththLocalDateTime = LocalDateTime.parse(StartOfCurrentMonththString,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime previousMonththLocalDateTime = currentMonththLocalDateTime.minusMonths(1);


		List<Attendance> attendenceList = attendanceDao.getAttendenceFromLastMonthToPresentMonth(
				currentMonththLocalDateTime, previousMonththLocalDateTime, userinfoId);
		//System.out.println(attendenceList.size());

		for (Attendance attendance : attendenceList) {
			//System.out.println(attendance);
			totalWorkingHour = totalWorkingHour + attendance.getWorkinghours();
		}
		
		
		return totalWorkingHour;
	}

}
