package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.controller.LoanController;
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
		Logger logger = Logger.getLogger(LoanController.class);
		int total_leave_days = 1;
		LocalDateTime temp = leave.getEntryfrom();
		//(loginTime.plusHours(8).isBefore(defultLogoutTime)
		while(leave.getEntryfrom().isBefore(leave.getEntryto())) {
			total_leave_days++;
			leave.setEntryfrom(leave.getEntryfrom().plusDays(1));
			System.out.println(leave.getEntryfrom());
		}
		System.out.println(total_leave_days);
		leave.setEntryfrom(temp);
		leave.setTotal_leave_days(total_leave_days);
		logger.info("post Leave Request : "+leave);
		leaveDao.postLeaveApplication(leave);
	}

	public void performADRequest(Leave leave) {
		
		int total_leave_days = 1;
		
		LocalDateTime temp = leave.getEntryfrom();
		
		//(loginTime.plusHours(8).isBefore(defultLogoutTime)
		while(leave.getEntryfrom().isBefore(leave.getEntryto())) {
			total_leave_days++;
			
			leave.setEntryfrom(leave.getEntryfrom().plusDays(1));
			
			System.out.println(leave.getEntryfrom());
		}
		System.out.println(total_leave_days);
		
		leave.setEntryfrom(temp);
		leave.setTotal_leave_days(total_leave_days);
		
		System.out.println("performADRequest : "+leave);

		leaveDao.confirmPendingLeaveApplication(leave);
	}

	public boolean isPandingRequest(int userinfo_id) {
		
		
		List<Leave> leave = leaveDao.checkPandingLeaveRequest(userinfo_id);
		if(leave.size() == 0)
			return false;
		
		
		if(leave.size() != 0) {
			System.out.println("LAst leave request element "+leave.get(leave.size()-1));
    	}
		
		return true;
	}

	public int getLeaveApplicationIdBasedOnUserinfo_id(int userinfo_id) {
		Leave leave = leaveDao.getLeaveApplicationIdBasedOnUserinfo_id(userinfo_id);
		return leave.getId();
	}

	public List<Leave> getAllLeaveRequests() {
		
		List<Leave> leavePendingRequest = leaveDao.getAllLeaveRequests();
		
//			for (Leave leave : leavePendingRequest) {
//				System.out.println(leave.getUserinfo_id());
//			}
		
		return leavePendingRequest;
	}

	public void ignorePendingApplicationId(int id) {
		leaveDao.ignorePendingApplicationId(id);
		
	}

	public void acceptPendingApplicationId(int id) {
		leaveDao.acceptPendingApplicationId(id);
		
	}
}
