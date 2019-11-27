package com.abdullah.pms.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
	public void deleteById(int id) {
	}


	@Override
	public Optional<UserInfo> findById(int decodeUserId) {
		return userInfoRepository.findById(decodeUserId);
	}


	@Override
	public UserInfo save(@Valid UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}

	@Override
	public Optional<UserInfo> exists(String username) {
		return userInfoRepository.findByUsername(username);
	}

	@Override
	public Optional<UserInfo> existsEmail(String email) {
		return userInfoRepository.findByEmail(email);
	}

}
