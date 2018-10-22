package com.abdullah.PayrollManagementSystem.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.abdullah.PayrollManagementSystem.validation.ValidEmail;

public class Userinfo {
	private int id;
	@NotBlank
	@Size(min=2  ,max=15)
	@Pattern(regexp="^\\w{4,}$")
	private String username;
	@NotBlank
	@Size(min=3  ,max=15)
	@Pattern(regexp="^\\S+$")
	private String password;
	
	private boolean enabled;
	
	private String authority;

	@Size(min=4  ,max=250)
	private String fullname;
	@Size(min=4  ,max=250)
	private String address;
	@NotNull
	//@Pattern(regexp=".*\\@.*\\..*", message = "Not a valied email address")
	// ValideEmail Annotation created by us, see import com.spring.web.validation.ValidEmail; package
	@ValidEmail
	private String email;
	
	private int phone;

	public Userinfo( ) {
		
	}

	public Userinfo( String username, String password, boolean enabled, String authority, String fullname,
			String address, String email, int phone) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public Userinfo(int id, String username, String password, boolean enabled, String authority, String fullname,
			String address, String email, int phone) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Userinfo [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", authority=" + authority + ", fullname=" + fullname + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + "]";
	}
}
