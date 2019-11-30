package com.abdullah.pms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.abdullah.pms.domain.UserInfo;


public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
	List<UserInfo> findAll();
	Optional<UserInfo> findById(int id);
	void deleteById(int id);
	Optional<UserInfo> findByUsername(String username);
	Optional<UserInfo> findByEmail(String email);
	//UserDetails findByUsername(String username);
}
