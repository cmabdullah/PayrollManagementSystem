package com.abdullah.pms.domain;

import java.time.LocalDateTime;

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
public class Shift {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	//@Column(name = "id", unique = true, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	private int id;
	private String name;
	private int duration;
	private String startTime;
	private String endTime;
	private String type;

}
