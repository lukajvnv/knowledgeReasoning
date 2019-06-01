package com.application.medCareApplication.view;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.application.medCareApplication.controller.PatientsTablePopUpMenu;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.table.PatientsTableModel;

public class PatientsTablePanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4868641755855233722L;
	private JTable patientsTable;
	
	

	/**
	 * Create the frame.
	 */
	public PatientsTablePanel() {
		//setBounds(100, 100, 450, 300);
		//setBorder(new EmptyBorder(5, 5, 5, 5));
		//setLayout(new BorderLayout(0, 0));
		
		PatientsTableModel tableModel = new PatientsTableModel();
		patientsTable = new JTable(tableModel);
		
		patientsTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
		patientsTable.setFillsViewportHeight(true);
		patientsTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		setViewportView(patientsTable);
		
		this.getViewport().add(patientsTable);
		
		addListeners();
	}
	
	/**
	 * Dodavanje obradjivaca dogadjaja 
	 */
	public void addListeners(){
		patientsTable.setComponentPopupMenu(new PatientsTablePopUpMenu());
		
		patientsTable.addKeyListener(new KeyAdapter() {
			

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						int selectedRowIndex = patientsTable.getSelectedRow();
						if(selectedRowIndex > -1){
							PatientsTableModel model = (PatientsTableModel) patientsTable.getModel();
							Patient p = model.getSelectedRow(selectedRowIndex).getPatient();
							System.out.println("uspesan enter");
							System.out.println(p);
							
							//dispose();
							PatientFrame patientFrame = new PatientFrame(p);
							patientFrame.setVisible(true);
							
							
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		});
		
		patientsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)  {check(e);}
			@Override
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON3) { //if the event shows the menu
			    	int index = patientsTable.rowAtPoint(e.getPoint());
			    	if(index > -1) {
			    		patientsTable.setRowSelectionInterval(index, index);
			    	}
			    }
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					try {
						int selectedRowIndex = patientsTable.getSelectedRow();
						if(selectedRowIndex > -1){
							PatientsTableModel model = (PatientsTableModel) patientsTable.getModel();
							Patient p = model.getSelectedRow(selectedRowIndex).getPatient();
							System.out.println("uspesan levi dvoklik");
							System.out.println(p);
							
							//dispose();
							PatientFrame patientFrame = new PatientFrame(p);
							patientFrame.setVisible(true);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		});
		
		
	}
	
	public void refreshData() {
		PatientsTableModel tableModel = (PatientsTableModel) patientsTable.getModel();
		tableModel.refreshData();
	}

	public JTable getTable() {
		return patientsTable;
	}
}
