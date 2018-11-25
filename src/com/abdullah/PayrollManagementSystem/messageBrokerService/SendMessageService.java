package com.abdullah.PayrollManagementSystem.messageBrokerService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.controller.LoanController;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;
import com.abdullah.PayrollManagementSystem.dao.LeaveMessage;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.LoanDao;
import com.abdullah.PayrollManagementSystem.dao.MessageDao;

@Service("sendMessageService")
public class SendMessageService {
	private static Logger logger = Logger.getLogger(LoanController.class);
	
	private LeaveDao leaveDao;
	@Autowired
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}
	
	private MessageDao messageDao;
	
	
	@Autowired
	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
	private LoanDao loanDao;

	@Autowired
	public void setLoanDao(LoanDao loanDao) {
		this.loanDao = loanDao;
	}

	public void postLeaveRejectionMessage(int id) throws IOException, Exception {
		Leave leave = leaveDao.getLeaveApplicationInfoBasedOnLeaveId(id);
		logger.info("Showing retrive leave info for perform messaging operation"+leave);
		
		String message = "Hi "+leave.getFullname()+ " your leave request "+leave.getEntryfrom() + " to "+ leave.getEntryto() +" has been rejeced";
		String queueName = String.valueOf(leave.getUserinfo_id());
		String mapkey = "pendingleave";
		//pushMessageToQueue(queueName, message);
		//pushMessageToRedisQueue(queueName, message);
		messageDao.pushMessageToRedisQueue(queueName,mapkey, message);
		//INFO - Showing retrive leave info for perform messaging operationLeave [id=32, reasone=,m/,.ml, entryfrom=2018-11-01T00:00, entryto=2018-11-16T00:00, userinfo_id=2020, status=2, leavetype=regular, entryfromString=null, entrytoString=null, total_leave_days=16, fullname=MR Bin, email=bin@gmail.com, deniedLeaveRequest=0]
	}
	public String getPendingLeaveMessage(String queueName) throws IOException, TimeoutException {
		LeaveMessage leaveMessage = new LeaveMessage();
		String mapkey = "pendingleave";
		
		//String message = getMessagefromQueue(queueName, leaveMessage);
		String redisMessage = messageDao.getRedisMessagefromQueue(queueName,mapkey, leaveMessage);
		
		return redisMessage;
	}

	public void postLeaveAcceptionMessage(int id) {
		Leave leave = leaveDao.getLeaveApplicationInfoBasedOnLeaveId(id);
		logger.info("Showing retrive leave info for perform messaging operation"+leave);
		
		String message = "Hi "+leave.getFullname()+ " your leave request "+leave.getEntryfrom() + " to "+ leave.getEntryto() +" has been accept";
		String queueName = String.valueOf(leave.getUserinfo_id());
		String mapkey = "pendingleave";
		//pushMessageToQueue(queueName, message);
		//pushMessageToRedisQueue(queueName, message);
		messageDao.pushMessageToRedisQueue(queueName,mapkey, message);
		
	}

	public void postLoanRejectionMessage(int id) {
		Loan loan = loanDao.getLoanApplicationInfoBasedOnLoanId(id);
		
		String message = "Hi you request "+loan.getAmount()+ " BDT for loan, but we cannot accept at this moment";
		String queueName = String.valueOf(loan.getUserinfo_id());
		String mapkey = "pendingloan";
		messageDao.pushMessageToRedisQueue(queueName,mapkey, message);
		
	}
	public void postLoanAcceptionMessage(int id) {
		Loan loan = loanDao.getLoanApplicationInfoBasedOnLoanId(id);
		
		String message = "Hi you request "+loan.getAmount()+ " BDT for loan, but we accept your request";
		String queueName = String.valueOf(loan.getUserinfo_id());
		String mapkey = "pendingloan";
		messageDao.pushMessageToRedisQueue(queueName,mapkey, message);
		
	}
	
	public String getPendingLoanMessage(String queueName) throws IOException, TimeoutException {
		//just message class
		LeaveMessage leaveMessage = new LeaveMessage();
		String mapkey = "pendingloan";
		
		//String message = getMessagefromQueue(queueName, leaveMessage);
		String redisMessage = messageDao.getRedisMessagefromQueue(queueName,mapkey, leaveMessage);
		
		return redisMessage;
	}

}
