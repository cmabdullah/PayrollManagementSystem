package com.abdullah.PayrollManagementSystem.dao;

import com.abdullah.PayrollManagementSystem.validation.ValidEmail;

public class Email {
	
	private int id;
	@ValidEmail
	private String email;
	
	private String text;

	
	public Email() {

		// TODO Auto-generated constructor stub
	}
	
	
	
	public Email(String email, String text) {

		this.email = email;
		this.text = text;
	}



	public Email(int id, String email, String text) {
		this.id = id;
		this.email = email;
		this.text = text;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	@Override
	public String toString() {
		return "Email [id=" + id + ", email=" + email + ", text=" + text + "]";
	}

}
