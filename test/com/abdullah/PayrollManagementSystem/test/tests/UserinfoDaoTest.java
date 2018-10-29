package com.abdullah.PayrollManagementSystem.test.tests;

import static org.junit.Assert.assertTrue;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.abdullah.PayrollManagementSystem.dao.Userinfo;
import com.abdullah.PayrollManagementSystem.dao.UserinfoDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
		"classpath:com/abdullah/PayrollManagementSystem/config/dao-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/config/security-context.xml",
		"classpath:com/abdullah/PayrollManagementSystem/test/config/datasource.xml"	
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserinfoDaoTest {
	@Autowired
	private UserinfoDao userinfoDao; 
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new  JdbcTemplate(dataSource);
	}
	@Test
	public void loadNewUserInfo() {
		//Userinfo( String username, String password, boolean enabled, String authority, String fullname, String address, String email, int phone)
		Userinfo userinfo = new Userinfo("asdfg", "asdfg",true,"ROLE_ADMIN","C M ABU", "Bonosree","cm@gmail.com", 01717);
		
		assertTrue("User create should be return true",userinfoDao.create(userinfo));
		
		
	}

}
