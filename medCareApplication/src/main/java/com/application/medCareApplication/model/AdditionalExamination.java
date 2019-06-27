package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "additional_examination")
public class AdditionalExamination {
	
	@Id
	@Column(name = "additional_examination_id")
	private int id;
	
	@Column(name = "Id_Pacijenta")
	private int patientId;
	
	@Column(name = "id_rtg")
	private int idRtg;
	
	@Column(name = "id_ct")
	private int idCt;
	
	@Column(name = "id_ks")
	private int idKs;
	
	@Column(name = "id_uz")
	private int idUz;
	
	@Column(name = "diagnosis")
	private String diagnosis;
	
	public AdditionalExamination() {
		
	}

	public AdditionalExamination(int id, int patientId, int idRtg, int idCt, int idKs, int idUz,
			String diagnosis) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.idRtg = idRtg;
		this.idCt = idCt;
		this.idKs = idKs;
		this.idUz = idUz;
		this.diagnosis = diagnosis;
	}

	public int getAnamnesisId() {
		return id;
	}

	public void setAnamnesisId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getIdRtg() {
		return idRtg;
	}

	public void setIdRtg(int idRtg) {
		this.idRtg = idRtg;
	}

	public int getIdCt() {
		return idCt;
	}

	public void setIdCt(int idCt) {
		this.idCt = idCt;
	}

	public int getIdKs() {
		return idKs;
	}

	public void setIdKs(int idKs) {
		this.idKs = idKs;
	}

	public int getIdUz() {
		return idUz;
	}

	public void setIdUz(int idUz) {
		this.idUz = idUz;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	
	

}
