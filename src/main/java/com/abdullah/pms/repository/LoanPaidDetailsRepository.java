package com.abdullah.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.LoanPaidDetails;

public interface LoanPaidDetailsRepository extends JpaRepository<LoanPaidDetails, Integer>{

	List<LoanPaidDetails> findByLoan(Loan loan);

}
