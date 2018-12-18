package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class SearchData {
	private int id;
	
	private LocalDateTime entryfrom;
	private LocalDateTime entryto;
	private String username;
	private String entryfromString;
	private String entrytoString;
	
	public SearchData(){
		
	}

	public SearchData(int id, LocalDateTime entryfrom, LocalDateTime entryto, String username, String entryfromString,
			String entrytoString) {
		this.id = id;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
		this.username = username;
		this.entryfromString = entryfromString;
		this.entrytoString = entrytoString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "SearchData [id=" + id + ", entryfrom=" + entryfrom + ", entryto=" + entryto + ", username=" + username
				+ ", entryfromString=" + entryfromString + ", entrytoString=" + entrytoString + "]";
	}	
}
