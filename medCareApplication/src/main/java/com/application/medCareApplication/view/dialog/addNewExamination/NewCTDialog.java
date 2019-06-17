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

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.examination.CTpluca;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.displayExaminations.ViewPatientAnamnesis;


public class NewCTDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 212032573842458559L;
	private final JPanel contentPanel = new JPanel();
	private JPanel personalAnamnesisPanel;
	
	private ButtonGroup smokingButtonGroup;
	private ButtonGroup alcoholButtonGroup;
	private ButtonGroup employedButtonGroup;
	private ButtonGroup workingConditionButtonGroup;
	private ButtonGroup livingPlaceButtonGroup;
	private ButtonGroup livingObjectButtonGroup;
	private ButtonGroup petButtonGroup;
	
	private Patient patient;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewCTDialog dialog = new NewCTDialog(new Patient());
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
	public NewCTDialog(Patient patient, ViewPatientAnamnesis p) {
		this(patient);
		this.panel = p;
	}
	
	public NewCTDialog(Patient p) {
		this.patient = p;
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		String titleText = String.format("CT pluca za pacijenta: %s %s", patient.getFirstName(), patient.getLastName());
		setTitle(titleText);
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(701,243);
		
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
			personalAnamnesisPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dodatni pregled", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
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
				JLabel smokingLabel = new JLabel("CT pluca:");
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
				
			}
			
		}
		{
			{
				
					employedButtonGroup = new ButtonGroup();
			}
			{
				
					workingConditionButtonGroup = new ButtonGroup();
			}
		}
		{
			{
				
					livingPlaceButtonGroup = new ButtonGroup();
			}
			{
				
					livingObjectButtonGroup = new ButtonGroup();
			}
			{
				
					petButtonGroup = new ButtonGroup();
			}
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
						/*System.out.println("add action button");
						String alcohol = Utils.getSelectedButtonText(alcoholButtonGroup);
						alcohol = (alcohol != null) ? alcohol : "Ne";
						String smoking = Utils.getSelectedButtonText(smokingButtonGroup);
						smoking = (smoking != null) ? smoking : "Ne";
						String pet = Utils.getSelectedButtonText(petButtonGroup);
						pet = (pet != null) ? pet : "Ne";	
						
						System.out.println("preko selection ");*/
						int patientId = patient.getPatientId();
						/*String employed = employedButtonGroup.getSelection().getActionCommand();
						String workingCondition = workingConditionButtonGroup.getSelection().getActionCommand();
						String livingObject = livingObjectButtonGroup.getSelection().getActionCommand();
						String livingPlace = livingPlaceButtonGroup.getSelection().getActionCommand();*/
						
						String ct_pluca = smokingButtonGroup.getSelection().getActionCommand();
	
						CTpluca ct = new CTpluca(-1, patientId, ct_pluca);
					
						DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
						try {
							dbHandler.createCTPluca(ct);
							//DefaultListModel<Anamnesis> model =  (DefaultListModel<Anamnesis>) panel.getPatientAnamnesisList().getModel();
							//model.addElement(anamnesis);
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
