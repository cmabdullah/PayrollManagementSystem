package com.abdullah.pms.cash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.cash.repository.RedisRepository;
import com.abdullah.pms.cash.service.MessageService;
import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.Loan;
import com.abdullah.pms.domain.Salary;
import com.abdullah.pms.domain.UserInfo;

@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	RedisRepository redisRepository;

	@Override
	public void postLeaveAcceptionMessage(Leave leave) {
		
		//void put(H key, HK hashKey, HV value);
		String message = "Hi " + leave.getUserInfo().getFullname() + " your leave request " + leave.getEntryFrom() + " to "
				+ leave.getEntryTo() + " has been accept";
		String queueName = String.valueOf(leave.getUserInfo().getId());
		String mapkey = "pendingLeave";
		redisRepository.add(mapkey, queueName, message );
		
	}
	
	@Override
	public void postLeaveRejectionMessage(Leave leave) {
		//void put(H key, HK hashKey, HV value);
				String message = "Hi " + leave.getUserInfo().getFullname() + " your leave request " + leave.getEntryFrom() + " to "
						+ leave.getEntryTo() + " has been rejected";
				String queueName = String.valueOf(leave.getUserInfo().getId());
				String mapkey = "pendingLeave";
				redisRepository.add(mapkey, queueName, message );
	}
	
	@Override
	public String getPendingLeaveMessage(UserInfo userInfo, String mapkey) {
		return redisRepository.findByKey( mapkey, String.valueOf(userInfo.getId()));
	}
	
	@Override
	public void givePermissionToPayBonus() {
		String message = "give bonus";
		String queueName = "isApprovedByAdmin";
		String mapkey = "pendingBonus";
		redisRepository.add( mapkey,queueName, message);
		
	}
	@Override
	public void givePermissionToPaySalary() {
		String message = "give salary";
		String queueName = "isApprovedByAdmin";
		String mapkey = "pendingSalary";
		redisRepository.add( mapkey,queueName, message);
	}
	@Override
	public String isMenagerPermissionGiven(String string) {
		String queueName = "isApprovedByAdmin";
		String mapkey = "pendingSalary";
		if (string.equals("y")) {
			// !dequeue
			return redisRepository.findByKey( mapkey, queueName);
		} if (string.equals("n")) {
			// dequeue
			redisRepository.delete( mapkey, queueName);
			return "pendingSalaryDeleted";
		} else {
			return "Sorry"; //mock return
		}
	}
	@Override
	public String isMenagerBonusPermissionGiven(String string) {
		// just message class
				String queueName = "isApprovedByAdmin";
				String mapkey = "pendingBonus";
				if (string.equals("y")) {
					// !dequeue
					return redisRepository.findByKey( mapkey, queueName);
				} if (string.equals("n")) {
					// dequeue
					redisRepository.delete( mapkey, queueName);
					return "pendingbonusDeleted";
				} else {
					return "Sorry"; //mock return
				}
	}
	
	@Override
	public void postLoanAcceptionMessage(Loan loan) {
		String message = "Hi " + loan.getUserInfo().getFullname() + " your leave request has been accept";
		String queueName = String.valueOf(loan.getUserInfo().getId());
		String mapkey = "pendingLoan";
		redisRepository.add(mapkey, queueName, message );
		
	}
	@Override
	public void postLoanRejectionMessage(Loan loan) {
		String message = "Hi " + loan.getUserInfo().getFullname() + " your leave request has been rejected";String queueName = String.valueOf(loan.getUserInfo().getId());
		String mapkey = "pendingLoan";
		redisRepository.add(mapkey, queueName, message );
		
	}
	
	@Override
	public String getPendingLoanMessage(UserInfo userInfo, String mapkey) {
		return redisRepository.findByKey( mapkey, String.valueOf(userInfo.getId()));
	}

	@Override
	public String getPendingSalaryMessage(UserInfo userInfo, String mapkey) {
		return redisRepository.findByKey( mapkey, String.valueOf(userInfo.getId()));
	}

	@Override
	public void postSalaryMessage(Salary savedSalary) {
		String message = "Hi " + savedSalary.getUserInfo().getFullname() + " your salary has been given ";
		String queueName = String.valueOf(savedSalary.getUserInfo().getId());
		String mapkey = "pendingSalary";
		redisRepository.add(mapkey, queueName, message );
		
	}
	
	
	@Override
	public void delete(String queueName, String mapkey) {
		redisRepository.delete( mapkey, queueName);
	}
	

}
