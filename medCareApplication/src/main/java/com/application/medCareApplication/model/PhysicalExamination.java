package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "physical_examination")
public class PhysicalExamination {

	@Id
	private int physicalExaminationId;
	
	@Column(name = "IdPacijenta")
	private int patientId;
	
	@Column(name = "Pusenje")
	private String smoking;
	
	@Column(name = "Alkohol")
	private String alcohol;
	
	@Column(name = "Stanje")
	private String employed;
	
	@Column(name = "Tezina")
	private String workingCondition;
	
	@Column(name = "Zivi")
	private String livingPlace;
	
	@Column(name = "Stanuje")
	private String livingObject;
	
	@Column(name = "Ljubimci")
	private String pet;
	
	public PhysicalExamination() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhysicalExamination(int patientId, String smoking, String alcohol, String employed, String workingCondition,
			String livingPlace, String livingObject, String pet) {
		super();
		this.patientId = patientId;
		this.smoking = smoking;
		this.alcohol = alcohol;
		this.employed = employed;
		this.workingCondition = workingCondition;
		this.livingPlace = livingPlace;
		this.livingObject = livingObject;
		this.pet = pet;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getSmoking() {
		return smoking;
	}

	public void setSmoking(String smoking) {
		this.smoking = smoking;
	}

	public String getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

	public String getEmployed() {
		return employed;
	}

	public void setEmployed(String employed) {
		this.employed = employed;
	}

	public String getWorkingCondition() {
		return workingCondition;
	}

	public void setWorkingCondition(String workingCondition) {
		this.workingCondition = workingCondition;
	}

	public String getLivingPlace() {
		return livingPlace;
	}

	public void setLivingPlace(String livingPlace) {
		this.livingPlace = livingPlace;
	}

	public String getLivingObject() {
		return livingObject;
	}

	public void setLivingObject(String livingObject) {
		this.livingObject = livingObject;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

}
