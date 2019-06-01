package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "physical_examination")
public class PhysicalExamination {
	
	@Id
	@Column(name = "physical_Examination_Id")
	private int physicalExaminationId;
	
	@Column(name = "Id_Pacijenta")
	private int patientId;
	
	@Column(name = "Temperatura")
	private String bodyTemperature;
	
	@Column(name = "Disajni_zvuk")
	private String respiratorySound;
	
	@Column(name = "Sumovi")
	private String respiratoryNoise;

	public PhysicalExamination() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PhysicalExamination(int physicalExaminationId, int patientId, String bodyTemperature,
			String respiratorySound, String respiratoryNoise) {
		super();
		this.physicalExaminationId = physicalExaminationId;
		this.patientId = patientId;
		this.bodyTemperature = bodyTemperature;
		this.respiratorySound = respiratorySound;
		this.respiratoryNoise = respiratoryNoise;
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
		return respiratoryNoise;
	}

	public void setRespriratoryNoise(String respriratoryNoise) {
		this.respiratoryNoise = respriratoryNoise;
	}



	@Override
	public String toString() {
		return "[temperatura=" + bodyTemperature + ", disajniZvuk=" + respiratorySound
				+ ", sumovi=" + respiratoryNoise + "]";
	}
}
