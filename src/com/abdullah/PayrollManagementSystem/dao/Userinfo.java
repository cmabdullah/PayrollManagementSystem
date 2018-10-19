package com.abdullah.PayrollManagementSystem.dao;

public class Userinfo {
	private int id;
	private String username;
	private String password;
	private String usertype;
	private String status;
	private String fullname;
	private String address;
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
