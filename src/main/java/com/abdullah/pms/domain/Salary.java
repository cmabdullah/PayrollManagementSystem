package com.abdullah.pms.domain;

import java.time.LocalDateTime;

public class Salary {
	
	private int id;
	private int userinfo_id;
	private String username;
	private String usertype;
	private String status;
	private String fullname;
	private String address;
	private String email;
	private int phone;

	private int loan_id;
	private int grade_id;

	// seems this variable not needed
	private int leaveusers_id;
	private int monthlyWorkingHour;
	private int totalLeaveDays;
	private int loanAmount;
	private boolean loanStatus;
	private int paidamount;
	private String regular, bonus;
	private float totalsalary;
	LocalDateTime datemonthyear;
	LocalDateTime joiningDate;

	// grade entity
	private int basic;
	private int medicalallowence;
	private int houserent;
	private int transport;
	private int lunch;
	private int study;

	private float installment;
	private int isLoanStatusPaid;
	private int amount;

}
