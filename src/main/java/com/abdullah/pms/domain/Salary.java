package com.abdullah.pms.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Salary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	private int id;
	
	@ManyToOne
	@JoinColumn(name="userInfoId")
	private UserInfo userInfo;
	@OneToOne
	@JoinColumn(name="loanId")
	private Loan loan;
	@OneToOne
	@JoinColumn(name="gradeId")
	private Grade grade;
	@ManyToOne
	@JoinColumn(name="leaveId")
	private Leave leave;
	
	private int monthlyWorkingHour;
	private int totalLeaveDays;
	private String regular;
	private String bonus;
	private float totalSalary;
	private float netSalary;
	private Date paymentDate;

}
