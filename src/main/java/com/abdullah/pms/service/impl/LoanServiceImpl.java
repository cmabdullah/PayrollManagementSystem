package com.abdullah.pms.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.LoanRepository;
import com.abdullah.pms.service.GradeService;
import com.abdullah.pms.service.LoanService;

@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	GradeService gradeService;
	
	//Business loan=salary*6, medical loan = salary*3, study loan=salary*2
	@Override
	public void postLoanApplication(@Valid Loan loan, UserInfo userInfo) {
		loan.setUserInfo(userInfo);
		loan.setStatus(0);
		loan.setPlaceDate(new Date());
		
		if (loan.getLoanType().equals("business")) {
			//salary*6
		loan.setAmount(userInfo.getGrade().getBasic() * 6);
			//userInfo.getGrade().getBasic() * 6;
		}
		else if (loan.getLoanType().equals("study")) {
			//salary*3
			loan.setAmount(userInfo.getGrade().getBasic() * 3);
		}
		else if (loan.getLoanType().equals("study")) {
			//salary*2
			loan.setAmount(userInfo.getGrade().getBasic() * 2);
		}
		loanRepository.save(loan);
	}

	@Override
	public boolean isPandingRequest(UserInfo userInfo) {
		int status = 0;// for pending status
		Optional<Loan> loan = loanRepository.loanInfo(status, userInfo).stream().findAny();
		return loan.isPresent();
	}

	@Override
	public List<Loan> getAllLoanRequests() {
		
		//pending loan status = 0
		List<Loan> leaveList = loanRepository.findByStatus(0);
		return leaveList;
	}

	@Override
	public Optional<Loan> findById(int id) {
		return loanRepository.findById(id);
	}

	@Override
	public Loan save(Loan loan) {
		return loanRepository.save(loan);
	}

	@Override
	public boolean isRunningLoan(UserInfo userInfo) {
		int status = 1;// for running loan status
		Optional<Loan> loan = loanRepository.loanInfo(status, userInfo).stream().findAny();
		return loan.isPresent();
	}
}
