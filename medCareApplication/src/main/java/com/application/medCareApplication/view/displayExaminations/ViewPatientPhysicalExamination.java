package com.application.medCareApplication.view.displayExaminations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.model.Therapy;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.PatientFrame;
import com.application.medCareApplication.view.dialog.NewPhysicalExaminationDialog;
import com.application.medCareApplication.view.recommendation.AdditionalExaminationDialog;
import com.application.medCareApplication.view.recommendation.RecommendedDiagnosisDialog;

import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

public class ViewPatientPhysicalExamination extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 587890044341809586L;
	
	private JList<PhysicalExamination> patientPhysicalExaminatonList;
	private Patient patient;
	private DefaultListModel<PhysicalExamination> physicalExaminatinoListModel;
	
	private PhysicalExamination patientPhysicalExamination = new PhysicalExamination(); //PhysicalExamination konkretnog pacijenta na osnovu koje cbr treba da nam da rezultat dopunskog pregleda
	Collection<RetrievalResult> eval;
	
	private JButton newButton;
	private PatientFrame patientFrame;
	
	@SuppressWarnings("serial")
	public ViewPatientPhysicalExamination(Patient p, PatientFrame pf) {
		MainFrame.getInstance().setIsAnamnesis(false);
		MainFrame.getInstance().setCurrentPatient(p);
		this.patient = p;
		this.patientFrame = pf;
		
		DefaultListModel<PhysicalExamination> physicalExaminatinoListModel = new DefaultListModel<PhysicalExamination>();
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<PhysicalExamination> anemnesis = dbHandler.selectAllPatientPhysicalExamination(patient);
		

		for (PhysicalExamination a : anemnesis) {
			patientPhysicalExamination = a;
			physicalExaminatinoListModel.addElement(a);
		}
		
		
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Svi (fizikalni) pregledi");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		toolBar.add(lblNewLabel);
		
	    newButton = new JButton("Novi");
		newButton.setIcon(new ImageIcon("images\\doc_new_icon&24.png"));
		newButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NewPhysicalExaminationDialog d = new NewPhysicalExaminationDialog(patient, ViewPatientPhysicalExamination.this);
				d.setVisible(true);
				
			}
		});
		toolBar.add(newButton);
		
		JButton deleteButton = new JButton("Obr\u0161i");
		deleteButton.setIcon(new ImageIcon("images\\delete_icon&24.png"));
		toolBar.add(deleteButton);
		
		JButton detailsButton = new JButton("Detaljnije");
		detailsButton.setIcon(new ImageIcon("images\\info_icon&24.png"));
		toolBar.add(detailsButton);
		
		JButton recomendedButton = new JButton("Preporuci dopunska ispitivanja");
		recomendedButton.setIcon(new ImageIcon("images/arrow_top_right_icon&24.png"));
		toolBar.add(recomendedButton);
		
		recomendedButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				List<PhysicalExamination> anemnesis = dbHandler.selectAllPatientPhysicalExamination(patient);
				if(physicalExaminatinoListModel.isEmpty() && anemnesis.isEmpty()) {
					System.out.println("nema fizikalnog pregleda pa ne moze da se preporucuje!");
					Utils.info("Nema fizikalnog pregleda pa ne mogu da se preporucuju dopunski pregledi!");	
				} else {
					
					
					AdditionalExaminationDialog dialog = new AdditionalExaminationDialog(patient,false);
					dialog.setVisible(true);
				}
				
				
				
				
			}
		});
		
		JButton recommendedDiagnosisButton = new JButton("Preporucena dijagnoza");
		recommendedDiagnosisButton.setIcon(new ImageIcon("images/round_and_up_icon&24.png"));
		toolBar.add(recommendedDiagnosisButton);
		recommendedDiagnosisButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<PhysicalExamination> anemnesis = dbHandler.selectAllPatientPhysicalExamination(patient);
				if(physicalExaminatinoListModel.isEmpty() && anemnesis.isEmpty()) {
					System.out.println("nema fizikalnog pregleda pa ne moze da se preporucuje!");
					Utils.info("Nema fizikalnog pregleda pa ne mogu da se preporucuju dopunski pregledi!");	
				} else {
					
					
					RecommendedDiagnosisDialog dialog = new RecommendedDiagnosisDialog(patient,false);
					dialog.setVisible(true);
				}				
			}
		});
		
//		Component horizontalStrut = Box.createHorizontalStrut(50);
//		horizontalStrut.setBackground(Color.WHITE);
//		toolBar.add(horizontalStrut);
//		
		
		
		initList();
		
		
//		if(!physicalExaminatinoListModel.isEmpty()) {
//			newButton.setEnabled(false);
//		}
		disableNewButtonIfIsNotEmpty();
		
		JScrollPane scrollPane = new JScrollPane(patientPhysicalExaminatonList);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void disableNewButtonIfIsNotEmpty() {
		if(!physicalExaminatinoListModel.isEmpty()) {
			newButton.setEnabled(false);
		}
	}
	
	private void initList() {
		physicalExaminatinoListModel = new DefaultListModel<PhysicalExamination>();
		
		
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<PhysicalExamination> physicalExaminations = dbHandler.selectPatientPhysicalExaminations(patient);
			
		for (PhysicalExamination p : physicalExaminations) {
			physicalExaminatinoListModel.addElement(p);
		}
		
		patientPhysicalExaminatonList = new JList<PhysicalExamination>(physicalExaminatinoListModel);
		patientPhysicalExaminatonList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(patientPhysicalExaminatonList.getSelectedValue());
			}
		});
		patientPhysicalExaminatonList.setForeground(Color.BLACK);
		patientPhysicalExaminatonList.setBackground(Color.WHITE);
		
		
		
		//patientAnamnesisList.setCellRenderer(new ListRenderer());
		patientPhysicalExaminatonList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		//Dodavanje popUpMenija
		/*PopUpMenus p = new PopUpMenus();
		patientPhysicalExaminatonList.add(p);

		
		patientPhysicalExaminatonList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			    	patientPhysicalExaminatonList.setSelectedIndex(patientPhysicalExaminatonList.locationToIndex(e.getPoint())); //select the item
			        p.show(patientPhysicalExaminatonList, e.getX(), e.getY()); //and show the menu
			    }
			}
			
			public void mouseClicked(MouseEvent e) {
			           if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  

			           }
			       }
		});*/
		
		patientPhysicalExaminatonList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			    	patientPhysicalExaminatonList.setSelectedIndex(patientPhysicalExaminatonList.locationToIndex(e.getPoint())); //select the item
			        //p.show(patientTherapyList, e.getX(), e.getY()); //and show the menu
			    }
			}
			
			public void mouseClicked(MouseEvent e) {
			           if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  
			        	   PhysicalExamination t = patientPhysicalExaminatonList.getSelectedValue();
							if(t != null){
								System.out.println("usao enter");
								patientFrame.setRightPaneComponent(t);
								
								
								
							}
			           }
			       }
		});
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public JList<PhysicalExamination> getPatientPhysicalExaminatonList() {
		return patientPhysicalExaminatonList;
	}

	public void setPatientPhysicalExaminatonList(JList<PhysicalExamination> patientPhysicalExaminatonList) {
		this.patientPhysicalExaminatonList = patientPhysicalExaminatonList;
	}

}
