package com.abdullah.pms.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.abdullah.pms.domain.UserInfo;

public interface UserInfoService {
	List<UserInfo> findAll();
	
	void deleteById(int id);

	Optional<UserInfo> findById(int decodeUserId);

	UserInfo save(@Valid UserInfo userInfo);

	Optional<UserInfo> exists(String username);

	Optional<UserInfo> existsEmail(String email);
	
	List<UserInfo> findByEnabled(boolean enabled);
}
