package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "therapy")
public class Therapy {
	
	@Id
	@Column(name = "therapy_Id")
	private int therapyId;
	
	@Column(name = "Id_Pacijenta")
	private int patientId;
	
	@Column(name = "Dijagnoza")
	private String diagnose;
	
	@Column(name = "Terapija")
	private String therapy;
	
	@Column(name = "Datum")
	private String date;

	public Therapy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Therapy(int therapyId, int patientId, String diagnose, String therapy, String date) {
		super();
		this.therapyId = therapyId;
		this.patientId = patientId;
		this.diagnose = diagnose;
		this.therapy = therapy;
		this.date = date;
	}

	public int getTherapyId() {
		return therapyId;
	}

	public void setTherapyId(int therapyId) {
		this.therapyId = therapyId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getTherapy() {
		return therapy;
	}

	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Therapy [therapyId=" + therapyId + ", patientId=" + patientId + ", diagnose=" + diagnose + ", therapy="
				+ therapy + ", date=" + date + "]";
	}

	
	//Da se cuva neki trag kako se doslo do zakljucka...
	
	
}
