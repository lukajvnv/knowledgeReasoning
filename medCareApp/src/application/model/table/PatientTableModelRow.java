package application.model.table;

import java.util.Vector;

import application.model.Patient;

public class PatientTableModelRow {

	private Patient patient;
	//private Map<String,String> rowValues = new HashMap<String, String>();
	
	public PatientTableModelRow(Patient patient) {
		this.patient = patient;
	}

	/**
	 * Dodavanje jednog obeležja torke sa njenom vrednosti u kolekciju obeležja torke
	 * @param key - id obeležja
	 * @param object - vrednost obeležja
	 */
	/*public void addColumnValue(String key, String object){
		rowValues.put(key, object);
	}*/

	
	/**
	 * Konvertovanje kolekcije obeležja torke u niz Objekata
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
	 * Dobavljanje kolekcije id obeležja-vrednost za obeležja koja èine kljuè 
	 * @param labelKeys - kolekcija obeležja kljuèa
	 * @return - mapa koja se sastoji od parova (identifikator obeležja, sadržaj obeležje sa datim identifikatorom)
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
