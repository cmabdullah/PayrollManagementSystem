package com.abdullah.PayrollManagementSystem.dao;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;


import com.abdullah.PayrollManagementSystem.validation.ValidEmail;

public class UserinfoUpdateableData {
	private int id;
	
	@NotBlank
	@Size(min=3  ,max=15 , message="Name must be between 3 to 15")
	@Pattern(regexp="^\\S+$")
	private String password;

	//javascript validation perpose
	private String confirmpassword;
	
	
	@Size(min=4  ,max=250)
	private String fullname;
	@Size(min=4  ,max=250)
	private String address;
	//@NotNull
	
	//@ValidEmail
	private String email;
	
	private int phone;
	
	
	public UserinfoUpdateableData( ) {
		
	}


	public UserinfoUpdateableData(String password, String confirmpassword, String fullname, String address,
			String email, int phone) {
		
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.fullname = fullname;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}


	public UserinfoUpdateableData(int id, String password, String confirmpassword, String fullname, String address,
			String email, int phone) {

		this.id = id;
		this.password = password;
		this.confirmpassword = confirmpassword;
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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmpassword() {
		return confirmpassword;
	}


	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
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
		return "UserinfoUpdateableData [id=" + id + ", password=" + password + ", confirmpassword=" + confirmpassword
				+ ", fullname=" + fullname + ", address=" + address + ", email=" + email + ", phone=" + phone + "]";
	}
	
	

}