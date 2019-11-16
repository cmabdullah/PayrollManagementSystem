package com.abdullah.pms.service;

import java.util.Optional;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.AttendanceLog;

public interface AttendanceLogService {
	AttendanceLog save(AttendanceLog attendanceLog);

	boolean hasLogout(Attendance hasLoginEmployee);
}
