package com.abdullah.pms.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class CUser {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //add this 
	//@Column(name = "id", unique = true, nullable = false)
	@Basic(fetch = FetchType.EAGER)
	private int id;
	
	//@Size(min=6, message="Enter at least 6 Characters...")
	private String username;
	private String password;
	
}
