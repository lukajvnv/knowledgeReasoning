package com.application.medCareApplication.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.Resources;
import com.application.medCareApplication.model.examination.CTpluca;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.displayExaminations.ViewPatientDiagnosis;
import com.application.medCareApplication.view.displayExaminations.ViewPatientPhysicalExamination;

public class NewDiagnosisDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField patientIdTextField;
	private JTextField patientFirstNameTextField;
	private JTextField patientLastNameTextField;
	private JLabel patientIdLabel;
	private JLabel patientFirstNameLabel;
	private JLabel patientLastNameLabel;
	private JLabel patientRespiratorySoundLabel;
	private JLabel patientRespiratoryNoiseLabel;
	private JPanel patientBodyTemperaturePanel;
	private JRadioButton regularTemperatureRadioButton;
	private JRadioButton lowerBodyTemperatureRadioButton;
	private JRadioButton upperBodyTemperatureRadioButton;
	private JButton addPhysicalExamanationButton;
	private JButton cancelButton;
	private JComboBox patientRespiratorySoundComboBox;
	private JComboBox<String> patientRespiratoryNoiseComboBox;

	private ButtonGroup bg;

	public static void main(String[] args) {
		try {
			NewDiagnosisDialog dialog = new NewDiagnosisDialog(new Patient());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Diagnosis diagnosis;
	private Patient patient;
	private Resources resource;

	/**
	 * Create the dialog.
	 */
	
	private ViewPatientDiagnosis panel;
	
	public NewDiagnosisDialog(Patient p, ViewPatientDiagnosis panel) {
		this(p);
		this.panel = panel;
	}
	
	public NewDiagnosisDialog(Patient p) {
		this.patient = p;
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Dodavanje dijagnoze");
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(600,450);
		
		
		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30};
		gbl_contentPanel.rowHeights = new int[] {30, 0, 30, 0, 30, 0, 30, 0, 30, 0, 30, 30, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			patientIdLabel = new JLabel("Id pacijenta");
			GridBagConstraints gbc_patientIdLabel = new GridBagConstraints();
			gbc_patientIdLabel.anchor = GridBagConstraints.WEST;
			gbc_patientIdLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientIdLabel.gridx = 2;
			gbc_patientIdLabel.gridy = 1;
			contentPanel.add(patientIdLabel, gbc_patientIdLabel);
		}
		{
			patientIdTextField = new JTextField();
			patientIdLabel.setLabelFor(patientIdTextField);
			patientIdTextField.setText(patient.getPatientId().toString());
			patientIdTextField.setEditable(false);
			GridBagConstraints gbc_patientIdTextField = new GridBagConstraints();
			gbc_patientIdTextField.insets = new Insets(5, 5, 5, 5);
			gbc_patientIdTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_patientIdTextField.gridx = 6;
			gbc_patientIdTextField.gridy = 1;
			contentPanel.add(patientIdTextField, gbc_patientIdTextField);
			patientIdTextField.setColumns(10);
		}
		{
			patientFirstNameLabel = new JLabel("Ime pacijenta:");
			GridBagConstraints gbc_patientFirstNameLabel = new GridBagConstraints();
			gbc_patientFirstNameLabel.anchor = GridBagConstraints.WEST;
			gbc_patientFirstNameLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientFirstNameLabel.gridx = 2;
			gbc_patientFirstNameLabel.gridy = 3;
			contentPanel.add(patientFirstNameLabel, gbc_patientFirstNameLabel);
		}
		{
			patientFirstNameTextField = new JTextField();
			patientFirstNameLabel.setLabelFor(patientFirstNameTextField);
			patientFirstNameTextField.setText(p.getFirstName());
			patientFirstNameTextField.setEditable(false);
			GridBagConstraints gbc_patientFirstNameTextField = new GridBagConstraints();
			gbc_patientFirstNameTextField.insets = new Insets(5, 5, 5, 5);
			gbc_patientFirstNameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_patientFirstNameTextField.gridx = 6;
			gbc_patientFirstNameTextField.gridy = 3;
			contentPanel.add(patientFirstNameTextField, gbc_patientFirstNameTextField);
			patientFirstNameTextField.setColumns(10);
		}
		{
			patientLastNameLabel = new JLabel("Prezime pacijenta:");
			GridBagConstraints gbc_patientLastNameLabel = new GridBagConstraints();
			gbc_patientLastNameLabel.anchor = GridBagConstraints.WEST;
			gbc_patientLastNameLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientLastNameLabel.gridx = 2;
			gbc_patientLastNameLabel.gridy = 5;
			contentPanel.add(patientLastNameLabel, gbc_patientLastNameLabel);
		}
		{
			patientLastNameTextField = new JTextField();
			patientLastNameLabel.setLabelFor(patientLastNameTextField);
			patientLastNameTextField.setText(patient.getLastName());
			patientLastNameTextField.setEditable(false);
			GridBagConstraints gbc_patientLastNameTextField = new GridBagConstraints();
			gbc_patientLastNameTextField.insets = new Insets(5, 5, 5, 5);
			gbc_patientLastNameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_patientLastNameTextField.gridx = 6;
			gbc_patientLastNameTextField.gridy = 5;
			contentPanel.add(patientLastNameTextField, gbc_patientLastNameTextField);
			patientLastNameTextField.setColumns(10);
		}
		{
	
		JLabel lblNewLabel_2 = new JLabel("Dijagnoza:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(5, 5, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 7;
		contentPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Odaberi");
		btnNewButton_1.addActionListener(new AbstractAction() {
			
			private static final long serialVersionUID = -2138745628081908457L;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SelectAnamnesisDialog d = new SelectAnamnesisDialog(false);
				d.setVisible(true);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("images/arrow_top_right_icon&24.png"));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 6;
		gbc_btnNewButton_1.gridy = 7;
		contentPanel.add(btnNewButton_1, gbc_btnNewButton_1);
		}
		JPanel buttonPane = new JPanel();
		FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
		fl_buttonPane.setHgap(25);
		buttonPane.setLayout(fl_buttonPane);
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			JButton addDiagnosisButton = new JButton("Dodaj");
			addDiagnosisButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			addDiagnosisButton.setActionCommand("OK");
			buttonPane.add(addDiagnosisButton);
			//getRootPane().setDefaultButton(addAnamnesisButton);
			addDiagnosisButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int diagnosisId = resource.getResourceId();
					int patientId = patient.getPatientId();
					String diagnosisName = diagnosis.getDiagnose();
					String diagnosisDate = diagnosis.getDate();
					
					Diagnosis diagnosis = new Diagnosis(diagnosisId, patientId, diagnosisName, diagnosisDate);
					
					DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
					try {
						dbHandler.createDiagnosis(diagnosis);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					dispose();
				}
			});
		}
		{
			JButton cancelButton = new JButton("Prekini");
			cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			buttonPane.add(cancelButton);
		}
		
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
