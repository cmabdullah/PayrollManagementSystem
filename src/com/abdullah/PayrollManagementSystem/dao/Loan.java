package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;

import com.abdullah.PayrollManagementSystem.controller.LoanController;

public class Loan {
	private static Logger logger = Logger.getLogger(LoanController.class);

	// loan table entity
	private int id;
	private LocalDateTime placedate;
	private LocalDateTime approvedate;
	private int amount;
	@NotBlank
	@Size(min = 3, max = 6)
	@Pattern(regexp = "[0-9]+")
	private String amountValidation;
	private boolean status;
	private int userinfo_id;
	private String reason;

	// loanpaiddetails table entity
	private int lpid;
	private LocalDateTime datetime;
	private int paidamount;
	private int loan_id;
	

	public Loan() {

	}

	// no id exist
	public Loan(LocalDateTime placedate, LocalDateTime approvedate, int amount, String amountValidation, boolean status,
			int userinfo_id, String reason, LocalDateTime datetime, int paidamount, int loan_id) {
		this.placedate = placedate;
		this.approvedate = approvedate;
		this.amount = amount;
		this.amountValidation = amountValidation;
		this.status = status;
		this.userinfo_id = userinfo_id;
		this.reason = reason;
		this.datetime = datetime;
		this.paidamount = paidamount;
		this.loan_id = loan_id;
	}

	// lpid exist

	public Loan(LocalDateTime placedate, LocalDateTime approvedate, int amount, String amountValidation, boolean status,
			int userinfo_id, String reason, int lpid, LocalDateTime datetime, int paidamount, int loan_id) {
		this.placedate = placedate;
		this.approvedate = approvedate;
		this.amount = amount;
		this.amountValidation = amountValidation;
		this.status = status;
		this.userinfo_id = userinfo_id;
		this.reason = reason;
		this.lpid = lpid;
		this.datetime = datetime;
		this.paidamount = paidamount;
		this.loan_id = loan_id;
	}

	// id lpid exist
	public Loan(int id, LocalDateTime placedate, LocalDateTime approvedate, int amount, String amountValidation,
			boolean status, int userinfo_id, String reason, int lpid, LocalDateTime datetime, int paidamount,
			int loan_id) {
		this.id = id;
		this.placedate = placedate;
		this.approvedate = approvedate;
		this.amount = amount;
		this.amountValidation = amountValidation;
		this.status = status;
		this.userinfo_id = userinfo_id;
		this.reason = reason;
		this.lpid = lpid;
		this.datetime = datetime;
		this.paidamount = paidamount;
		this.loan_id = loan_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getPlacedate() {
		return placedate;
	}

	public void setPlacedate(LocalDateTime placedate) {
		this.placedate = placedate;
	}

	public LocalDateTime getApprovedate() {
		return approvedate;
	}

	public void setApprovedate(LocalDateTime approvedate) {
		this.approvedate = approvedate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getAmountValidation() {
		return amountValidation;
	}

	public void setAmountValidation(String amountValidation) {
		this.amountValidation = amountValidation;
		
		if(amountValidation.matches("[0-9]+")) {
			this.amount = Integer.parseInt(amountValidation);
			logger.info("data parse successfull from amountValidation validation string to amount integer");
		}
		
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getUserinfo_id() {
		return userinfo_id;
	}

	public void setUserinfo_id(int userinfo_id) {
		this.userinfo_id = userinfo_id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getLpid() {
		return lpid;
	}

	public void setLpid(int lpid) {
		this.lpid = lpid;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public int getPaidamount() {
		return paidamount;
	}

	public void setPaidamount(int paidamount) {
		this.paidamount = paidamount;
	}

	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	@Override
	public String toString() {
		return "Loan [id=" + id + ", placedate=" + placedate + ", approvedate=" + approvedate + ", amount=" + amount
				+ ", amountValidation=" + amountValidation + ", status=" + status + ", userinfo_id=" + userinfo_id
				+ ", reason=" + reason + ", lpid=" + lpid + ", datetime=" + datetime + ", paidamount=" + paidamount
				+ ", loan_id=" + loan_id + "]";
	}
	
	
	
	
	

}
