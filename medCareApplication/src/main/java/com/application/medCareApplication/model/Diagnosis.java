package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diagnosis")
public class Diagnosis {
	
	@Id
	@Column(name = "diagnosis_Id")
	private int diagnosisId;
	
	@Column(name = "Id_Pacijenta")
	private int patientId;
	
	
	@Column(name = "Dijagnoza")
	private String diagnose;
	
	@Column(name = "Datum")
	private String date;
	
	//Da se cuva neki trag kako se doslo do zakljucka...

	public Diagnosis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Diagnosis(int diagnosisId, int patientId, String diagnose, String date) {
		super();
		this.diagnosisId = diagnosisId;
		this.patientId = patientId;
		this.diagnose = diagnose;
		this.date = date;
	}
	
	

	@Override
	public String toString() {
		return "Diagnosis [diagnosisId=" + diagnosisId + ", patientId=" + patientId + ", diagnose=" + diagnose
				+ ", date=" + date + "]";
	}

	public int getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
}
