package com.abdullah.pms.cash.service;

import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.Salary;
import com.abdullah.pms.domain.UserInfo;

public interface MessageService {

	void postLeaveAcceptionMessage(Leave leavesaveRes);

	String getPendingLeaveMessage(UserInfo userInfo, String mapkey);

	void givePermissionToPayBonus();

	void givePermissionToPaySalary();

	String isMenagerPermissionGiven(String string);

	String isMenagerBonusPermissionGiven(String string);

	void postLeaveRejectionMessage(Leave leavesaveRes);

	void postLoanAcceptionMessage(Loan loanSaveRes);

	void postLoanRejectionMessage(Loan loanSaveRes);
	
	String getPendingLoanMessage(UserInfo userInfo, String mapkey);

	String getPendingSalaryMessage(UserInfo userInfo, String mapkey);

	void postSalaryMessage(Salary savedSalary);
	
	public void delete(String queueName, String mapkey);

}
