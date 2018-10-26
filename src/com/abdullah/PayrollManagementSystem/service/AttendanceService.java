package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDate;
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
}
