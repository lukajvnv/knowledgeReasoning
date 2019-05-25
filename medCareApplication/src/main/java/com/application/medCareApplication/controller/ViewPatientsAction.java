package com.application.medCareApplication.controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.PatientsTablePanel;

public class ViewPatientsAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 54631680868587748L;


	public ViewPatientsAction() {
		// TODO Auto-generated constructor stub
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.SHIFT_MASK));
		putValue(SHORT_DESCRIPTION, "Svi pacijenti");
		putValue(LONG_DESCRIPTION, "Svi pacijenti");
	}
	
	public ViewPatientsAction(String name) {
		// TODO Auto-generated constructor stub
		this();
		putValue(NAME, name);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//PatientTableFrame newPatientDialog = new PatientTableFrame();
		//newPatientDialog.setVisible(true);
	
		
		JPanel mainPanel = MainFrame.getInstance().getMainPanel();
		mainPanel.removeAll();
		//mainPanel.repaint();
		
		PatientsTablePanel patientsTablePanel = new PatientsTablePanel();
		mainPanel.add(patientsTablePanel, BorderLayout.CENTER);
		mainPanel.validate();
	}

}
