package com.abdullah.PayrollManagementSystem.dao;

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
	
	//seems this variable not needed
	private int leaveusers_id;

	private int monthlyWorkingHour;

	private int totalLeaveDays;

	private int  loanAmount;
	private boolean loanStatus;

	private int  paidamount;
	
	private String regular, bonus;
	
	private float totalsalary;
	
	LocalDateTime datemonthyear;
	
	
	//grade entity
	private int basic;
	private int medicalallowence;
	private int houserent;
	private int transport;
	private int lunch;
	private int study;
	
	private float installment;
	public Salary( ) {
		
	}
	public Salary(int userinfo_id, String username, String usertype, String status, String fullname, String address,
			String email, int phone, int loan_id, int grade_id, int leaveusers_id, int monthlyWorkingHour,
			int totalLeaveDays, int loanAmount, boolean loanStatus, int paidamount, String regular, String bonus,
			int basic, int medicalallowence, int houserent, int transport, int lunch, int study, float totalsalary, LocalDateTime datemonthyear, float installment) {
		this.userinfo_id = userinfo_id;
		this.username = username;
		this.usertype = usertype;
		this.status = status;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.loan_id = loan_id;
		this.grade_id = grade_id;
		this.leaveusers_id = leaveusers_id;
		this.monthlyWorkingHour = monthlyWorkingHour;
		this.totalLeaveDays = totalLeaveDays;
		this.loanAmount = loanAmount;
		this.loanStatus = loanStatus;
		this.paidamount = paidamount;
		this.regular = regular;
		this.bonus = bonus;
		this.basic = basic;
		this.medicalallowence = medicalallowence;
		this.houserent = houserent;
		this.transport = transport;
		this.lunch = lunch;
		this.study = study;
		this.totalsalary = totalsalary;
		this.datemonthyear = datemonthyear;
		this.installment = installment;
	}
	public Salary(int id, int userinfo_id, String username, String usertype, String status, String fullname,
			String address, String email, int phone, int loan_id, int grade_id, int leaveusers_id,
			int monthlyWorkingHour, int totalLeaveDays, int loanAmount, boolean loanStatus, int paidamount,
			String regular, String bonus, int basic, int medicalallowence, int houserent, int transport, int lunch,
			int study, float totalsalary, LocalDateTime datemonthyear, float installment) {
		this.id = id;
		this.userinfo_id = userinfo_id;
		this.username = username;
		this.usertype = usertype;
		this.status = status;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.loan_id = loan_id;
		this.grade_id = grade_id;
		this.leaveusers_id = leaveusers_id;
		this.monthlyWorkingHour = monthlyWorkingHour;
		this.totalLeaveDays = totalLeaveDays;
		this.loanAmount = loanAmount;
		this.loanStatus = loanStatus;
		this.paidamount = paidamount;
		this.regular = regular;
		this.bonus = bonus;
		this.basic = basic;
		this.medicalallowence = medicalallowence;
		this.houserent = houserent;
		this.transport = transport;
		this.lunch = lunch;
		this.study = study;
		this.totalsalary = totalsalary;
		this.datemonthyear = datemonthyear;
		this.installment = installment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserinfo_id() {
		return userinfo_id;
	}
	public void setUserinfo_id(int userinfo_id) {
		this.userinfo_id = userinfo_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public int getLoan_id() {
		return loan_id;
	}
	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}
	public int getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(int grade_id) {
		this.grade_id = grade_id;
	}
	public int getLeaveusers_id() {
		return leaveusers_id;
	}
	public void setLeaveusers_id(int leaveusers_id) {
		this.leaveusers_id = leaveusers_id;
	}
	public int getMonthlyWorkingHour() {
		return monthlyWorkingHour;
	}
	public void setMonthlyWorkingHour(int monthlyWorkingHour) {
		this.monthlyWorkingHour = monthlyWorkingHour;
	}
	public int getTotalLeaveDays() {
		return totalLeaveDays;
	}
	public void setTotalLeaveDays(int totalLeaveDays) {
		this.totalLeaveDays = totalLeaveDays;
	}
	public int getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
	public boolean isLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(boolean loanStatus) {
		this.loanStatus = loanStatus;
	}
	public int getPaidamount() {
		return paidamount;
	}
	public void setPaidamount(int paidamount) {
		this.paidamount = paidamount;
	}
	public String getRegular() {
		return regular;
	}
	public void setRegular(String regular) {
		this.regular = regular;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public int getBasic() {
		return basic;
	}
	public void setBasic(int basic) {
		this.basic = basic;
	}
	public int getMedicalallowence() {
		return medicalallowence;
	}
	public void setMedicalallowence(int medicalallowence) {
		this.medicalallowence = medicalallowence;
	}
	public int getHouserent() {
		return houserent;
	}
	public void setHouserent(int houserent) {
		this.houserent = houserent;
	}
	public int getTransport() {
		return transport;
	}
	public void setTransport(int transport) {
		this.transport = transport;
	}
	public int getLunch() {
		return lunch;
	}
	public void setLunch(int lunch) {
		this.lunch = lunch;
	}
	public int getStudy() {
		return study;
	}
	public void setStudy(int study) {
		this.study = study;
	}
	
	
	public float getTotalsalary() {
		return totalsalary;
	}
	public void setTotalsalary(float totalsalary) {
		this.totalsalary = totalsalary;
	}

	public LocalDateTime getDatemonthyear() {
		return datemonthyear;
	}
	public void setDatemonthyear(LocalDateTime datemonthyear) {
		this.datemonthyear = datemonthyear;
	}
	
	
	
	public float getInstallment() {
		return installment;
	}
	public void setInstallment(float installment) {
		this.installment = installment;
	}
	@Override
	public String toString() {
		return "Salary [id=" + id + ", userinfo_id=" + userinfo_id + ", username=" + username + ", usertype=" + usertype
				+ ", status=" + status + ", fullname=" + fullname + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + ", loan_id=" + loan_id + ", grade_id=" + grade_id + ", leaveusers_id="
				+ leaveusers_id + ", monthlyWorkingHour=" + monthlyWorkingHour + ", totalLeaveDays=" + totalLeaveDays
				+ ", loanAmount=" + loanAmount + ", loanStatus=" + loanStatus + ", paidamount=" + paidamount
				+ ", regular=" + regular + ", bonus=" + bonus + ", totalsalary=" + totalsalary + ", datemonthyear="
				+ datemonthyear + ", basic=" + basic + ", medicalallowence=" + medicalallowence + ", houserent="
				+ houserent + ", transport=" + transport + ", lunch=" + lunch + ", study=" + study + ", installment="
				+ installment + "]";
	}


	
}
