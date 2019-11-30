package com.abdullah.pms.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.pms.domain.Attendance;
import com.abdullah.pms.domain.AttendanceLog;
import com.abdullah.pms.domain.Shift;
import com.abdullah.pms.domain.UserInfo;
import com.abdullah.pms.repository.AttendanceRepository;
import com.abdullah.pms.service.AttendanceLogService;
import com.abdullah.pms.service.AttendanceService;
import com.abdullah.pms.service.ShiftService;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceRepository attendanceRepository;

	@Autowired
	AttendanceLogService attendanceLogService;

	@Autowired
	ShiftService shiftService;

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
	public Optional<Attendance> hasLogin(UserInfo userInfo) {
		List<Attendance> list = attendanceRepository.retrieveByTodaysLogin(LocalDate.now(), userInfo);
		System.out.println("Size : " + list.size());
		// in case multiple value came,
		Optional<Attendance> opt = list.stream().findFirst();
		list.forEach(n -> System.out.println(n.toString()));
		System.out.println("Size : " + list.size());
		return opt;
	}

	@Override
	public boolean doLogin(UserInfo userInfo, String remoteAddr) {

		Attendance attendance = Attendance.builder().userInfo(userInfo).grade(userInfo.getGrade())
				.shift(userInfo.getShift()).loginIpAddress(remoteAddr).loginDate(LocalDate.now()).build();
		//save attendance
		Optional<Attendance> at = Optional.ofNullable(attendanceRepository.save(attendance));
		
		Optional<Shift> op = shiftService.findAll().stream().filter(n -> n.getId() == userInfo.getShift().getId())
				.findAny();
		System.out.println("Shift object " + op.get().toString());
		String currentTime = LocalDateTime.now().toString();

		LocalDateTime startTime = LocalDateTime.parse(
				currentTime.substring(0, 10) + " " + op.get().getStartTime().substring(0, 2) + ":00:00",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));// 10 AM
		System.out.println("startTime : " + startTime);

		LocalDateTime endTime = LocalDateTime.parse(
				currentTime.substring(0, 10) + " " + op.get().getEndTime().substring(0, 2) + ":00:00",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));// 07 PM
		System.out.println("endTime : " + endTime);
		
		LocalDateTime lt;
		// shift validation logic
		if (Integer.parseInt(currentTime.substring(11, 13)) < Integer
				.parseInt(startTime.toString().substring(11, 13))) {
			// 7<10
			// then 10
			lt = startTime;
		} else if (Integer.parseInt(currentTime.substring(11, 13)) > Integer
				.parseInt(endTime.toString().substring(11, 13))) {
			// 9 > 8
			// then 8
			// lt = endTime
			lt = endTime;
		} else {
			lt = LocalDateTime.now();
		}

		// valid logoutTime(LocalDateTime.now()) shift validation enabled
		AttendanceLog attendanceLog = AttendanceLog.builder().loginTime(lt).logoutTime(lt).userId(userInfo.getId())
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
