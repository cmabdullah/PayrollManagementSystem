package com.abdullah.PayrollManagementSystem.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("leavetestDao")
public class LeavetestDao {
	private NamedParameterJdbcTemplate jdbc;
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean create(Leavetest leavetest) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(leavetest);
		return jdbc.update("insert into leaveusers (reasone , status, leavetype,userinfo_id,entryfrom, entryto) values (:reasone,:status,:leavetype,:userinfo_id,:entryfrom, :entryto)", params) == 1;
	}
}
