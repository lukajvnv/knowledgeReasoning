package com.application.medCareApplication.model.table;

import java.util.Vector;

import com.application.medCareApplication.model.Patient;

public class PatientTableModelRow {

	private Patient patient;
	//private Map<String,String> rowValues = new HashMap<String, String>();
	
	public PatientTableModelRow(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Dodavanje jednog obele�ja torke sa njenom vrednosti u kolekciju obele�ja torke
	 * @param key - id obele�ja
	 * @param object - vrednost obele�ja
	 */
	/*public void addColumnValue(String key, String object){
		rowValues.put(key, object);
	}*/

	
	/**
	 * Konvertovanje kolekcije obele�ja torke u niz Objekata
	 * @return - niz objekata Object klase
	 */
	/*public Object[] addRowToTable(){
		return rowValues.values().toArray();
	}*/

	public Vector<Object> addRowToTable(){
		return getPatient().getStructuredData();
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	/**
	 * Dobavljanje kolekcije id obele�ja-vrednost za obele�ja koja �ine klju� 
	 * @param labelKeys - kolekcija obele�ja klju�a
	 * @return - mapa koja se sastoji od parova (identifikator obele�ja, sadr�aj obele�je sa datim identifikatorom)
	 */
	/*public Map<String, String> getKeyValues(List<Label> labelKeys){
		Map<String, String> keyValues= new HashMap<String, String>();
		for(Label labelKey : labelKeys){
			String keyId = labelKey.getId();
			keyValues.put(keyId, (String) rowValues.get(keyId));
		}
		return keyValues;
	}*/
}
