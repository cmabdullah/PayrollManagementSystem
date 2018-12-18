package com.abdullah.PayrollManagementSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	public boolean updateAttendence(Attendance attendance) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(attendance);
		return jdbc.update("update attendence set logouttime=:logouttime,workinghours=:workinghours  where id=:id", params) == 1;
	}

	public List<Attendance> getAttendenceFromLastMonthToPresentMonth(LocalDateTime currentMonththLocalDateTime,
			LocalDateTime previousMonththLocalDateTime, int userinfoId) {
		//"SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND status=:status and leavetype='regular'  and entryfrom between '" + oneMonthsBeforeDate + "' and '" + currentDate + "' "
		return jdbc.query("select * from attendence where logintime between '" + previousMonththLocalDateTime + "' and '" + currentMonththLocalDateTime + "' and userinfo_id=' " + userinfoId+ "' " , new RowMapper<Attendance>() {
			public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				Attendance attendance = new Attendance();
				attendance.setId(rs.getInt("id"));
				attendance.setWorkinghours(rs.getInt("workinghours"));
				return attendance;
			
}
		});
	}

	public List<Attendance> getAllAttendanceBetween(LocalDate entryfrom, LocalDate entryto) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("entryfrom", entryfrom );
		params.addValue("entryto", entryto );
		//SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL
		//select * from attendence where logintime between '2018-10-15 22:56:07' and '2018-11-17 15:32:43' ;
		//SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL
		//"select * from leaveusers where entryfrom between '" + previousMonththLocalDateTime + "' and '" + currentMonththLocalDateTime + "' and  userinfo_id='"+ userinfoId+"' and leavetype='regular' and status='1'"
		return jdbc.query("select * from attendence where logintime between '" + entryfrom + "' and '" + entryto + "' and logouttime IS NOT NULL", params, new RowMapper<Attendance>() {
			public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				Attendance attendance = new Attendance();
				attendance.setId(rs.getInt("id"));
				attendance.setLogintime(rs.getTimestamp("logintime").toLocalDateTime());
				attendance.setLogouttime(rs.getTimestamp("logouttime").toLocalDateTime());
				attendance.setUserinfo_id(rs.getInt("userinfo_id"));
				attendance.setIpaddress(rs.getString("ipaddress"));
				attendance.setWorkinghours(rs.getInt("workinghours"));
				
				//System.out.println("Retriving data from database : " + attendance);
				
				return attendance;// return single object
			}
		});
	}

	public List<Attendance> getAllAttendanceBetween(LocalDate today, LocalDate sevenDaysAgo, int userId) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("entryfrom", today );
		params.addValue("logintime", today + "%");
		params.addValue("entryto", sevenDaysAgo );
		params.addValue("userinfo_id", userId );
		//select * from attendence where   ( logouttime IS NOT NULL and logintime like :logintime  and userinfo_id=:userinfo_id ) or logintime between '" + sevenDaysAgo  + "' and '" + today + "'   and userinfo_id='" + userId + "'
		return jdbc.query("select * from attendence where (logouttime IS NOT NULL and logintime like :logintime  and userinfo_id=:userinfo_id ) or (logouttime IS NOT NULL and logintime between '" + sevenDaysAgo  + "' and '" + today + "'   and userinfo_id='" + userId + "')", params, new RowMapper<Attendance>() {
			public Attendance mapRow(ResultSet rs, int rowNum) throws SQLException {
				Attendance attendance = new Attendance();
				attendance.setId(rs.getInt("id"));
				attendance.setLogintime(rs.getTimestamp("logintime").toLocalDateTime());
				attendance.setLogouttime(rs.getTimestamp("logouttime").toLocalDateTime());
				attendance.setUserinfo_id(rs.getInt("userinfo_id"));
				attendance.setIpaddress(rs.getString("ipaddress"));
				attendance.setWorkinghours(rs.getInt("workinghours"));
				
				//System.out.println("Retriving data from database : " + attendance);
				
				return attendance;// return single object
			}
		});
	}


	
	
	

	
	
}
