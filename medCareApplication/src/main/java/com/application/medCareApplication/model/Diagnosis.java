package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

@Entity
@Table(name = "diagnosis")
public class Diagnosis implements CaseComponent {
	
	@Id
	@Column(name = "diagnosis_Id")
	private int diagnosisId;
	
	@Column(name = "Id_Pacijenta")
	private int patientId;
	
	@Column(name = "Dijagnoza")
	private String diagnose;
	
	@Column(name = "Datum")
	private String date;
	
	@Column(name = "ct")
	private String ct;
	
	@Column(name = "rtg")
	private String rtg;
	
	@Column(name = "eritrociti")
	private String eritrociti;
	
	@Column(name = "leukociti")
	private String leukociti;
	
	@Column(name = "parametarske_inflamacije")
	private String parametarske_inflamacije;
	
	public Diagnosis(int diagnosisId, int patientId, String date, String ct, String rtg,
			String eritrociti, String leukociti, String parametarske_inflamacije, String diagnose) {
		super();
		this.diagnosisId = diagnosisId;
		this.patientId = patientId;	
		this.date = date;
		this.ct = ct;
		this.rtg = rtg;
		this.eritrociti = eritrociti;
		this.leukociti = leukociti;
		this.parametarske_inflamacije = parametarske_inflamacije;
		this.diagnose = diagnose;
	}

	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	public String getRtg() {
		return rtg;
	}

	public void setRtg(String rtg) {
		this.rtg = rtg;
	}

	public String getEritrociti() {
		return eritrociti;
	}

	public void setEritrociti(String eritrociti) {
		this.eritrociti = eritrociti;
	}

	public String getLeukociti() {
		return leukociti;
	}

	public void setLeukociti(String leukociti) {
		this.leukociti = leukociti;
	}

	public String getParametarske_inflamacije() {
		return parametarske_inflamacije;
	}

	public void setParametarske_inflamacije(String parametarske_inflamacije) {
		this.parametarske_inflamacije = parametarske_inflamacije;
	}

	public void setPrametarske_inflamacije(String prametarske_inflamacije) {
		this.parametarske_inflamacije = prametarske_inflamacije;
	}

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
				+ ", date=" + date + ", ct=" + ct + ", rtg=" + rtg + ", eritrociti=" + eritrociti + ", leukociti="
				+ leukociti + ", parametarske_inflamacije=" + parametarske_inflamacije + "]";
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

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
