package com.abdullah.PayrollManagementSystem.messageBrokerService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.controller.LoanController;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service("sendMessageService")
public class SendMessageService {
	private static Logger logger = Logger.getLogger(LoanController.class);
	
	private LeaveDao leaveDao;
	@Autowired
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}
	
	public void postLeaveConfirmationMessage(int id) throws IOException, Exception {
		Leave leave = leaveDao.getLeaveApplicationInfoBasedOnLeaveId(id);
		logger.info("Showing retrive leave info for perform messaging operation"+leave);
		
		String message = "Hi "+leave.getFullname()+ " You got Leave";
		String queueName = String.valueOf(leave.getUserinfo_id());
		//pushMessageToQueue(queueName, message);
		//INFO - Showing retrive leave info for perform messaging operationLeave [id=32, reasone=,m/,.ml, entryfrom=2018-11-01T00:00, entryto=2018-11-16T00:00, userinfo_id=2020, status=2, leavetype=regular, entryfromString=null, entrytoString=null, total_leave_days=16, fullname=MR Bin, email=bin@gmail.com, deniedLeaveRequest=0]
	}
	
	private void pushMessageToQueue(String queueName, String message) throws Exception, IOException {
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setUsername("guest");
	    factory.setPassword("guest");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(queueName, false, false, false, null);
	    //String message = "Hello World!";
	    channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
	    System.out.println(" [x] Sent '" + message + "'");

	    channel.close();
	    connection.close();
	}

}
