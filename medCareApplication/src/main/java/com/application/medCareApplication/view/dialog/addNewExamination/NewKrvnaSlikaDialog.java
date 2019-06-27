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
import com.application.medCareApplication.model.examination.CTpluca;
import com.application.medCareApplication.model.examination.KrvnaSlika;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.displayExaminations.ViewPatientAnamnesis;


public class NewKrvnaSlikaDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 212032573842458559L;
	private final JPanel contentPanel = new JPanel();
	

	private ButtonGroup livingPlaceButtonGroup;
	private ButtonGroup livingObjectButtonGroup;
	private ButtonGroup petButtonGroup;
	
	private Patient patient;
	
	private Boolean anam;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewKrvnaSlikaDialog dialog = new NewKrvnaSlikaDialog(new Patient(),true);
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
	public NewKrvnaSlikaDialog(Patient patient, ViewPatientAnamnesis p) {
		this(patient,true);
		this.panel = p;
	}
	
	public NewKrvnaSlikaDialog(Patient p, Boolean anamneza) {
		this.patient = p;
		anam = anamneza; //ako smo iz anamneze pozvali ovaj dijalog TRUE, ako smo pozvali iz fiz pregleda FALSE
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		String titleText = String.format("Dodavanje krvne slike za pacijenta: %s %s", patient.getFirstName(), patient.getLastName());
		setTitle(titleText);
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(858,301);
		
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
			
		}
		{
			JPanel socioeconomicalPanel = new JPanel();
			socioeconomicalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Krvna slika: ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
			GridBagConstraints gbc_socioeconomicalPanel = new GridBagConstraints();
			gbc_socioeconomicalPanel.insets = new Insets(0, 0, 5, 0);
			gbc_socioeconomicalPanel.fill = GridBagConstraints.BOTH;
			gbc_socioeconomicalPanel.gridx = 0;
			gbc_socioeconomicalPanel.gridy = 2;
			contentPanel.add(socioeconomicalPanel, gbc_socioeconomicalPanel);
			GridBagLayout gbl_socioeconomicalPanel = new GridBagLayout();
			gbl_socioeconomicalPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30};
			gbl_socioeconomicalPanel.rowHeights = new int[] {0, 0, 0, 0, 0};
			gbl_socioeconomicalPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
			gbl_socioeconomicalPanel.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
			socioeconomicalPanel.setLayout(gbl_socioeconomicalPanel);
			{
				JLabel livingPlaceLabel = new JLabel("Leukociti:");
				GridBagConstraints gbc_livingPlaceLabel = new GridBagConstraints();
				gbc_livingPlaceLabel.anchor = GridBagConstraints.WEST;
				gbc_livingPlaceLabel.insets = new Insets(0, 0, 5, 5);
				gbc_livingPlaceLabel.gridx = 2;
				gbc_livingPlaceLabel.gridy = 1;
				socioeconomicalPanel.add(livingPlaceLabel, gbc_livingPlaceLabel);
			}
			{
				JPanel livingPlacePanel = new JPanel();
				GridBagConstraints gbc_livingPlacePanel = new GridBagConstraints();
				gbc_livingPlacePanel.insets = new Insets(0, 0, 5, 0);
				gbc_livingPlacePanel.fill = GridBagConstraints.BOTH;
				gbc_livingPlacePanel.gridx = 6;
				gbc_livingPlacePanel.gridy = 1;
				socioeconomicalPanel.add(livingPlacePanel, gbc_livingPlacePanel);
				livingPlacePanel.setLayout(new GridLayout(0, 2, 0, 0));
				
					JRadioButton cityLivingPlaceRadioButton = new JRadioButton("Poviseni/Leukocitoza");
					cityLivingPlaceRadioButton.setActionCommand("Poviseni");
					cityLivingPlaceRadioButton.setSelected(true);
					cityLivingPlaceRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingPlacePanel.add(cityLivingPlaceRadioButton);
				
				
					JRadioButton villageLivingPlaceRadioButton = new JRadioButton("Snizeni/Leukopenija");
					villageLivingPlaceRadioButton.setActionCommand("Snizeni");
					villageLivingPlaceRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingPlacePanel.add(villageLivingPlaceRadioButton);
				
					livingPlaceButtonGroup = new ButtonGroup();
					livingPlaceButtonGroup.add(cityLivingPlaceRadioButton);
					livingPlaceButtonGroup.add(villageLivingPlaceRadioButton);
			}
			{
				JLabel livingObjectNewLabel = new JLabel("Eritrociti:");
				GridBagConstraints gbc_livingObjectNewLabel = new GridBagConstraints();
				gbc_livingObjectNewLabel.anchor = GridBagConstraints.WEST;
				gbc_livingObjectNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_livingObjectNewLabel.gridx = 2;
				gbc_livingObjectNewLabel.gridy = 2;
				socioeconomicalPanel.add(livingObjectNewLabel, gbc_livingObjectNewLabel);
			}
			{
				JPanel livingObjectPanel = new JPanel();
				GridBagConstraints gbc_livingObjectPanel = new GridBagConstraints();
				gbc_livingObjectPanel.insets = new Insets(0, 0, 5, 0);
				gbc_livingObjectPanel.fill = GridBagConstraints.BOTH;
				gbc_livingObjectPanel.gridx = 6;
				gbc_livingObjectPanel.gridy = 2;
				socioeconomicalPanel.add(livingObjectPanel, gbc_livingObjectPanel);
				livingObjectPanel.setLayout(new GridLayout(1, 0, 0, 0));
				
					JRadioButton flatLivingObjectRadioButton = new JRadioButton("Poviseni/Eritrocitoza");
					flatLivingObjectRadioButton.setActionCommand("Poviseni");
					flatLivingObjectRadioButton.setSelected(true);
					flatLivingObjectRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingObjectPanel.add(flatLivingObjectRadioButton);
				
				
					JRadioButton houseLivingObjecctRadioButton = new JRadioButton("Snizeni/Anemija");
					houseLivingObjecctRadioButton.setActionCommand("Snizeni");
					houseLivingObjecctRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingObjectPanel.add(houseLivingObjecctRadioButton);
				
					livingObjectButtonGroup = new ButtonGroup();
					livingObjectButtonGroup.add(flatLivingObjectRadioButton);
					livingObjectButtonGroup.add(houseLivingObjecctRadioButton);
			}
			{
				JLabel petLabel = new JLabel("Parametarske inflamacije:");
				GridBagConstraints gbc_petLabel = new GridBagConstraints();
				gbc_petLabel.anchor = GridBagConstraints.WEST;
				gbc_petLabel.insets = new Insets(0, 0, 5, 5);
				gbc_petLabel.gridx = 2;
				gbc_petLabel.gridy = 3;
				socioeconomicalPanel.add(petLabel, gbc_petLabel);
			}
			{
				JPanel petPanel = new JPanel();
				GridBagConstraints gbc_petPanel = new GridBagConstraints();
				gbc_petPanel.insets = new Insets(0, 0, 5, 0);
				gbc_petPanel.fill = GridBagConstraints.BOTH;
				gbc_petPanel.gridx = 6;
				gbc_petPanel.gridy = 3;
				socioeconomicalPanel.add(petPanel, gbc_petPanel);
				petPanel.setLayout(new GridLayout(1, 0, 0, 0));
				
					JRadioButton petYesRadioButton = new JRadioButton("Povisene");
					petYesRadioButton.setActionCommand("Povisene");
					petYesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					petPanel.add(petYesRadioButton);
				
				
					JRadioButton petNoRadioButton = new JRadioButton("U normi");
					petNoRadioButton.setActionCommand("U normi");
					petNoRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					petPanel.add(petNoRadioButton);
				
					petButtonGroup = new ButtonGroup();
					petButtonGroup.add(petYesRadioButton);
					petButtonGroup.add(petNoRadioButton);
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
						System.out.println("add action button");
					/*	String alcohol = Utils.getSelectedButtonText(alcoholButtonGroup);
						alcohol = (alcohol != null) ? alcohol : "Ne";
						String smoking = Utils.getSelectedButtonText(smokingButtonGroup);
						smoking = (smoking != null) ? smoking : "Ne";
						String pet = Utils.getSelectedButtonText(petButtonGroup);
						pet = (pet != null) ? pet : "Ne";	*/
						
						System.out.println("preko selection ");
						int patientId = patient.getPatientId();
						//String employed = employedButtonGroup.getSelection().getActionCommand();
						//String workingCondition = workingConditionButtonGroup.getSelection().getActionCommand();
						String livingObject = livingObjectButtonGroup.getSelection().getActionCommand();
						String livingPlace = livingPlaceButtonGroup.getSelection().getActionCommand();
						String pet = petButtonGroup.getSelection().getActionCommand();
						KrvnaSlika k = new KrvnaSlika(-1, patientId, livingPlace, livingObject, pet);
					
						DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
						try {
							dbHandler.createKrvnaSlika(k,anam);
							//DefaultListModel<KrvnaSlika> model =  (DefaultListModel<KrvnaSlika>) panel.getPatientAnamnesisList().getModel();
							//model.addElement(anamnesis);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//ubacujemo u novu tabelu add exam
						KrvnaSlika ctt = new KrvnaSlika();
						List<Object> lista = dbHandler.selectAllPatientCBC(p);
						for (Object object : lista) {
							ctt = (KrvnaSlika) object;
						}
						
						if(dbHandler.selectAdditionalExamination(p.getPatientId()) == null) {
							AdditionalExamination novi = new AdditionalExamination();
							System.out.println("idd1? " + ctt.getId());
							novi.setIdKs(ctt.getId());
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
							novi.setIdKs(ctt.getId());
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

	

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
