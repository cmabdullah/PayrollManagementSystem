package com.abdullah.pms.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.AttendanceLog;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.AttendanceRepository;
import com.abdullah.pms.service.AttendanceLogService;
import com.abdullah.pms.service.AttendanceService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	AttendanceLogService attendanceLogService;
	
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
	
	@Override
	public Attendance hasLogin(UserInfo userInfo) {
		List<Attendance> list = attendanceRepository.retrieveByTodaysLogin(LocalDate.now(), userInfo);
		System.out.println("Size : " + list.size());
		// in case multiple value came,
		Optional<Attendance> opt = list.stream().findFirst();
		list.forEach(n -> System.out.println(n.toString()));

		return opt.get();
	}

	@Override
	public boolean doLogin(UserInfo userInfo, String remoteAddr) {

		Attendance attendance = Attendance.builder().userInfo(userInfo).grade(userInfo.getGrade())
				.shift(userInfo.getShift()).loginIpAddress(remoteAddr).loginDate(LocalDate.now()).build();
		Optional<Attendance> at = Optional.ofNullable( attendanceRepository.save(attendance));

		//valid logoutTime(LocalDateTime.now()) shift validation
		AttendanceLog attendanceLog = AttendanceLog.builder().loginTime(LocalDateTime.now()).logoutTime(LocalDateTime.now()).userId(userInfo.getId())
				.shiftId(userInfo.getShift().getId()).attendance(attendance).ipAddress(remoteAddr).build();

		Optional<AttendanceLog> atl = Optional.ofNullable(attendanceLogService.save(attendanceLog));
		System.out.println("At obj" + at.toString());
		System.out.println("atl obj" + atl.toString());

		return at.isPresent() && atl.isPresent();
	}

	@Override
	public Attendance save(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

}
