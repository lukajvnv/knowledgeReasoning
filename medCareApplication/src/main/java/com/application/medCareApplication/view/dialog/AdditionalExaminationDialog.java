package com.application.medCareApplication.view.dialog;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.AdditionalExaminationEnum;
import com.application.medCareApplication.utils.PopUpMenus;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.handler.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class AdditionalExaminationDialog extends JDialog {
	
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
	
	private JList<Anamnesis> patientAnamnesisList;
	
	private AdditionalExaminationEnum addEnum;
	private String poslednji4 = "";
	
	
	public AdditionalExaminationDialog(Patient p) {
		
		this.patient = p;
		this.patientAnamnesisList = new JList<>();
		String titleText = String.format("Predlog dopunskih ispitivanja na osnovu anamneze: %s %s", patient.getFirstName(), patient.getLastName());
		setTitle(titleText);
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(920,184);
		
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
			personalAnamnesisPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Predlog: ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 255)));
			GridBagConstraints gbc_personalAnamnesisPanel = new GridBagConstraints();
			gbc_personalAnamnesisPanel.insets = new Insets(0, 0, 5, 5);
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
			
			String temp = "";
			String temp2 = "";
			CBRCase cbr = new CBRCase();
			for (RetrievalResult retrievalResult : MainFrame.getInstance().getEval()) {
				cbr = retrievalResult.get_case();
				temp2 = retrievalResult.get_case().getDescription().toString();
				temp = retrievalResult.get_case().getDescription().toString() + " -> " + retrievalResult.getEval();
				break; // za sad cu uzimati samo jedan predlog
			}
			
			String[] prvi = temp2.split(",");
			String poslednji = prvi[prvi.length-1];
			poslednji = poslednji.trim();
			
			String[] poslednji2 = poslednji.split("=");
			String poslednji3 = poslednji2[1];
			
			poslednji4 = poslednji3.substring(0, poslednji3.length()-1);
			
			
			
			//System.out.println("Da li sam ga dobro splitovao ? ? ? " + poslednji3 + " *** " + poslednji4);
			
			JTextArea textArea = new JTextArea();
			textArea.setText(temp);
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.gridwidth = 7;
			gbc_textArea.insets = new Insets(0, 0, 5, 0);
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = 0;
			personalAnamnesisPanel.add(textArea, gbc_textArea);
		}
		
		JButton btnUradi = new JButton("Uradi '" + poslednji4 + "'" );
		GridBagConstraints gbc_btnUradi = new GridBagConstraints();
		gbc_btnUradi.insets = new Insets(0, 0, 0, 5);
		gbc_btnUradi.gridx = 0;
		gbc_btnUradi.gridy = 5;
		contentPanel.add(btnUradi, gbc_btnUradi);
		
		btnUradi.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(poslednji4.equals("KRVNA_SLIKA")) {
					NewKrvnaSlikaDialog k = new NewKrvnaSlikaDialog(patient);
					k.setVisible(true);			
					dispose();
				} else if(poslednji4.equals("RTG_PLUCA")){
					NewRTGPlucaDialog r = new NewRTGPlucaDialog(patient);
					r.setVisible(true);
					dispose();
				} else if(poslednji4.equals("CT_PLUCA")) {
					NewCTDialog c = new NewCTDialog(patient);
					c.setVisible(true);
					dispose();
				} else if(poslednji4.equals("UZ_PLUCNE_MARAMICE")) {
					NewUltraZvukDialog u = new NewUltraZvukDialog(patient);
					u.setVisible(true);
					dispose();
				} else {
					Utils.warning("Nema ponudjenih dodatnih ispitivanja na osnovu anamneze!");
					System.out.println("Nema ponudjenih dodatnih ispitivanja na osnovu anamneze");
				}
				
			}
		});
		

	}
	
	

}
