package com.application.medCareApplication.model.examination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "krvna_slika")
public class KrvnaSlika {
	
	@Id
	@Column(name = "krvna_id")
	private Integer id;
	
	@Column(name = "id_pacijenta")
	private Integer patientId;
	
	@Column(name = "leukociti")
	private String leukociti;
	
	@Column(name = "eritrociti")
	private String eritrociti;
	
	@Column(name = "parametarske_inflamacije")
	private String parametarske_inflamacije;

	public KrvnaSlika() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KrvnaSlika(Integer id, Integer patientId, String leukociti, String eritrociti,
			String parametarske_inflamacije) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.leukociti = leukociti;
		this.eritrociti = eritrociti;
		this.parametarske_inflamacije = parametarske_inflamacije;
	}

	public KrvnaSlika(Integer patientId, String leukociti, String eritrociti, String parametarske_inflamacije) {
		super();
		this.patientId = patientId;
		this.leukociti = leukociti;
		this.eritrociti = eritrociti;
		this.parametarske_inflamacije = parametarske_inflamacije;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getLeukociti() {
		return leukociti;
	}

	public void setLeukociti(String leukociti) {
		this.leukociti = leukociti;
	}

	public String getEritrociti() {
		return eritrociti;
	}

	public void setEritrociti(String eritrociti) {
		this.eritrociti = eritrociti;
	}

	public String getParametarske_inflamacije() {
		return parametarske_inflamacije;
	}

	public void setParametarske_inflamacije(String parametarske_inflamacije) {
		this.parametarske_inflamacije = parametarske_inflamacije;
	}

	@Override
	public String toString() {
		return "KrvnaSlika [id=" + id + ", patientId=" + patientId + ", leukociti=" + leukociti + ", eritrociti="
				+ eritrociti + ", parametarske_inflamacije=" + parametarske_inflamacije + "]";
	}
	
	
	
}
