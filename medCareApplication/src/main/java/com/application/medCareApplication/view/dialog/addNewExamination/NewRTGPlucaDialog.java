package com.application.medCareApplication.view.dialog.addNewExamination;

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
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.application.medCareApplication.model.AdditionalExamination;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.examination.KrvnaSlika;
import com.application.medCareApplication.model.examination.RTGPluca;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.displayExaminations.ViewPatientAnamnesis;


public class NewRTGPlucaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 212032573842458559L;
	private final JPanel contentPanel = new JPanel();
	private JPanel personalAnamnesisPanel;
	
	private ButtonGroup smokingButtonGroup;
	private ButtonGroup alcoholButtonGroup;

	
	private Patient patient;
	private Boolean patoloski;
	
	private Boolean anam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewRTGPlucaDialog dialog = new NewRTGPlucaDialog(new Patient(),true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	private ViewPatientAnamnesis panel;
	
	/**
	 * @wbp.parser.constructor
	 */
	public NewRTGPlucaDialog(Patient patient, ViewPatientAnamnesis p) {
		this(patient,true);
		this.panel = p;
	}
	
	public NewRTGPlucaDialog(Patient p, Boolean anamneza) {
		this.patient = p;
		this.patoloski = false;
		anam = anamneza;
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		String titleText = String.format("RTG pluca za pacijenta: %s %s", patient.getFirstName(), patient.getLastName());
		setTitle(titleText);
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(723,253);
		
		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 30};
		gbl_contentPanel.columnWeights = new double[]{1.0};
		gbl_contentPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		contentPanel.setLayout(gbl_contentPanel);
		
		
		
		
		{
			personalAnamnesisPanel = new JPanel();
			personalAnamnesisPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Rentgen pluca:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
			GridBagConstraints gbc_personalAnamnesisPanel = new GridBagConstraints();
			gbc_personalAnamnesisPanel.insets = new Insets(0, 0, 5, 0);
			gbc_personalAnamnesisPanel.fill = GridBagConstraints.BOTH;
			gbc_personalAnamnesisPanel.gridx = 0;
			gbc_personalAnamnesisPanel.gridy = 0;
			contentPanel.add(personalAnamnesisPanel, gbc_personalAnamnesisPanel);
			GridBagLayout gbl_personalAnamnesisPanel = new GridBagLayout();
			gbl_personalAnamnesisPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30, 0};
			gbl_personalAnamnesisPanel.rowHeights = new int[] {0, 0};
			gbl_personalAnamnesisPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
			gbl_personalAnamnesisPanel.rowWeights = new double[]{1.0, 1.0};
			personalAnamnesisPanel.setLayout(gbl_personalAnamnesisPanel);
			{
				JLabel smokingLabel = new JLabel("RTG:");
				GridBagConstraints gbc_smokingLabel = new GridBagConstraints();
				gbc_smokingLabel.fill = GridBagConstraints.BOTH;
				gbc_smokingLabel.anchor = GridBagConstraints.WEST;
				gbc_smokingLabel.gridx = 2;
				gbc_smokingLabel.gridy = 0;
				personalAnamnesisPanel.add(smokingLabel, gbc_smokingLabel);
			}
			{
				JPanel smokingPanel = new JPanel();
				GridBagConstraints gbc_smokingPanel = new GridBagConstraints();
				gbc_smokingPanel.fill = GridBagConstraints.BOTH;
				gbc_smokingPanel.gridx = 6;
				gbc_smokingPanel.gridy = 0;
				personalAnamnesisPanel.add(smokingPanel, gbc_smokingPanel);
				smokingPanel.setLayout(new GridLayout(0, 2, 5, 5));
				
					JRadioButton smokingYesRadioButton = new JRadioButton("Normalan");
					smokingYesRadioButton.setActionCommand("Normalan");
					smokingYesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					smokingPanel.add(smokingYesRadioButton);
				
				
					JRadioButton smokingNoRadioButton = new JRadioButton("Patoloski");
					smokingNoRadioButton.setActionCommand("Patoloski");
					smokingNoRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					smokingPanel.add(smokingNoRadioButton);
				
				smokingButtonGroup = new ButtonGroup();
				smokingButtonGroup.add(smokingYesRadioButton);
				smokingButtonGroup.add(smokingNoRadioButton);
				
				smokingNoRadioButton.addActionListener(new AbstractAction() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						// TODO NAPRAVITI DA SE POJAVI SLEDECI RED SAMO NA OVAJ RADIO BUTTON
						patoloski = true;
					}
				});
				
			
				
			}
			

			{
				JLabel alcoholLabel = new JLabel("Patoloski rentgen:");
				GridBagConstraints gbc_alcoholLabel = new GridBagConstraints();
				gbc_alcoholLabel.fill = GridBagConstraints.BOTH;
				gbc_alcoholLabel.anchor = GridBagConstraints.WEST;
				gbc_alcoholLabel.insets = new Insets(0, 0, 0, 5);
				gbc_alcoholLabel.gridx = 2;
				gbc_alcoholLabel.gridy = 1;
				personalAnamnesisPanel.add(alcoholLabel, gbc_alcoholLabel);
			}
			{
				JPanel alcoholPanel = new JPanel();
				GridBagConstraints gbc_alcoholPanel = new GridBagConstraints();
				gbc_alcoholPanel.fill = GridBagConstraints.BOTH;
				gbc_alcoholPanel.gridx = 6;
				gbc_alcoholPanel.gridy = 1;
				personalAnamnesisPanel.add(alcoholPanel, gbc_alcoholPanel);
				alcoholPanel.setLayout(new GridLayout(1, 2, 5, 5));
				
					JRadioButton alcoholYesRadioButton = new JRadioButton("Homogene lezije");
					alcoholYesRadioButton.setActionCommand("Homogene lezije");
					alcoholYesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					alcoholPanel.add(alcoholYesRadioButton);
				
				
					JRadioButton alcoholNoRadioButton = new JRadioButton("Inhomogene lezije");
					alcoholNoRadioButton.setActionCommand("Inhomogene lezije");
					alcoholNoRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					alcoholPanel.add(alcoholNoRadioButton);
					
				    alcoholButtonGroup = new ButtonGroup();
					alcoholButtonGroup.add(alcoholYesRadioButton);
					alcoholButtonGroup.add(alcoholNoRadioButton);
			}
		}
		{
			
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(25);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addAnamnesisButton = new JButton("Dodaj");
				addAnamnesisButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				addAnamnesisButton.setActionCommand("OK");
				buttonPane.add(addAnamnesisButton);
				//getRootPane().setDefaultButton(addAnamnesisButton);
				addAnamnesisButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					/*	System.out.println("add action button");
						String alcohol = Utils.getSelectedButtonText(alcoholButtonGroup);
						alcohol = (alcohol != null) ? alcohol : "Ne";
						String smoking = Utils.getSelectedButtonText(smokingButtonGroup);
						smoking = (smoking != null) ? smoking : "Ne";
						String pet = Utils.getSelectedButtonText(petButtonGroup);
						pet = (pet != null) ? pet : "Ne";	
						
						System.out.println("preko selection ");*/
						int patientId = patient.getPatientId();/*
						String employed = employedButtonGroup.getSelection().getActionCommand();
						String workingCondition = workingConditionButtonGroup.getSelection().getActionCommand();
						String livingObject = livingObjectButtonGroup.getSelection().getActionCommand();
						String livingPlace = livingPlaceButtonGroup.getSelection().getActionCommand();*/
						
						String rtgPluca = smokingButtonGroup.getSelection().getActionCommand();
						String lezije = alcoholButtonGroup.getSelection().getActionCommand();
	
						RTGPluca rtg = new RTGPluca(-1, patientId, rtgPluca, lezije);
					
						DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
						try {
							dbHandler.createRTGPluca(rtg,anam);
							//DefaultListModel<Anamnesis> model =  (DefaultListModel<Anamnesis>) panel.getPatientAnamnesisList().getModel();
						//	model.addElement(anamnesis);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//ubacujemo u novu tabelu add exam
						RTGPluca ctt = new RTGPluca();
						List<Object> lista = dbHandler.selectAllPatientCBC(p);
						for (Object object : lista) {
							ctt = (RTGPluca) object;
						}
						
						if(dbHandler.selectAdditionalExamination(p.getPatientId()) == null) {
							AdditionalExamination novi = new AdditionalExamination();
							System.out.println("idd1? " + ctt.getId());
							novi.setIdRtg(ctt.getId());
							novi.setPatientId(patientId);
							try {
								dbHandler.createAdditionalExamination(novi);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							AdditionalExamination novi = dbHandler.selectAdditionalExamination(p.getPatientId());
							System.out.println("idd2? " + ctt.getId());
							novi.setIdRtg(ctt.getId());
							try {
								dbHandler.updateAdditionalExamination(novi);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//novi.setPatientId(patientId);
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
	}

	public JPanel getPersonalAnamnesisPanel() {
		return personalAnamnesisPanel;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
