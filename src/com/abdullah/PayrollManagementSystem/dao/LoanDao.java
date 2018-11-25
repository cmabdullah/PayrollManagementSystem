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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
		// select * from notices where id = :id
		// SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND entryfrom IS NULL
		// AND entryto IS NULL
		return jdbc.query(
				"SELECT * FROM loan where userinfo_id=:userinfo_id AND approvedate IS NULL AND status IS NULL", params,
				new RowMapper<Loan>() {
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
		logger.info("Loan object : " + loan);
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(loan);
		return jdbc.update(
				"insert into loan (placedate,amount,userinfo_id,reason) values (:placedate,:amount,:userinfo_id,:reason)",
				params) == 1;
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

	public boolean deletePendingLoanApplication(Loan loan) { // delete method
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(loan);
		return jdbc.update("update loan set approvedate=:approvedate,status=:status where id=:id", params) == 1;
	}


	public List<Loan> checkRunningLoan(int userinfo_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("userinfo_id", userinfo_id);
		// select * from notices where id = :id
		// SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND entryfrom IS NULL
		// AND entryto IS NULL
		return jdbc.query(
				"SELECT * FROM loan where userinfo_id=:userinfo_id AND approvedate IS NOT NULL AND status='1'", params,
				new RowMapper<Loan>() {
					public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
						Loan loan = new Loan();
						loan.setId(rs.getInt("id"));
						loan.setReason(rs.getString("reason"));
						loan.setAmount(rs.getInt("amount"));
						loan.setUserinfo_id(rs.getInt("userinfo_id"));
						// System.out.println("Retriving loan info from database : " + loan);
						return loan;// return single object
					}
				});
	}

	public Loan getLoanAmountBasedOnLoanID(int loan_id) {

		// MapSqlParameterSource params = new MapSqlParameterSource("name", "cm");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("loan_id", loan_id);
		// return jdbc.query("select * from notices where name = 'cm'", new
		// RowMapper<Notice>() {
		return jdbc.queryForObject("select * from loan where id = :loan_id", params, new RowMapper<Loan>() {
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setAmount(rs.getInt("amount"));
				;
				return loan;// return single object
			}
		});
	}

	public List<Loan> getLoanPaidDetailsBasedOnLoanId(int loan_id) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("loan_id", loan_id);
		// select * from notices where id = :id
		// SELECT * FROM leaveusers where userinfo_id=:userinfo_id AND entryfrom IS NULL
		// AND entryto IS NULL
		return jdbc.query("SELECT * FROM loanpaiddetails where loan_id=:loan_id", params, new RowMapper<Loan>() {
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setPaidamount(rs.getFloat("paidamount"));
				return loan;// return single object

			}
		});
	}
	
	@Transactional
	public int[] payLoanInstallment(List<Loan> loan) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(loan.toArray());
		return jdbc.batchUpdate("insert into loanpaiddetails (datetime , paidamount, loan_id) values (:datetime,:paidamount,:loan_id)", params);
	}


	public boolean updateLoanStatusBasedOnLoanId(Loan loan) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(loan);
		return jdbc.update("update loan set status=:status where id=:loan_id", params) == 1;
		
	}

	public boolean acceptPendingLoanApplication(Loan loan) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(loan);
		return jdbc.update("update loan set approvedate=:approvedate,status=:status where id=:id", params) == 1;
		
	}

	 public Loan getLoanApplicationInfoBasedOnLoanId(int id) {
		 MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("id", id);

			return jdbc.queryForObject("SELECT * FROM loan where id=:id", params, new RowMapper<Loan>() {
				public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
					Loan loan = new Loan();
					loan.setId(rs.getInt("id"));
					loan.setAmount(rs.getFloat("amount"));
					loan.setUserinfo_id(rs.getInt("userinfo_id"));
					return loan;// return single object
				}
			});
	}

}
