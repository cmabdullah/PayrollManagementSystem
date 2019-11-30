package com.abdullah.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.UserInfo;

public interface LoanRepository extends JpaRepository<Loan, Integer>{

	@Query("SELECT a FROM Loan a WHERE a.status = :status AND a.userInfo = :userInfo")
    List<Loan> loanInfo(@Param("status") int status, @Param("userInfo") UserInfo userInfo );

	List<Loan> findByStatus(int i);
	
}
