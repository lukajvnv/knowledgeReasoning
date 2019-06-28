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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.displayExaminations.ViewPatientAnamnesis;


public class NewAnamnesisDialog extends JDialog {

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
	
	private JButton btnNewButton;
	
	private Patient patient;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NewAnamnesisDialog dialog = new NewAnamnesisDialog(new Patient());
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
	public NewAnamnesisDialog(Patient patient, ViewPatientAnamnesis p) {
		this(patient);
		this.panel = p;
	}
	
	public NewAnamnesisDialog(Patient p) {
		this.patient = p;
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		String titleText = String.format("Dodavanje anamneze za pacijenta: %s %s", patient.getFirstName(), patient.getLastName());
		setTitle(titleText);
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(600,600);
		
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
			personalAnamnesisPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Li\u010Dna anamneza", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
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
				JLabel smokingLabel = new JLabel("Pu\u0161a\u010D:");
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
				
					JRadioButton smokingYesRadioButton = new JRadioButton("Da");
					smokingYesRadioButton.setActionCommand("Da");
					smokingYesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					smokingPanel.add(smokingYesRadioButton);
				
				
					JRadioButton smokingNoRadioButton = new JRadioButton("Ne");
					smokingNoRadioButton.setActionCommand("Ne");
					smokingNoRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					smokingPanel.add(smokingNoRadioButton);
				
				smokingButtonGroup = new ButtonGroup();
				smokingButtonGroup.add(smokingYesRadioButton);
				smokingButtonGroup.add(smokingNoRadioButton);
				
			}
			{
				JLabel alcoholLabel = new JLabel("Alkohol:");
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
				
					JRadioButton alcoholYesRadioButton = new JRadioButton("Da");
					alcoholYesRadioButton.setActionCommand("Da");
					alcoholYesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					alcoholPanel.add(alcoholYesRadioButton);
				
				
					JRadioButton alcoholNoRadioButton = new JRadioButton("Ne");
					alcoholNoRadioButton.setActionCommand("Ne");
					alcoholNoRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					alcoholPanel.add(alcoholNoRadioButton);
					
				    alcoholButtonGroup = new ButtonGroup();
					alcoholButtonGroup.add(alcoholYesRadioButton);
					alcoholButtonGroup.add(alcoholNoRadioButton);
			}
		}
		{
			JPanel profesionalAnamnesisPanel = new JPanel();
			profesionalAnamnesisPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Profesionalna anamneza", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
			GridBagConstraints gbc_profesionalAnamnesisPanel = new GridBagConstraints();
			gbc_profesionalAnamnesisPanel.insets = new Insets(0, 0, 5, 0);
			gbc_profesionalAnamnesisPanel.fill = GridBagConstraints.BOTH;
			gbc_profesionalAnamnesisPanel.gridx = 0;
			gbc_profesionalAnamnesisPanel.gridy = 1;
			contentPanel.add(profesionalAnamnesisPanel, gbc_profesionalAnamnesisPanel);
			GridBagLayout gbl_profesionalAnamnesisPanel = new GridBagLayout();
			gbl_profesionalAnamnesisPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30};
			gbl_profesionalAnamnesisPanel.rowHeights = new int[]{0, 0, 0};
			gbl_profesionalAnamnesisPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
			gbl_profesionalAnamnesisPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			profesionalAnamnesisPanel.setLayout(gbl_profesionalAnamnesisPanel);
			{
				JLabel employedLabel = new JLabel("Stanje:");
				GridBagConstraints gbc_employedLabel = new GridBagConstraints();
				gbc_employedLabel.anchor = GridBagConstraints.WEST;
				gbc_employedLabel.insets = new Insets(0, 0, 5, 5);
				gbc_employedLabel.gridx = 2;
				gbc_employedLabel.gridy = 0;
				profesionalAnamnesisPanel.add(employedLabel, gbc_employedLabel);
			}
			{
				JPanel emloyedPanel = new JPanel();
				GridBagConstraints gbc_emloyedPanel = new GridBagConstraints();
				gbc_emloyedPanel.insets = new Insets(0, 0, 5, 0);
				gbc_emloyedPanel.fill = GridBagConstraints.BOTH;
				gbc_emloyedPanel.gridx = 6;
				gbc_emloyedPanel.gridy = 0;
				profesionalAnamnesisPanel.add(emloyedPanel, gbc_emloyedPanel);
				emloyedPanel.setLayout(new GridLayout(0, 2, 0, 0));
				
					JRadioButton employedRadioButton = new JRadioButton("Zaposlen");
					employedRadioButton.setActionCommand("Zaposlen");
					employedRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					emloyedPanel.add(employedRadioButton);
				
				
					JRadioButton unemployedRadioButton = new JRadioButton("Nezaposlen");
					unemployedRadioButton.setActionCommand("Nezaposlen");
					unemployedRadioButton.setSelected(true);
					unemployedRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					emloyedPanel.add(unemployedRadioButton);
				
					employedButtonGroup = new ButtonGroup();
					employedButtonGroup.add(employedRadioButton);
					employedButtonGroup.add(unemployedRadioButton);
			}
			{
				JLabel workingConditionLabel = new JLabel("Te\u017Eina:");
				GridBagConstraints gbc_workingConditionLabel = new GridBagConstraints();
				gbc_workingConditionLabel.anchor = GridBagConstraints.WEST;
				gbc_workingConditionLabel.insets = new Insets(0, 0, 0, 5);
				gbc_workingConditionLabel.gridx = 2;
				gbc_workingConditionLabel.gridy = 1;
				profesionalAnamnesisPanel.add(workingConditionLabel, gbc_workingConditionLabel);
			}
			{
				JPanel workingConditionPanel = new JPanel();
				GridBagConstraints gbc_workingConditionPanel = new GridBagConstraints();
				gbc_workingConditionPanel.fill = GridBagConstraints.BOTH;
				gbc_workingConditionPanel.gridx = 6;
				gbc_workingConditionPanel.gridy = 1;
				profesionalAnamnesisPanel.add(workingConditionPanel, gbc_workingConditionPanel);
				workingConditionPanel.setLayout(new GridLayout(0, 2, 0, 0));
				
					JRadioButton lightWorkingConditionRadioButton = new JRadioButton("Fizicki lak posao");
					lightWorkingConditionRadioButton.setActionCommand("Fizicki lak posao");
					lightWorkingConditionRadioButton.setSelected(true);
					lightWorkingConditionRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					workingConditionPanel.add(lightWorkingConditionRadioButton);
				
				
					JRadioButton hardWorkingConditionRadioButton = new JRadioButton("Fizicki tezak posao");
					hardWorkingConditionRadioButton.setActionCommand("Fizicki tezak posao");
					hardWorkingConditionRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					workingConditionPanel.add(hardWorkingConditionRadioButton);
				
					workingConditionButtonGroup = new ButtonGroup();
					workingConditionButtonGroup.add(lightWorkingConditionRadioButton);
					workingConditionButtonGroup.add(hardWorkingConditionRadioButton);
			}
		}
		{
			JPanel socioeconomicalPanel = new JPanel();
			socioeconomicalPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Socioekonomski uslovi", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
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
				JLabel livingPlaceLabel = new JLabel("Mesto stanovanja:");
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
				
					JRadioButton cityLivingPlaceRadioButton = new JRadioButton("Grad");
					cityLivingPlaceRadioButton.setActionCommand("Grad");
					cityLivingPlaceRadioButton.setSelected(true);
					cityLivingPlaceRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingPlacePanel.add(cityLivingPlaceRadioButton);
				
				
					JRadioButton villageLivingPlaceRadioButton = new JRadioButton("Selo");
					villageLivingPlaceRadioButton.setActionCommand("Selo");
					villageLivingPlaceRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingPlacePanel.add(villageLivingPlaceRadioButton);
				
					livingPlaceButtonGroup = new ButtonGroup();
					livingPlaceButtonGroup.add(cityLivingPlaceRadioButton);
					livingPlaceButtonGroup.add(villageLivingPlaceRadioButton);
			}
			{
				JLabel livingObjectNewLabel = new JLabel("Objekat stanovanja:");
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
				
					JRadioButton flatLivingObjectRadioButton = new JRadioButton("Stan");
					flatLivingObjectRadioButton.setActionCommand("Stan");
					flatLivingObjectRadioButton.setSelected(true);
					flatLivingObjectRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingObjectPanel.add(flatLivingObjectRadioButton);
				
				
					JRadioButton houseLivingObjecctRadioButton = new JRadioButton("Kuca");
					houseLivingObjecctRadioButton.setActionCommand("Kuca");
					houseLivingObjecctRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					livingObjectPanel.add(houseLivingObjecctRadioButton);
				
					livingObjectButtonGroup = new ButtonGroup();
					livingObjectButtonGroup.add(flatLivingObjectRadioButton);
					livingObjectButtonGroup.add(houseLivingObjecctRadioButton);
			}
			{
				JLabel petLabel = new JLabel("Ku\u0107ni ljubimci:");
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
				
					JRadioButton petYesRadioButton = new JRadioButton("Da");
					petYesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					petPanel.add(petYesRadioButton);
				
				
					JRadioButton petNoRadioButton = new JRadioButton("Ne");
					petNoRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
					petPanel.add(petNoRadioButton);
				
					petButtonGroup = new ButtonGroup();
					petButtonGroup.add(petYesRadioButton);
					petButtonGroup.add(petNoRadioButton);
			}
		}
		{
			JPanel olderPersonalAnamnesisPanel = new JPanel();
			olderPersonalAnamnesisPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ranije bolesti", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
			GridBagConstraints gbc_olderPersonalAnamnesisPanel = new GridBagConstraints();
			gbc_olderPersonalAnamnesisPanel.insets = new Insets(0, 0, 5, 0);
			gbc_olderPersonalAnamnesisPanel.fill = GridBagConstraints.BOTH;
			gbc_olderPersonalAnamnesisPanel.gridx = 0;
			gbc_olderPersonalAnamnesisPanel.gridy = 3;
			contentPanel.add(olderPersonalAnamnesisPanel, gbc_olderPersonalAnamnesisPanel);
			GridBagLayout gbl_olderPersonalAnamnesisPanel = new GridBagLayout();
			gbl_olderPersonalAnamnesisPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30, 0, 30, 30, 30, 30, 30};
			gbl_olderPersonalAnamnesisPanel.rowHeights = new int[] {0, 0, 0};
			gbl_olderPersonalAnamnesisPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
			gbl_olderPersonalAnamnesisPanel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			olderPersonalAnamnesisPanel.setLayout(gbl_olderPersonalAnamnesisPanel);
			
			JLabel lblNewLabel = new JLabel("Odaberite od ponu\u0111enih:");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 2;
			gbc_lblNewLabel.gridy = 0;
			olderPersonalAnamnesisPanel.add(lblNewLabel, gbc_lblNewLabel);
			
			btnNewButton = new JButton("Odaberi");
			
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
					SelectAnamnesisDialog d = new SelectAnamnesisDialog(true);
					d.setReferenca(NewAnamnesisDialog.this);
					d.setVisible(true);
					
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setIcon(new ImageIcon("images/arrow_top_right_icon&24.png"));
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.insets = new Insets(0, 5, 0, 5);
			gbc_btnNewButton.gridx = 8;
			gbc_btnNewButton.gridy = 0;
			olderPersonalAnamnesisPanel.add(btnNewButton, gbc_btnNewButton);
		}
		{
		/*	JPanel familyPanel = new JPanel();
			familyPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Porodi\u010Dna anamneza", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
			GridBagConstraints gbc_familyPanel = new GridBagConstraints();
			gbc_familyPanel.fill = GridBagConstraints.BOTH;
			gbc_familyPanel.gridx = 0;
			gbc_familyPanel.gridy = 4;
			contentPanel.add(familyPanel, gbc_familyPanel);
			GridBagLayout gbl_familyPanel = new GridBagLayout();
			gbl_familyPanel.columnWidths = new int[] {30, 30, 0, 30, 30, 30, 30, 30, 30, 30, 30, 30};
			gbl_familyPanel.rowHeights = new int[] {0, 0, 0};
			gbl_familyPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0};
			gbl_familyPanel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			familyPanel.setLayout(gbl_familyPanel);
			
			JLabel lblNewLabel_2 = new JLabel("Plu\u0107na oboljena u porodici:");
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_2.gridx = 2;
			gbc_lblNewLabel_2.gridy = 0;
			familyPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 8;
			gbc_panel.gridy = 0;
			familyPanel.add(panel, gbc_panel);
			panel.setLayout(new GridLayout(0, 2, 5, 5));
			
			JRadioButton rdbtnNewRadioButton = new JRadioButton("Da");
			rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panel.add(rdbtnNewRadioButton);
			
			JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Ne");
			rdbtnNewRadioButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			panel.add(rdbtnNewRadioButton_1);
			
			JLabel lblNewLabel_1 = new JLabel("Odaberite od ponu\u0111enih:");
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel_1.gridx = 2;
			gbc_lblNewLabel_1.gridy = 1;
			familyPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			JButton btnNewButton_1 = new JButton("Odaberi");
			btnNewButton_1.addActionListener(new AbstractAction() {
				
				/**
				 * 
				 */
	/*			private static final long serialVersionUID = -2138745628081908457L;

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
			gbc_btnNewButton_1.gridx = 8;
			gbc_btnNewButton_1.gridy = 1;
			familyPanel.add(btnNewButton_1, gbc_btnNewButton_1);*/
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
						String alcohol = Utils.getSelectedButtonText(alcoholButtonGroup);
						alcohol = (alcohol != null) ? alcohol : "Ne";
						String smoking = Utils.getSelectedButtonText(smokingButtonGroup);
						smoking = (smoking != null) ? smoking : "Ne";
						String pet = Utils.getSelectedButtonText(petButtonGroup);
						pet = (pet != null) ? pet : "Ne";	
						
						System.out.println("preko selection ");
						int patientId = patient.getPatientId();
						String employed = employedButtonGroup.getSelection().getActionCommand();
						String workingCondition = workingConditionButtonGroup.getSelection().getActionCommand();
						String livingObject = livingObjectButtonGroup.getSelection().getActionCommand();
						String livingPlace = livingPlaceButtonGroup.getSelection().getActionCommand();
	
						Anamnesis anamnesis = new Anamnesis(-1, patientId, smoking, alcohol, employed, workingCondition, livingPlace, livingObject, pet);
					
						DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
						try {
							anamnesis = dbHandler.createAnamnesis(anamnesis);
							DefaultListModel<Anamnesis> model =  (DefaultListModel<Anamnesis>) panel.getPatientAnamnesisList().getModel();
							model.addElement(anamnesis);
							panel.disableNewButtonIfIsNotEmpty();
							
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

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}
	
	
}
