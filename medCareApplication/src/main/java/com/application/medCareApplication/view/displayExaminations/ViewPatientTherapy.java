package com.application.medCareApplication.view.displayExaminations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.PopUpMenus;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;

public class ViewPatientTherapy extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8031918432102395935L;

	private Patient patient;
	
	private JList<Anamnesis> patientTherapyList;
	private JScrollPane scrollPane;
	private DefaultListModel<Anamnesis> therapyListModel;

	
	public ViewPatientTherapy(Patient p) {
		this.patient = p;
		
		setLayout(new BorderLayout(0, 0));
		//setSize(200, 500);
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Sve Terapije");
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
		
		// initList();
				
		scrollPane = new JScrollPane(patientTherapyList);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	
	private void initList() {
		therapyListModel = new DefaultListModel<Anamnesis>();
		
		
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<Anamnesis> anemnesis = dbHandler.selectAllPatientAnamnesis(patient);
		
		
		for (Anamnesis a : anemnesis) {
			 therapyListModel.addElement(a);
		}
		
		patientTherapyList = new JList<Anamnesis>(therapyListModel);
		patientTherapyList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(patientTherapyList.getSelectedValue());
			}
		});
		patientTherapyList.setForeground(Color.BLACK);
		patientTherapyList.setBackground(Color.WHITE);
		
		
		
		//patientTherapyList.setCellRenderer(new ListRenderer());
		patientTherapyList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		//Dodavanje popUpMenija
		PopUpMenus p = new PopUpMenus();
		patientTherapyList.add(p);

		
		patientTherapyList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			    	patientTherapyList.setSelectedIndex(patientTherapyList.locationToIndex(e.getPoint())); //select the item
			        p.show(patientTherapyList, e.getX(), e.getY()); //and show the menu
			    }
			}
			
			public void mouseClicked(MouseEvent e) {
			           if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  

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
