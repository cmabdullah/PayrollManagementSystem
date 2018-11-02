package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Leave {
	private int id;
	private String reasone;
	private LocalDateTime entryfrom;
	private LocalDateTime entryto;
	private int userinfo_id;
	private boolean status;
	private String leavetype;
	
	private String entryfromString;
	private String entrytoString;
	
	private int total_leave_days;
	
	
	public Leave(){
		
	}

	public Leave(String reasone, LocalDateTime entryfrom, LocalDateTime entryto, int userinfo_id, boolean status,
			String leavetype, String entryfromString, String entrytoString, int total_leave_days) {
		this.reasone = reasone;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
		this.userinfo_id = userinfo_id;
		this.status = status;
		this.leavetype = leavetype;
		this.entryfromString = entryfromString;
		this.entrytoString = entrytoString;
		this.total_leave_days = total_leave_days;
	}
	
	

	public Leave(int id, String reasone, LocalDateTime entryfrom, LocalDateTime entryto, int userinfo_id, boolean status,
			String leavetype, String entryfromString, String entrytoString, int total_leave_days) {
		this.id = id;
		this.reasone = reasone;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
		this.userinfo_id = userinfo_id;
		this.status = status;
		this.leavetype = leavetype;
		this.entryfromString = entryfromString;
		this.entrytoString = entrytoString;
		this.total_leave_days = total_leave_days;
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
	
	

	public String getEntryfromString() {
		return entryfromString;
	}

	public void setEntryfromString(String entryfromString) {
		this.entryfromString = entryfromString;
		//convert string to Localdatetime
		this.entryfrom = LocalDateTime.parse(entryfromString.concat(" 00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    	//LocalDateTime defultLogoutTime = LocalDateTime.parse(defultLogoutTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}
	
	

	public String getEntrytoString() {
		return entrytoString;
	}

	public void setEntrytoString(String entrytoString) {
		this.entrytoString = entrytoString;
		this.entryto =  LocalDateTime.parse(entrytoString.concat(" 00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	
	
	
	
	public int getTotal_leave_days() {
		return total_leave_days;
	}

	public void setTotal_leave_days(int total_leave_days) {
		this.total_leave_days = total_leave_days;
	}

	@Override
	public String toString() {
		return "Leave [id=" + id + ", reasone=" + reasone + ", entryfrom=" + entryfrom + ", entryto=" + entryto
				+ ", userinfo_id=" + userinfo_id + ", status=" + status + ", leavetype=" + leavetype
				+ ", entryfromString=" + entryfromString + ", entrytoString=" + entrytoString + ", total_leave_days="
				+ total_leave_days + "]";
	}

	

	
	

	

}