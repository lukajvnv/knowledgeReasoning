package com.application.medCareApplication.model.examination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ultra_zvuk") // ultrazvuk plucne maramice
public class UltraZvuk {

	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "id_pacijenta")
	private Integer patientId;
	
	@Column(name = "dubina_izliva")
	private String dubina_izliva;
	
	@Column(name = "visina_izliva")
	private String visina_izliva;
	
	@Column(name = "gustina_izliva")
	private String gustina_izliva;
	
	@Column(name = "mesto_punkcije")
	private String mesto_punkcije;

	public UltraZvuk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UltraZvuk(Integer id, Integer patientId, String dubina_izliva, String visina_izliva, String gustina_izliva,
			String mesto_punkcije) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.dubina_izliva = dubina_izliva;
		this.visina_izliva = visina_izliva;
		this.gustina_izliva = gustina_izliva;
		this.mesto_punkcije = mesto_punkcije;
	}

	public UltraZvuk(Integer patientId, String dubina_izliva, String visina_izliva, String gustina_izliva,
			String mesto_punkcije) {
		super();
		this.patientId = patientId;
		this.dubina_izliva = dubina_izliva;
		this.visina_izliva = visina_izliva;
		this.gustina_izliva = gustina_izliva;
		this.mesto_punkcije = mesto_punkcije;
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

	public String getDubina_izliva() {
		return dubina_izliva;
	}

	public void setDubina_izliva(String dubina_izliva) {
		this.dubina_izliva = dubina_izliva;
	}

	public String getVisina_izliva() {
		return visina_izliva;
	}

	public void setVisina_izliva(String visina_izliva) {
		this.visina_izliva = visina_izliva;
	}

	public String getGustina_izliva() {
		return gustina_izliva;
	}

	public void setGustina_izliva(String gustina_izliva) {
		this.gustina_izliva = gustina_izliva;
	}

	public String getMesto_punkcije() {
		return mesto_punkcije;
	}

	public void setMesto_punkcije(String mesto_punkcije) {
		this.mesto_punkcije = mesto_punkcije;
	}
	
	
	
}
