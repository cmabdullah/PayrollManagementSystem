package com.abdullah.PayrollManagementSystem.test.tests;

import java.time.LocalDate;
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

import com.abdullah.PayrollManagementSystem.dao.Leave;
import com.abdullah.PayrollManagementSystem.dao.LeaveDao;
import com.abdullah.PayrollManagementSystem.dao.Salary;
import com.abdullah.PayrollManagementSystem.dao.SalaryDao;


@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/abdullah/PayrollManagementSystem/config/dao-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/config/security-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/test/config/datasource.xml"	
})
@RunWith(SpringJUnit4ClassRunner.class)
public class SalaryDaoTest {
	@Autowired
	private SalaryDao salaryDao; 
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new  JdbcTemplate(dataSource);
	}
	@Test
	public void salaryJoin() {
		
		List<Salary> salaryBetween = salaryDao.getAllAttendanceBetween(LocalDate.now().minusYears(1), LocalDate.now());
		System.out.println("salaryBetween : "+ salaryBetween);
		System.out.println("salary size : "+ salaryBetween.size());
		
		for (Salary salary : salaryBetween) {
			System.out.println(salary);
		}
	}
}
