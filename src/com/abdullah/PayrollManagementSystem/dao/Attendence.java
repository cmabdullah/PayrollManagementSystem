package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;

public class Attendence {
	
	private int id;
	
	private LocalDateTime logintime;
	
	private LocalDateTime logouttime ;
	
	private int userinfo_id;

	
	public Attendence(LocalDateTime logintime, LocalDateTime logouttime, int userinfo_id) {
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.userinfo_id = userinfo_id;
	}
	
	public Attendence(int id, LocalDateTime logintime, LocalDateTime logouttime, int userinfo_id) {
		this.id = id;
		this.logintime = logintime;
		this.logouttime = logouttime;
		this.userinfo_id = userinfo_id;
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

	@Override
	public String toString() {
		return "Attendence [id=" + id + ", logintime=" + logintime + ", logouttime=" + logouttime + ", userinfo_id="
				+ userinfo_id + "]";
	}
}
