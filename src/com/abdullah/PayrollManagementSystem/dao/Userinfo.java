package com.abdullah.PayrollManagementSystem.dao;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.abdullah.PayrollManagementSystem.validation.ValidEmail;

public class Userinfo {
	private int id;
	@Size(min=2  ,max=100 , message="Name must be between 2 to 100")
	private String username;
	@Size(min=3  ,max=250, message="username must be between 3 to 250")
	private String password;
	
	private String usertype;
	
	private String status;
	@Size(min=4  ,max=250, message="Fullname must be between 4 to 250")
	private String fullname;
	@Size(min=4  ,max=250, message="address must be between 4 to 250")
	private String address;
	@NotNull
	//@Pattern(regexp=".*\\@.*\\..*", message = "Not a valied email address")
	// ValideEmail Annotation created by us, see import com.spring.web.validation.ValidEmail; package
	@ValidEmail
	private String email;
	private int phone;
	
	public Userinfo( ) {
		
	}
	
	public Userinfo( String username, String password, String usertype, String status, String fullname,
			String address, String email, int phone) {

		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.status = status;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}
	
	public Userinfo(int id, String username, String password, String usertype, String status, String fullname,
			String address, String email, int phone) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
		this.status = status;
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
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "Userinfo [id=" + id + ", username=" + username + ", password=" + password + ", usertype=" + usertype
				+ ", status=" + status + ", fullname=" + fullname + ", address=" + address + ", email=" + email
				+ ", phone=" + phone + "]";
	}
	
}
