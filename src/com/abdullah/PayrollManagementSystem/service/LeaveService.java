package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
		List<Leave> leavePendingRequestSortList = new ArrayList<>();//filter leave request
//		for (Leave leave : leavePendingRequest) {
//			System.out.println(leave);
//		}
		int j = 0;
		for (int i = 0 ; i<leavePendingRequest.size() ; i++) {
			//System.out.println(leavePendingRequest.get(i));
			int x = 0;
			if (leavePendingRequest.get(i).getStatus() == 0) {
				x = count(leavePendingRequest.get(i).getUserinfo_id(), leavePendingRequest);
				leavePendingRequest.get(i).setDeniedLeaveRequest(x);
				leavePendingRequestSortList.add(leavePendingRequest.get(i));
			}
				//System.out.println(x);
			//leavePendingRequest.get(i).setDeniedLeaveRequest(x);
		}
		return leavePendingRequestSortList;
	}
	
	public static int count(int id, List<Leave> leavePendingRequest ) {
		int counter = 0;
		for (int i = 0 ; i<leavePendingRequest.size() ; i++) {
			//System.out.println(leavePendingRequest.get(i));
			if ((id == leavePendingRequest.get(i).getUserinfo_id()) && leavePendingRequest.get(i).getStatus() == 2)
				counter++;
		}
		return counter;
	}

	public void ignorePendingApplicationId(int id) {
		leaveDao.ignorePendingApplicationId(id);
		
	}

	public void acceptPendingApplicationId(int id) {
		leaveDao.acceptPendingApplicationId(id);
		
	}

	public boolean isLeaveRequestOutOfLimit(int userinfo_id) {
		
		List<Leave> leavePendingRequestOutOfLimit = leaveDao.isLeaveRequestOutOfLimit(2020);
		
		int count = 0;
		if(leavePendingRequestOutOfLimit.size() != 0) {
			for(int i = 0 ;i<leavePendingRequestOutOfLimit.size() ; i++ ) {
				count = count + leavePendingRequestOutOfLimit.get(i).getTotal_leave_days();
			}
			System.out.println("Count : "+count);
    	}
		System.out.println("leave size : "+leavePendingRequestOutOfLimit.size());
		
		if(count > 2)
			return true;
		else
			return false;
	}

	public List<Leave> getLeaveStatusGroupBy() {
		
		List<Leave> leaveStatusGroupBy = leaveDao.getLeaveStatusGroupBy();
		System.out.println(leaveStatusGroupBy.size());
		return leaveStatusGroupBy;
	}

	public List<Leave> getLeaveStatusGroupByLeavetype(String leavetype) {
		List<Leave> leaveStatusGroupByLeavetype = leaveDao.getLeaveStatusGroupByLeavetype(leavetype);
		System.out.println(leaveStatusGroupByLeavetype.size());
		return leaveStatusGroupByLeavetype;
	}
}
