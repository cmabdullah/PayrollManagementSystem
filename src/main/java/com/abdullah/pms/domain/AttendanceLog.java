package com.abdullah.pms.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class AttendanceLog {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String ipAddress;
	private LocalDateTime loginTime;
	private LocalDateTime logoutTime;
	private int userId;
	private int shiftId;
	
	@ManyToOne
	@JoinColumn(name="attendanceId")
	private Attendance attendance;
}
