package com.abdullah.PayrollManagementSystem.dao;

public class LeaveMessage {
	private String message;

	public LeaveMessage() {
	}

	public LeaveMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "LeaveMessage [message=" + message + "]";
	}
	

}
