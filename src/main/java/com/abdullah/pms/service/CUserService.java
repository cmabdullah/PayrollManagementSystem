package com.abdullah.pms.service;

import java.util.List;
import java.util.Optional;

import com.abdullah.pms.domain.CUser;

public interface CUserService {
	Optional<CUser> findByUsername(String username);
	
	void save(List<CUser> cUsers);
}
