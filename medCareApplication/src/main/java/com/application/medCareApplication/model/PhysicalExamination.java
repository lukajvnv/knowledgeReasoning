package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

@Entity
@Table(name = "physical_examination")
public class PhysicalExamination implements CaseComponent {
	
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
	
	@Column(name = "dopunska_ispitivanja")
	private String additionalExamination;
	
	@Column(name = "diagnosis")
	private String diagnosis;

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public PhysicalExamination() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PhysicalExamination(int physicalExaminationId, int patientId, String bodyTemperature,
			String respiratorySound, String respiratoryNoise, String additionalExamination, String diagnosis) {
		super();
		this.physicalExaminationId = physicalExaminationId;
		this.patientId = patientId;
		this.bodyTemperature = bodyTemperature;
		this.respiratorySound = respiratorySound;
		this.respiratoryNoise = respiratoryNoise;
		this.additionalExamination = additionalExamination;
		this.diagnosis = diagnosis;
	}

	public PhysicalExamination(int physicalExaminationId, int patientId, String bodyTemperature,
			String respiratorySound, String respiratoryNoise, String additionalExamination) {
		super();
		this.physicalExaminationId = physicalExaminationId;
		this.patientId = patientId;
		this.bodyTemperature = bodyTemperature;
		this.respiratorySound = respiratorySound;
		this.respiratoryNoise = respiratoryNoise;
		this.additionalExamination = additionalExamination;
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



	public int getPhysicalExaminationId() {
		return physicalExaminationId;
	}

	public void setPhysicalExaminationId(int physicalExaminationId) {
		this.physicalExaminationId = physicalExaminationId;
	}

	public String getRespiratoryNoise() {
		return respiratoryNoise;
	}

	public void setRespiratoryNoise(String respiratoryNoise) {
		this.respiratoryNoise = respiratoryNoise;
	}

	public String getAdditionalExamination() {
		return additionalExamination;
	}

	public void setAdditionalExamination(String additionalExamination) {
		this.additionalExamination = additionalExamination;
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
		return "PhysicalExamination [physicalExaminationId=" + physicalExaminationId + ", patientId=" + patientId
				+ ", bodyTemperature=" + bodyTemperature + ", respiratorySound=" + respiratorySound
				+ ", respiratoryNoise=" + respiratoryNoise + ", additionalExamination=" + additionalExamination
				+ ", diagnosis=" + diagnosis + "]";
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
