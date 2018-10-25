package com.abdullah.PayrollManagementSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;




@Component("attendenceDao")
public class AttendanceDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean create(Attendance attendence) {
		System.out.println("Dao layer : "+attendence);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(attendence);
		return jdbc.update("insert into attendence (id,logintime , logouttime, userinfo_id,ipaddress,workinghours) values (:id,:logintime,:logouttime, :userinfo_id,:ipaddress,:workinghours)", params) == 1;
	}
	
	public List<Attendance> getLoginInfoBatch(LocalDate logintime, int userinfo_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("logintime", logintime + "%");
		params.addValue("userinfo_id", userinfo_id);
		return jdbc.query("SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL", params, new RowMapper<Attendance>() {
			public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				Attendance attendance = new Attendance();
				attendance.setId(rs.getInt("id"));
				attendance.setLogintime(rs.getTimestamp("logintime").toLocalDateTime());
				//attendance.setLogouttime(rs.getTimestamp("logouttime").toLocalDateTime());
				attendance.setUserinfo_id(rs.getInt("userinfo_id"));
				attendance.setIpaddress(rs.getString("ipaddress"));
				attendance.setWorkinghours(rs.getInt("workinghours"));
				
				System.out.println("Retriving data from database : " + attendance);
				
				return attendance;// return single object
			}
		});
	}
	
	public List<Attendance> getLogoutInfoBatch(LocalDate logintime, int userinfo_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("logintime", logintime + "%");
		params.addValue("userinfo_id", userinfo_id);
		return jdbc.query("SELECT * FROM attendence where logintime <  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL", params, new RowMapper<Attendance>() {
			public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				Attendance attendance = new Attendance();
				attendance.setId(rs.getInt("id"));
				attendance.setLogintime(rs.getTimestamp("logintime").toLocalDateTime());
				//attendance.setLogouttime(rs.getTimestamp("logouttime").toLocalDateTime());
				attendance.setUserinfo_id(rs.getInt("userinfo_id"));
				attendance.setIpaddress(rs.getString("ipaddress"));
				attendance.setWorkinghours(rs.getInt("workinghours"));
				
				System.out.println("Retriving logout from database : " + attendance);
				
				return attendance;// return single object
			}
		});
	}
	
	
}
