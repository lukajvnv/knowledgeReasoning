package application.view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import application.model.Patient;

import java.awt.GridLayout;
import javax.swing.JRadioButton;

public class NewPhysicalExaminationDialog2 extends JDialog {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewPhysicalExaminationDialog2 dialog = new NewPhysicalExaminationDialog2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NewPhysicalExaminationDialog2() {
		setTitle("Dodavanje fizikalnog pregleda");
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
			patientIdTextField.setText("55555");
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
			patientFirstNameTextField.setText("Luka ");
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
			patientLastNameTextField.setText("Jovanovic");
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
			JLabel patientBodyTemperatureLabel = new JLabel("Telesna temperatura:");
			GridBagConstraints gbc_patientBodyTemperatureLabel = new GridBagConstraints();
			gbc_patientBodyTemperatureLabel.anchor = GridBagConstraints.WEST;
			gbc_patientBodyTemperatureLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientBodyTemperatureLabel.gridx = 2;
			gbc_patientBodyTemperatureLabel.gridy = 7;
			contentPanel.add(patientBodyTemperatureLabel, gbc_patientBodyTemperatureLabel);
		}
		{
			patientBodyTemperaturePanel = new JPanel();
			ButtonGroup bg = new ButtonGroup();
			GridBagConstraints gbc_patientBodyTemperaturePanel = new GridBagConstraints();
			gbc_patientBodyTemperaturePanel.insets = new Insets(5, 5, 5, 5);
			gbc_patientBodyTemperaturePanel.fill = GridBagConstraints.BOTH;
			gbc_patientBodyTemperaturePanel.gridx = 6;
			gbc_patientBodyTemperaturePanel.gridy = 7;
			contentPanel.add(patientBodyTemperaturePanel, gbc_patientBodyTemperaturePanel);
			patientBodyTemperaturePanel.setLayout(new GridLayout(1, 3, 5, 5));
			{
				regularTemperatureRadioButton = new JRadioButton("Normalna");
				regularTemperatureRadioButton.setSelected(true);
				patientBodyTemperaturePanel.add(regularTemperatureRadioButton);
			}
			{
				upperBodyTemperatureRadioButton = new JRadioButton("Povisena");
				patientBodyTemperaturePanel.add(upperBodyTemperatureRadioButton);
			}
			{
				lowerBodyTemperatureRadioButton = new JRadioButton("Sni\u017Eena");
				patientBodyTemperaturePanel.add(lowerBodyTemperatureRadioButton);
			}
			bg.add(regularTemperatureRadioButton);
			bg.add(upperBodyTemperatureRadioButton);
			bg.add(lowerBodyTemperatureRadioButton);
		}
		{
			patientRespiratorySoundLabel = new JLabel("Disajni zvuk:");
			GridBagConstraints gbc_patientRespiratorySoundLabel = new GridBagConstraints();
			gbc_patientRespiratorySoundLabel.anchor = GridBagConstraints.WEST;
			gbc_patientRespiratorySoundLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientRespiratorySoundLabel.gridx = 2;
			gbc_patientRespiratorySoundLabel.gridy = 9;
			contentPanel.add(patientRespiratorySoundLabel, gbc_patientRespiratorySoundLabel);
		}
		{
			JComboBox patientRespiratorySoundComboBox = new JComboBox();
			patientRespiratorySoundLabel.setLabelFor(patientRespiratorySoundComboBox);
			patientRespiratorySoundComboBox.setModel(new DefaultComboBoxModel(new String[] {"Regularni", "Patolo\u0161ki"}));
			patientRespiratorySoundComboBox.setSelectedIndex(0);
			GridBagConstraints gbc_patientRespiratorySoundComboBox = new GridBagConstraints();
			gbc_patientRespiratorySoundComboBox.insets = new Insets(5, 5, 5, 5);
			gbc_patientRespiratorySoundComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_patientRespiratorySoundComboBox.gridx = 6;
			gbc_patientRespiratorySoundComboBox.gridy = 9;
			contentPanel.add(patientRespiratorySoundComboBox, gbc_patientRespiratorySoundComboBox);
		}
		{
			patientRespiratoryNoiseLabel = new JLabel("\u0160umovi");
			GridBagConstraints gbc_patientRespiratoryNoiseLabel = new GridBagConstraints();
			gbc_patientRespiratoryNoiseLabel.anchor = GridBagConstraints.WEST;
			gbc_patientRespiratoryNoiseLabel.insets = new Insets(5, 5, 5, 5);
			gbc_patientRespiratoryNoiseLabel.gridx = 2;
			gbc_patientRespiratoryNoiseLabel.gridy = 11;
			contentPanel.add(patientRespiratoryNoiseLabel, gbc_patientRespiratoryNoiseLabel);
		}
		{
			JComboBox<String> patientRespiratoryNoiseComboBox = new JComboBox<String>();
			patientRespiratoryNoiseLabel.setLabelFor(patientRespiratoryNoiseComboBox);
			patientRespiratoryNoiseComboBox.addItem("Normalan");
			patientRespiratoryNoiseComboBox.addItem("Pukoti");
			patientRespiratoryNoiseComboBox.addItem("Žvizduci");
			patientRespiratoryNoiseComboBox.setSelectedIndex(0);
			GridBagConstraints gbc_patientRespiratoryNoiseComboBox = new GridBagConstraints();
			gbc_patientRespiratoryNoiseComboBox.insets = new Insets(5, 5, 5, 5);
			gbc_patientRespiratoryNoiseComboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_patientRespiratoryNoiseComboBox.gridx = 6;
			gbc_patientRespiratoryNoiseComboBox.gridy = 11;
			contentPanel.add(patientRespiratoryNoiseComboBox, gbc_patientRespiratoryNoiseComboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(25);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addPhysicalExamanationButton = new JButton("Dodaj");
				addPhysicalExamanationButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				//addPhysicalExamanationButton.setActionCommand("OK");
				addPhysicalExamanationButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("radio button");
						/*boolean valid = validation();
						
						if(!valid) {
							return;
						}
						
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
				buttonPane.add(addPhysicalExamanationButton);
				//getRootPane().setDefaultButton(addPhysicalExamanationButton);
			}
			{
				cancelButton = new JButton("Prekini");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				//cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
			
		}
		addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
						
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter");
					addPhysicalExamanationButton.doClick();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cancelButton.doClick();
				}
				
			}
		});
	}

	public JButton getAddPhysicalExamanationButton() {
		return addPhysicalExamanationButton;
	}
	public JButton getCancelButton() {
		return cancelButton;
	}
}
