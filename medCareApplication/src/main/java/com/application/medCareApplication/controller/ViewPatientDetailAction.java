package com.application.medCareApplication.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;

import com.application.medCareApplication.view.PatientFrame;

public class ViewPatientDetailAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6422397053207068172L;

	public ViewPatientDetailAction() {
		// TODO Auto-generated constructor stub
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.SHIFT_MASK));
		putValue(SHORT_DESCRIPTION, "Dodaj novog pacijenta");
		putValue(LONG_DESCRIPTION, "Dodaj novog pacijenta");
	}
	
	public ViewPatientDetailAction(String name) {
		// TODO Auto-generated constructor stub
		this();
		putValue(NAME, name);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		PatientFrame patientFrame = new PatientFrame();
		patientFrame.setVisible(true);
	}

}
