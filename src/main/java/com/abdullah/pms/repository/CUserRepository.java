package com.abdullah.pms.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.abdullah.pms.domain.CUser;

public interface CUserRepository extends CrudRepository<CUser,Long>{
	Optional<CUser> findByUsername(String username);
}
