package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
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

	public void performADRequest(Leave leave) {
		
		
	}

	public boolean isPandingRequest(int userinfo_id) {
		// TODO Auto-generated method stub
		
		
		
		List<Leave> leave = leaveDao.checkPandingLeaveRequest(userinfo_id);
		if(leave.size() == 0)
			return false;
		
		
		if(leave.size() != 0) {
			System.out.println("LAst leave request element "+leave.get(leave.size()-1));
    	}
		
		return true;
	}
	
}
