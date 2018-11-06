package com.abdullah.PayrollManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.LoanDao;

@Service("loanService")
public class LoanService {
	private LoanDao loanDao;

	@Autowired
	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}
	

}
