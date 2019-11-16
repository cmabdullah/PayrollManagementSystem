package com.abdullah.pms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.UserInfoRepository;
import com.abdullah.pms.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	UserInfoRepository userInfoRepository;

	@Override
	public List<UserInfo> findAll() {
		return userInfoRepository.findAll();
	}

	@Override
	public Optional<Attendance> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

}
