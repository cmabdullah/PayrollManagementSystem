package com.abdullah.pms.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Attendance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	//@Basic(fetch = FetchType.EAGER)
	private int id;
	private String loginIpAddress;
	private LocalDate loginDate;
	private int workingHours;
	
	@OneToOne
	@JoinColumn(name="gradeId")
	private Grade grade;
	
	@OneToOne
	@JoinColumn(name="shiftId")
	private Shift shift;
	
	@ManyToOne
	@JoinColumn(name="userInfoId")
	private UserInfo userInfo;
	
	@OneToMany
	//(mappedBy = "attendance")
	@JsonIgnore
	private List<AttendanceLog> AttendanceLog;

}
