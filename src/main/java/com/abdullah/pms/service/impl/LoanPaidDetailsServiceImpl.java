package com.abdullah.pms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.LoanPaidDetails;
import com.abdullah.pms.repository.LoanPaidDetailsRepository;
import com.abdullah.pms.service.LoanPaidDetailsService;
@Service
public class LoanPaidDetailsServiceImpl implements LoanPaidDetailsService{

	@Autowired
	LoanPaidDetailsRepository loanPaidDetailsRepository;
	@Override
	public List<LoanPaidDetails> findByLoan(Loan loan) {
		return loanPaidDetailsRepository.findByLoan(loan);
	}
	@Override
	public LoanPaidDetails save(LoanPaidDetails loanPaidDetails) {
		return loanPaidDetailsRepository.save(loanPaidDetails);
	}

}
