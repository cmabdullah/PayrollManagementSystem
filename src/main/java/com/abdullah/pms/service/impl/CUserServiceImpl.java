package com.abdullah.pms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.CUser;
import com.abdullah.pms.repository.CUserRepository;
import com.abdullah.pms.service.CUserService;
@Service
public class CUserServiceImpl implements CUserService {
	@Autowired
	CUserRepository cUserRepository;

	@Override
	public Optional<CUser> findByUsername(String username) {
		return cUserRepository.findByUsername(username);
	}

	@Override
	public void save(List<CUser> cUsers) {
		for(CUser cUser : cUsers)
			cUserRepository.save(cUser);
		
	}

}
