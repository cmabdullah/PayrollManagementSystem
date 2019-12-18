package com.abdullah.pms.service.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.UserInfoRepository;
import com.abdullah.pms.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	@Autowired
	UserInfoRepository userInfoRepository;

	//@Cacheable(value= "findAllCache", unless= "#result.size() == 0")
	@Override
	public List<UserInfo> findAll() {
		return userInfoRepository.findAll();
	}


	@Override
	public void deleteById(int id) {
	}

	//@Cacheable(value= "findByIdCache", key= "#id")	
	@Override
	public Optional<UserInfo> findById(int id) {
		return userInfoRepository.findById(id);
	}


	@Override
//	@Caching(
//			put= { @CachePut(value= "findByIdCache", key= "#userInfo.id") },
//			evict= { @CacheEvict(value= "findAllCache", allEntries= true) }
//		)
	public UserInfo save(@Valid UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}

	//@Cacheable(value= "existsCache", key= "#username")	
	@Override
	public Optional<UserInfo> exists(String username) {
		return userInfoRepository.findByUsername(username);
	}

	//@Cacheable(value= "existsEmailCache", key= "#email")	
	@Override
	public Optional<UserInfo> existsEmail(String email) {
		return userInfoRepository.findByEmail(email);
	}


	@Override
	public List<UserInfo> findByEnabled(boolean enabled) {
		// TODO Auto-generated method stub
		return userInfoRepository.findByEnabled(enabled);
	}

}
