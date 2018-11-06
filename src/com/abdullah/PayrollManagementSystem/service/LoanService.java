package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.LoanDao;

@Service("loanService")
public class LoanService {
	private LoanDao loanDao;

	@Autowired
	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}

	public void postLeaveApplication(Loan loan) {
		loan.setPlacedate(LocalDateTime.now());
		System.out.println("Loan service layer : "+loan);
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
	

}
