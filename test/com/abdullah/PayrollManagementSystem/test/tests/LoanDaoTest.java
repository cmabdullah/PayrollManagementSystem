package com.abdullah.PayrollManagementSystem.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
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
import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;
import com.abdullah.PayrollManagementSystem.dao.Loan;
import com.abdullah.PayrollManagementSystem.dao.LoanDao;
import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoDao;


@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/abdullah/PayrollManagementSystem/config/dao-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/config/security-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/test/config/datasource.xml"	
})
@RunWith(SpringJUnit4ClassRunner.class)
public class LoanDaoTest {
	@Autowired
	private LoanDao loanDao; 
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new  JdbcTemplate(dataSource);
	}
	@Test
	public void loadNewUserInfo() {
		List<Loan> loanList = loanDao.checkRunningLoanDetails(2026);
		assertEquals("Number of users shuld be 2",2, loanList.size());
		
		
		
	}
}