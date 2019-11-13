package com.abdullah.pms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.repository.AttendanceRepository;
import com.abdullah.pms.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Override
	public List<Attendance> findAll() {
		return attendanceRepository.findAll();
	}

	@Override
	public Optional<Attendance> findById(int id) {
		return attendanceRepository.findById(id);
	}

	@Override
	public void deleteById(int id) {
		attendanceRepository.deleteById(id);
		
	}



}
