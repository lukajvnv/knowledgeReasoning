package com.application.medCareApplication.view.dialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.view.PatientFrame;

public class UpdatePatientDialog extends NewPatientDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Patient patient;
	private PatientFrame frame;
	
	public UpdatePatientDialog(Patient patient, PatientFrame frame) {
		this.patient = patient;
		this.frame = frame;
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Izmena pacijenta informacija o pacijentu");
		
		//initComponents();
		setTextFields();
		
		
		addPatientButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				boolean valid = validation();
				
				if(!valid) {
					return;
				}
				
				//SAD NE RADI UPDATE
				/*String firstName = firstNameTextField.getText().trim();
				String lastName = lastNameTextField.getText().trim();
				String address = addressTextField.getText().trim();
				String telephone = telephoneNumberTextField.getText().trim();
				String jmbg = jmbgTextField.getText().trim();
				String dateOfBirth = dateOfBirthDateField.getValue();
				
				Patient p = new Patient(patient.getPatientId(), firstName, lastName, jmbg, dateOfBirth, address, telephone);
				System.out.println(p);
				
				DatabaseHandler databaseHandler = MainFrame.getInstance().getDatabaseHandler();
				try {
					databaseHandler.updatePatient(p);
					frame.setPatient(p);
					frame.setPatientInfoLabel();
					MainFrame.getInstance().updateMainPanelPatientsTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			
				dispose();
			}
		});
	}
	
	
	private void setTextFields() {
		firstNameTextField.setText(patient.getFirstName());
		lastNameTextField.setText(patient.getLastName());
		addressTextField.setText(patient.getAddress());
		telephoneNumberTextField.setText(patient.getTelephoneNumber());
		jmbgTextField.setText(patient.getJmbg());
		dateOfBirthDateField.setValue(patient.getDateOfBirth());
		
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
