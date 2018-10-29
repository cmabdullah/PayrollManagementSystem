package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;

public class Leavetest {
	private int id;
	private String reasone;
	private boolean status;
	private String leavetype;
	private int userinfo_id;
	private LocalDateTime entryfrom;
	private LocalDateTime entryto;
	
	
	
	public Leavetest() {

	}



	public Leavetest(String reasone, boolean status, String leavetype, int userinfo_id,LocalDateTime entryfrom,LocalDateTime entryto) {
		this.reasone = reasone;
		this.status = status;
		this.leavetype = leavetype;
		this.userinfo_id = userinfo_id;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
	}



	public Leavetest(int id, String reasone, boolean status, String leavetype, int userinfo_id,LocalDateTime entryfrom,LocalDateTime entryto) {
		this.id = id;
		this.reasone = reasone;
		this.status = status;
		this.leavetype = leavetype;
		this.userinfo_id = userinfo_id;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
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



	public boolean getStatus() {
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



	public int getUserinfo_id() {
		return userinfo_id;
	}



	public void setUserinfo_id(int userinfo_id) {
		this.userinfo_id = userinfo_id;
	}



	public LocalDateTime getEntryfrom() {
		return entryfrom;
	}



	public void setEntryfrom(LocalDateTime entryfrom) {
		this.entryfrom = entryfrom;
	}

	


	public LocalDateTime getEntryto() {
		return entryto;
	}



	public void setEntryto(LocalDateTime entryto) {
		this.entryto = entryto;
	}



	@Override
	public String toString() {
		return "Leavetest [id=" + id + ", reasone=" + reasone + ", status=" + status + ", leavetype=" + leavetype
				+ ", userinfo_id=" + userinfo_id + ", entryfrom=" + entryfrom + ", entryto=" + entryto + "]";
	}



	


	



	
	
	
	
	
}
