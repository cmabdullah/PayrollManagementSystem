package com.abdullah.PayrollManagementSystem.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

@Component("UserinfoDao")
public class UserinfoDao {
	private NamedParameterJdbcTemplate jdbc;

	public UserinfoDao() {
		System.out.println("Beans are configured");
	}

	// @Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Userinfo> getUserinfos() {

		return jdbc.query("select * from userinfo", new RowMapper<Userinfo>() {
			public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Userinfo userinfo = new Userinfo();
				userinfo.setId(rs.getInt("id"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setPassword(rs.getString("password"));
				userinfo.setUsertype(rs.getString("usertype"));
				userinfo.setStatus(rs.getString("status"));
				userinfo.setFullname(rs.getString("fullname"));
				userinfo.setAddress(rs.getString("address"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setPhone(rs.getInt("phone"));
				
				return userinfo;
			}
		});
	}

	public boolean delete(int id) { // delete method
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.update("delete from userinfo where id = :id", params) == 1; // return true if success
	}

	@Transactional
	public int[] create(List<Userinfo> userinfo) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(userinfo.toArray());
		return jdbc.batchUpdate("insert into userinfo (id,username , password, usertype,status ,fullname,address , email, phone) values (:id,:username,:password, :usertype, :status , :fullname, :address , :email, :phone)", params);
	}

	public boolean create(Userinfo userinfo) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userinfo);
		return jdbc.update("insert into userinfo (id,username , password, usertype,status ,fullname,address , email, phone) values (:id,:username,:password, :usertype, :status , :fullname, :address , :email, :phone)", params) == 1;
	}

	public boolean update(Userinfo userinfo) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userinfo);
		return jdbc.update("update userinfo set username=:username,password=:password,usertype=:usertype,status=:status,fullname=:fullname,address=:address,email=:email ,phone=:phone where id=:id", params) == 1;
	}

	public Userinfo getUserinfo(int id){

		//MapSqlParameterSource params = new MapSqlParameterSource("name", "cm");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		//return jdbc.query("select * from notices where name = 'cm'", new RowMapper<Notice>() {
		return jdbc.queryForObject("select * from userinfo where id = :id", params, new RowMapper<Userinfo>() {
			public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Userinfo userinfo = new Userinfo();
				userinfo.setId(rs.getInt("id"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setPassword(rs.getString("password"));
				userinfo.setUsertype(rs.getString("usertype"));
				userinfo.setStatus(rs.getString("status"));
				userinfo.setFullname(rs.getString("fullname"));
				userinfo.setAddress(rs.getString("address"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setPhone(rs.getInt("phone"));
				
				return userinfo;// return single object
			}
		});
	}
}
