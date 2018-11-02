package com.abdullah.PayrollManagementSystem.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("leaveDao")
public class LeaveDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean postLeaveApplication(Leave leave) {
		System.out.println("Leave object : "+leave);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(leave);
		return jdbc.update("insert into leaveusers (reasone,entryfrom,entryto,userinfo_id,status,leavetype) values (:reasone,:entryfrom,:entryto,:userinfo_id,:status,:leavetype)", params) == 1;
	}

	public List<Leave> checkPandingLeaveRequest(int userinfo_id) {
		// TODO Auto-generated method stub
		return null;
	}


}