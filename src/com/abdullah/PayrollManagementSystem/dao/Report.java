package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
	private int id;

	private LocalDateTime entryfrom;
	private LocalDateTime entryto;

	private String reportType;
	
	private String entryfromString;
	private String entrytoString;

	
	public Report(){
		
	}

	public Report(LocalDateTime entryfrom, LocalDateTime entryto, String reportType, String entryfromString,
			String entrytoString) {
		super();
		this.entryfrom = entryfrom;
		this.entryto = entryto;
		this.reportType = reportType;
		this.entryfromString = entryfromString;
		this.entrytoString = entrytoString;
	}
	
	

	public Report(int id, LocalDateTime entryfrom, LocalDateTime entryto, String reportType, String entryfromString,
			String entrytoString) {
		super();
		this.id = id;
		this.entryfrom = entryfrom;
		this.entryto = entryto;
		this.reportType = reportType;
		this.entryfromString = entryfromString;
		this.entrytoString = entrytoString;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
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
		System.out.println("entrytoString : "+entrytoString);
		this.entryto =  LocalDateTime.parse(entrytoString.concat(" 00:00"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
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
		return "Report [id=" + id + ", entryfrom=" + entryfrom + ", entryto=" + entryto + ", reportType=" + reportType
				+ ", entryfromString=" + entryfromString + ", entrytoString=" + entrytoString + "]";
	}

	
	
	

}
