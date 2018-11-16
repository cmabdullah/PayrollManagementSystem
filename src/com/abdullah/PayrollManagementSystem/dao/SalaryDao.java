package com.abdullah.PayrollManagementSystem.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
}
