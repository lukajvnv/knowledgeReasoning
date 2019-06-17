package com.application.medCareApplication.view.displayExaminations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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

import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.PopUpMenus;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.PatientFrame;
import com.application.medCareApplication.view.recommendation.MedicamentsRecommendationFrame;

public class ViewPatientDiagnosis extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7510497224335781135L;
	
	private Patient patient;
	
	private JList<Diagnosis> patientDiagnosisList;
	private JScrollPane scrollPane;
	private DefaultListModel<Diagnosis> diagnosisListModel;

	private PatientFrame patientFrame;
	
	public ViewPatientDiagnosis(Patient p, PatientFrame patientFrame) {
		this.patient = p;
		this.patientFrame = patientFrame;
		
		setLayout(new BorderLayout(0, 0));
		//setSize(200, 500);
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Sve Dijagnoze");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		toolBar.add(lblNewLabel);
		
		
		JButton newButton = new JButton("Novi");
		newButton.setIcon(new ImageIcon("images\\doc_new_icon&24.png"));
		newButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		toolBar.add(newButton);
		
		JButton deleteButton = new JButton("Obir\u0161i");
		deleteButton.setIcon(new ImageIcon("images\\delete_icon&24.png"));
		toolBar.add(deleteButton);
		
		JButton detailsButton = new JButton("Detaljnije");
		detailsButton.setIcon(new ImageIcon("images\\info_icon&24.png"));
		toolBar.add(detailsButton);
		
		JButton recomendedButton = new JButton("Preporuči terapiju");
		recomendedButton.setIcon(new ImageIcon("images/arrow_top_right_icon&24.png"));
		toolBar.add(recomendedButton);
		
		recomendedButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Diagnosis selectedDiagnosis = patientDiagnosisList.getSelectedValue();
				MedicamentsRecommendationFrame f = new MedicamentsRecommendationFrame(patient, selectedDiagnosis);
				f.setVisible(true);
			}
		});
		
		//Component horizontalStrut = Box.createHorizontalStrut(50);
		//horizontalStrut.setBackground(Color.WHITE);
		//toolBar.add(horizontalStrut);
		
		
		
		 initList();
				
		scrollPane = new JScrollPane(patientDiagnosisList);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	private void initList() {
		diagnosisListModel = new DefaultListModel<Diagnosis>();
		
		
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<Diagnosis> anemnesis = dbHandler.selectAllPatientDiagnosis(patient);
		
		
		for (Diagnosis a : anemnesis) {
			 diagnosisListModel.addElement(a);
		}
		
		patientDiagnosisList = new JList<Diagnosis>(diagnosisListModel);
		patientDiagnosisList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(patientDiagnosisList.getSelectedValue());
			}
		});
		patientDiagnosisList.setForeground(Color.BLACK);
		patientDiagnosisList.setBackground(Color.WHITE);
		
		
		
		//patientDiagnosisList.setCellRenderer(new ListRenderer());
		patientDiagnosisList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		//Dodavanje popUpMenija
		PopUpMenus p = new PopUpMenus();
		patientDiagnosisList.add(p);

		
		patientDiagnosisList.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Diagnosis d = patientDiagnosisList.getSelectedValue();
						if(d != null){
							System.out.println("usao enter");
							patientFrame.setRightPaneComponent(d);
							
							
							
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
			
		});
		
		patientDiagnosisList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			    	patientDiagnosisList.setSelectedIndex(patientDiagnosisList.locationToIndex(e.getPoint())); //select the item
			        p.show(patientDiagnosisList, e.getX(), e.getY()); //and show the menu
			    }
			}
			
			public void mouseClicked(MouseEvent e) {
			           if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  
			        	   Diagnosis d = patientDiagnosisList.getSelectedValue();
							if(patientDiagnosisList != null){
								System.out.println("usao dvoklik");
								patientFrame.setRightPaneComponent(d);
								
								
								
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

}
