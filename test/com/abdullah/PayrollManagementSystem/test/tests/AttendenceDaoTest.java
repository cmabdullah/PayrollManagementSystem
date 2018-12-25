package com.abdullah.PayrollManagementSystem.test.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abdullah.PayrollManagementSystem.dao.Attendance;
import com.abdullah.PayrollManagementSystem.dao.AttendanceDao;
import com.abdullah.PayrollManagementSystem.dao.AttendanceVisualizer;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:com/abdullah/PayrollManagementSystem/config/dao-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/config/security-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class AttendenceDaoTest {
	@Autowired
	private AttendanceDao AttendenceDao;
	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);
	}

	@Test
	public void loadNewUserInfo() {
		// Userinfo( String username, String password, boolean enabled, String
		// authority, String fullname, String address, String email, int phone)

		Attendance attendence = new Attendance();

		String currentMonthString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());

		System.out.println("currentMonthString : " + currentMonthString);
		// 2018-12-16 13:53
		// 2018-12-01 00:00
		// StartOfCurrentMonththString : 2018-01-01 00:00
		String StartOfCurrentMonththString = currentMonthString.substring(0, 5).concat("01-01 00:00");
		System.out.println("StartOfCurrentMonththString : " + StartOfCurrentMonththString);
		// convert string to Localdatetime
		LocalDateTime currentMonththLocalDateTime = LocalDateTime.parse(StartOfCurrentMonththString,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime previousMonththLocalDateTime = currentMonththLocalDateTime.minusMonths(1);

		int userinfoId = 2019;

		List<Attendance> attendenceList = AttendenceDao.getAttendenceFromLastMonthToPresentMonth(
				currentMonththLocalDateTime, previousMonththLocalDateTime, userinfoId);
		// assertEquals("Number of users shuld be 1",2, attendenceList.size());

		for (Attendance attendance : attendenceList) {
			System.out.println(attendance);
		}
		// assertTrue("User create should be return
		// true",userinfoDao.existsUserId(2020));

		LocalDateTime firstDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("01-01 00:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime lastDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("12-30 23:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

		System.out.println("firstDayOfYear : " + firstDayOfYear + " lastDayOfYear : " + lastDayOfYear);

		List<Attendance> attendenceListOfThisYear = AttendenceDao.getAllAttendanceBetween(firstDayOfYear.toLocalDate(),
				lastDayOfYear.toLocalDate());
		System.out.println("attendenceListOfThisYear size : "+ attendenceListOfThisYear.size());
		System.out.println(attendenceListOfThisYear.size());
		for (Attendance attendance : attendenceListOfThisYear) {
			//System.out.println(attendance);
			attendance.setUserinfo_idObject(new Integer(attendance.getUserinfo_id()));
		}
		
		
		Set<String> set2 = new TreeSet<String>();
		for (int i = 0; i < attendenceListOfThisYear.size(); i++) {
			String currentMonthString1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
					.format(attendenceListOfThisYear.get(i).getLogintime()).substring(0, 10);
			set2.add(currentMonthString1);
		}

		
		System.out.println("My set Size : "+set2.size());
		ArrayList<String> arrayList = new ArrayList<>(set2);
		System.out.println("My array Size : "+arrayList.size());
		//HashMap<String, Integer> map = new HashMap<>();
		List<AttendanceVisualizer> attendanceVisualizer = new ArrayList<>();
		
		for (int i = 0; i < arrayList.size(); i++) {//outer loop 8
			AttendanceVisualizer x = new AttendanceVisualizer();
			List<Attendance> attendenceListInstance = new ArrayList<>();
			int flag = 0;
			for (int j = 0; j < attendenceListOfThisYear.size(); j++) {//17
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
		
		//2018-12-16
		
		
//		for (Map.Entry<String,Integer> entry : map.entrySet()) {
//			AttendanceVisualizer x = new AttendanceVisualizer();
//			x.setLocalDate(LocalDate.parse(entry.getKey(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//			x.setTotalDays(entry.getValue());
//			attendanceVisualizer.add(x);
//			String key = entry.getKey();
//			 int value = entry.getValue();
//			System.out.println(key +" : "+value);
//		}
		
		System.out.println("attendanceVisualizer : "+attendanceVisualizer.size());
		
		
		for (AttendanceVisualizer attendanceVisualizer2 : attendanceVisualizer) {
			System.out.println(attendanceVisualizer2);
		}

	}

	private int myFilter(List<Attendance> attendenceListInstance) {
		Set<Integer> set2 = new TreeSet<>();
		for (int i = 0; i < attendenceListInstance.size(); i++) {
			set2.add(attendenceListInstance.get(i).getUserinfo_id());
		}
		return set2.size();
	}
	
	
	@Test
public void getLastSevenDaysAttendance() {
		int userId = 2026;
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime sevenDaysAgo = today.minusWeeks(1);
		System.out.println("today : "+today +" sevenDaysAgo : "+sevenDaysAgo);
		
		//username : zxcv entryfrom : 2018-10-02T00:00 entryto 2018-12-18T00:00 userId : 2026
		
		List<Attendance> attendenceListOfThisYear = AttendenceDao.getAllAttendanceBetween(today.toLocalDate(),sevenDaysAgo.toLocalDate(), userId);
		//assertEquals("Number of users shuld be 1",2, attendenceListOfThisYear.size());
		System.out.println("attendenceListSpecificEmployee "+attendenceListOfThisYear.size());
		
		for (Attendance attendance : attendenceListOfThisYear) {
			System.out.println(attendance);
		}
		

	}
	
	
	
	@Test
	public void getAllAttendanceBetween() {
		int userId = 2030;
		LocalDateTime firstDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("01-01 00:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime lastDayOfYear = LocalDateTime.parse(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
				.format(LocalDateTime.now()).substring(0, 5).concat("12-30 23:00"),
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		//System.out.println("today : "+today +" sevenDaysAgo : "+sevenDaysAgo);
		
		//username : zxcv entryfrom : 2018-10-02T00:00 entryto 2018-12-18T00:00 userId : 2026
		
		List<Attendance> attendenceListOfThisYear = AttendenceDao.getAllAttendanceBetween(lastDayOfYear.toLocalDate(),
				firstDayOfYear.toLocalDate(), userId);
		//assertEquals("Number of users shuld be 1",2, attendenceListOfThisYear.size());
		System.out.println("attendenceListSpecificEmployeeBetween this year"+attendenceListOfThisYear.size());
		
		
		
		
		
		
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
		
		
		
		
		
		
		
		for (AttendanceVisualizer attendance : attendanceVisualizer) {
			System.out.println(attendance);
		}
		
		Attendance attendencesBetween = AttendenceDao.pendingLogoutProcess(2026);
		System.out.println("Logout back test : "+attendencesBetween);
		

	}
	
	
	
}
