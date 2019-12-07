package com.abdullah.pms.service;

import java.util.List;

import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.LoanPaidDetails;

public interface LoanPaidDetailsService {

	List<LoanPaidDetails> findByLoan(Loan loan);

	LoanPaidDetails save(LoanPaidDetails loanPaidDetails);

}
