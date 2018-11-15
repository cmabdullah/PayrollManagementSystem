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


@Component("loanDao")
public class LoanDao {
private NamedParameterJdbcTemplate jdbc;
private static Logger logger = Logger.getLogger(LoanController.class);
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	
	public List<Loan> checkPandingLoanRequest(int userinfo_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userinfo_id", userinfo_id);
		//select * from notices where id = :id
		//SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND entryfrom IS NULL AND entryto IS NULL
		return jdbc.query("SELECT * FROM loan where userinfo_id=:userinfo_id AND approvedate IS NULL AND status IS NULL", params, new RowMapper<Loan>() {
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setId(rs.getInt("id"));
				loan.setReason(rs.getString("reason"));
				loan.setAmount(rs.getInt("amount"));
				loan.setUserinfo_id(rs.getInt("userinfo_id"));
				System.out.println("Retriving loan info from database : " + loan);
				return loan;// return single object
			}
		});
	}
	
	public boolean postLoanApplication(Loan loan) {
		logger.info("Loan object : "+loan);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(loan);
		return jdbc.update("insert into loan (placedate,amount,userinfo_id,reason) values (:placedate,:amount,:userinfo_id,:reason)", params) == 1;
	}

	
	public List<Loan> getAllLoanPendingRequests() {
		return jdbc.query("SELECT * FROM loan where  approvedate IS NULL AND status IS NULL", new RowMapper<Loan>() {
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setId(rs.getInt("id"));
				loan.setUserinfo_id(rs.getInt("userinfo_id"));
				loan.setReason(rs.getString("reason"));
				loan.setAmount(rs.getInt("amount"));
				
				return loan;
			}
		});
	}
	
	public boolean deletePendingLoanApplication(int id) { // delete method
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.update("delete from loan where id = :id", params ) == 1 ; // return true if success
	}
	
	public List<Loan> checkRunningLoan(int userinfo_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userinfo_id", userinfo_id);
		//select * from notices where id = :id
		//SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND entryfrom IS NULL AND entryto IS NULL
		return jdbc.query("SELECT * FROM loan where userinfo_id=:userinfo_id AND approvedate IS NOT NULL AND status='1'", params, new RowMapper<Loan>() {
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setId(rs.getInt("id"));
				loan.setReason(rs.getString("reason"));
				loan.setAmount(rs.getInt("amount"));
				loan.setUserinfo_id(rs.getInt("userinfo_id"));
				System.out.println("Retriving loan info from database : " + loan);
				return loan;// return single object
			}
		});
	}
	
	
	
}
