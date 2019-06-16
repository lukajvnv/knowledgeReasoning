package com.application.medCareApplication.model.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.PatientsColumn;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;

public class PatientsTableModel extends DefaultTableModel {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4642477034681718563L;
	//private Map<String, Label> headerColumns;
	private ArrayList<PatientTableModelRow> rowsData; 
	
	private DatabaseHandler databaseHandler;
	
	public PatientsTableModel(){
		this.rowsData = new ArrayList<PatientTableModelRow>();
		
		databaseHandler = MainFrame.getInstance().getDatabaseHandler();
		
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
		List<Patient> patients = databaseHandler.selectAllPatients();
		for(Patient p: patients) {
			PatientTableModelRow ptr = new PatientTableModelRow(p);
			rowsData.add(ptr);
			addRow(ptr.addRowToTable());
		}
	}
	
	public void refreshData() {
		this.rowsData = new ArrayList<PatientTableModelRow>();
		collectData();
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
