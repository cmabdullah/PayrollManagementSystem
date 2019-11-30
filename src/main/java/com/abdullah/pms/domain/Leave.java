package com.abdullah.pms.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="leaves")
public class Leave {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	private int id;
	private String reason;
	private Date entryFrom;
	private Date entryTo;
	private int status;
	private String leaveType;
	private int totalLeaveDays;
	private int deniedLeaveRequest;
	
	@ManyToOne
	@JoinColumn(name="userInfoId")
	private UserInfo userInfo;
	
}
