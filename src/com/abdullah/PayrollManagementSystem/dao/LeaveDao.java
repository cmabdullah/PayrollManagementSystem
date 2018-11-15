package com.abdullah.PayrollManagementSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
				leave.setStatus(rs.getInt("status"));
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
		LocalDate currentDate = LocalDate.now(); //2018-11-10
		LocalDate oneMonthsBeforeDate = LocalDate.now().minusMonths(1);//2018-10-10
		
		return jdbc.query("SELECT * FROM leaveusers  LEFT JOIN userinfo ON leaveusers.userinfo_id = userinfo.id  where  status='0' OR (status='2' and entryfrom between '" + oneMonthsBeforeDate + "' and '" + currentDate + "' );", new RowMapper<Leave>() {
			public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
				Leave leave = new Leave();
				leave.setId(rs.getInt("id"));
				leave.setUserinfo_id(rs.getInt("userinfo_id"));
				leave.setReasone(rs.getString("reasone"));
				leave.setLeavetype(rs.getString("leavetype"));
				leave.setEntryfrom(rs.getTimestamp("entryfrom").toLocalDateTime());
				leave.setEntryto(rs.getTimestamp("entryto").toLocalDateTime());
				leave.setTotal_leave_days(rs.getInt("total_leave_days"));
				leave.setFullname(rs.getString("fullname"));
				leave.setEmail(rs.getString("email"));
				leave.setStatus(rs.getInt("status"));
				
				//this query throw exception
				//SELECT reasone, leavetype, entryfrom,entryto,total_leave_days,fullname,email FROM PayrollManagementSystem.leaveusers  LEFT JOIN userinfo ON leaveusers.userinfo_id = userinfo.id  where  status='0';
				
				
				//SELECT * FROM PayrollManagementSystem.leaveusers  LEFT JOIN userinfo ON leaveusers.userinfo_id = userinfo.id  where  status='0' OR (status='2' and entryfrom between '2018-10-10' and '2018-11-10' );
				return leave;
			}
		});
	}


	

	
	public boolean ignorePendingApplicationId(int id)  {
		Leave leaveIdUpdate = new Leave();
		leaveIdUpdate.setId(id);
		leaveIdUpdate.setStatus(2);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(leaveIdUpdate);
		return jdbc.update("update leaveusers set status=:status where id=:id", params) == 1;
	}

	
	
	public boolean acceptPendingApplicationId(int id)  {
		Leave leaveIdUpdate = new Leave();
		leaveIdUpdate.setId(id);
		leaveIdUpdate.setStatus(1);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(leaveIdUpdate);
		return jdbc.update("update leaveusers set status=:status where id=:id", params) == 1;
	}

	public List<Leave> isLeaveRequestOutOfLimit(int userinfo_id) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userinfo_id", userinfo_id);
		params.addValue("status", 1);
		
		LocalDate currentDate = LocalDate.now(); //2018-11-10
		LocalDate oneMonthsBeforeDate = LocalDate.now().minusMonths(1);//2018-10-10

		return jdbc.query("SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND status=:status and leavetype='regular'  and entryfrom between '" + oneMonthsBeforeDate + "' and '" + currentDate + "' ", params, new RowMapper<Leave>() {
			public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
				Leave leave = new Leave();
				leave.setId(rs.getInt("id"));
				leave.setUserinfo_id(rs.getInt("userinfo_id"));
				leave.setTotal_leave_days(rs.getInt("total_leave_days"));
				System.out.println("Retriving leave info from database : " + leave);
				return leave;// return single object
			}
		});	
	}
	
	
	
	
	public Leave getLeaveApplicationInfoBasedOnLeaveId(int id){

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("SELECT * FROM leaveusers  LEFT JOIN userinfo ON leaveusers.userinfo_id = userinfo.id  where  leaveusers.id=:id", params, new RowMapper<Leave>() {
			public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
				Leave leave = new Leave();
				leave.setId(rs.getInt("id"));
				leave.setUserinfo_id(rs.getInt("userinfo_id"));
				leave.setReasone(rs.getString("reasone"));
				leave.setLeavetype(rs.getString("leavetype"));
				leave.setEntryfrom(rs.getTimestamp("entryfrom").toLocalDateTime());
				leave.setEntryto(rs.getTimestamp("entryto").toLocalDateTime());
				leave.setTotal_leave_days(rs.getInt("total_leave_days"));
				leave.setFullname(rs.getString("fullname"));
				leave.setEmail(rs.getString("email"));
				leave.setStatus(rs.getInt("status"));
				return leave;// return single object
			}
		});
	}

	public List<Leave> checkRegularLeaveFromLastMonthToPresentMonth(LocalDateTime currentMonththLocalDateTime,
			LocalDateTime previousMonththLocalDateTime, int userinfoId) {
		//"SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND status=:status and leavetype='regular'  and entryfrom between '" + oneMonthsBeforeDate + "' and '" + currentDate + "' "
				return jdbc.query("select * from leaveusers where entryfrom between '" + previousMonththLocalDateTime + "' and '" + currentMonththLocalDateTime + "' and  userinfo_id='"+ userinfoId+"' and leavetype='regular' and status='1'", new RowMapper<Leave>() {
					public Leave mapRow(ResultSet rs, int rowNum) throws SQLException {
						Leave leave = new Leave();
						leave.setId(rs.getInt("id"));
						leave.setLeavetype(rs.getString("leavetype"));
						leave.setTotal_leave_days(rs.getInt("total_leave_days"));
						return leave;
					
}
				});
	}

//	public void confirmPendingLeaveApplication(Leave leave) {
//		// TODO Auto-generated method stub
//		
//	}
}
