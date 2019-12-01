package com.abdullah.pms.cash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.cash.repository.RedisRepository;
import com.abdullah.pms.cash.service.MessageService;
import com.abdullah.pms.domain.Leave;
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
		String mapkey = "pendingleave";
		redisRepository.add(mapkey, queueName, message );
		
	}
	@Override
	public String getPendingLeaveMessage(UserInfo userInfo, String mapkey) {
		return redisRepository.findByKey( mapkey, String.valueOf(userInfo.getId()));
	}

}
