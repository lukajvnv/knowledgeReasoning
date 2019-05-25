package application.model.table;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import application.model.Patient;
import utils.PatientsColumn;

public class PatientsTableModel extends DefaultTableModel {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4642477034681718563L;
	//private Map<String, Label> headerColumns;
	private ArrayList<PatientTableModelRow> rowsData; 
	
	public PatientsTableModel(){
		this.rowsData = new ArrayList<PatientTableModelRow>();
		
		initTableHeader();
		collectData();
	}
	
	/**
	 * Metoda kojom se na osnovu obelezja koja cine resurs formiraju kolone tabele
	 */
	private void initTableHeader(){
		//Inicijalizacija kolona
		addColumn(PatientsColumn.patientId);
		addColumn(PatientsColumn.firstNameColumn);
		addColumn(PatientsColumn.lastNameColumn);
		addColumn(PatientsColumn.dateOfBirthColumn);
		addColumn(PatientsColumn.jmbgColumn);
		addColumn(PatientsColumn.addressColumn);
		addColumn(PatientsColumn.telephoneNumberColumn);
	}
	
	public void collectData() {
		setRowCount(0);
		for(int i = 0; i < 15; i++) {
			Patient p = new Patient(i, "ime" + i, "prezime" + i, "jmbg" + i, "15.5.2018", "adresa" + i, "telefon" + i);
			PatientTableModelRow ptr = new PatientTableModelRow(p);
			rowsData.add(ptr);
			addRow(ptr.addRowToTable());
		}
	}
	
	public void create() {
		
	}
	
	public void read() {
		
	}
	
	public void update() {
		
	}
	
	
	
	
	/**
	 * Vraca selektovanu torku
	 * @param selectedIndex - redni broj selektovane torke
	 * @return - objekat TableModelRow koji je selektovan
	 */
	public PatientTableModelRow getSelectedRow(int selectedIndex) {
		return rowsData.get(selectedIndex);
	}
	
	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;	
	}

}
