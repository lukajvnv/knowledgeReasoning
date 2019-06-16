package com.application.medCareApplication.view.recommendation;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.PrologHandler;
import com.application.medCareApplication.utils.components.RDFHandler;
import com.application.medCareApplication.view.MainFrame;

public class PreventionExaminationRecommendationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7794704454170290773L;
	
	
	private JPanel contentPane;
	private JTextField patientAgeTextField;
	private JComboBox<String> earlyAnamnesisComboBox;
	private JComboBox<String> personalAnamnesisComboBox;
	private JComboBox<String> familyAnamnesisComboBox;
	
	
	private JList<String> rbrPreventExaminationList = new JList<String>();
	private JList<String> cbrPreventExaminationList = new JList<String>();
	
	private List<String> patientPersonalAnamnesis;
	private List<String> patientEarlyAnamnesis;
	private List<String> patientFamilyAnamnesis;

	private Patient patient;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreventionExaminationRecommendationFrame frame = new PreventionExaminationRecommendationFrame(new Patient());
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
	public PreventionExaminationRecommendationFrame(Patient p) {
		this.patient = p;
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Preporuka preventivnog pregleda za pacijenta");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700, 700);
		setResizable(false);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel infoPanel = new JPanel();
		contentPane.add(infoPanel, BorderLayout.NORTH);
		
		JLabel infoLabel = new JLabel("Preporuka preventivnog pregleda za pacijenta"  + p.getFirstName() + " " + p.getLastName());
		infoPanel.add(infoLabel);
		
		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		buttonPanel.add(btnNewButton);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel upperPanel = new JPanel();
		mainPanel.add(upperPanel);
		GridBagLayout gbl_upperPanel = new GridBagLayout();
		gbl_upperPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30, 30, 30, 0};
		gbl_upperPanel.rowHeights = new int[]{0, 30, 0, 30, 0, 30, 0, 30, 0, 0};
		gbl_upperPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_upperPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		upperPanel.setLayout(gbl_upperPanel);
		
		JLabel patientAgeLabel = new JLabel("Godine pacijenta:");
		GridBagConstraints gbc_patientAgeLabel = new GridBagConstraints();
		gbc_patientAgeLabel.anchor = GridBagConstraints.WEST;
		gbc_patientAgeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_patientAgeLabel.gridx = 2;
		gbc_patientAgeLabel.gridy = 0;
		upperPanel.add(patientAgeLabel, gbc_patientAgeLabel);
		
		patientAgeTextField = new JTextField();
		patientAgeTextField.setEditable(false);
		GridBagConstraints gbc_patientAgeTextField = new GridBagConstraints();
		gbc_patientAgeTextField.insets = new Insets(0, 0, 5, 0);
		gbc_patientAgeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_patientAgeTextField.gridx = 8;
		gbc_patientAgeTextField.gridy = 0;
		upperPanel.add(patientAgeTextField, gbc_patientAgeTextField);
		patientAgeTextField.setColumns(10);
		
		int[] numbers = Utils.extractDate(patient.getDateOfBirth());
		int age = 2019 - numbers[0];
		patientAgeTextField.setText(Integer.toString(age));
		
		JLabel earlyAnamnesisLabel = new JLabel("Ranija anamneza:");
		GridBagConstraints gbc_earlyAnamnesisLabel = new GridBagConstraints();
		gbc_earlyAnamnesisLabel.anchor = GridBagConstraints.WEST;
		gbc_earlyAnamnesisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_earlyAnamnesisLabel.gridx = 2;
		gbc_earlyAnamnesisLabel.gridy = 4;
		upperPanel.add(earlyAnamnesisLabel, gbc_earlyAnamnesisLabel);
		
		earlyAnamnesisComboBox = new JComboBox<String>();
		GridBagConstraints gbc_earlyAnamnesisComboBox = new GridBagConstraints();
		gbc_earlyAnamnesisComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_earlyAnamnesisComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_earlyAnamnesisComboBox.gridx = 8;
		gbc_earlyAnamnesisComboBox.gridy = 4;
		upperPanel.add(earlyAnamnesisComboBox, gbc_earlyAnamnesisComboBox);
		
		JLabel personalAnamnesisLabel = new JLabel("Licna anamneza:");
		GridBagConstraints gbc_personalAnamnesisLabel = new GridBagConstraints();
		gbc_personalAnamnesisLabel.anchor = GridBagConstraints.WEST;
		gbc_personalAnamnesisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_personalAnamnesisLabel.gridx = 2;
		gbc_personalAnamnesisLabel.gridy = 2;
		upperPanel.add(personalAnamnesisLabel, gbc_personalAnamnesisLabel);
		
		personalAnamnesisComboBox = new JComboBox<String>();
		GridBagConstraints gbc_personalAnamnesisComboBox = new GridBagConstraints();
		gbc_personalAnamnesisComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_personalAnamnesisComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_personalAnamnesisComboBox.gridx = 8;
		gbc_personalAnamnesisComboBox.gridy = 2;
		upperPanel.add(personalAnamnesisComboBox, gbc_personalAnamnesisComboBox);
		
		JLabel familyAnamnesisLabel = new JLabel("Porodicna anamneza:");
		GridBagConstraints gbc_familyAnamnesisLabel = new GridBagConstraints();
		gbc_familyAnamnesisLabel.anchor = GridBagConstraints.WEST;
		gbc_familyAnamnesisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_familyAnamnesisLabel.gridx = 2;
		gbc_familyAnamnesisLabel.gridy = 6;
		upperPanel.add(familyAnamnesisLabel, gbc_familyAnamnesisLabel);
		
		familyAnamnesisComboBox = new JComboBox<String>();
		GridBagConstraints gbc_familyAnamnesisComboBox = new GridBagConstraints();
		gbc_familyAnamnesisComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_familyAnamnesisComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_familyAnamnesisComboBox.gridx = 8;
		gbc_familyAnamnesisComboBox.gridy = 6;
		upperPanel.add(familyAnamnesisComboBox, gbc_familyAnamnesisComboBox);
		
		JButton viewPreventAnamnesisButton = new JButton("Pogledaj rezultate");
		GridBagConstraints gbc_viewPreventAnamnesisButton = new GridBagConstraints();
		gbc_viewPreventAnamnesisButton.gridx = 8;
		gbc_viewPreventAnamnesisButton.gridy = 8;
		viewPreventAnamnesisButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String age = patientAgeTextField.getText().trim();
				String earlyAnamnesis = (String) earlyAnamnesisComboBox.getSelectedItem();
				String familyAnamnesis = (String) familyAnamnesisComboBox.getSelectedItem();
				String personalAnamnesis = (String) personalAnamnesisComboBox.getSelectedItem();
				
				earlyAnamnesis = earlyAnamnesis.equals("") ? "*" : earlyAnamnesis;
				familyAnamnesis = familyAnamnesis.equals("") ? "*" : familyAnamnesis;
				personalAnamnesis = personalAnamnesis.equals("") ? "*" : personalAnamnesis;
				
				PrologHandler prologHandler = MainFrame.getInstance().getPrologHandler();
				String prologQuery = String.format("preventive_examination(%s, %s, %s, %s, PreventPregled)", age, personalAnamnesis, earlyAnamnesis, familyAnamnesis);
				List<String> rbrPreventExamination = new ArrayList<String>();
				try {
				     rbrPreventExamination = prologHandler.findResults("preventive_examination_updated.pl", prologQuery);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				RDFHandler rdfHandler = new RDFHandler("preventiveExamination.ttl");
				List<String> cbrPreventExamination = rdfHandler.findPreventionExamination(age, personalAnamnesis, earlyAnamnesis, familyAnamnesis);
			
				displayResult(rbrPreventExaminationList, rbrPreventExamination);
				displayResult(cbrPreventExaminationList, cbrPreventExamination);
			
			}
		});
		upperPanel.add(viewPreventAnamnesisButton, gbc_viewPreventAnamnesisButton);
		
		JPanel lowerPanel = new JPanel();
		mainPanel.add(lowerPanel);
		lowerPanel.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel rbrPanel = new JPanel();
		lowerPanel.add(rbrPanel);
		rbrPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel rbrInfoPanel = new JPanel();
		rbrPanel.add(rbrInfoPanel, BorderLayout.NORTH);
		
		JLabel rbrInfoLabel = new JLabel("Preporuka po RBR");
		rbrInfoPanel.add(rbrInfoLabel);
		
		JScrollPane rbrScrollPane = new JScrollPane(rbrPreventExaminationList);
		rbrPanel.add(rbrScrollPane, BorderLayout.CENTER);
		
		JPanel cbrPanel = new JPanel();
		lowerPanel.add(cbrPanel);
		cbrPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel cbrInfoPanel = new JPanel();
		cbrPanel.add(cbrInfoPanel, BorderLayout.NORTH);
		
		JLabel cbrInfoLabel = new JLabel("Preporuka po CBR");
		cbrInfoPanel.add(cbrInfoLabel);
		
		JScrollPane cbrScrollPane = new JScrollPane(cbrPreventExaminationList);
		cbrPanel.add(cbrScrollPane, BorderLayout.CENTER);
		
		initPersonalAnamnesisComboBox();
		initEarlyAnamnesisComboBox();
		initFamilyAnamnesisComboBox();
	}
	
	private void displayResult(JList<String> list, List<String> result) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(String r: result) {
			model.addElement(r);
		}
		list.setModel(model);
	}

	private void initPersonalAnamnesisComboBox() {
		List<String> answer = new ArrayList<String>();
		for(String s: answer) {
			//answer.add("");
		}
		personalAnamnesisComboBox.addItem("");
		personalAnamnesisComboBox.addItem("smoking");
		personalAnamnesisComboBox.addItem("alcohol");	
	}
	
	private void initFamilyAnamnesisComboBox() {
		familyAnamnesisComboBox.addItem("");
		familyAnamnesisComboBox.addItem("cancer");
		familyAnamnesisComboBox.addItem("alergy");
		familyAnamnesisComboBox.addItem("asthma");
	}
	
	private void initEarlyAnamnesisComboBox() {
		earlyAnamnesisComboBox.addItem("");
		earlyAnamnesisComboBox.addItem("copd");
		earlyAnamnesisComboBox.addItem("asthma");
	}
	
	public JTextField getPatientAgeTextField() {
		return patientAgeTextField;
	}
	public JComboBox<String> getEarlyAnamnesisComboBox() {
		return earlyAnamnesisComboBox;
	}
	public JComboBox<String> getPersonalAnamnesisComboBox() {
		return personalAnamnesisComboBox;
	}
	public JComboBox<String> getFamilyAnamnesisComboBox() {
		return familyAnamnesisComboBox;
	}
}
