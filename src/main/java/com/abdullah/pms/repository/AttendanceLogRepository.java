package com.abdullah.pms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdullah.pms.domain.AttendanceLog;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long>{
	List<AttendanceLog> findByAttendanceId(int id);
}
