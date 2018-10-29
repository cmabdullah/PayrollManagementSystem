package com.abdullah.PayrollManagementSystem.service;

import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;

@Service("leaveService")
public class LeaveService {
	private LeaveDao leaveDao;

	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}

	public void postLeaveApplication(Leave leave) {
		System.out.println("Leave service layer : "+leave);
		leaveDao.postLeaveApplication(leave);
		
	}
	
}
