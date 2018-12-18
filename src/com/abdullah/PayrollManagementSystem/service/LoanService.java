package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.LoanDao;
import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.abdullah.PayrollManagementSystem.dao.SalaryDao;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoDao;

@Service("loanService")
public class LoanService {
	private LoanDao loanDao;

	@Autowired
	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}
	private SalaryDao salaryDao;

	@Autowired
	public void setSalaryDao(SalaryDao salaryDao) {
		this.salaryDao = salaryDao;
	}


	public void postLeaveApplication(Loan loan) {
		loan.setPlacedate(LocalDateTime.now());
		System.out.println("Loan service layer : "+loan);
		
//		Userinfo  userinfo = salaryDao.getUserGradeId(loan.getUserinfo_id());
//		System.out.println("Service layer userinfo object "+ userinfo);
		Salary salary = salaryDao.getUserInfoWithGradeInfo(loan.getUserinfo_id());
		
		float basic = salary.getBasic();
		float medicalallowence = (basic * 15)/100;
		float houserent = (basic * 50)/100;
		float transport = (basic * 10)/100;
		float lunch = (basic * 10)/100;
		float study = (basic * 25)/100;
		
		//increment salary year wise
		float totalsalary = basic + medicalallowence + houserent + transport + lunch + study ;
		
		System.out.println("totalsalary :: "+totalsalary);
		
		
		
		
		if (loan.getLoanType().equals("give_business")) {
			loan.setAmount(totalsalary*6);
		} else if(loan.getLoanType().equals("give_study")) {
			loan.setAmount(totalsalary*4);
		}else if(loan.getLoanType().equals("give_medical")){
			loan.setAmount(totalsalary*3);
		}
		
		//Loan [id=0, placedate=null, approvedate=null, amount=0.0, amountValidation=null, status=0, userinfo_id=2028, reason=geggb, lpid=0, datetime=null, paidamount=0.0, loan_id=0, loanType=give_study]
		
		
		loanDao.postLoanApplication(loan);
		
	}

	public boolean isPandingLoanRequest(int userinfo_id) {
		List<Loan> loan = loanDao.checkPandingLoanRequest(userinfo_id);
		if(loan.size() == 0)
			return false;
		
		
		if(loan.size() != 0) {
			System.out.println("LAst leave request element "+loan.get(loan.size()-1));
    	}
		
		return true;
	}

	public List<Loan> getAllPendingRequests() {
		return loanDao.getAllLoanPendingRequests();
	}

	public void deletePendingLoanApplication(int id) {
		Loan loan = new Loan();
		int status = 3;//rejection flag is 3
		loan.setId(id);
		loan.setStatus(status);
		loan.setApprovedate(LocalDateTime.now());//keep data though this application is reject
		loanDao.deletePendingLoanApplication(loan);
	}


	public void acceptPendingLoanApplication(int id) {
		Loan loan = new Loan();
		int status = 1; //accept flag is 1
		loan.setId(id);
		loan.setStatus(status);
		loan.setApprovedate(LocalDateTime.now());
		System.out.println("Loan Accept Service layer loan object : "+loan);
		loanDao.acceptPendingLoanApplication(loan);
		
	}


	public boolean isRunningLoan(int userinfo_id) {
		List<Loan> loan = loanDao.checkRunningLoan(userinfo_id);
		if(loan.size() == 0)
			return false;
		
		
		if(loan.size() != 0) {
			System.out.println("LAst loan element "+loan.get(loan.size()-1));
    	}
		
		return true;
	}


	public Loan getRunningLoanInformations(int userinfo_id) {
		List<Loan> loanList = loanDao.checkRunningLoanDetails(userinfo_id);
		float paidAmount = 0;
		Loan loan = new Loan();
		for (Loan loan2 : loanList) {
			paidAmount = paidAmount + loan2.getPaidamount();
			loan.setAmount(loan2.getAmount());
		}
		
		loan.setPaidamount(paidAmount);
		return loan;
	}


	public List<Loan> getAllLoanBetween(LocalDate entryfrom, LocalDate entryto) {
		List<Loan> loanBetweenBetween = loanDao.getAllLoanBetween(entryfrom, entryto);
		return loanBetweenBetween;
	}


	public List<Loan> getAllLoanBetween(LocalDate entryfrom, LocalDate entryto, int userId) {
		List<Loan> loanBetweenBetween = loanDao.getAllLoanBetween(entryfrom, entryto , userId);
		return loanBetweenBetween;
	}
}
