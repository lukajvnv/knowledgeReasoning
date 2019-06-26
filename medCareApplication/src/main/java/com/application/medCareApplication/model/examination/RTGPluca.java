package com.application.medCareApplication.model.examination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rtg_pluca")
public class RTGPluca {

	@Id
	@Column(name = "rtg_id")
	private Integer id;
	
	@Column(name = "id_pacijenta")
	private Integer patientId;
	
	@Column(name = "rtg") //kakav je rtg: normalan ili patoloski
	private String rtg;
	
	@Column(name = "lezije") //da li ima homogene ili inhomogene lezije
	private String homogene_lezije;


	public RTGPluca() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RTGPluca(Integer id, Integer patientId, String rtg, String homogene_lezije) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.rtg = rtg;
		this.homogene_lezije = homogene_lezije;
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


	public String getRtg() {
		return rtg;
	}


	public void setRtg(String rtg) {
		this.rtg = rtg;
	}


	public String getHomogene_lezije() {
		return homogene_lezije;
	}


	public void setHomogene_lezije(String homogene_lezije) {
		this.homogene_lezije = homogene_lezije;
	}


	@Override
	public String toString() {
		return "RTGPluca [id=" + id + ", patientId=" + patientId + ", rtg=" + rtg + ", homogene_lezije="
				+ homogene_lezije + "]";
	}


	
}
