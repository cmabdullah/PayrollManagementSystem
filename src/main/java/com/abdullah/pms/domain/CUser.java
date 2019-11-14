package com.abdullah.pms.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
	@GeneratedValue
	private int id;
	
	//@Size(min=6, message="Enter at least 6 Characters...")
	private String username;
	private String password;
	
}
