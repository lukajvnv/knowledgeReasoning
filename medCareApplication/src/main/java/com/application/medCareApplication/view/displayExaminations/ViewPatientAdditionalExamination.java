package com.application.medCareApplication.view.displayExaminations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.model.examination.CTpluca;
import com.application.medCareApplication.model.examination.KrvnaSlika;
import com.application.medCareApplication.model.examination.RTGPluca;
import com.application.medCareApplication.model.examination.UltraZvuk;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;

public class ViewPatientAdditionalExamination extends JPanel {

	private JList<Object> patientAdditionalExaminatonList;
	private Patient patient;
	private DefaultListModel<Object> patientAdditionalExaminatonListModel;
	
	
	@SuppressWarnings("serial")
	public ViewPatientAdditionalExamination(Patient p) {
		this.patient = p;
				
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Sva dopunska ispitivanja");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		toolBar.add(lblNewLabel);
		
		JButton newButton = new JButton("Novi");
		newButton.setIcon(new ImageIcon("images\\doc_new_icon&24.png"));
		newButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		toolBar.add(newButton);
		
		JButton deleteButton = new JButton("Obr\u0161i");
		deleteButton.setIcon(new ImageIcon("images\\delete_icon&24.png"));
		toolBar.add(deleteButton);
		
		JButton detailsButton = new JButton("Detaljnije");
		detailsButton.setIcon(new ImageIcon("images\\info_icon&24.png"));
		toolBar.add(detailsButton);
		
		JButton recommendedDiagnosisButton = new JButton("Preporucena dijagnoza");
		recommendedDiagnosisButton.setIcon(new ImageIcon("images/round_and_up_icon&24.png"));
		toolBar.add(recommendedDiagnosisButton);
		recommendedDiagnosisButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object obj = patientAdditionalExaminatonList.getSelectedValue();
				if(obj instanceof CTpluca) {
					
				} else if(obj instanceof KrvnaSlika) {
					
				} else if(obj instanceof RTGPluca) {
					
				} else if(obj instanceof UltraZvuk) {
					
				}
			}
		});
		
		initList();
		
		JScrollPane scrollPane = new JScrollPane(patientAdditionalExaminatonList);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initList() {
		patientAdditionalExaminatonListModel = new DefaultListModel<Object>();
		
		
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<Object> physicalExaminations = dbHandler.selectAllPatientAdditionalExaminations(patient);
			
		for (Object p : physicalExaminations) {
			patientAdditionalExaminatonListModel.addElement(p);
		}
				
		patientAdditionalExaminatonList = new JList<Object>(patientAdditionalExaminatonListModel);
		patientAdditionalExaminatonList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(patientAdditionalExaminatonList.getSelectedValue());
			}
		});
		patientAdditionalExaminatonList.setForeground(Color.BLACK);
		patientAdditionalExaminatonList.setBackground(Color.WHITE);
		
		patientAdditionalExaminatonList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	

}
