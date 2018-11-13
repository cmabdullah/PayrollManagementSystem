package com.abdullah.PayrollManagementSystem.messageBrokerService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.controller.LoanController;
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;
import com.abdullah.PayrollManagementSystem.dao.LeaveMessage;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

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
		
		String message = "Hi "+leave.getFullname()+ " You got Leave from "+leave.getEntryfrom() + " to "+ leave.getEntryto();
		String queueName = String.valueOf(leave.getUserinfo_id());
		pushMessageToQueue(queueName, message);
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

	public String getPendingLeaveMessage(String queueName) throws IOException, TimeoutException {
		LeaveMessage leaveMessage = new LeaveMessage();
		
		//String message = getMessagefromQueue(queueName, leaveMessage);
		String redisMessage = getRedisMessagefromQueue(queueName, leaveMessage);
		
		return redisMessage;
	}

	private String getRedisMessagefromQueue(String queueName, LeaveMessage leaveMessage) {
		
		// address of your redis server
				String redisHost = "localhost";
				Integer redisPort = 6379;

				// the jedis connection pool..
				JedisPool pool = new JedisPool(redisHost, redisPort);

				// add some values in Redis HASH
				String key = queueName;
				Jedis jedis = pool.getResource();
				try {

					// after saving the data, lets retrieve them to be sure that it has really added
					// in redis
					Map<String, String> retrieveMap = jedis.hgetAll(key);
					String fieldname = "";
					for (String keyMap : retrieveMap.keySet()) {
						
						if (keyMap.equals("pendingleave")) {
							leaveMessage.setMessage(retrieveMap.get(keyMap));
							fieldname = retrieveMap.get(keyMap);
							jedis.hdel(key, keyMap);
							return leaveMessage.getMessage();
						}

						// System.out.println(keyMap);

						// System.out.println(keyMap + " " + retrieveMap.get(keyMap));
					}

				} catch (JedisException e) {
					// if something wrong happen, return it back to the pool
					if (null != jedis) {
						pool.returnBrokenResource(jedis);
						jedis = null;
					}
				} finally {
					/// it's important to return the Jedis instance to the pool once you've finished
					/// using it
					if (null != jedis)
						pool.returnResource(jedis);
				}
				return null;
			}

	private String getMessagefromQueue(String queueName, LeaveMessage leaveMessage) throws IOException, TimeoutException {
		
		ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    factory.setUsername("guest");
	    factory.setPassword("guest");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();

	    channel.queueDeclare(queueName, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C where Queue name : "+queueName);

	    DefaultConsumer consumer = new DefaultConsumer(channel) {
	      @Override
	      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws UnsupportedEncodingException {
	        System.out.println("handleDelivery : ");
	    	  String message = new String(body, "UTF-8");
				//leaveMessage.setMessage(message);
		        System.out.println(" [x] Received '" + message + "'");
			
	        
	      
}
	    };
	    System.out.println("After inner class");
	    channel.basicConsume(queueName, true, consumer);
//	    channel.close();
	    connection.close();
		
		
		return leaveMessage.getMessage();
	}

}
