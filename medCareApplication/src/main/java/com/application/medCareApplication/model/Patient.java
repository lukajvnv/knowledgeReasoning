package com.application.medCareApplication.model;
	import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

	@Entity
	@Table(name = "patient")
	public class Patient{
		
		@Id
	    @Column(name = "Id_Pacijenta", unique = true)
		private Integer patientId;
		
		@Column(name = "Ime")
		private String firstName;
		
		@Column(name = "Prezime")
		private String lastName;
		
		@Column(name = "Jmbg")
		private String jmbg;
		
		@Column(name = "Datum_rodjenja")
		private String dateOfBirth;
		
		@Column(name = "Adresa")
		private String address;
		
		@Column(name = "Telefon")
		private String telephoneNumber;
		
		//mapiranje pacijenata i ranijih bolesti
		@ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "ranije_bolesti", 
	    	joinColumns = @JoinColumn(name = "patient_id"), 
	    	inverseJoinColumns = @JoinColumn(name = "diagnosis_id"))
		@OnDelete(action = OnDeleteAction.CASCADE) 
	    private Set<Resources> ranijeBolesti = new HashSet<>();
		
		//mapiranje pacijenata i bolesti iz porodice
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "porodicne_bolesti", 
		joinColumns = @JoinColumn(name = "patient_id"), 
		inverseJoinColumns = @JoinColumn(name = "diagnosis_id"))
		@OnDelete(action = OnDeleteAction.CASCADE) 
		private Set<Resources> porodicneBolesti = new HashSet<>();
		
		@OneToMany(mappedBy = "patient")
		private Set<Anamnesis> anamnesis = new HashSet<Anamnesis>();
		
		
		
		@Transient
		private Vector<Object> structuredData;
		
		public Patient() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Patient(Integer patientId, String firstName, String lastName, String jmbg, String dateOfBirth,
				String address, String telephoneNumber) {
			super();
			this.patientId = patientId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.jmbg = jmbg;
			this.dateOfBirth = dateOfBirth;
			this.address = address;
			this.telephoneNumber = telephoneNumber;
			
			createVectorOfData();
		}

		private void createVectorOfData() {
			Vector<Object> objects = new Vector<Object>();
			objects.add(getPatientId());
			objects.add(getFirstName());
			objects.add(getLastName());
			objects.add(getDateOfBirth());
			objects.add(getJmbg());
			objects.add(getAddress());
			objects.add(getTelephoneNumber());
			setStructuredData(objects);
		}
		
		public Vector<Object> getStructuredData() {
			return structuredData;
		}

		public void setStructuredData(Vector<Object> structuredData) {
			this.structuredData = structuredData;
		}

		public Integer getPatientId() {
			return patientId;
		}

		public void setPatientId(Integer patientId) {
			this.patientId = patientId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getJmbg() {
			return jmbg;
		}

		public void setJmbg(String jmbg) {
			this.jmbg = jmbg;
		}

		public String getDateOfBirth() {
			return dateOfBirth;
		}

		public void setDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getTelephoneNumber() {
			return telephoneNumber;
		}

		public void setTelephoneNumber(String telephoneNumber) {
			this.telephoneNumber = telephoneNumber;
		}

		@Override
		public String toString() {
			return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName + ", jmbg="
					+ jmbg + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", telephoneNumber="
					+ telephoneNumber + ", ranijeBolesti=" + ranijeBolesti + ", porodicneBolesti=" + porodicneBolesti
					+ "]";
		}
		
		
	}

