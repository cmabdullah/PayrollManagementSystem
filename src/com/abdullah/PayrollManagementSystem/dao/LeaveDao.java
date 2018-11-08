package com.abdullah.PayrollManagementSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.abdullah.PayrollManagementSystem.controller.LoanController;

@Component("leaveDao")
public class LeaveDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private static Logger logger = Logger.getLogger(LoanController.class);
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public boolean postLeaveApplication(Leave leave) {
		System.out.println("Leave object : "+leave);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(leave);
		return jdbc.update("insert into leaveusers (reasone,entryfrom,entryto,userinfo_id,status,leavetype,total_leave_days) values (:reasone,:entryfrom,:entryto,:userinfo_id,:status,:leavetype, :total_leave_days)", params) == 1;
	}

	public List<Leave> checkPandingLeaveRequest(int userinfo_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userinfo_id", userinfo_id);
		params.addValue("status", 0);
		
		//select * from notices where id = :id
		//SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND entryfrom IS NULL AND entryto IS NULL
		return jdbc.query("SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND status=:status", params, new RowMapper<Leave>() {
			public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
				Leave leave = new Leave();
				leave.setId(rs.getInt("id"));
				leave.setReasone(rs.getString("reasone"));
				leave.setStatus(rs.getBoolean("status"));
				leave.setLeavetype(rs.getString("leavetype"));
				leave.setUserinfo_id(rs.getInt("userinfo_id"));
				//null pointer exception fro both due to retrive null value
//				leave.setEntryfrom(rs.getTimestamp("entryfrom").toLocalDateTime());
//				leave.setEntryto(rs.getTimestamp("entryto").toLocalDateTime());
				System.out.println("Retriving leave info from database : " + leave);
				return leave;// return single object
			}
		});
	}
	
	public Leave getLeaveApplicationIdBasedOnUserinfo_id(int userinfo_id){

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userinfo_id", userinfo_id);
		return jdbc.queryForObject("select id from leaveusers where userinfo_id = :userinfo_id AND entryfrom IS NULL AND entryto IS NULL", params, new RowMapper<Leave>() {
			public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
				Leave leave = new Leave();
				leave.setId(rs.getInt("id"));
				return leave;// return single object
			}
		});
	}
	
	
	public boolean confirmPendingLeaveApplication(Leave leave) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(leave);
		return jdbc.update("update leaveusers set leavetype=:leavetype,entryfrom=:entryfrom,entryto=:entryto,total_leave_days=:total_leave_days  where id=:id", params) == 1;
	}

	public List<Leave> getAllLeaveRequests() {
		return jdbc.query("SELECT * FROM leaveusers where  entryfrom IS NULL AND entryto IS NULL", new RowMapper<Leave>() {
			public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
				Leave leave = new Leave();
				leave.setId(rs.getInt("id"));
				leave.setUserinfo_id(rs.getInt("userinfo_id"));
				leave.setReasone(rs.getString("reasone"));
				return leave;
			}
		});
	}
	


//	public void confirmPendingLeaveApplication(Leave leave) {
//		// TODO Auto-generated method stub
//		
//	}
}