package com.abdullah.pms.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
@Entity
public class Attendance {
	@Id
	private int id;
	private String loginIpAddress;
	private LocalDateTime loginTime;
	private LocalDateTime logoutTime;
	private int workingHours;
	private int userId;
	private int shiftId;

}
