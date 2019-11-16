package com.abdullah.pms.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
public class Grade {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	@Basic(fetch = FetchType.EAGER)
	private int id;
	private String gradeType;
	private int basic;
	private int medicalAllowence;
	private int houseRent;
	private int transport;
	private int lunch;
	private int study;

}
