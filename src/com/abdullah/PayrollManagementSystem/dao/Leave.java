package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;

public class Leave {
	private int id;
	private String reasone;
	private LocalDateTime from;
	private LocalDateTime to;
	private int userinfo_id;
	private boolean status;
	private String leavetype;
	
	Leave(){
		
	}

	public Leave(String reasone, LocalDateTime from, LocalDateTime to, int userinfo_id, boolean status,
			String leavetype) {
		this.reasone = reasone;
		this.from = from;
		this.to = to;
		this.userinfo_id = userinfo_id;
		this.status = status;
		this.leavetype = leavetype;
	}
	
	

	public Leave(int id, String reasone, LocalDateTime from, LocalDateTime to, int userinfo_id, boolean status,
			String leavetype) {
		this.id = id;
		this.reasone = reasone;
		this.from = from;
		this.to = to;
		this.userinfo_id = userinfo_id;
		this.status = status;
		this.leavetype = leavetype;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReasone() {
		return reasone;
	}

	public void setReasone(String reasone) {
		this.reasone = reasone;
	}

	public LocalDateTime getFrom() {
		return from;
	}

	public void setFrom(LocalDateTime from) {
		this.from = from;
	}

	public LocalDateTime getTo() {
		return to;
	}

	public void setTo(LocalDateTime to) {
		this.to = to;
	}

	public int getUserinfo_id() {
		return userinfo_id;
	}

	public void setUserinfo_id(int userinfo_id) {
		this.userinfo_id = userinfo_id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getLeavetype() {
		return leavetype;
	}

	public void setLeavetype(String leavetype) {
		this.leavetype = leavetype;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", reasone=" + reasone + ", from=" + from + ", to=" + to + ", userinfo_id="
				+ userinfo_id + ", status=" + status + ", leavetype=" + leavetype + "]";
	}

}
