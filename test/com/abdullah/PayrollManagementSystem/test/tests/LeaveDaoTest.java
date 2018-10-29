package com.abdullah.PayrollManagementSystem.test.tests;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoDao;


@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/abdullah/PayrollManagementSystem/config/dao-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/config/security-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/test/config/datasource.xml"	
})
@RunWith(SpringJUnit4ClassRunner.class)
public class LeaveDaoTest {
	@Autowired
	private LeaveDao leaveDao; 
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new  JdbcTemplate(dataSource);
	}
	@Test
	public void loadNewUserInfo() {
		//Userinfo( String username, String password, boolean enabled, String authority, String fullname, String address, String email, int phone)
		String reasone = "FFFF";
		LocalDateTime from = null;
		LocalDateTime to = null;
		int userinfo_id = 2026;
		boolean status = true;
		String leavetype = null;
		
		Leave leave = new Leave(reasone,from,to,userinfo_id,status,leavetype);
		assertTrue("User create should be return true",leaveDao.postLeaveApplication(leave));
		
		
	}

}
