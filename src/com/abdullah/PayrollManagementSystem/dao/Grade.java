package com.abdullah.PayrollManagementSystem.dao;

public class Grade {
	
	private int id;
	private int basic;
	private int medicalallowence;
	private int houserent;
	private int transport;
	private int lunch;
	private int study;
	
	public Grade() {
		
	}

	public Grade(int basic, int medicalallowence, int houserent, int transport, int lunch,  int study) {
		this.basic = basic;
		this.medicalallowence = medicalallowence;
		this.houserent = houserent;
		this.transport = transport;
		this.lunch = lunch;
		this.study = study;
	}

	public Grade(int id, int basic, int medicalallowence, int houserent, int transport, int lunch,
			int study) {
		this.id = id;
		this.basic = basic;
		this.medicalallowence = medicalallowence;
		this.houserent = houserent;
		this.transport = transport;
		this.lunch = lunch;
		this.study = study;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public int getMedicalallowence() {
		return medicalallowence;
	}

	public void setMedicalallowence(int medicalallowence) {
		this.medicalallowence = medicalallowence;
	}

	public int getHouserent() {
		return houserent;
	}

	public void setHouserent(int houserent) {
		this.houserent = houserent;
	}

	public int getTransport() {
		return transport;
	}

	public void setTransport(int transport) {
		this.transport = transport;
	}

	public int getLunch() {
		return lunch;
	}

	public void setLunch(int lunch) {
		this.lunch = lunch;
	}


	public int getStudy() {
		return study;
	}

	public void setStudy(int study) {
		this.study = study;
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", basic=" + basic + ", medicalallowence=" + medicalallowence + ", houserent="
				+ houserent + ", transport=" + transport + ", lunch=" + lunch + ", study=" + study + "]";
	}

}
