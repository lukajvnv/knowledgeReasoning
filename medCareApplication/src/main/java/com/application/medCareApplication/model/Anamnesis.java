package com.application.medCareApplication.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

@Entity
@Table(name = "anamnesis")
public class Anamnesis implements CaseComponent {

	@Id
	@Column(name = "anamnesis_id")
	private int anamnesisId;
	
	@Column(name = "Id_Pacijenta")
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
	
	@Column(name = "dopunska_ispitivanja")
	private String additionalExamination;
	
	@Column(name = "diagnosis")
	private String diagnosis;
	
	public Anamnesis(int anamnesisId, int patientId, String smoking, String alcohol, String employed,
			String workingCondition, String livingPlace, String livingObject, String pet, String additionalExamination,
			String diagnosis) {
		super();
		this.anamnesisId = anamnesisId;
		this.patientId = patientId;
		this.smoking = smoking;
		this.alcohol = alcohol;
		this.employed = employed;
		this.workingCondition = workingCondition;
		this.livingPlace = livingPlace;
		this.livingObject = livingObject;
		this.pet = pet;
		this.additionalExamination = additionalExamination;
		this.diagnosis = diagnosis;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Patient patient;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Resources> bolesti = new HashSet<Resources>();

	
	public Anamnesis() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Anamnesis(int anamnesisId, int patientId, String smoking, String alcohol, String employed,
			String workingCondition, String livingPlace, String livingObject, String pet, String additionalExamination,
			Patient patient, Set<Resources> bolesti) {
		super();
		this.anamnesisId = anamnesisId;
		this.patientId = patientId;
		this.smoking = smoking;
		this.alcohol = alcohol;
		this.employed = employed;
		this.workingCondition = workingCondition;
		this.livingPlace = livingPlace;
		this.livingObject = livingObject;
		this.pet = pet;
		this.additionalExamination = additionalExamination;
		this.patient = patient;
		this.bolesti = bolesti;
	}

	public Anamnesis(int anamnesisId, int patientId, String smoking, String alcohol, String employed,
			String workingCondition, String livingPlace, String livingObject, String pet, String additionalExamination,
			Patient patient) {
		super();
		this.anamnesisId = anamnesisId;
		this.patientId = patientId;
		this.smoking = smoking;
		this.alcohol = alcohol;
		this.employed = employed;
		this.workingCondition = workingCondition;
		this.livingPlace = livingPlace;
		this.livingObject = livingObject;
		this.pet = pet;
		this.additionalExamination = additionalExamination;
		this.patient = patient;
	}

	public Anamnesis(int anamnesisId, int patientId, String smoking, String alcohol, String employed,
			String workingCondition, String livingPlace, String livingObject, String pet,
			String additionalExamination) {
		super();
		this.anamnesisId = anamnesisId;
		this.patientId = patientId;
		this.smoking = smoking;
		this.alcohol = alcohol;
		this.employed = employed;
		this.workingCondition = workingCondition;
		this.livingPlace = livingPlace;
		this.livingObject = livingObject;
		this.pet = pet;
		this.additionalExamination = additionalExamination;
	}

	public Anamnesis(int anamnesisId, int patientId, String smoking, String alcohol, String employed,
			String workingCondition, String livingPlace, String livingObject, String pet) {
		super();
		this.anamnesisId = anamnesisId;
		this.patientId = patientId;
		this.smoking = smoking;
		this.alcohol = alcohol;
		this.employed = employed;
		this.workingCondition = workingCondition;
		this.livingPlace = livingPlace;
		this.livingObject = livingObject;
		this.pet = pet;
	}

	public Set<Resources> getBolesti() {
		return bolesti;
	}

	public void setBolesti(Set<Resources> bolesti) {
		this.bolesti = bolesti;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getAdditionalExamination() {
		return additionalExamination;
	}

	public void setAdditionalExamination(String additionalExamination) {
		this.additionalExamination = additionalExamination;
	}

	public int getAnamnesisId() {
		return anamnesisId;
	}

	public void setAnamnesisId(int anamnesisId) {
		this.anamnesisId = anamnesisId;
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
	
	@Override
	public String toString() {
		return "Anamnesis [anamnesisId=" + anamnesisId + ", patientId=" + patientId + ", smoking=" + smoking
				+ ", alcohol=" + alcohol + ", employed=" + employed + ", workingCondition=" + workingCondition
				+ ", livingPlace=" + livingPlace + ", livingObject=" + livingObject + ", pet=" + pet
				+ ", additionalExamination=" + additionalExamination + ", diagnosis=" + diagnosis + ", patient="
				+ patient + ", bolesti=" + bolesti + "]";
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}
