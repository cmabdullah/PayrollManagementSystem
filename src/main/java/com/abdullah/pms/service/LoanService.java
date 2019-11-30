package com.abdullah.pms.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.UserInfo;

public interface LoanService {

	void postLoanApplication(@Valid Loan loan, UserInfo userInfo);

	boolean isPandingRequest(UserInfo userInfo);

	List<Loan> getAllLoanRequests();

	Optional<Loan> findById(int id);

	Loan save(Loan loan);

	boolean isRunningLoan(UserInfo userInfo);

}
