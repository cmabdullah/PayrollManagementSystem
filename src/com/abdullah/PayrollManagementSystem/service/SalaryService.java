package com.abdullah.PayrollManagementSystem.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.controller.LoanController;
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
import com.abdullah.PayrollManagementSystem.messageBrokerService.SendMessageService;




@Service("salaryService")
public class SalaryService {
	private static Logger logger = Logger.getLogger(LoanController.class);

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
	
	@Autowired
	SendMessageService sendMessageService;
	
	public void setSendMessageService(SendMessageService sendMessageService) {
		this.sendMessageService = sendMessageService;
	}

	public void calculateSalary(String bonus) {
		List<Userinfo> userEnableInfo = userinfoDao.showAllEnabledUsers();
		List<Grade> grade = gradeDao.getAllGradeList();
		List<Salary> salary1 = new ArrayList<>();

		System.out.println(userEnableInfo.size());
		for (Userinfo userinfo : userEnableInfo) {
			//logger.info(userinfo);
			Salary salary = new Salary();
			int loanId = 0;
//			logger.info("your ID : "+userinfo.getId()+"total Working hour : "+calculateTotalWorkingHour(userinfo.getId()));
//			logger.info("your usedLeaveThisMonth : "+userinfo.getId()+" total  leave days : "+calculateTotalLeaveDays(userinfo.getId()));
//			logger.info("your Id  : "+userinfo.getId()+" your running loan id : "+checkRunningLoan(userinfo.getId()));
			
			salary.setUserinfo_id(userinfo.getId());
			salary.setMonthlyWorkingHour(calculateTotalWorkingHour(userinfo.getId()));
			salary.setTotalLeaveDays(calculateTotalLeaveDays(userinfo.getId()));
			loanId = checkRunningLoan(userinfo.getId());
			salary.setLoan_id(loanId);
			salary.setLoanStatus(loanId==0 ? false : true);
			salary.setUsertype(userinfo.getAuthority());
			salary.setGrade_id(userinfo.getGrade_id());
			salary.setDatemonthyear(LocalDateTime.now());
			salary.setJoiningDate(userinfo.getJoiningDate());
			salary.setBonus(bonus);
			
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
//				logger.info("Error");
			}
			salary1.add(salary);
		}
		
		
		 processLowLevelCalculation(salary1);


	}
	
	private void processLowLevelCalculation(List<Salary> salary) {
		
		List<Salary> AllUsersSalary = (List<Salary>) salary;
		List<Loan> payLoan = new ArrayList<>();
		List<Loan> paidLoanUpdateLoanId = new ArrayList<>();
		
		for (Salary salary2 : AllUsersSalary) {
			
			float basic = salary2.getBasic();
			float medicalallowence = (basic * 15)/100;
			float houserent = (basic * 50)/100;
			float transport = (basic * 10)/100;
			float lunch = (basic * 10)/100;
			float study = (basic * 25)/100;
			
			//increment salary year wise
			float totalsalary = basic + medicalallowence + houserent + transport + lunch + study ;
			//totalsalary=30030
//			logger.info("totalsalary "+totalsalary);
			
			
			try {
				if (salary2.getBonus().equals("give_bonus")) {
					totalsalary = totalsalary*2;
				}
			} catch (Exception e) {
				System.out.println("No bonus given");
			}
			
			
			
			float oneDaySalary = totalsalary/22;//15001.36364
//			logger.info("oneDaySalary : "+oneDaySalary);
			
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
			
			String loanStatusAndInstallment = payLoanEmi(salary2);
			salary2.setInstallment(Float.parseFloat(loanStatusAndInstallment.substring(1)));//return installment value substring fron index 1
			
			salary2.setIsLoanStatusPaid(Integer.parseInt(loanStatusAndInstallment.substring(0, 1)));
			
//			logger.info("Salary Datails "+salary2 );
		
			//String afterPayLonCurrentSalary = payLoanEmi(salary2);
			
		}//for loop end
/****		
INFO - salary Object : Salary [id=0, userinfo_id=2018, username=null, usertype=ROLE_ADMIN, status=null, fullname=null, address=null, email=null, phone=0, loan_id=1000, grade_id=1233, leaveusers_id=0, monthlyWorkingHour=100, totalLeaveDays=0, loanAmount=0, loanStatus=true, paidamount=0, regular=null, bonus=null, totalsalary=37800.0, datemonthyear=2018-11-16T16:19:02.498, basic=22000, medicalallowence=15, houserent=50, transport=10, lunch=10, study=25, installment=1000.0]
INFO - salary Object : Salary [id=0, userinfo_id=2026, username=null, usertype=ROLE_ADMIN, status=null, fullname=null, address=null, email=null, phone=0, loan_id=0, grade_id=1233, leaveusers_id=0, monthlyWorkingHour=0, totalLeaveDays=0, loanAmount=0, loanStatus=false, paidamount=0, regular=null, bonus=null, totalsalary=0.0, datemonthyear=2018-11-16T16:19:02.502, basic=22000, medicalallowence=15, houserent=50, transport=10, lunch=10, study=25, installment=0.0]
INFO - salary Object : Salary [id=0, userinfo_id=2027, username=null, usertype=ROLE_ACCOUNTANT, status=null, fullname=null, address=null, email=null, phone=0, loan_id=0, grade_id=1235, leaveusers_id=0, monthlyWorkingHour=111, totalLeaveDays=0, loanAmount=0, loanStatus=false, paidamount=0, regular=null, bonus=null, totalsalary=29925.0, datemonthyear=2018-11-16T16:19:02.507, basic=16500, medicalallowence=15, houserent=50, transport=10, lunch=10, study=25, installment=0.0]
INFO - salary Object : Salary [id=0, userinfo_id=2028, username=null, usertype=ROLE_EMPLOYEE, status=null, fullname=null, address=null, email=null, phone=0, loan_id=1001, grade_id=1234, leaveusers_id=0, monthlyWorkingHour=96, totalLeaveDays=2, loanAmount=0, loanStatus=true, paidamount=0, regular=null, bonus=null, totalsalary=24570.0, datemonthyear=2018-11-16T16:19:02.515, basic=14300, medicalallowence=15, houserent=50, transport=10, lunch=10, study=25, installment=1228.5]
*/		
		
		for (Salary salary3 : AllUsersSalary) {
			logger.info("salary Object : "+salary3);
			if(salary3.isLoanStatus() == true && salary3.getIsLoanStatusPaid() == 1) {
				Loan loan = new Loan();
				loan.setPaidamount(salary3.getInstallment());
				loan.setLoan_id(salary3.getLoan_id());
				loan.setDatetime(LocalDateTime.now());
				payLoan.add(loan);
				
			} 
			if(salary3.isLoanStatus() == true && salary3.getIsLoanStatusPaid() == 2) {
				
				Loan loan = new Loan();
				loan.setPaidamount(salary3.getInstallment());
				loan.setLoan_id(salary3.getLoan_id());
				loan.setDatetime(LocalDateTime.now());
				loan.setStatus(salary3.getIsLoanStatusPaid());
				payLoan.add(loan);
				paidLoanUpdateLoanId.add(loan);
			}
		}
		
//		for (Loan loan : payLoan) {
//			logger.info("Loan Details : "+loan);
//		}
		
		for (Loan loan : paidLoanUpdateLoanId) {
			loanDao.updateLoanStatusBasedOnLoanId(loan);
			logger.info("paid Loan Details : "+loan);
		}
		
		//perform loan dao operation
		loanDao.payLoanInstallment(payLoan);
		
		//perform transection
		salaryDao.giveSalary(AllUsersSalary);
		
//		for (Salary salary4 : AllUsersSalary) {
//			System.out.println("AllUsersSalary :: "+salary4);
//			// queue name userinfo_id=2029
//			//totalsalary not equals zero
//			if (salary4.getTotalsalary() > 0) {
//				//System.out.println(salary4.getUserinfo_id());
//				sendMessageService.postSalaryGivenMessage(salary4.getTotalsalary(), salary4.getUserinfo_id());
//			}
//			
//		}
		//send notification
		//labda expression
		AllUsersSalary.stream().parallel().forEach(salary5 -> {
            if (salary5.getTotalsalary() > 0) {
            	sendMessageService.postSalaryGivenMessage(salary5.getTotalsalary(), salary5.getUserinfo_id());
            }
        });
		
	}

	private String payLoanEmi(Salary salary2) {
		
		int loan_id = salary2.getLoan_id();//1000
		float salary = salary2.getTotalsalary();
		float installment = 0;//kisti
		float checkLoanAmountIsLessThenOrGraterThen = 0;
		float extraMoney = 0;
		int loanStatus = 1;
//		
//		logger.info("salary2 object inside payLoanEmi Method : "+ salary2);
//		logger.info("Get Salary : "+ salary2.getTotalsalary());
		
		Loan loan =  loanDao.getLoanAmountBasedOnLoanID(loan_id);
		float loanAmountBasedOnLoanId = loan.getAmount();
		
//		logger.info("loanAmountBasedOnLoanId : "+loanAmountBasedOnLoanId);
		
		List<Loan> loanpaiddetails = loanDao.getLoanPaidDetailsBasedOnLoanId(loan_id);
		
		float paidAmount = 0;
		for (Loan loan2 : loanpaiddetails) {
			paidAmount = paidAmount + loan2.getPaidamount();
//			logger.info("your paid amount ::::::: "+ loan2);
		}
		
//		logger.info(" your loanAmountBasedOnLoanId "+loanAmountBasedOnLoanId + " Your paidAmount : " +paidAmount + "Your Salary " + salary);
		
		if(loanAmountBasedOnLoanId > paidAmount) {
			//81000 80000
			installment = (salary*5)/100 ; //5% 10_000
			
			checkLoanAmountIsLessThenOrGraterThen = paidAmount + installment; //90_000
			if (loanAmountBasedOnLoanId <= checkLoanAmountIsLessThenOrGraterThen) {
				extraMoney = checkLoanAmountIsLessThenOrGraterThen - loanAmountBasedOnLoanId;//9000
				installment = (checkLoanAmountIsLessThenOrGraterThen - extraMoney) - paidAmount ; //81_000 bdt
				loanStatus = 2;
			}
		} 
//		else {
//			installment = (salary*5)/100 ; //5% 10_000
//		}
		
//		logger.info(" installment :::: "+installment);
		
		
		
		return String.valueOf(loanStatus)+String.valueOf(installment);
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

	public List<Salary> getAllSalaryBetween(LocalDate entryfrom, LocalDate entryto) {
		List<Salary> salaryBetween = salaryDao.getAllAttendanceBetween(entryfrom, entryto);
		return salaryBetween;
	}

	public List<Salary> getAllSalaryBetween(LocalDate entryfrom, LocalDate entryto , int userId) {
		List<Salary> salaryBetween = salaryDao.getAllSalaryBetween(entryfrom, entryto , userId);
		return salaryBetween;
	}

}
