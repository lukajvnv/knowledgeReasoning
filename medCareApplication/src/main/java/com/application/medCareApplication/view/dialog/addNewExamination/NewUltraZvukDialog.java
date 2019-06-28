package com.application.medCareApplication.view.dialog.addNewExamination;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.application.medCareApplication.model.AdditionalExamination;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.examination.RTGPluca;
import com.application.medCareApplication.model.examination.UltraZvuk;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;

public class NewUltraZvukDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1307086526063669280L;
	private final JPanel contentPanel = new JPanel();
	private JTextField respirationNumberTextField;
	private JTextField saturationO2TextField;
	private JTextField bodyTemperatureTextField;
	private JTextField systalBloodPressureTextField;
	private JLabel respirationNumberLabel;
	private JLabel saturationO2NumberLabel;
	private JLabel bodyTemperatureLabel;
	private JLabel systalBloodPressureLabel;
	
	private Patient patient;
	
	private Boolean anam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewUltraZvukDialog dialog = new NewUltraZvukDialog(new Patient(),true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewUltraZvukDialog(Patient p, Boolean anamneza) {
		this.patient = p;
		anam = anamneza;
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Dodavanje ultrazvuka pregleda");
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(543,320);
		
		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30};
		gbl_contentPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			respirationNumberLabel = new JLabel("Dubina izliva:");
			GridBagConstraints gbc_respirationNumberLabel = new GridBagConstraints();
			gbc_respirationNumberLabel.anchor = GridBagConstraints.WEST;
			gbc_respirationNumberLabel.insets = new Insets(5, 5, 5, 5);
			gbc_respirationNumberLabel.gridx = 2;
			gbc_respirationNumberLabel.gridy = 2;
			contentPanel.add(respirationNumberLabel, gbc_respirationNumberLabel);
		}
		{
			respirationNumberTextField = new JTextField();
			respirationNumberLabel.setLabelFor(respirationNumberTextField);
			GridBagConstraints gbc_respirationNumberTextField = new GridBagConstraints();
			gbc_respirationNumberTextField.insets = new Insets(5, 5, 5, 0);
			gbc_respirationNumberTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_respirationNumberTextField.gridx = 6;
			gbc_respirationNumberTextField.gridy = 2;
			contentPanel.add(respirationNumberTextField, gbc_respirationNumberTextField);
			respirationNumberTextField.setColumns(10);
		}
		{
			saturationO2NumberLabel = new JLabel("Visina izliva:");
			GridBagConstraints gbc_saturationO2NumberLabel = new GridBagConstraints();
			gbc_saturationO2NumberLabel.anchor = GridBagConstraints.WEST;
			gbc_saturationO2NumberLabel.insets = new Insets(5, 5, 5, 5);
			gbc_saturationO2NumberLabel.gridx = 2;
			gbc_saturationO2NumberLabel.gridy = 4;
			contentPanel.add(saturationO2NumberLabel, gbc_saturationO2NumberLabel);
		}
		{
			saturationO2TextField = new JTextField();
			saturationO2NumberLabel.setLabelFor(saturationO2TextField);
			GridBagConstraints gbc_saturationO2TextField = new GridBagConstraints();
			gbc_saturationO2TextField.insets = new Insets(5, 5, 5, 0);
			gbc_saturationO2TextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_saturationO2TextField.gridx = 6;
			gbc_saturationO2TextField.gridy = 4;
			contentPanel.add(saturationO2TextField, gbc_saturationO2TextField);
			saturationO2TextField.setColumns(10);
		}
		{
			bodyTemperatureLabel = new JLabel("Gustina izliva:");
			GridBagConstraints gbc_bodyTemperatureLabel = new GridBagConstraints();
			gbc_bodyTemperatureLabel.anchor = GridBagConstraints.WEST;
			gbc_bodyTemperatureLabel.insets = new Insets(5, 5, 5, 5);
			gbc_bodyTemperatureLabel.gridx = 2;
			gbc_bodyTemperatureLabel.gridy = 6;
			contentPanel.add(bodyTemperatureLabel, gbc_bodyTemperatureLabel);
		}
		{
			bodyTemperatureTextField = new JTextField();
			bodyTemperatureLabel.setLabelFor(bodyTemperatureTextField);
			GridBagConstraints gbc_bodyTemperatureTextField = new GridBagConstraints();
			gbc_bodyTemperatureTextField.insets = new Insets(5, 5, 5, 0);
			gbc_bodyTemperatureTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_bodyTemperatureTextField.gridx = 6;
			gbc_bodyTemperatureTextField.gridy = 6;
			contentPanel.add(bodyTemperatureTextField, gbc_bodyTemperatureTextField);
			bodyTemperatureTextField.setColumns(10);
		}
		{
			systalBloodPressureLabel = new JLabel("Mesto punkcije:");
			GridBagConstraints gbc_systalBloodPressureLabel = new GridBagConstraints();
			gbc_systalBloodPressureLabel.anchor = GridBagConstraints.WEST;
			gbc_systalBloodPressureLabel.insets = new Insets(5, 5, 5, 5);
			gbc_systalBloodPressureLabel.gridx = 2;
			gbc_systalBloodPressureLabel.gridy = 8;
			contentPanel.add(systalBloodPressureLabel, gbc_systalBloodPressureLabel);
		}
		{
			systalBloodPressureTextField = new JTextField();
			systalBloodPressureLabel.setLabelFor(systalBloodPressureTextField);
			GridBagConstraints gbc_systalBloodPressureTextField = new GridBagConstraints();
			gbc_systalBloodPressureTextField.insets = new Insets(5, 5, 5, 0);
			gbc_systalBloodPressureTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_systalBloodPressureTextField.gridx = 6;
			gbc_systalBloodPressureTextField.gridy = 8;
			contentPanel.add(systalBloodPressureTextField, gbc_systalBloodPressureTextField);
			systalBloodPressureTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(25);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton addNewEwsScoreButton = new JButton("Dodaj");
				addNewEwsScoreButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				addNewEwsScoreButton.setActionCommand("OK");
				buttonPane.add(addNewEwsScoreButton);
				//getRootPane().setDefaultButton(okButton);
				addNewEwsScoreButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("radio button");
						/*boolean valid = validation();
						
						if(!valid) {
							Utils.warning("Nevalidan unos!");
							return;
						}*/
						
						String dubina = respirationNumberTextField.getText().trim();
						String visina = saturationO2TextField.getText().trim();
						String gustina = bodyTemperatureTextField.getText().trim();
						String mesto = systalBloodPressureTextField.getText().trim();

						
						UltraZvuk u = new UltraZvuk(-1, patient.getPatientId(), dubina, visina, gustina, mesto);
						System.out.println(u.toString());
						
						DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
						try {
							dbHandler.createUltraZvuk(u,anam);
						//	DefaultListModel<Anamnesis> model =  (DefaultListModel<Anamnesis>) panel.getPatientAnamnesisList().getModel();
						//	model.addElement(anamnesis);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//ubacujemo u novu tabelu add exam
						UltraZvuk ctt = new UltraZvuk();
						List<Object> lista = dbHandler.selectAllPatientUV(p);
						for (Object object : lista) {
							ctt = (UltraZvuk) object;
						}
						
						if(dbHandler.selectAdditionalExamination(p.getPatientId()) == null) {
							AdditionalExamination novi = new AdditionalExamination();
							System.out.println("idd1? " + ctt.getId());
							novi.setIdUz(ctt.getId());
							novi.setPatientId(patient.getPatientId());
							try {
								dbHandler.createAdditionalExamination(novi);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							AdditionalExamination novi = dbHandler.selectAdditionalExamination(p.getPatientId());
							System.out.println("idd2? " + ctt.getId());
							novi.setIdUz(ctt.getId());
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
	
	private boolean validation() {
		boolean valid = true;
		
		for(Component c : contentPanel.getComponents()) {
			if(c instanceof JTextField) {
				String text = ((JTextField) c).getText().trim();
				if(!text.matches("\\d+")) {
					valid = false;
				}
			}
		}		
		return valid;
	}

	
}
