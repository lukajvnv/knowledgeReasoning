package com.application.medCareApplication.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.application.medCareApplication.controller.PatientsTablePopUpMenu;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.table.PatientsTableModel;

public class PatientTableFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9071514013721694970L;
	private JPanel contentPane;
	private JTable patientsTable;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientTableFrame frame = new PatientTableFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PatientTableFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		PatientsTableModel tableModel = new PatientsTableModel();
		patientsTable = new JTable(tableModel);
		
		patientsTable.setPreferredScrollableViewportSize(new Dimension(500, 400));
		patientsTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(patientsTable);
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		addListeners();
	}
	
	/**
	 * Dodavanje obradjivaca dogadjaja 
	 */
	public void addListeners(){
		patientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
							
							dispose();
							PatientFrame patientFrame = new PatientFrame();
							patientFrame.setVisible(true);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		});
		
		patientsTable.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
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
							
							dispose();
							PatientFrame patientFrame = new PatientFrame();
							patientFrame.setVisible(true);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}
		});
		
		
	}

	public JTable getTable() {
		return patientsTable;
	}
}
