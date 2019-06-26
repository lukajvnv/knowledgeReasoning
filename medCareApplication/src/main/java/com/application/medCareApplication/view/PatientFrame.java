package com.application.medCareApplication.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.dialog.NewEwsScoreDialog;
import com.application.medCareApplication.view.dialog.UpdatePatientDialog;
import com.application.medCareApplication.view.displayExaminations.DisplayInfo;
import com.application.medCareApplication.view.displayExaminations.ViewPatientAdditionalExamination;
import com.application.medCareApplication.view.displayExaminations.ViewPatientAnamnesis;
import com.application.medCareApplication.view.displayExaminations.ViewPatientDiagnosis;
import com.application.medCareApplication.view.displayExaminations.ViewPatientPhysicalExamination;
import com.application.medCareApplication.view.displayExaminations.ViewPatientTherapy;
import com.application.medCareApplication.view.recommendation.PreventionExaminationRecommendationFrame;

@SuppressWarnings("serial")
public class PatientFrame extends JFrame {

	private DatabaseHandler databaseHandler;
	
	private JPanel contentPane;
	private JTextField patientIdtextField;

	private Patient patient;
	private JButton searchButton;
	private JLabel patientInfoLabel;
	private JSplitPane splitPane;
	private JPanel rightPanel;
	private JPanel leftPanel;
	

	/**
	 * Create the frame.
	 */
	public PatientFrame(Patient p) {
		// setovanje pacijenta
		databaseHandler = MainFrame.getInstance().getDatabaseHandler();
		setPatient(p);
		
		//osnovna podesavanja prozora pacijenta
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Profil pacijenta");
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth/2 + 400, screenHeight/2 + 250);
        setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// podesavanja toolbara prozora
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JLabel patientIdLabel = new JLabel("Pacijent(Id):");
		toolBar.add(patientIdLabel);
		
		patientIdtextField = new JTextField(10);
		//patientIdtextField.addFocusListener(new MyFieldFocusListener());
		//textField.setPreferredSize(new Dimension(50,25));
		patientIdtextField.setMaximumSize(patientIdtextField.getPreferredSize());

		toolBar.add(patientIdtextField);
		
		searchButton = new JButton("Pretra\u017Ei");
		searchButton.setToolTipText("Pretraga po id pacijenta");
		searchButton.setIcon(new ImageIcon("images/zoom_icon&24.png"));
		searchButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					int id = Integer.parseInt(patientIdtextField.getText().trim());
					Patient p = databaseHandler.selectPatient(id);
					
					if(p != null) {
						patient = p;
						refreshFrame();
					}else {
						Utils.error("Pacijent sa unetim id ne postoji!");
					}
					
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					Utils.error("Nevalidan id!");
				}
				
			}
		});
		toolBar.add(searchButton);
		
		JButton refreshButton = new JButton("Preventivni");
		refreshButton.setToolTipText("Preporuka preventivnog pregleda za pacijenta");
		refreshButton.setIcon(new ImageIcon("images/import_icon&24.png"));
		refreshButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PreventionExaminationRecommendationFrame f = new PreventionExaminationRecommendationFrame(patient);
				f.setVisible(true);
			}
		});
		toolBar.add(refreshButton);
		
		JButton patientInfoButton = new JButton("Osnovni podaci");
		patientInfoButton.setToolTipText("Osnovni podaci pacijenta");
		patientInfoButton.setIcon(new ImageIcon("images/contact_card_icon&24.png"));
		patientInfoButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UpdatePatientDialog d = new UpdatePatientDialog(patient, PatientFrame.this);
				d.setVisible(true);
			}
		});
		toolBar.add(patientInfoButton);
		
		JButton symptomsButton = new JButton("Anamneze");
		symptomsButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewPatientAnamnesis p = new ViewPatientAnamnesis(patient);
				leftPanel = p;
				splitPane.setLeftComponent(leftPanel);
			}
		});
		
		symptomsButton.setToolTipText("Sve anamneze pacijenta");
		symptomsButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		toolBar.add(symptomsButton);
		
		JButton physicalExaminationsButton = new JButton("Pregledi");
		physicalExaminationsButton.setToolTipText("Svi pregledi pacijenta");
		physicalExaminationsButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		physicalExaminationsButton.addActionListener(new AbstractAction() {	
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewPatientPhysicalExamination p = new ViewPatientPhysicalExamination(patient);
				splitPane.setLeftComponent(p);
			}
		});
		toolBar.add(physicalExaminationsButton);
		
		JButton proceduresButton = new JButton("Ispitivanja");
		proceduresButton.setToolTipText("Sva ispitivanja pacijenta");
		proceduresButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		toolBar.add(proceduresButton);
		proceduresButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ViewPatientAdditionalExamination p = new ViewPatientAdditionalExamination(patient);
				splitPane.setLeftComponent(p);
			}
		});
		
		JButton diagnosisButton = new JButton("Dijagnoze");
		diagnosisButton.setToolTipText("Sve dijagnoze pacijenta");
		diagnosisButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewPatientDiagnosis p = new ViewPatientDiagnosis(patient, PatientFrame.this);
				splitPane.setLeftComponent(p);
			}
		});
		diagnosisButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		toolBar.add(diagnosisButton);
		
		JButton medicamentsButton = new JButton("Terapije");
		medicamentsButton.setToolTipText("Sve terapije pacijenta");
		medicamentsButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		medicamentsButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ViewPatientTherapy p = new ViewPatientTherapy(patient, PatientFrame.this);
				splitPane.setLeftComponent(p);
			}
		});
		toolBar.add(medicamentsButton);
		
		JButton ewsButton = new JButton("EWS");
		ewsButton.setToolTipText("Unos rezultata ews pregleda!");
		ewsButton.setIcon(new ImageIcon("images/shield_icon&24.png"));
		ewsButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewEwsScoreDialog p = new NewEwsScoreDialog(patient);
				p.setVisible(true);
			}
		});
		toolBar.add(ewsButton);
		
		JButton backButton = new JButton("Povratak");
		backButton.setToolTipText("Povratak na tabelu sa pacijentima");
		backButton.setIcon(new ImageIcon("images/playback_reload_icon&24.png"));
		backButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		toolBar.add(backButton);
		
		//podesavanje glavnog panela koji cine 2 panela
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		splitPane = new JSplitPane();
		mainPanel.add(splitPane, BorderLayout.CENTER);
		
		// Divider postavljen da bude nevidljiv
		//splitPane.setDividerSize(0);
//		splitPane.setDividerLocation(0.5); //????? kako da bude uvek na pola				
		splitPane.setResizeWeight(0.5d);
		
		
		rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder(null, "Detalji", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(rightPanel);
		
		leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		
			
		//Prikaz najosnovnijih podataka o pacijentu
		JPanel patientInfoPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) patientInfoPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(patientInfoPanel, BorderLayout.NORTH);
		
		patientInfoLabel = new JLabel();
		patientInfoLabel.setForeground(Color.RED);
		patientInfoLabel.setHorizontalAlignment(SwingConstants.LEFT);
		patientInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		setPatientInfoLabel();
		patientInfoPanel.add(patientInfoLabel);
	}

	public void setPatientInfoLabel() {
		String patientInfoText = String.format("%s %s, ID: %s, (%s)", patient.getLastName(), patient.getFirstName(), patient.getPatientId(), patient.getDateOfBirth());
		patientInfoLabel.setText(patientInfoText);
	}
	
	public void refreshFrame() {
		setPatientInfoLabel();
		rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder(null, "Detalji", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(rightPanel);
		
		leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
	
	}
	
	public void setRightPaneComponent(Object objectToDisplay) {
		rightPanel = new DisplayInfo(objectToDisplay);
		rightPanel.setBorder(new TitledBorder(null, "Detalji", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(rightPanel);
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public JTextField getPatientIdtextField() {
		return patientIdtextField;
	}
	public JButton getSearchButton() {
		return searchButton;
	}
	public JLabel getPatientInfoLabel() {
		return patientInfoLabel;
	}
	public JSplitPane getSplitPane() {
		return splitPane;
	}
	public JPanel getRightPanel() {
		return rightPanel;
	}
	public JPanel getLeftPanel() {
		return leftPanel;
	}
}
