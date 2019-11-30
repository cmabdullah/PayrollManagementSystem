package com.abdullah.pms.service.impl;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Leave;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.LeaveRepository;
import com.abdullah.pms.service.LeaveService;

@Service
public class LeaveServiceImpl implements LeaveService{

	@Autowired
	LeaveRepository leaveRepository;
	
	@Override
	public Leave postLeaveApplication(@Valid Leave leave, LocalDate entryFrom, LocalDate entryTo , UserInfo userInfo) {
		leave.setStatus(0);
		leave.setUserInfo(userInfo);
		
		int totalLeaveDays = 1;
		while(entryFrom.isBefore(entryTo)) {
			System.out.println("FFFF");
			totalLeaveDays++;
			entryFrom = entryFrom.plusDays(1);
			System.out.println(entryFrom + " : "+ entryTo);
		}
		leave.setTotalLeaveDays(totalLeaveDays);
		return leaveRepository.save(leave);
	}
	
	

}
