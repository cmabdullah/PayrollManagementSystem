package com.abdullah.PayrollManagementSystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.AttendanceDao;
import com.abdullah.PayrollManagementSystem.dao.AttendanceVisualizer;
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
	
	
	public List<AttendanceVisualizer> getAllAttendanceOfThisYear() {

		LocalDateTime firstDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("01-01 00:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime lastDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("12-30 23:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		//System.out.println("firstDayOfYear : " + firstDayOfYear + " lastDayOfYear : " + lastDayOfYear);

		List<Attendance> attendenceListOfThisYear = attendanceDao.getAllAttendanceBetween(firstDayOfYear.toLocalDate(),
				lastDayOfYear.toLocalDate());
		//System.out.println(attendenceListOfThisYear.size());
		for (Attendance attendance : attendenceListOfThisYear) {
			//System.out.println(attendance);
			attendance.setUserinfo_idObject(new Integer(attendance.getUserinfo_id()));
		}
		
		//List<Attendance> attendenceListOfThisYearQ = attendenceListOfThisYear;

		Set<String> set2 = new TreeSet<String>();
		for (int i = 0; i < attendenceListOfThisYear.size(); i++) {
			String currentMonthString1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
					.format(attendenceListOfThisYear.get(i).getLogintime()).substring(0, 10);
			set2.add(currentMonthString1);
		}

		ArrayList<String> arrayList = new ArrayList<>(set2);
		//HashMap<String, Integer> map = new HashMap<>();
		List<AttendanceVisualizer> attendanceVisualizer = new ArrayList<>();
		
		for (int i = 0; i < arrayList.size(); i++) {
			AttendanceVisualizer x = new AttendanceVisualizer();
			List<Attendance> attendenceListInstance = new ArrayList<>();
			int flag = 0;
			for (int j = 0; j < attendenceListOfThisYear.size(); j++) {
				if (arrayList.get(i).equals(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
						.format(attendenceListOfThisYear.get(j).getLogintime()).substring(0, 10)))
					//flag++;
					attendenceListInstance.add(attendenceListOfThisYear.get(j));
			}
			flag = myFilter(attendenceListInstance);
			//map.put(arrayList.get(i), flag);
			System.out.println("arrayList.get(i) " + arrayList.get(i) + " days : " + flag);
			x.setLocalDate(arrayList.get(i));
			x.setTotalDays(flag);
			attendanceVisualizer.add(x);
		}

		//System.out.println("attendanceVisualizer : "+attendanceVisualizer.size());
//		for (AttendanceVisualizer attendanceVisualizer2 : attendanceVisualizer) {
//			System.out.println(attendanceVisualizer2);
//		}
//		
		return attendanceVisualizer;
	}
	
	
	private int myFilter(List<Attendance> attendenceListInstance) {
		Set<Integer> set2 = new TreeSet<>();
		for (int i = 0; i < attendenceListInstance.size(); i++) {
			set2.add(attendenceListInstance.get(i).getUserinfo_id());
		}
		return set2.size();
	}
	
	public List<AttendanceVisualizer> getSingleUserAttendanceOfThisYear(int userId) {

		LocalDateTime firstDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("01-01 00:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime lastDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("12-30 23:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		List<Attendance> attendenceListOfThisYear = attendanceDao.getAllAttendanceBetween(lastDayOfYear.toLocalDate(),
				 firstDayOfYear.toLocalDate(), userId);
		//System.out.println(attendenceListOfThisYear.size());
		for (Attendance attendance : attendenceListOfThisYear) {
			//System.out.println(attendance);
			attendance.setUserinfo_idObject(new Integer(attendance.getUserinfo_id()));
		}
		
		//List<Attendance> attendenceListOfThisYearQ = attendenceListOfThisYear;

		Set<String> set2 = new TreeSet<String>();
		for (int i = 0; i < attendenceListOfThisYear.size(); i++) {
			String currentMonthString1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
					.format(attendenceListOfThisYear.get(i).getLogintime()).substring(0, 10);
			set2.add(currentMonthString1);
		}

		ArrayList<String> arrayList = new ArrayList<>(set2);
		//HashMap<String, Integer> map = new HashMap<>();
		List<AttendanceVisualizer> attendanceVisualizer = new ArrayList<>();
		
		for (int i = 0; i < arrayList.size(); i++) {
			AttendanceVisualizer x = new AttendanceVisualizer();
			int flag = 0;
			for (int j = 0; j < attendenceListOfThisYear.size(); j++) {
				if (arrayList.get(i).equals(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
						.format(attendenceListOfThisYear.get(j).getLogintime()).substring(0, 10)))
					flag++;
					
			}
			System.out.println("arrayList.get(i) " + arrayList.get(i) + " days : " + flag);
			x.setLocalDate(arrayList.get(i));
			x.setTotalDays(flag);
			attendanceVisualizer.add(x);
		}
		return attendanceVisualizer;
	}

	public List<Attendance> getLastSevenDaysAttendance(int userId) {
		
		LocalDate today = LocalDate.now();
		LocalDate sevenDaysAgo = today.minusWeeks(1);
		List<Attendance> attendenceListOfThisYear = attendanceDao.getAllAttendanceBetween(today,sevenDaysAgo, userId);
		return attendenceListOfThisYear;
	}
	//return specific id info
	public List<Attendance> getAllAttendanceBetween(LocalDate entryfrom, LocalDate entryto, int userId) {
		List<Attendance> attendencesBetween = attendanceDao.getAllAttendanceBetween(entryfrom, entryto, userId);
		return attendencesBetween;
	}
	
	//return specific id info
	public List<Attendance> getAllAttendanceOfSingleEmployeeBetweenThisYear(int userId) {
		
		
		LocalDateTime firstDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("01-01 00:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime lastDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("12-30 23:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		
		List<Attendance> attendencesBetween = attendanceDao.getAllAttendanceBetween(lastDayOfYear.toLocalDate(),
				firstDayOfYear.toLocalDate(), userId);
		return attendencesBetween;
	}
	//this method low level implementation is under construction
	public void pendingLogoutProcess(int userId) {
		
		try {
			//Attendance [id=0, logintime=2018-12-24T01:20:09, logouttime=null, userinfo_id=2026, userinfo_idObject=null, ipaddress=null, workinghours=0]
			Attendance attendencesBetween = attendanceDao.pendingLogoutProcess(userId);
			
			//convert localdate to string
	    	String defultLogoutTimeString = DateTimeFormatter.ofPattern("yyyy-MM-dd").format( attendencesBetween.getLogintime().toLocalDate()) +" 18:00";
	    	//convert string to Localdatetime
	    	LocalDateTime defultLogoutTime = LocalDateTime.parse(defultLogoutTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	    	System.out.println("defultLogoutTime : "+ defultLogoutTime);
			LocalDateTime currentTime = attendencesBetween.getLogintime();
			System.out.println("currentTime : "+ currentTime);
			//this method has bug write testcase
			int count = 0;
			System.out.println("defultLogoutTime "+defultLogoutTime);
			
			if (currentTime.plusHours(8).isBefore(defultLogoutTime)) {
				count = 8;
			} else if(currentTime.plusHours(7).isBefore(defultLogoutTime)) {
				count = 7;
			} else if(currentTime.plusHours(6).isBefore(defultLogoutTime)) {
				count = 6;
			} else if(currentTime.plusHours(5).isBefore(defultLogoutTime)) {
				count = 5;
			} else if(currentTime.plusHours(4).isBefore(defultLogoutTime)) {
				count = 4;
			} else if(currentTime.plusHours(3).isBefore(defultLogoutTime)) {
				count = 3;
			} else if(currentTime.plusHours(2).isBefore(defultLogoutTime)) {
				count = 2;
			} else if(currentTime.plusHours(1).isBefore(defultLogoutTime)) {
				count = 1;
			} else if(currentTime.plusHours(0).isBefore(defultLogoutTime)) {
				count = 0;
			} else {
				count = 0;
			}
			System.out.println(count);
			attendencesBetween.setLogouttime(LocalDateTime.now());
			attendencesBetween.setWorkinghours(count);
			
			((AttendanceDao) attendanceDao).updateAttendenceTrappedState(attendencesBetween);
			
			
		} catch (Exception e) {
			System.out.println("Auto logout by admin exception catched"+e.getMessage());
		}
		
		
		
		
		
		
		
	}
	
	
}
