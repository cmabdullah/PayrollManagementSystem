package com.abdullah.PayrollManagementSystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;





@Component("gradeDao")
public class GradeDao {
	
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Grade> getAllGradeList() {
		return jdbc.query("select * from grade", new RowMapper<Grade>() {
			public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
				Grade grade = new Grade();
				grade.setId(rs.getInt("id"));
				grade.setBasic(rs.getInt("basic"));
				grade.setMedicalallowence(rs.getInt("medicalallowence"));
				grade.setHouserent(rs.getInt("houserent"));
				grade.setTransport(rs.getInt("transport"));
				grade.setLunch(rs.getInt("lunch"));
				grade.setStudy(rs.getInt("study"));
				return grade;

			}

		});
	}

}
