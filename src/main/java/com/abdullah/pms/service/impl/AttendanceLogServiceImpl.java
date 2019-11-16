package com.abdullah.pms.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.AttendanceLog;
import com.abdullah.pms.repository.AttendanceLogRepository;
import com.abdullah.pms.service.AttendanceLogService;
import com.abdullah.pms.service.AttendanceService;
@Service
public class AttendanceLogServiceImpl implements AttendanceLogService{
	@Autowired
	AttendanceLogRepository attendanceLogRepository;
	
	@Autowired
	AttendanceService attendanceService;

	@Override
	public AttendanceLog save(AttendanceLog attendanceLog) {
		return attendanceLogRepository.save(attendanceLog);
		
	}

	@Override
	public boolean hasLogout(Attendance attendance) {
		Optional<List<AttendanceLog>> list = Optional.ofNullable(  attendanceLogRepository.findByAttendanceId(attendance.getId()));
		
		list.get().forEach(n -> System.out.println("Ha Haaa"+n.toString()));
		
		Optional<AttendanceLog> opt = Optional.empty();
		if (list.isPresent()) {
			opt = Optional.of((list.get()).get(0));
			opt.get().setLogoutTime(LocalDateTime.now());
			opt.get().setId(0);
			
			
//			Attendance attendance1 = Attendance.builder().id(attendance.getId()).build();
//			AttendanceLog attendanceLog = AttendanceLog.builder()
//					.id((list.get()).get(0).getId())
//					.ipAddress((list.get()).get(0).getIpAddress())
//					.loginTime((list.get()).get(0).getLoginTime())
//					.logoutTime(LocalDateTime.now())
//					.userId((list.get()).get(0).getUserId())
//					.shiftId((list.get()).get(0).getShiftId())
//					.attendance(attendance).build();
//			attendanceLogRepository.save(attendanceLog);
			
			System.out.println("Saving attendanceLog object : "+ opt.get().toString());
			
			attendanceLogRepository.save(opt.get());
			
			//comute working hour
			double workingHours = computeLogInLogOutDiff(opt.get().getLoginTime(), LocalDateTime.now());
			System.out.println("AttendanceLog : "+opt.get().toString());
			attendance.setWorkingHours(workingHours);
			attendanceService.save(attendance);
			
		}
		
		
		return false;
	}
	
	double computeLogInLogOutDiff(LocalDateTime loginTime, LocalDateTime logoutTime) {
		
		long millis1 = loginTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		long millis2 = logoutTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		long diff = millis2 - millis1;
		
		int seconds = (int) (diff / 1000) % 60 ;
		int minutes = (int) ((diff / (1000*60)) % 60);
		int hours   = (int) ((diff / (1000*60*60)) % 24);
		System.out.println("Seconds : "+ seconds+ " minutes : "+ minutes+ " hours : "+hours);
		double workingHours = Double.parseDouble( hours+"."+minutes+"");
		
		System.out.println(workingHours);
		return workingHours;
	}

}
