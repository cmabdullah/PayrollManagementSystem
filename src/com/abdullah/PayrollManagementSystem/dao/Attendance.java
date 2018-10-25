package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;

public class Attendance {
	
	private int id;
	
	private LocalDateTime logintime;
	
	private LocalDateTime logouttime ;
	
	private int userinfo_id;
	
	private String ipaddress;
	
	private int workinghours;
	
	public Attendance() {

	}
	public Attendance( LocalDateTime logintime, LocalDateTime logouttime, int userinfo_id, String ipaddress,
			int workinghours) {

		this.logintime = logintime;
		this.logouttime = logouttime;
		this.userinfo_id = userinfo_id;
		this.ipaddress = ipaddress;
		this.workinghours = workinghours;
	}

	public Attendance(int id, LocalDateTime logintime, LocalDateTime logouttime, int userinfo_id, String ipaddress,
			int workinghours) {
		this.id = id;
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.userinfo_id = userinfo_id;
		this.ipaddress = ipaddress;
		this.workinghours = workinghours;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getLogintime() {
		return logintime;
	}
	public void setLogintime(LocalDateTime logintime) {
		this.logintime = logintime;
	}
	public LocalDateTime getLogouttime() {
		return logouttime;
	}
	public void setLogouttime(LocalDateTime logouttime) {
		this.logouttime = logouttime;
	}
	public int getUserinfo_id() {
		return userinfo_id;
	}
	public void setUserinfo_id(int userinfo_id) {
		this.userinfo_id = userinfo_id;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public int getWorkinghours() {
		return workinghours;
	}
	public void setWorkinghours(int workinghours) {
		this.workinghours = workinghours;
	}
	@Override
	public String toString() {
		return "Attendance [id=" + id + ", logintime=" + logintime + ", logouttime=" + logouttime + ", userinfo_id="
				+ userinfo_id + ", ipaddress=" + ipaddress + ", workinghours=" + workinghours + "]";
	}
	
	

	
	
}
