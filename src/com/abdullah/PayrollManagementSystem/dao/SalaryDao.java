package com.abdullah.PayrollManagementSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.abdullah.PayrollManagementSystem.controller.LoanController;

@Component("salaryDao")
public class SalaryDao {
	private NamedParameterJdbcTemplate jdbc;
	private static Logger logger = Logger.getLogger(LoanController.class);
		
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	
		@Transactional
		public int[] giveSalary(List<Salary> salary) {
			SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(salary.toArray());
			return jdbc.batchUpdate("insert into salary (id,datemonthyear , Loan_id, grade_id,userinfo_id ,leaveusers_id,totalsalary) values (:id,:datemonthyear,:Loan_id,:grade_id , :userinfo_id, :leaveusers_id , :totalsalary)", params);
		}


		public Salary getUserInfoWithGradeInfo(int userinfo_id) {
			
			//;
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", userinfo_id);//select * from userinfo where id = :id
			return jdbc.queryForObject("SELECT * FROM userinfo inner join grade on userinfo.grade_id = grade.id where userinfo.id=:id", params, new RowMapper<Salary>() {
				public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {
					Salary salary = new Salary();
					salary.setBasic(rs.getInt("basic"));
					salary.setMedicalallowence(rs.getInt("medicalallowence"));
					salary.setHouserent(rs.getInt("houserent"));
					salary.setTransport(rs.getInt("transport"));
					salary.setLunch(rs.getInt("lunch"));
					salary.setStudy(rs.getInt("study"));
					return salary;
				}
			});
		}


		public Userinfo getUserGradeId(int userinfo_id) {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", userinfo_id);
			return jdbc.queryForObject("select * from userinfo where id = :id", params, new RowMapper<Userinfo>() {
				public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
					Userinfo userinfo = new Userinfo();
					userinfo.setId(rs.getInt("id"));
					userinfo.setGrade_id(rs.getInt("grade_id"));
					userinfo.setJoiningDate(rs.getTimestamp("joiningDate").toLocalDateTime());
					return userinfo;
				}
			});
		}


		public List<Salary> getAllAttendanceBetween(LocalDate entryfrom, LocalDate entryto) {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("entryfrom", entryfrom );
			params.addValue("entryto", entryto );
			//SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL
			//select * from attendence where logintime between '2018-10-15 22:56:07' and '2018-11-17 15:32:43' ;
			//SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL
			//"select * from leaveusers where entryfrom between '" + previousMonththLocalDateTime + "' and '" + currentMonththLocalDateTime + "' and  userinfo_id='"+ userinfoId+"' and leavetype='regular' and status='1'"
			//select * from attendence where logintime between '" + entryfrom + "' and '" + entryto + "' and logouttime IS NOT NULL
			return jdbc.query("select s.id  , s.datemonthyear , s.totalsalary , u.fullname , u.email ,  s.Loan_id , l.amount, l.status , g.basic from salary as s  LEFT JOIN  userinfo as u   ON s.userinfo_id = u.id LEFT JOIN  loan as l  ON s.Loan_id = l.id LEFT JOIN  grade as g  ON s.grade_id = g.id where (l.amount != 0  and u.id != 0 and s.totalsalary != 0 ) and datemonthyear between '" + entryfrom + "' and '" + entryto + "'", params, new RowMapper<Salary>() {
				public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {
					Salary salary = new Salary();
					salary.setId(rs.getInt("id"));
					salary.setDatemonthyear(rs.getTimestamp("datemonthyear").toLocalDateTime());
					salary.setTotalsalary(rs.getFloat("totalsalary"));
					salary.setFullname(rs.getString("fullname"));
					salary.setEmail(rs.getString("email"));
					salary.setLoan_id(rs.getInt("Loan_id"));
					salary.setAmount(rs.getInt("amount"));
					salary.setStatus(rs.getString("status"));
					salary.setBasic(rs.getInt("basic"));
					return salary;
				}
			});
		}


		public List<Salary> getAllSalaryBetween(LocalDate entryfrom, LocalDate entryto, int userId) {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("entryfrom", entryfrom );
			params.addValue("entryto", entryto );
			//SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL
			//select * from attendence where logintime between '2018-10-15 22:56:07' and '2018-11-17 15:32:43' ;
			//SELECT * FROM attendence where logintime like  :logintime AND userinfo_id=:userinfo_id AND logouttime IS NULL
			//"select * from leaveusers where entryfrom between '" + previousMonththLocalDateTime + "' and '" + currentMonththLocalDateTime + "' and  userinfo_id='"+ userinfoId+"' and leavetype='regular' and status='1'"
			//select * from attendence where logintime between '" + entryfrom + "' and '" + entryto + "' and logouttime IS NOT NULL
			return jdbc.query("select s.id  , s.datemonthyear , s.totalsalary , u.fullname , u.email ,  s.Loan_id , l.amount, l.status , g.basic from salary as s  LEFT JOIN  userinfo as u   ON s.userinfo_id = u.id LEFT JOIN  loan as l  ON s.Loan_id = l.id LEFT JOIN  grade as g  ON s.grade_id = g.id where (l.amount != 0  and u.id != 0 and s.totalsalary != 0 ) and datemonthyear between '" + entryfrom + "' and '" + entryto + "' and s.userinfo_id='" + userId + "'", params, new RowMapper<Salary>() {
				public Salary mapRow(ResultSet rs, int rowNum) throws SQLException {
					Salary salary = new Salary();
					salary.setId(rs.getInt("id"));
					salary.setDatemonthyear(rs.getTimestamp("datemonthyear").toLocalDateTime());
					salary.setTotalsalary(rs.getFloat("totalsalary"));
					salary.setFullname(rs.getString("fullname"));
					salary.setEmail(rs.getString("email"));
					salary.setLoan_id(rs.getInt("Loan_id"));
					salary.setAmount(rs.getInt("amount"));
					salary.setStatus(rs.getString("status"));
					salary.setBasic(rs.getInt("basic"));
					return salary;
				}
			});
		}
}
