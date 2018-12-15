package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.AttendanceDao;
@Service("attendanceService")
public class AttendanceService {
	private AttendanceDao attendanceDao;
	
	@Autowired
	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}

	public void create(Attendance attendance) {
		attendanceDao.create(attendance);
		
	}

	

	public boolean hasLogin(String username, int userinfo_id, LocalDate loginDate) {
		
		if (username == null)
			return false;
		
		System.out.println("hasLogin info username : " +username +" userinfo_id: "+userinfo_id +" loginDate "+loginDate);
		
		List<Attendance> attendences = attendanceDao.getLoginInfoBatch(loginDate, userinfo_id);
		if(attendences.size() == 0)
			return false;
		
		
		if(attendences.size() != 0) {
			System.out.println("LAst element "+attendences.get(attendences.size()-1));
    	}
		
		return true;
		
	}

	public boolean hasLogout(String username, int userinfo_id, LocalDate loginDate) {
		List<Attendance> lateattendence = attendanceDao.getLogoutInfoBatch(loginDate, userinfo_id);
		
		
		
		if(lateattendence.size() != 0) { //work on this case
			System.out.println("Mised logout "+lateattendence.get(lateattendence.size()-1));
			return true;
			
		}
		
		
		
		
		if(lateattendence.size() == 0)
			return false;
		
		
		return false;
	}

	public void giveLogoutPermissionAndCalculateWorkingHour(String username, int userinfo_id, LocalDate loginDate) {
		
		List<Attendance> attendences = attendanceDao.getLoginInfoBatch(loginDate, userinfo_id);
		
		if(attendences.size() != 0) {
			
			Attendance attendence = attendences.get(attendences.size()-1);
			LocalDateTime loginTime = attendence.getLogintime();
			//convert localdate to string
	    	String defultLogoutTimeString = DateTimeFormatter.ofPattern("yyyy-MM-dd").format( LocalDate.now()) +" 18:00";
	    	//convert string to Localdatetime
	    	LocalDateTime defultLogoutTime = LocalDateTime.parse(defultLogoutTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

			LocalDateTime currentTime = LocalDateTime.now();
			
			if(currentTime.isBefore(defultLogoutTime)) {
				defultLogoutTime  = currentTime;
			}
			

			
			int count = 0;
			System.out.println("defultLogoutTime "+defultLogoutTime);
			
			if (loginTime.plusHours(8).isBefore(defultLogoutTime)) {
				count = 8;
			} else if(loginTime.plusHours(7).isBefore(defultLogoutTime)) {
				count = 7;
			} else if(loginTime.plusHours(6).isBefore(defultLogoutTime)) {
				count = 6;
			} else if(loginTime.plusHours(5).isBefore(defultLogoutTime)) {
				count = 5;
			} else if(loginTime.plusHours(4).isBefore(defultLogoutTime)) {
				count = 4;
			} else if(loginTime.plusHours(3).isBefore(defultLogoutTime)) {
				count = 3;
			} else if(loginTime.plusHours(2).isBefore(defultLogoutTime)) {
				count = 2;
			} else if(loginTime.plusHours(1).isBefore(defultLogoutTime)) {
				count = 1;
			} else if(loginTime.plusHours(0).isBefore(defultLogoutTime)) {
				count = 0;
			} else {
				count = 0;
			}
			
			attendence.setLogouttime(defultLogoutTime);
			attendence.setWorkinghours(count);
			
			attendanceDao.updateAttendence(attendence);
			System.out.println(count);
			
			
			System.out.println("give permission to logout belongs to this id  "+attendences.get(attendences.size()-1));
    	}
		
		
		
	}

	public List<Attendance> getAllAttendanceBetween(LocalDate entryfrom, LocalDate entryto) {
		List<Attendance> attendencesBetween = attendanceDao.getAllAttendanceBetween(entryfrom, entryto);
		return attendencesBetween;
	}
}
