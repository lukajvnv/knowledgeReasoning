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

import com.application.medCareApplication.connector.CbrApplication;
import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.PopUpMenus;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.handler.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.dialog.AdditionalExaminationDialog;
import com.application.medCareApplication.view.dialog.NewAnamnesisDialog;

import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;

public class ViewPatientAnamnesis extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4912684520335316350L;

	private JList<Anamnesis> patientAnamnesisList;
	private Patient patient;
	private JScrollPane scrollPane;
	private DefaultListModel<Anamnesis> anamnesisListModel;
	
	private Anamnesis patientAnamnesis = new Anamnesis(); //anamneza konkretnog pacijenta na osnovu koje cbr treba da nam da rezultat dopunskog pregleda
	Collection<RetrievalResult> eval;
	
	
	@SuppressWarnings("serial")
	public ViewPatientAnamnesis(Patient p) {
		MainFrame.getInstance().setIsAnamnesis(true);
		MainFrame.getInstance().setCurrentPatient(p);
		this.patient = p;
		DefaultListModel<Anamnesis> anamnesisListModel = new DefaultListModel<Anamnesis>();
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<Anamnesis> anemnesis = dbHandler.selectAllPatientAnamnesis(patient);
		

		for (Anamnesis a : anemnesis) {
			 patientAnamnesis = a;
			 anamnesisListModel.addElement(a);
		}
		
		setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JButton newButton = new JButton("Novi");
		newButton.setIcon(new ImageIcon("images\\doc_new_icon&24.png"));
		newButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NewAnamnesisDialog p = new NewAnamnesisDialog(patient, ViewPatientAnamnesis.this);
				p.setVisible(true);
			}
		});
		toolBar.add(newButton);
		
		JButton deleteButton = new JButton("Obir\u0161i");
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
				
				if(anamnesisListModel.isEmpty()) {
					System.out.println("nema anamneza pa ne moze da se preporucuje!");
					Utils.info("Nema anamneza pa ne mogu da se preporucuju dopunski pregledi!");		
				} else {
					CbrApplication app = new CbrApplication();
					try {
						app.configure();
						
						app.preCycle();
						
						CBRQuery query = new CBRQuery();

						Anamnesis anam = new Anamnesis();
						anam.setAlcohol(patientAnamnesis.getAlcohol());
						anam.setEmployed(patientAnamnesis.getEmployed());
						anam.setLivingObject(patientAnamnesis.getLivingObject());
						anam.setLivingPlace(patientAnamnesis.getLivingPlace());
						anam.setPet(patientAnamnesis.getPet());
						anam.setSmoking(patientAnamnesis.getSmoking());
						anam.setWorkingCondition(patientAnamnesis.getWorkingCondition());
						
						
						query.setDescription( anam );
						
						app.cycle(query);

						app.postCycle();
						
						
					} catch (ExecutionException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					AdditionalExaminationDialog dialog = new AdditionalExaminationDialog(patient);
					dialog.setVisible(true);
				}
				
				
				
			}
		});
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		horizontalStrut.setBackground(Color.WHITE);
		toolBar.add(horizontalStrut);
		
		JLabel lblNewLabel = new JLabel("Sve anamneze");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		toolBar.add(lblNewLabel);
		
		JButton btnPregledDopunskiIspitivanja = new JButton("Pregled dopunski ispitivanja");
		toolBar.add(btnPregledDopunskiIspitivanja);
		btnPregledDopunskiIspitivanja.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Anamnesis a = dbHandler.selectPatientAnamnesis(patient.getPatientId());
			//	Patient pom = dbHandler.selectPatient(patient.getPatientId());
				System.out.println("Pregled dopunskih ispitivanja: " + a.getAdditionalExamination());
				Utils.info("Pregled dopunskih ispitivanja: " + a.getAdditionalExamination());
				
			}
		});
		
		initList();
		
		System.out.println("Lista anamneza: " + anamnesisListModel.isEmpty());
		
		scrollPane = new JScrollPane(patientAnamnesisList);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initList() {
		anamnesisListModel = new DefaultListModel<Anamnesis>();
		
		
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<Anamnesis> anemnesis = dbHandler.selectAllPatientAnamnesis(patient);
		
		/*for (int i = 0; i < 15; i++) {
			Anamnesis a = new Anamnesis(1, 1, "da", "da", "zaposlen", "tezak", "selo", "stan", "ker");
			 anamnesisListModel.addElement(a);
		}*/
		for (Anamnesis a : anemnesis) {
			 anamnesisListModel.addElement(a);
		}
		
		patientAnamnesisList = new JList<Anamnesis>(anamnesisListModel);
		patientAnamnesisList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(patientAnamnesisList.getSelectedValue());
			}
		});
		patientAnamnesisList.setForeground(Color.BLACK);
		patientAnamnesisList.setBackground(Color.WHITE);
		
		
		
		//patientAnamnesisList.setCellRenderer(new ListRenderer());
		patientAnamnesisList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		//Dodavanje popUpMenija
		PopUpMenus p = new PopUpMenus();
		patientAnamnesisList.add(p);

		
		patientAnamnesisList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			    	patientAnamnesisList.setSelectedIndex(patientAnamnesisList.locationToIndex(e.getPoint())); //select the item
			        p.show(patientAnamnesisList, e.getX(), e.getY()); //and show the menu
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

	public JList<Anamnesis> getPatientAnamnesisList() {
		return patientAnamnesisList;
	}

	public void setPatientAnamnesisList(JList<Anamnesis> patientAnamnesisList) {
		this.patientAnamnesisList = patientAnamnesisList;
	}

}
