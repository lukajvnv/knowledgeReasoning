package com.application.medCareApplication.model;
	import java.io.Serializable;
import java.util.Vector;

	public class Patient implements Serializable {
		
		private Integer patientId;
		
		private String firstName;
		private String lastName;
		private String jmbg;
		private String dateOfBirth;
		private String address;
		private String telephoneNumber;
		
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
					+ telephoneNumber + "]";
		}
		
		
	}

