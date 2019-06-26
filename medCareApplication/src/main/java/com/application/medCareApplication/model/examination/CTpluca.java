package com.application.medCareApplication.model.examination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ct_pluca")
public class CTpluca {
	
	@Id
	@Column(name = "ct_id")
	private Integer id;
	
	@Column(name = "id_pacijenta")
	private Integer patientId;
	
	@Column(name = "ct")
	private String ct;
	
	

	public CTpluca() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CTpluca(Integer id, Integer patientId, String ct) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.ct = ct;
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



	public String getCt() {
		return ct;
	}



	public void setCt(String ct) {
		this.ct = ct;
	}



	@Override
	public String toString() {
		return "CTpluca [id=" + id + ", patientId=" + patientId + ", ct=" + ct + "]";
	}

	
	
	
}
