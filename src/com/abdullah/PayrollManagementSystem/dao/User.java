package com.abdullah.PayrollManagementSystem.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.abdullah.PayrollManagementSystem.validation.ValidEmail;

public class User {
	//user form validation added
	@NotBlank(message="username cannot be blank")
	@Size(min=4  ,max=15)
	@Pattern(regexp="^\\w{4,}$",message="Username only consist number letter underscore")
	private String username;
	@NotBlank(message="Password cannot be blank.....")
	@Size(min=8  ,max=15, message="Password must be between 8 to 15 long")
	@Pattern(regexp="^\\S+$",message="Password cannot contain any space")	
	private String password;
	@ValidEmail
	private String email;
	private boolean enabled;
	private String authority;
	
	public User() {
		
	}

	public User(String username, String password, String email, boolean enabled, String authority) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}