package com.abdullah.PayrollManagementSystem.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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


@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/abdullah/PayrollManagementSystem/config/dao-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/config/security-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/test/config/datasource.xml"	
})
@RunWith(SpringJUnit4ClassRunner.class)
public class AttendenceDaoTest {
	@Autowired
	private AttendanceDao AttendenceDao; 
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new  JdbcTemplate(dataSource);
	}
	@Test
	public void loadNewUserInfo() {
		//Userinfo( String username, String password, boolean enabled, String authority, String fullname, String address, String email, int phone)
		
		Attendance attendence = new Attendance();
		
		
		String currentMonthString = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(LocalDateTime.now());
		String StartOfCurrentMonththString = currentMonthString.substring(0, 8).concat("01 00:00");

		// convert string to Localdatetime
		LocalDateTime currentMonththLocalDateTime = LocalDateTime.parse(StartOfCurrentMonththString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		LocalDateTime previousMonththLocalDateTime = currentMonththLocalDateTime.minusMonths(1);
		
		int userinfoId = 2019;
		
		
		List<Attendance> attendenceList = AttendenceDao.getAttendenceFromLastMonthToPresentMonth(currentMonththLocalDateTime, previousMonththLocalDateTime , userinfoId);
		assertEquals("Number of users shuld be 1",2, attendenceList.size());
		
		for (Attendance attendance : attendenceList) {
			System.out.println(attendance);
		}
//		assertTrue("User create should be return true",userinfoDao.existsUserId(2020));
		
		
		
	}

}
