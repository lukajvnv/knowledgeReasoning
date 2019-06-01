package com.application.medCareApplication.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.handler.DatabaseHandler;
import com.application.medCareApplication.view.dialog.NewAnamnesisDialog;
import com.application.medCareApplication.view.dialog.NewEwsScoreDialog;
import com.application.medCareApplication.view.dialog.NewPhysicalExaminationDialog;
import com.application.medCareApplication.view.dialog.UpdatePatientDialog;
import com.application.medCareApplication.view.utils.MyFieldFocusListener;

@SuppressWarnings("serial")
public class PatientFrame extends JFrame {

	private DatabaseHandler databaseHandler;
	
	private JPanel contentPane;
	private JTextField patientIdtextField;

	private Patient patient;
	private JButton searchButton;
	private JLabel patientInfoLabel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientFrame frame = new PatientFrame(new Patient());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PatientFrame(Patient p) {
		databaseHandler = MainFrame.getInstance().getDatabaseHandler();
		setPatient(p);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //setSize(screenWidth/2 + 100, screenHeight/2 + 130);
        setSize(screenWidth/2 + 300, screenHeight/2 + 250);
        setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JLabel patientIdLabel = new JLabel("Pacijent(Id):");
		toolBar.add(patientIdLabel);
		
		patientIdtextField = new JTextField(10);
		patientIdtextField.addFocusListener(new MyFieldFocusListener());
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
						/*String patientInfoText = String.format("%s %s, ID: %s, (%s)", patient.getLastName(), patient.getFirstName(), patient.getPatientId(), patient.getDateOfBirth());
						patientInfoLabel.setText(patientInfoText);*/
						setPatientInfoLabel();
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
		
		JButton refreshButton = new JButton("Osve\u017Ei");
		refreshButton.setIcon(new ImageIcon("images/refresh_icon&24.png"));
		toolBar.add(refreshButton);
		
		JButton patientInfoButton = new JButton("Osnovni podaci");
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
				NewAnamnesisDialog p = new NewAnamnesisDialog();
				p.setVisible(true);
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
				NewPhysicalExaminationDialog p = new NewPhysicalExaminationDialog();
				p.setVisible(true);
			}
		});
		toolBar.add(physicalExaminationsButton);
		
		JButton proceduresButton = new JButton("Ispitivanja");
		proceduresButton.setToolTipText("Sva ispitivanja pacijenta");
		proceduresButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		toolBar.add(proceduresButton);
		
		JButton diagnosisButton = new JButton("Dijagnoze");
		diagnosisButton.setToolTipText("Sve dijagnoze pacijenta");
		diagnosisButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		toolBar.add(diagnosisButton);
		
		JButton medicamentsButton = new JButton("Terapije");
		medicamentsButton.setToolTipText("Sve terapije pacijenta");
		medicamentsButton.setIcon(new ImageIcon("images/folder_icon&24.png"));
		toolBar.add(medicamentsButton);
		
		JButton ewsButton = new JButton("EWS");
		ewsButton.setIcon(new ImageIcon("images/shield_icon&24.png"));
		ewsButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewEwsScoreDialog p = new NewEwsScoreDialog();
				p.setVisible(true);
			}
		});
		toolBar.add(ewsButton);
		
		JButton backButton = new JButton("Povratak");
		backButton.setToolTipText("Povratak na tabelu sa pacijentima");
		backButton.setIcon(new ImageIcon("images/playback_reload_icon&24.png"));
		toolBar.add(backButton);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		mainPanel.add(splitPane, BorderLayout.CENTER);
		
		// Divider postavljen da bude nevidljiv
		//splitPane.setDividerSize(0);
				
		splitPane.setResizeWeight(0.5);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new TitledBorder(null, "Detalji", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		splitPane.setRightComponent(rightPanel);
		
		JPanel leftPanel = new JPanel();
		splitPane.setLeftComponent(leftPanel);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_1 = new JToolBar();
		leftPanel.add(toolBar_1, BorderLayout.NORTH);
		
		JButton btnNewButton_8 = new JButton("Novi");
		btnNewButton_8.setIcon(new ImageIcon("images\\doc_new_icon&24.png"));
		toolBar_1.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("Obir\u0161i");
		btnNewButton_9.setIcon(new ImageIcon("images\\delete_icon&24.png"));
		toolBar_1.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("New button");
		toolBar_1.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("New button");
		toolBar_1.add(btnNewButton_11);
		
		Component horizontalStrut = Box.createHorizontalStrut(100);
		horizontalStrut.setBackground(Color.WHITE);
		toolBar_1.add(horizontalStrut);
		
		JLabel lblNewLabel = new JLabel("Sve anamneze");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		toolBar_1.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		leftPanel.add(scrollPane, BorderLayout.CENTER);
		
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
}
