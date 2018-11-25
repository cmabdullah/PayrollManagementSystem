package com.abdullah.PayrollManagementSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
}
