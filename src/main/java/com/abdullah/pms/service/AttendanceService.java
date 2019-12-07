package com.abdullah.pms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.UserInfo;

public interface AttendanceService {
	List<Attendance> findAll();
	Optional<Attendance> findById(int id);
	void deleteById(int id);
	Attendance save(Attendance attendance);
	Optional<Attendance> hasLogin(UserInfo userInfo);
	boolean doLogin(UserInfo userInfo, String remoteAddr);
	List<Attendance> findByUserInfoAndLoginDateBetween(UserInfo userInfo, LocalDate dateFrom, LocalDate dateTo);
	
}
