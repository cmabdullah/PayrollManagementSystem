package com.abdullah.PayrollManagementSystem.dao;

public class Leavetest {
	private int id;
	private String reasone;
	private boolean status;
	private String leavetype;
	
	
	
	public Leavetest() {

	}



	public Leavetest(String reasone, boolean status, String leavetype) {
		this.reasone = reasone;
		this.status = status;
		this.leavetype = leavetype;
	}



	public Leavetest(int id, String reasone, boolean status, String leavetype) {
		this.id = id;
		this.reasone = reasone;
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



	@Override
	public String toString() {
		return "Leavetest [id=" + id + ", reasone=" + reasone + ", status=" + status + ", leavetype=" + leavetype + "]";
	}	
	
	
	
	
}
