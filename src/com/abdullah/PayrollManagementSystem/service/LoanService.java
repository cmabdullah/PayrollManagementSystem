package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		Userinfo  userinfo = salaryDao.getUserGradeId(loan.getUserinfo_id());
		
		Salary salary = salaryDao.getUserInfoWithGradeInfo(userinfo.getGrade_id());
		
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
		loanDao.deletePendingLoanApplication(id);
	}
}
