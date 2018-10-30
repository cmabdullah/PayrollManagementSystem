package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;

@Service("leaveService")
public class LeaveService {
	private LeaveDao leaveDao;
	@Autowired
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}

	public void postLeaveApplication(Leave leave) {
		System.out.println("Leave service layer : "+leave);
		
		leaveDao.postLeaveApplication(leave);
		
	}
	
}
