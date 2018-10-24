package com.abdullah.PayrollManagementSystem.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("attendenceDao")
public class AttendenceDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean create(Attendence attendence) {
		System.out.println("Dao layer : "+attendence);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(attendence);
		return jdbc.update("insert into attendence (id,logintime , logouttime, userinfo_id) values (:id,:logintime,:logouttime, :userinfo_id)", params) == 1;
	}
}
