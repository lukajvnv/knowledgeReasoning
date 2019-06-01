package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anamnesis")
public class Anamnesis {
	@Id
	private int anamnesisId;
	
	@Column(name = "IdPacijenta")
	private int patientId;
	
	@Column(name = "Temperatura")
	private String bodyTemperature;
	
	@Column(name = "Disajni_zvuk")
	private String respiratorySound;
	
	@Column(name = "Sumovi")
	private String respriratoryNoise;

	public Anamnesis() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Anamnesis(int patientId, String bodyTemperature, String respiratorySound, String respriratoryNoise) {
		super();
		this.patientId = patientId;
		this.bodyTemperature = bodyTemperature;
		this.respiratorySound = respiratorySound;
		this.respriratoryNoise = respriratoryNoise;
	}



	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(String bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public String getRespiratorySound() {
		return respiratorySound;
	}

	public void setRespiratorySound(String respiratorySound) {
		this.respiratorySound = respiratorySound;
	}

	public String getRespriratoryNoise() {
		return respriratoryNoise;
	}

	public void setRespriratoryNoise(String respriratoryNoise) {
		this.respriratoryNoise = respriratoryNoise;
	}
}
