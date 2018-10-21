package com.abdullah.PayrollManagementSystem.dao;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDao")
public class UserDao {
	private NamedParameterJdbcTemplate jdbc;
	
	public UserDao() {
		System.out.println("userDao Beans are configured");
	}
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	
	
	@Transactional
	public boolean create(User user) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);
		jdbc.update("insert into users (username,password , email, enabled) values (:username,:password,:email,:enabled)", params);
		return jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params) == 1;
	}
}