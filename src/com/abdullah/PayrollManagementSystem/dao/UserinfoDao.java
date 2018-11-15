package com.abdullah.PayrollManagementSystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;


import javax.sql.DataSource;


@Component("userinfoDao")
public class UserinfoDao {
	private NamedParameterJdbcTemplate jdbc;

	public UserinfoDao() {
		System.out.println("Beans are configured");
	}

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//old code
	public List<Userinfo> getUserinfos() {

		return jdbc.query("select * from userinfo", new RowMapper<Userinfo>() {
			public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Userinfo userinfo = new Userinfo();
				userinfo.setId(rs.getInt("id"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setFullname(rs.getString("fullname"));
				userinfo.setAddress(rs.getString("address"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setPhone(rs.getInt("phone"));
				
				return userinfo;
			}
		});
	}
	//old code
	public boolean delete(int id) { // delete method
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		return jdbc.update("delete from userinfo where id = :id", params) == 1; // return true if success
	}

	//@Transactional
	public int[] create(List<Userinfo> userinfo) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(userinfo.toArray());
		return jdbc.batchUpdate("insert into userinfo (id,username , password, usertype,status ,fullname,address , email, phone) values (:id,:username,:password, :usertype, :status , :fullname, :address , :email, :phone)", params);
	}
	@Transactional
	public boolean create(Userinfo userinfo) {
		System.out.println("DAO layer info "+ userinfo);
		//BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userinfo);
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("username", userinfo.getUsername());
		params.addValue("password", passwordEncoder.encode(userinfo.getPassword()));
		params.addValue("authority", userinfo.getAuthority()); 
		params.addValue("enabled", userinfo.isEnabled());
		
		params.addValue("fullname", userinfo.getFullname());
		params.addValue("address", userinfo.getAddress());
		params.addValue("email", userinfo.getEmail());
		params.addValue("phone", userinfo.getPhone());
		params.addValue("joiningDate", LocalDateTime.now());
		

		
		return  jdbc.update("insert into userinfo (username,fullname , address, email,phone ,password , enabled, authority, joiningDate) values (:username,:fullname,:address, :email, :phone,:password,:enabled,:authority, :joiningDate)", params) == 1;
//		jdbc.update("insert into users (username) values (:username)", params);
//		jdbc.update("insert into authorities (username , authority) values (:username,:authority)", params)== 1;
	
	}

	public boolean updateUserEnabledStatus(Userinfo userinfo) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userinfo);
		return jdbc.update("update userinfo set enabled=:enabled where username=:username", params) == 1;
	}
	//old code
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
				userinfo.setFullname(rs.getString("fullname"));
				userinfo.setAddress(rs.getString("address"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setPhone(rs.getInt("phone"));
				
				return userinfo;// return single object
			}
		});
	}
	
	public boolean exists(String username) {
		return jdbc.queryForObject("select count(*) from userinfo where username=:username", new MapSqlParameterSource("username",username), Integer.class) > 0 ;
	}

	public boolean disableEnable(Userinfo userinfo) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userinfo);
		return jdbc.update("update userinfo set enabled=:enabled where username=:username", params) == 1;
		
	}
	
	public Userinfo getUserIdFromName(String username){

		//MapSqlParameterSource params = new MapSqlParameterSource("name", "cm");
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", username);
		//return jdbc.query("select * from notices where name = 'cm'", new RowMapper<Notice>() {
		return jdbc.queryForObject("select * from userinfo where username = :username", params, new RowMapper<Userinfo>() {
			public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Userinfo userinfo = new Userinfo();
				userinfo.setId(rs.getInt("id"));
				return userinfo;// return single object
			}
		});
	}

	public boolean existsUserId(int id) {
		return jdbc.queryForObject("select count(*) from userinfo where id=:id", new MapSqlParameterSource("id",id), Integer.class) > 0 ;
	}
	
	
	public List<Userinfo> showAllEnabledUsers() {//
		return jdbc.query("select * from userinfo where enabled = 1", new RowMapper<Userinfo>() {
			public Userinfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				Userinfo userinfo = new Userinfo();
				userinfo.setId(rs.getInt("id"));
				userinfo.setUsername(rs.getString("username"));
				userinfo.setFullname(rs.getString("fullname"));
				userinfo.setAddress(rs.getString("address"));
				userinfo.setEmail(rs.getString("email"));
				userinfo.setPhone(rs.getInt("phone"));
				userinfo.setEnabled(rs.getBoolean("enabled"));

				
				return userinfo;
			}
		});
	}
}
