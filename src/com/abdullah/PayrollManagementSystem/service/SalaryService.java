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
import com.abdullah.PayrollManagementSystem.dao.Grade;
import com.abdullah.PayrollManagementSystem.dao.GradeDao;
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
	
	
	private GradeDao gradeDao;

	@Autowired
	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}

	public void calculateSalary(String bonus) {
		List<Userinfo> userEnableInfo = userinfoDao.showAllEnabledUsers();
		List<Grade> grade = gradeDao.getAllGradeList();
		List<Salary> salary1 = new ArrayList<>();
		
		
//		for (Grade grade1 : grade) {
//			System.out.println("Grade Object : "+grade1);
//		}
		
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
			salary.setGrade_id(userinfo.getGrade_id());
			salary.setDatemonthyear(LocalDateTime.now());
			
			if(userinfo.getAuthority().equals("ROLE_ADMIN")) {
				salary.setBasic(grade.get(1).getBasic());
				salary.setMedicalallowence(grade.get(1).getMedicalallowence());
				salary.setHouserent(grade.get(1).getHouserent());
				salary.setTransport(grade.get(1).getTransport());
				salary.setLunch(grade.get(1).getLunch());
				salary.setStudy(grade.get(1).getStudy());
			} else if(userinfo.getAuthority().equals("ROLE_ACCOUNTANT")) {
				salary.setBasic(grade.get(2).getBasic());
				salary.setMedicalallowence(grade.get(2).getMedicalallowence());
				salary.setHouserent(grade.get(2).getHouserent());
				salary.setTransport(grade.get(2).getTransport());
				salary.setLunch(grade.get(2).getLunch());
				salary.setStudy(grade.get(2).getStudy());
			} else if (userinfo.getAuthority().equals("ROLE_EMPLOYEE")) {
				salary.setBasic(grade.get(3).getBasic());
				salary.setMedicalallowence(grade.get(3).getMedicalallowence());
				salary.setHouserent(grade.get(3).getHouserent());
				salary.setTransport(grade.get(3).getTransport());
				salary.setLunch(grade.get(3).getLunch());
				salary.setStudy(grade.get(3).getStudy());
			} else {
				System.out.println("Error");
			}
			
			System.out.println("Salary Object : "+salary);
			salary1.add(salary);
			
		}
		
//		System.out.println("Salary size: "+salary1.size());
//		
//		
//		for (Salary salary2 : salary1) {
//			System.out.println("salary1 Object : "+salary2);
//		}
		
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
			
			float basic = salary2.getBasic();
			float medicalallowence = (basic * 15)/100;
			float houserent = (basic * 50)/100;
			float transport = (basic * 10)/100;
			float lunch = (basic * 10)/100;
			float study = (basic * 25)/100;
			float totalsalary = basic + medicalallowence + houserent + transport + lunch + study ;
			//totalsalary=30030
			System.out.println("totalsalary "+totalsalary);
			
			float oneDaySalary = totalsalary/22;//15001.36364
			System.out.println("oneDaySalary : "+oneDaySalary);
			
			if(salary2.getTotalLeaveDays() ==1) {
				salary2.setMonthlyWorkingHour(salary2.getMonthlyWorkingHour() + 8);
			} else if(salary2.getTotalLeaveDays() ==2) {
				salary2.setMonthlyWorkingHour(salary2.getMonthlyWorkingHour() + 16);
			}
			
			//working hour ,constant
			if(salary2.getMonthlyWorkingHour() >= 178) {
				salary2.setTotalsalary(totalsalary);
			}else if (salary2.getMonthlyWorkingHour() > 154) {
				salary2.setTotalsalary(totalsalary-oneDaySalary);
				//3 d
			}else if( salary2.getMonthlyWorkingHour() > 130) {
				salary2.setTotalsalary(totalsalary-oneDaySalary*2);
				//6 d
			}else if (salary2.getMonthlyWorkingHour() > 106) {
				salary2.setTotalsalary(totalsalary-oneDaySalary*3);
				//9d
			}else if(salary2.getMonthlyWorkingHour() > 82) {
				salary2.setTotalsalary(totalsalary-oneDaySalary*4);
				//12d
			}else if(salary2.getMonthlyWorkingHour() > 58) {
				salary2.setTotalsalary(totalsalary-oneDaySalary*5);
				//15d
			} else {
				salary2.setTotalsalary(totalsalary*0);
			}
			System.out.println("Salary Datails "+salary2 );
		}
		
		
		
		//perform transection
		salaryDao.giveSalary(AllUsersSalary);
		
		//Salary Datails Salary [id=0, userinfo_id=2028, username=null, usertype=ROLE_EMPLOYEE, status=null, fullname=null, address=null, email=null, phone=0, loan_id=0, grade_id=1234, leaveusers_id=0, monthlyWorkingHour=96, totalLeaveDays=2, loanAmount=0, loanStatus=false, paidamount=0, regular=null, bonus=null, totalsalary=24570.0, basic=14300, medicalallowence=15, houserent=50, transport=10, lunch=10, study=25]
		//178 hour total job
		//basic 2200
		//Salary Datails Salary [id=0, userinfo_id=2028, username=null, usertype=ROLE_EMPLOYEE, status=null, fullname=null, address=null, email=null, phone=0, loan_id=0, grade_id=1234, leaveusers_id=0, monthlyWorkingHour=50, totalLeaveDays=8, loanAmount=0, loanStatus=false, paidamount=0, regular=null, bonus=null, totalsalary=0, basic=14300, medicalallowence=15, houserent=50, transport=10, lunch=10, study=25]
		
		
		
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
