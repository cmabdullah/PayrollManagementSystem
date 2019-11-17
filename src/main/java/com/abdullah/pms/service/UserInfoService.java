package com.abdullah.pms.service;

import java.util.List;
import java.util.Optional;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.UserInfo;

public interface UserInfoService {
	List<UserInfo> findAll();
	
	void deleteById(int id);

	Optional<UserInfo> findById(int decodeUserId);
}
