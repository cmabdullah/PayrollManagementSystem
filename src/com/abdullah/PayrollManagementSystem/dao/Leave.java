package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;

public class Leave {
	private int id;
	private String reasone;
	private LocalDateTime entryfrom;
	private LocalDateTime entryto;
	private int userinfo_id;
	private boolean status;
	private String leavetype;
	
	
	Leave(){
		
	}

	public Leave(String reasone, LocalDateTime entryfrom, LocalDateTime entryto, int userinfo_id, boolean status,
			String leavetype) {
		this.reasone = reasone;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
		this.userinfo_id = userinfo_id;
		this.status = status;
		this.leavetype = leavetype;
	}
	
	

	public Leave(int id, String reasone, LocalDateTime entryfrom, LocalDateTime entryto, int userinfo_id, boolean status,
			String leavetype) {
		this.id = id;
		this.reasone = reasone;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
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
		return "Leave [id=" + id + ", reasone=" + reasone + ", entryfrom=" + entryfrom + ", entryto=" + entryto
				+ ", userinfo_id=" + userinfo_id + ", status=" + status + ", leavetype=" + leavetype + "]";
	}

	

}
