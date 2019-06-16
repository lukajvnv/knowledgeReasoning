package com.application.medCareApplication.view.recommendation;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.hibernate.query.criteria.internal.expression.function.AbsFunction;

import com.application.medCareApplication.connector.CbrApplication;
import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.model.Resources;
import com.application.medCareApplication.utils.AdditionalExaminationEnum;
import com.application.medCareApplication.utils.PopUpMenus;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.dialog.addNewExamination.NewCTDialog;
import com.application.medCareApplication.view.dialog.addNewExamination.NewKrvnaSlikaDialog;
import com.application.medCareApplication.view.dialog.addNewExamination.NewRTGPlucaDialog;
import com.application.medCareApplication.view.dialog.addNewExamination.NewUltraZvukDialog;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

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
	
	private Anamnesis patientAnamnesis = new Anamnesis(); //anamneza konkretnog pacijenta na osnovu koje cbr treba da nam da rezultat dopunskog pregleda
	private PhysicalExamination patientPhysicalExamination = new PhysicalExamination(); // fiz pregled konkretnog pacijenta
	
	private JComboBox<String> combo;
	private JList<String> solutionList = new JList<String>();
	private List<String> allPossibleDiagnosis;
	
	private AdditionalExaminationEnum addEnum;
	private String poslednji4 = "";
	
	private Boolean vrsta; // vrsta ili ti da li je anamneza(true) ili fizikalni pregled (false) u pitanju
	
	
	public AdditionalExaminationDialog(Patient p,Boolean v) {
		this.vrsta = v;
		this.patient = p;
		this.patientAnamnesisList = new JList<>();
		
		
		//za anamnezu
		DefaultListModel<Anamnesis> anamnesisListModel = new DefaultListModel<Anamnesis>();
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		List<Anamnesis> anemnesis = dbHandler.selectAllPatientAnamnesis(patient);
		

		for (Anamnesis a : anemnesis) {
			 patientAnamnesis = a;
			 anamnesisListModel.addElement(a);
		}
		
		//za fizikalni pregled
		DefaultListModel<PhysicalExamination> physicalListModel = new DefaultListModel<PhysicalExamination>();
		List<PhysicalExamination> physical = dbHandler.selectAllPatientPhysicalExamination(patient);
		

		for (PhysicalExamination a : physical) {
			patientPhysicalExamination = a;
			physicalListModel.addElement(a);
		}
		
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		String titleText = String.format("Predlog dopunskih ispitivanja na osnovu anamneze: %s %s", patient.getFirstName(), patient.getLastName());
		setTitle(titleText);
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(589,530);
		
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
			
		/*	String temp = "";
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
			textArea.setText(temp);*/
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.gridwidth = 7;
			gbc_textArea.insets = new Insets(0, 0, 5, 0);
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = 0;
		//	personalAnamnesisPanel.add(textArea, gbc_textArea);
		}
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{30, 30, 0, 30, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{50, 50, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label_1 = new JLabel("Zakljucivanje:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 1;
		panel.add(label_1, gbc_label_1);
		
		combo = new JComboBox<String>();
		combo.addItem("Rule based");
		combo.addItem("Case based");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 1;
		panel.add(combo, gbc_comboBox);
		
		JButton button = new JButton("Pogledaj");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridx = 5;
		gbc_button.gridy = 1;
		panel.add(button, gbc_button);
		

		
		button.addActionListener(new AbstractAction() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String reasoning = (String) combo.getSelectedItem();
				List<String> solutionList = new ArrayList<String>();
				
				if(vrsta) { // RADICE SE ZA ANAMNEZU
					
					if(reasoning.equals("Rule based")) {
						///RULE BASE
						
						System.out.println("Tu smo 1");
						
						
						ProbabilisticNetwork net = new ProbabilisticNetwork("example");
						// loading from file
						 BaseIO io = new NetIO();
						 try {
							net = (ProbabilisticNetwork)io.load(new File("dopunska_ispitivanja.net"));
						} catch (LoadException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
						 System.out.println("STa se desava?? : " + net.getEdge(net.getNode("Smoking"), net.getNode("solution")));
						 Node n1 = net.getNode("solution");
						 System.out.println("STa se desava?? : " + n1.getStateAt(0));
						 
						 
						 Node n2 = net.getNode("Smoking");
						 System.out.println("Sta je ovo sad ? ? ? : " + n2.getStateAt(0));
						 n2.setStateAt("No", 0);
						 System.out.println("Sta je ovo sad ? ? ? : " + n2.getStateAt(0));
						 
					
						 n2.setStateAt("Yes", 0);
						 
						 System.out.println("Sta je ovo sad ? ? ? : "+ n2.getStateAt(0));
						 
						 System.out.println("***** COMPAILING *****");
						 IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
						 algorithm.setNetwork(net);
						 algorithm.run();
						 System.out.println("****** END COMPAILING ******");
						 
						 ProbabilisticNode factNode1 = (ProbabilisticNode)net.getNode("pet");
						 int stateIndex1;
						 if(patientAnamnesis.getPet().equals("Da")) {
							 stateIndex1 = 0; // index of state ""
						 } else {
							 stateIndex1 = 1; // index of state ""
						 }
						 factNode1.addFinding(stateIndex1);
						 
						 
						 
						 
						 
						 
						 ProbabilisticNode factNode2 = (ProbabilisticNode)net.getNode("living_object");
						 int stateIndex2;
						 if(patientAnamnesis.getLivingObject().equals("Stan")) {
							 stateIndex2 = 0; // index of state ""
						 } else {
							 stateIndex2 = 1; // index of state ""
						 }		 
						 factNode2.addFinding(stateIndex2);
						 
						 ProbabilisticNode factNode3 = (ProbabilisticNode)net.getNode("living_place");
						 int stateIndex3;
						 if(patientAnamnesis.getLivingPlace().equals("Grad")) {
							 stateIndex3 = 0; // index of state ""
						 } else {
							 stateIndex3 = 1; // index of state ""
						 }		 
						 factNode3.addFinding(stateIndex3);
						 
						 ProbabilisticNode factNode4 = (ProbabilisticNode)net.getNode("working_condition");
						 int stateIndex4;
						 if(patientAnamnesis.getWorkingCondition().equals("Fizicki lak posao")) {
							 stateIndex4 = 0; // index of state ""
						 } else {
							 stateIndex4 = 1; // index of state ""
						 }		
						 factNode4.addFinding(stateIndex4);
						 
						 
						 
						 ProbabilisticNode factNode5 = (ProbabilisticNode)net.getNode("Employed");
						 int stateIndex5;
						 if(patientAnamnesis.getEmployed().equals("Zaposlen")) {
							 stateIndex5 = 0; // index of state ""
						 } else {
							 stateIndex5 = 1; // index of state ""
						 }		
						 factNode5.addFinding(stateIndex5);
						 
						 ProbabilisticNode factNode6 = (ProbabilisticNode)net.getNode("Alcohol");
						 int stateIndex6;
						 if(patientAnamnesis.getAlcohol().equals("Da")) {
							 stateIndex6 = 0; // index of state ""
						 } else {
							 stateIndex6 = 1; // index of state ""
						 }		
						 factNode6.addFinding(stateIndex6);
						 
						 ProbabilisticNode factNode7 = (ProbabilisticNode)net.getNode("Smoking");
						 int stateIndex7 ;
						 if(patientAnamnesis.getSmoking().equals("Da")) {
							 stateIndex7 = 0; // index of state ""
						 } else {
							 stateIndex7 = 1; // index of state ""
						 }		
						 factNode7.addFinding(stateIndex7);
						 
						 
						 HashMap<Integer, Integer> mapa = dbHandler.selectAllRanijeBolesti(patient);
						 int diagnosisId = 0;
						 Resources resource = null;
						 
						 if(mapa.containsKey(patientAnamnesis.getPatientId())) {
							 
							 diagnosisId = mapa.get(patientAnamnesis.getPatientId());
							 System.out.println("? ---> " + diagnosisId);
							 
							 resource = dbHandler.selectResourceById(diagnosisId);
			 
						 } else {
							 System.out.println("Ne postoji ranija bolest, ne moze se tumaciti na osnovu toga");
							 
						 }
						 
						 ProbabilisticNode factNode8 = (ProbabilisticNode)net.getNode("ranije_bolesti");
						 int stateIndex8;
						 if(resource.getResourceName().equals("acute_bronchitis")) {
							 stateIndex8 = 0;
						 } else if(resource.getResourceName().equals("acute_sinusitis")) {
							 stateIndex8 = 1;
						 } else if(resource.getResourceName().equals("alergy")) {
							 stateIndex8 = 2;
						 } else if(resource.getResourceName().equals("asthma")) {
							 stateIndex8 = 3;
						 } else if(resource.getResourceName().equals("chronic_sinusitis")) {
							 stateIndex8 = 4;
						 } else if(resource.getResourceName().equals("common_cold")) {
							 stateIndex8 = 5;
						 } else if(resource.getResourceName().equals("copd")) {
							 stateIndex8 = 6;
						 } else if(resource.getResourceName().equals("lung_cancer")) {
							 stateIndex8 = 7;
						 } else if(resource.getResourceName().equals("pleural_efusion")) {
							 stateIndex8 = 8;
						 } else if(resource.getResourceName().equals("pneumonia")) {
							 stateIndex8 = 9;
						 } else if(resource.getResourceName().equals("pneumothorax")) {
							 stateIndex8 = 10;
						 } else if(resource.getResourceName().equals("smoking_addiction")) {
							 stateIndex8 = 11;
						 } else if(resource.getResourceName().equals("otitis_media")) {
							 stateIndex8 = 12;
						 } else if(resource.getResourceName().equals("seasonal_allergies")) {
							 stateIndex8 = 13;
						 } else if(resource.getResourceName().equals("flu")) {
							 stateIndex8 = 14;
						 } else {
							 //ako nema raniju bolest bice stanje u bajesu NONE
							 stateIndex8 = 15;
						 }
						 
						 factNode8.addFinding(stateIndex8);
						 
						 try {
					        	net.updateEvidences();
					        } catch (Exception e2) {
					        	System.out.println(e2.getMessage());               	
					        }
					        
						 List<Node> nodeList = net.getNodes();
					        // states overview after propagation
							for (Node node : nodeList) {
								System.out.println(node.getName());
								for (int i = 0; i < node.getStatesSize(); i++) {
									System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
								}
							}
						
							
						System.out.println("*************\n");
						List<Float> listaVerovatnoca = new ArrayList<Float>();
						
						String temp="";
						Node solution = net.getNode("solution");
						System.out.println("Solution: " + solution.getName());
						temp+= "Solution: " + solution.getName() + "\n";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							
							listaVerovatnoca.add(((ProbabilisticNode)solution).getMarginalAt(i));
							
						}
						Collections.sort(listaVerovatnoca);
						Collections.reverse(listaVerovatnoca);
						temp="";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							temp += solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i) + "\n";
							System.out.println(solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i));
							solutionList.add(temp);
							temp="";
						}
						
						
						System.out.println("*************\n" + temp);
						
						
						
						
					} else {
						System.out.println("Tu smo 2");
						
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
						
						for (String s : prvi) {
							solutionList.add(s);
						}
						
						String poslednji = prvi[prvi.length-3];
						poslednji = poslednji.trim();
						
						String[] poslednji2 = poslednji.split("=");
						String poslednji3 = poslednji2[1];
						
						poslednji4 = poslednji3.substring(0, poslednji3.length());
						System.out.println("Poslednji : " + poslednji4);
						//solutionList.add(temp);
					
					}
					displaySolutions(solutionList);
					

					
				} else { // RADICE SE ZA FIZIKALNI PREGLED
					
					if(reasoning.equals("Rule based")) { //RULE BASED
						
						ProbabilisticNetwork net = new ProbabilisticNetwork("example");
						// loading from file
						 BaseIO io = new NetIO();
						 try {
							net = (ProbabilisticNetwork)io.load(new File("dopunska_ispitivanja_fiz_preg.net"));
						} catch (LoadException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
						 System.out.println("STa se desava?? : " + net.getEdge(net.getNode("Smoking"), net.getNode("solution")));
						 Node n1 = net.getNode("solution");
						 System.out.println("STa se desava?? : " + n1.getStateAt(0));
						 

						 
						 System.out.println("***** COMPAILING *****");
						 IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
						 algorithm.setNetwork(net);
						 algorithm.run();
						 System.out.println("****** END COMPAILING ******");
						 
						 ProbabilisticNode factNode1 = (ProbabilisticNode)net.getNode("respiratoryNoise");
						 int stateIndex1;
						 if(patientPhysicalExamination.getRespiratoryNoise().equals("Normalan")) {
							 stateIndex1 = 0; // index of state ""
						 } else if(patientPhysicalExamination.getRespiratoryNoise().equals("Pukoti")) {
							 stateIndex1 = 1; // index of state ""
						 } else {
							 stateIndex1 = 2;
						 }
						 factNode1.addFinding(stateIndex1);
						 
						 ProbabilisticNode factNode2 = (ProbabilisticNode)net.getNode("respiratorySound");
						 int stateIndex2;
						 if(patientPhysicalExamination.getRespiratorySound().equals("Regularni")) {
							 stateIndex2 = 0; // index of state ""
						 } else {
							 stateIndex2 = 1;
						 }
						 factNode2.addFinding(stateIndex2);
						 
						 
						 ProbabilisticNode factNode3 = (ProbabilisticNode)net.getNode("bodyTemperature");
						 int stateIndex3;
						 if(patientPhysicalExamination.getBodyTemperature().equals("Normalna")) {
							 stateIndex3 = 0; // index of state ""
						 } else if(patientPhysicalExamination.getBodyTemperature().equals("Povisena")) {
							 stateIndex3 = 1; // index of state ""
						 } else {
							 stateIndex3 = 2;
						 }
						 factNode3.addFinding(stateIndex3);
						 
						 try {
					        	net.updateEvidences();
					        } catch (Exception e2) {
					        	System.out.println(e2.getMessage());               	
					        }
					        
						 List<Node> nodeList = net.getNodes();
					        // states overview after propagation
							for (Node node : nodeList) {
								System.out.println(node.getName());
								for (int i = 0; i < node.getStatesSize(); i++) {
									System.out.println(node.getStateAt(i) + ": " + ((ProbabilisticNode)node).getMarginalAt(i));
								}
							}
						
							
						System.out.println("*************\n");
						List<Float> listaVerovatnoca = new ArrayList<Float>();
						
						String temp="";
						Node solution = net.getNode("solution");
						System.out.println("Solution: " + solution.getName());
						temp+= "Solution: " + solution.getName() + "\n";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							
							listaVerovatnoca.add(((ProbabilisticNode)solution).getMarginalAt(i));
							
						}
						Collections.sort(listaVerovatnoca);
						Collections.reverse(listaVerovatnoca);
						temp="";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							temp += solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i) + "\n";
							System.out.println(solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i));
							solutionList.add(temp);
							temp="";
						}
						
						
						System.out.println("*************\n" + temp);
						
						
						
						
					} else { // CASED BASED
						
						CbrApplication app = new CbrApplication();
						try {
							app.configure();
							
							app.preCycle();
							
							CBRQuery query = new CBRQuery();

							PhysicalExamination pe = new PhysicalExamination();
							pe.setBodyTemperature(patientPhysicalExamination.getBodyTemperature());
							pe.setRespiratoryNoise(patientPhysicalExamination.getRespiratoryNoise());
							pe.setRespiratorySound(patientPhysicalExamination.getRespiratorySound());
							
							
							query.setDescription( pe );
							
							app.cycle(query);

							app.postCycle();
							
							//eval = MainFrame.getInstance().getEval();
						} catch (ExecutionException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
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
						
						for (String s : prvi) {
							solutionList.add(s);
						}
						
						String poslednji = prvi[prvi.length-3];
						poslednji = poslednji.trim();
						
						String[] poslednji2 = poslednji.split("=");
						String poslednji3 = poslednji2[1];
						
						poslednji4 = poslednji3.substring(0, poslednji3.length());
						System.out.println("Poslednji : " + poslednji4);
						//solutionList.add(temp);
						
					}
					displaySolutions(solutionList);
				}
				
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPanel.add(panel_1, gbc_panel_1);
		
		JLabel label_2 = new JLabel("Preporucena dopunska ispitivanja su:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(label_2);
		
		JScrollPane medicamentsSrollPane = new JScrollPane(solutionList);
		panel_1.add(medicamentsSrollPane, BorderLayout.CENTER);
		
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
	
	private void displaySolutions(List<String> solutions) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(String s : solutions) {
			model.addElement(s);
		}
		//medicamentsList = new JList<String>(model);
		solutionList.setModel(model);
	}
	
	public JComboBox<String> getResoningTypeComboBox() {
		return combo;
	}
	

}
