package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDate;

public class AttendanceVisualizer {
	
	String localDate ;
	int totalDays;
	
	public AttendanceVisualizer() {
		
	}

	public AttendanceVisualizer(String localDate, int totalDays) {
		super();
		this.localDate = localDate;
		this.totalDays = totalDays;
	}

	public String getLocalDate() {
		return localDate;
	}

	public void setLocalDate(String localDate) {
		this.localDate = localDate;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	@Override
	public String toString() {
		return "AttendanceVisualizer [localDate=" + localDate + ", totalDays=" + totalDays + "]";
	}


}
