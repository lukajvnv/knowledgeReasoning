package application.view.dialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utils.Utils;

public class NewEwsScoreDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1307086526063669280L;
	private final JPanel contentPanel = new JPanel();
	private JTextField respirationNumberTextField;
	private JTextField saturationO2TextField;
	private JTextField bodyTemperatureTextField;
	private JTextField systalBloodPressureTextField;
	private JTextField heartRateTextField;
	private JLabel patientDetailsLabel;
	private JLabel respirationNumberLabel;
	private JLabel saturationO2NumberLabel;
	private JLabel bodyTemperatureLabel;
	private JLabel systalBloodPressureLabel;
	private JLabel heartRateLabel;
	private JLabel aupuRateLabel;
	private JComboBox<String> aupuRateComboBox;
	private JLabel patientDetailsInfoLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewEwsScoreDialog dialog = new NewEwsScoreDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewEwsScoreDialog() {
		setTitle("Dodavanje ews scora za pacijenta");
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(600,500);
		
		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30};
		gbl_contentPanel.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			patientDetailsLabel = new JLabel("Pacijent:");
			GridBagConstraints gbc_patientDetailsLabel = new GridBagConstraints();
			gbc_patientDetailsLabel.anchor = GridBagConstraints.WEST;
			gbc_patientDetailsLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientDetailsLabel.gridx = 2;
			gbc_patientDetailsLabel.gridy = 0;
			contentPanel.add(patientDetailsLabel, gbc_patientDetailsLabel);
		}
		{
			patientDetailsInfoLabel = new JLabel("New label");
			GridBagConstraints gbc_patientDetailsInfoLabel = new GridBagConstraints();
			gbc_patientDetailsInfoLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientDetailsInfoLabel.gridx = 6;
			gbc_patientDetailsInfoLabel.gridy = 0;
			contentPanel.add(patientDetailsInfoLabel, gbc_patientDetailsInfoLabel);
		}
		{
			respirationNumberLabel = new JLabel("Udisaji:");
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
			saturationO2NumberLabel = new JLabel("Zasi\u0107enje O2:");
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
			bodyTemperatureLabel = new JLabel("Temperatura:");
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
			systalBloodPressureLabel = new JLabel("Sistolni pritisak:");
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
			heartRateLabel = new JLabel("Otkucaji srca:");
			GridBagConstraints gbc_heartRateLabel = new GridBagConstraints();
			gbc_heartRateLabel.anchor = GridBagConstraints.WEST;
			gbc_heartRateLabel.insets = new Insets(5, 5, 0, 5);
			gbc_heartRateLabel.gridx = 2;
			gbc_heartRateLabel.gridy = 12;
			contentPanel.add(heartRateLabel, gbc_heartRateLabel);
		}
		{
			heartRateTextField = new JTextField();
			heartRateLabel.setLabelFor(heartRateTextField);
			GridBagConstraints gbc_heartRateTextField = new GridBagConstraints();
			gbc_heartRateTextField.insets = new Insets(5, 5, 0, 0);
			gbc_heartRateTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_heartRateTextField.gridx = 6;
			gbc_heartRateTextField.gridy = 12;
			contentPanel.add(heartRateTextField, gbc_heartRateTextField);
			heartRateTextField.setColumns(10);
		}
		{
			aupuRateLabel = new JLabel("AUPU skala:");
			GridBagConstraints gbc_aupuRateLabel = new GridBagConstraints();
			gbc_aupuRateLabel.anchor = GridBagConstraints.WEST;
			gbc_aupuRateLabel.insets = new Insets(5, 5, 5, 5);
			gbc_aupuRateLabel.gridx = 2;
			gbc_aupuRateLabel.gridy = 10;
			contentPanel.add(aupuRateLabel, gbc_aupuRateLabel);
		}
		{
			aupuRateComboBox = new JComboBox<String>();
			aupuRateComboBox.addItem("A");
			aupuRateComboBox.addItem("B");
			aupuRateComboBox.addItem("C");
			aupuRateComboBox.addItem("D");

			aupuRateLabel.setLabelFor(aupuRateComboBox);
			GridBagConstraints gbc_aupuRateComboBox = new GridBagConstraints();
			gbc_aupuRateComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_aupuRateComboBox.insets = new Insets(5, 5, 5, 0);
			gbc_aupuRateComboBox.gridx = 6;
			gbc_aupuRateComboBox.gridy = 10;
			contentPanel.add(aupuRateComboBox, gbc_aupuRateComboBox);
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
						boolean valid = validation();
						
						if(!valid) {
							Utils.warning("Nevalidan unos!");
							return;
						}
						/*
						String firstName = firstNameTextField.getText().trim();
						String lastName = lastNameTextField.getText().trim();
						String address = addressTextField.getText().trim();
						String telephone = telephoneNumberTextField.getText().trim();
						String jmbg = jmbgTextField.getText().trim();
						String dateOfBirth = dateOfBirthDateField.getValue();
						
						Patient p = new Patient(1, firstName, lastName, jmbg, dateOfBirth, address, telephone);
						System.out.println(p);*/
					
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

	public JComboBox<String> getAupuRateComboBox() {
		return aupuRateComboBox;
	}
}
