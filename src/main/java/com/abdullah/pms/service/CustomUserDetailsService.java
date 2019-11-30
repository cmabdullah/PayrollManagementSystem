package com.abdullah.pms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.UserInfoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfo> userInfo = userInfoRepository.findByUsername(username);
		if (!userInfo.isPresent()) {
			throw new UsernameNotFoundException("User not exist with name : " + username);
		} else {
			CustomUserDetails userDetails = new CustomUserDetails();
			userDetails.setUserInfo(userInfo.get());
			return userDetails;
		}
	}
}