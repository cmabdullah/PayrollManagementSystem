package com.abdullah.pms.service;

import java.util.List;
import java.util.Optional;

import com.abdullah.pms.domain.Attendance;

public interface AttendanceService {
	List<Attendance> findAll();
	Optional<Attendance> findById(int id);
	void deleteById(int id);
}
