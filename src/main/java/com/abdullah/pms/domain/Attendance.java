package com.abdullah.pms.domain;

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
	private int aid;
	private String name;

}
