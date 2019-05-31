package com.application.medCareApplication.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.table.PatientsTableModel;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.PatientFrame;
import com.application.medCareApplication.view.PatientsTablePanel;

public class ViewPatientDetailAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6422397053207068172L;

	
	public ViewPatientDetailAction() {
		// TODO Auto-generated constructor stub
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK));
		putValue(SHORT_DESCRIPTION, "Informacije o pacijentu");
		putValue(LONG_DESCRIPTION, "Informacije o pacijentu");
	}
	
	public ViewPatientDetailAction(String name) {
		// TODO Auto-generated constructor stub
		this();
		putValue(NAME, name);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JPanel mainPanel = MainFrame.getInstance().getMainPanel();
		for(Component c : mainPanel.getComponents()) {
			if(c instanceof PatientsTablePanel) {
				JTable table = ((PatientsTablePanel)c).getTable();
				
				int selectedRowIndex = table.getSelectedRow();
				if(selectedRowIndex > -1){
					PatientsTableModel model = (PatientsTableModel) table.getModel();
					Patient p = model.getSelectedRow(selectedRowIndex).getPatient();
					System.out.println(p);
					
					PatientFrame patientFrame = new PatientFrame(p);
					patientFrame.setVisible(true);
							
				}
			}
		}
	}

}
