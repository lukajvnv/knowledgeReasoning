package com.application.medCareApplication.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.utils.MyDateField;

public class NewPatientDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6011252674744475176L;
	
	protected final JPanel contentPanel = new JPanel();
	protected JTextField firstNameTextField;
	protected JTextField lastNameTextField;
	protected JTextField jmbgTextField;
	protected JTextField addressTextField;
	protected JTextField telephoneNumberTextField;
	protected JLabel firstNameLabel;
	protected JLabel lastNameLabel;
	protected JLabel jmbgLabel;
	protected JLabel addressLabel;
	protected JLabel telephoneNumberLabel;
	protected MyDateField dateOfBirthDateField;
	protected JButton addPatientButton;
	protected JButton cancelButton;
	
	protected JPanel buttonPane;
	

	/**
	 * Create the dialog.
	 */
	public NewPatientDialog() {
		
		
		initComponents();
		
		
		
	}
	
	public void addActionListeners() {
		addPatientButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				boolean valid = validation();
				
				if(!valid) {
					return;
				}
				
				String firstName = firstNameTextField.getText().trim();
				String lastName = lastNameTextField.getText().trim();
				String address = addressTextField.getText().trim();
				String telephone = telephoneNumberTextField.getText().trim();
				String jmbg = jmbgTextField.getText().trim();
				String dateOfBirth = dateOfBirthDateField.getValue();
				
				Patient p = new Patient(-1, firstName, lastName, jmbg, dateOfBirth, address, telephone);
				System.out.println(p);
				
				DatabaseHandler databaseHandler = MainFrame.getInstance().getDatabaseHandler();
				try {
					databaseHandler.createPatient(p);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				MainFrame.getInstance().updateMainPanelPatientsTable();
				dispose();
			}
		});
	}
	
	public void initComponents() {
		setModal(true);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Dodavanje novog pacijenta");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(600,450);

		
		//setBounds(100, 100, 550, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 0, 0};
		gbl_contentPanel.rowHeights = new int[] {30, 0, 30, 0, 30, 0, 30, 0, 30, 0, 30, 0, 30, 30};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPanel.setLayout(gbl_contentPanel);
		{
			firstNameLabel = new JLabel("Ime pacijenta:");
			firstNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			GridBagConstraints gbc_firstNameLabel = new GridBagConstraints();
			gbc_firstNameLabel.anchor = GridBagConstraints.WEST;
			gbc_firstNameLabel.insets = new Insets(5, 5, 5, 5);
			gbc_firstNameLabel.gridx = 2;
			gbc_firstNameLabel.gridy = 1;
			contentPanel.add(firstNameLabel, gbc_firstNameLabel);
		}
		{
			firstNameTextField = new JTextField();
			//firstNameTextField.addFocusListener(new MyFieldFocusListener());
			firstNameLabel.setLabelFor(firstNameTextField);
			GridBagConstraints gbc_firstNameTextField = new GridBagConstraints();
			gbc_firstNameTextField.insets = new Insets(0, 0, 5, 0);
			gbc_firstNameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_firstNameTextField.gridx = 6;
			gbc_firstNameTextField.gridy = 1;
			contentPanel.add(firstNameTextField, gbc_firstNameTextField);
			firstNameTextField.setColumns(10);
		}
		{
			lastNameLabel = new JLabel("Prezime pacijenta:");
			GridBagConstraints gbc_lastNameLabel = new GridBagConstraints();
			gbc_lastNameLabel.anchor = GridBagConstraints.WEST;
			gbc_lastNameLabel.insets = new Insets(5, 5, 5, 5);
			gbc_lastNameLabel.gridx = 2;
			gbc_lastNameLabel.gridy = 3;
			contentPanel.add(lastNameLabel, gbc_lastNameLabel);
		}
		{
			lastNameTextField = new JTextField();
			lastNameLabel.setLabelFor(lastNameTextField);
			GridBagConstraints gbc_lastNameTextField = new GridBagConstraints();
			gbc_lastNameTextField.insets = new Insets(0, 0, 5, 0);
			gbc_lastNameTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_lastNameTextField.gridx = 6;
			gbc_lastNameTextField.gridy = 3;
			contentPanel.add(lastNameTextField, gbc_lastNameTextField);
			lastNameTextField.setColumns(10);
		}
		{
			jmbgLabel = new JLabel("Jmbg pacijenta:");
			GridBagConstraints gbc_jmbgLabel = new GridBagConstraints();
			gbc_jmbgLabel.anchor = GridBagConstraints.WEST;
			gbc_jmbgLabel.insets = new Insets(5, 5, 5, 5);
			gbc_jmbgLabel.gridx = 2;
			gbc_jmbgLabel.gridy = 5;
			contentPanel.add(jmbgLabel, gbc_jmbgLabel);
		}
		{
			jmbgTextField = new JTextField();
			jmbgLabel.setLabelFor(jmbgTextField);
			GridBagConstraints gbc_jmbgTextField = new GridBagConstraints();
			gbc_jmbgTextField.insets = new Insets(0, 0, 5, 0);
			gbc_jmbgTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_jmbgTextField.gridx = 6;
			gbc_jmbgTextField.gridy = 5;
			contentPanel.add(jmbgTextField, gbc_jmbgTextField);
			jmbgTextField.setColumns(10);
		}
		{
			JLabel dateOfBirthLabel = new JLabel("Datum ro\u0111enja:");
			GridBagConstraints gbc_dateOfBirthLabel = new GridBagConstraints();
			gbc_dateOfBirthLabel.anchor = GridBagConstraints.WEST;
			gbc_dateOfBirthLabel.insets = new Insets(5, 5, 5, 5);
			gbc_dateOfBirthLabel.gridx = 2;
			gbc_dateOfBirthLabel.gridy = 7;
			contentPanel.add(dateOfBirthLabel, gbc_dateOfBirthLabel);
		}
		{
			dateOfBirthDateField = new MyDateField();
			GridBagConstraints gbc_dateOfBirthDateField = new GridBagConstraints();
			gbc_dateOfBirthDateField.insets = new Insets(0, 0, 5, 0);
			gbc_dateOfBirthDateField.fill = GridBagConstraints.HORIZONTAL;
			gbc_dateOfBirthDateField.gridx = 6;
			gbc_dateOfBirthDateField.gridy = 7;
			contentPanel.add(dateOfBirthDateField, gbc_dateOfBirthDateField);
		}
		{
			addressLabel = new JLabel("Adresa pacijenta:");
			GridBagConstraints gbc_addressLabel = new GridBagConstraints();
			gbc_addressLabel.anchor = GridBagConstraints.WEST;
			gbc_addressLabel.insets = new Insets(5, 5, 5, 5);
			gbc_addressLabel.gridx = 2;
			gbc_addressLabel.gridy = 9;
			contentPanel.add(addressLabel, gbc_addressLabel);
		}
		{
			addressTextField = new JTextField();
			addressLabel.setLabelFor(addressTextField);
			GridBagConstraints gbc_addressTextField = new GridBagConstraints();
			gbc_addressTextField.insets = new Insets(0, 0, 5, 0);
			gbc_addressTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_addressTextField.gridx = 6;
			gbc_addressTextField.gridy = 9;
			contentPanel.add(addressTextField, gbc_addressTextField);
			addressTextField.setColumns(10);
		}
		{
			telephoneNumberLabel = new JLabel("Telefon pacijenta:");
			GridBagConstraints gbc_telephoneNumberLabel = new GridBagConstraints();
			gbc_telephoneNumberLabel.anchor = GridBagConstraints.WEST;
			gbc_telephoneNumberLabel.insets = new Insets(5, 5, 5, 5);
			gbc_telephoneNumberLabel.gridx = 2;
			gbc_telephoneNumberLabel.gridy = 11;
			contentPanel.add(telephoneNumberLabel, gbc_telephoneNumberLabel);
		}
		{
			telephoneNumberTextField = new JTextField();
			telephoneNumberLabel.setLabelFor(telephoneNumberTextField);
			GridBagConstraints gbc_telephoneNumberTextField = new GridBagConstraints();
			gbc_telephoneNumberTextField.insets = new Insets(0, 0, 5, 0);
			gbc_telephoneNumberTextField.fill = GridBagConstraints.HORIZONTAL;
			gbc_telephoneNumberTextField.gridx = 6;
			gbc_telephoneNumberTextField.gridy = 11;
			contentPanel.add(telephoneNumberTextField, gbc_telephoneNumberTextField);
			telephoneNumberTextField.setColumns(10);
		}
		{
		    buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(25);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addPatientButton = new JButton("Dodaj");
				addPatientButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				addPatientButton.setActionCommand("OK");
				
				buttonPane.add(addPatientButton);
				//getRootPane().setDefaultButton(addPatientButton); "oznacava dugme"
			}
			{
				cancelButton = new JButton("Prekini");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
						
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addPatientButton.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cancelButton.doClick();
				}
				
			}
		});
	}
	
	
	public boolean validation() {
		String firstName = firstNameTextField.getText().trim();
		String lastName = lastNameTextField.getText().trim();
		String address = addressTextField.getText().trim();
		String telephone = telephoneNumberTextField.getText().trim();
		String jmbg = jmbgTextField.getText().trim();
		String dateOfBirth = dateOfBirthDateField.getValue();
		
		if(firstName.equals("")) {
			Utils.warning("Popunite sva polja!");
			firstNameTextField.setBackground(Color.RED);
			return false;
		} 
		if(lastName.equals("")) {
			Utils.warning("Popunite sva polja!");
			return false;
		}
		if(address.equals("")) {
			Utils.warning("Popunite sva polja!");
			return false;
		}
		if(telephone.equals("")) {
			Utils.warning("Popunite sva polja!");
			return false;
		}
		if(dateOfBirth.equals("")) {
			Utils.warning("Odaberite datum!");
			return false;
		}
		
		if (!jmbg.matches("[0-9_]{13}")) {
			Utils.error("Nevalidan jmbg!");
			return false;
		}
		
		return true;
	}
	
}
