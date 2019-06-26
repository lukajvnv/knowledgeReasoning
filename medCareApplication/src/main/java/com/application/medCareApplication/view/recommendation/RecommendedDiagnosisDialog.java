package com.application.medCareApplication.view.recommendation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.application.medCareApplication.connector.CbrApplication;
import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.model.Resources;
import com.application.medCareApplication.model.Therapy;
import com.application.medCareApplication.utils.AdditionalExaminationEnum;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.utils.components.ReasoningAnswerData;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.dialog.addNewExamination.NewCTDialog;
import com.application.medCareApplication.view.dialog.addNewExamination.NewKrvnaSlikaDialog;
import com.application.medCareApplication.view.dialog.addNewExamination.NewRTGPlucaDialog;
import com.application.medCareApplication.view.dialog.addNewExamination.NewUltraZvukDialog;
import com.application.medCareApplication.view.utils.DateLabelFormatter;

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

public class RecommendedDiagnosisDialog extends JDialog {
	
	private static final long serialVersionUID = 212032573842458559L;
	private final JPanel contentPanel = new JPanel();
	
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
	private JButton btnDodaj = new JButton("Dodaj");
	private JButton button = new JButton("Pogledaj");
	
	private ReasoningAnswerData lastSelectedTherapyElement;
	private Diagnosis diagnosis;
	
	public RecommendedDiagnosisDialog(Patient p,Boolean v) {
		this.vrsta = v;
		this.patient = p;
		this.patientAnamnesisList = new JList<>();
		
		//btnUradi = new JButton();
		
		
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
		String titleText = String.format("Predlog dijagnoze: %s %s", patient.getFirstName(), patient.getLastName());
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
			
			GridBagConstraints gbc_textArea = new GridBagConstraints();
			gbc_textArea.gridwidth = 7;
			gbc_textArea.insets = new Insets(0, 0, 5, 0);
			gbc_textArea.fill = GridBagConstraints.BOTH;
			gbc_textArea.gridx = 0;
			gbc_textArea.gridy = 0;
		}
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
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
				poslednji4="";
				
			if(vrsta) { // RADICE SE ZA ANAMNEZU
					
					if(reasoning.equals("Rule based")) {
						///RULE BASE
						
						System.out.println("Tu smo 1");
						
						
						ProbabilisticNetwork net = new ProbabilisticNetwork("example");
						// loading from file
						 BaseIO io = new NetIO();
						 try {
							net = (ProbabilisticNetwork)io.load(new File("dijagnoze_anamneza.net"));
						} catch (LoadException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
						 System.out.println("STa se desava?? : " + net.getEdge(net.getNode("Smoking"), net.getNode("solution")));
						 Node n1 = net.getNode("diagnosis");
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
					
						 
						 ProbabilisticNode factNode3 = (ProbabilisticNode)net.getNode("living_place");
						 int stateIndex3;
						 if(patientAnamnesis.getLivingPlace().equals("Grad")) {
							 stateIndex3 = 0; // index of state ""
						 } else {
							 stateIndex3 = 1; // index of state ""
						 }		 
						 factNode3.addFinding(stateIndex3);
						 
						 
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
			 
						 } else {
							 System.out.println("Ne postoji ranija bolest, ne moze se tumaciti na osnovu toga");
							 
						 }
						 
						 
						 
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
						Node solution = net.getNode("diagnosis");
						System.out.println("Diagnosis: " + solution.getName());
						temp+= "Solution: " + solution.getName() + "\n";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							
							listaVerovatnoca.add(((ProbabilisticNode)solution).getMarginalAt(i));
							
						}
						Collections.sort(listaVerovatnoca);
						Collections.reverse(listaVerovatnoca);
						double max = 0.0;
						String maxId = "";
						temp="";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							temp += solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i) + "\n";
							System.out.println(solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i));
							solutionList.add(temp);
							temp="";
							if(((ProbabilisticNode)solution).getMarginalAt(i) > max) {
								maxId = "";
								max = ((ProbabilisticNode)solution).getMarginalAt(i);
								maxId += solution.getStateAt(i);
							}
						}
						
						System.out.println("*****\n");
						
						System.out.println(maxId.toUpperCase() + ": " + max);
						
						poslednji4 += maxId.toUpperCase();
						
						btnDodaj.setText("Dodaj '" + poslednji4 + "'" );
						
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
							anam.setLivingPlace(patientAnamnesis.getLivingPlace());
							anam.setSmoking(patientAnamnesis.getSmoking());							
							
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
						btnDodaj.setText("Dodaj '" + poslednji4 + "'");
						
					
					}
					displaySolutions(solutionList);		
			}	else { 
				if(reasoning.equals("Rule based")) { //RULE BASED
						
						ProbabilisticNetwork net = new ProbabilisticNetwork("example");
						// loading from file
						 BaseIO io = new NetIO();
						 try {
							net = (ProbabilisticNetwork)io.load(new File("dijagnoze_fizikalni_pregled.net"));
						} catch (LoadException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 
						 System.out.println("STa se desava?? : " + net.getEdge(net.getNode("Smoking"), net.getNode("solution")));
						 Node n1 = net.getNode("solution");
						 //System.out.println("STa se desava?? : " + n1.getStateAt(0));
						 

						 
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
						Node solution = net.getNode("diagnosis");
						System.out.println("Solution: " + solution.getName());
						temp+= "Solution: " + solution.getName() + "\n";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							
							listaVerovatnoca.add(((ProbabilisticNode)solution).getMarginalAt(i));
							
						}
						Collections.sort(listaVerovatnoca);
						Collections.reverse(listaVerovatnoca);
						temp="";
						double max = 0;
						String maxId = "";
						for(int i = 0; i < solution.getStatesSize(); i++) {
							temp += solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i) + "\n";
							System.out.println(solution.getStateAt(i) + ": " +  ((ProbabilisticNode)solution).getMarginalAt(i));
							solutionList.add(temp);
							temp="";
							if(((ProbabilisticNode)solution).getMarginalAt(i) > max) {
								maxId = "";
								max = ((ProbabilisticNode)solution).getMarginalAt(i);
								maxId += solution.getStateAt(i);
							}
						}
						
						
						System.out.println("*************\n" + temp);
						
						System.out.println(maxId.toUpperCase() + ": " + max);
						
						poslednji4 += maxId.toUpperCase();
						
						btnDodaj.setText("Dodaj '" + poslednji4 + "'" );
						
						
						
						
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
						
						String pom = solutionList.get(6);
						
						String[] sss = pom.split("=");
						
						String sss2 = sss[1];
						
						poslednji4 = sss2.substring(0, sss2.length()-1);
						
						System.out.println("Poslednji : " + poslednji4);
						btnDodaj.setText("Dodaj '" + poslednji4 + "'" );
						
						//solutionList.add(temp);
						
					}
					displaySolutions(solutionList);
				}
			}
				
			});
	//	});
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 7;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPanel.add(panel_1, gbc_panel_1);
		
		JLabel label_2 = new JLabel("Preporucene dijagnoze su:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(label_2);
		
		JScrollPane medicamentsSrollPane = new JScrollPane(solutionList);
		panel_1.add(medicamentsSrollPane, BorderLayout.CENTER);
		
		GridBagConstraints gbc_btnDodaj = new GridBagConstraints();
		gbc_btnDodaj.insets = new Insets(0, 0, 0, 5);
		gbc_btnDodaj.gridx = 1;
		gbc_btnDodaj.gridy = 5;
		contentPanel.add(btnDodaj, gbc_btnDodaj);
		btnDodaj.setText("Dodaj '" + poslednji4 + "'" );
		
		btnDodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					/*Izvuci sta je selektovan podatak konkretno lek/terapija */
					DateLabelFormatter dF = new DateLabelFormatter();		
					Diagnosis newDiagnosis = new Diagnosis(-1, patient.getPatientId(), poslednji4, dF.getDateFormatter().format(new Date()));
					DatabaseHandler handler = MainFrame.getInstance().getDatabaseHandler();
					try {
						handler.createDiagnosis(newDiagnosis);
						Utils.info("Dijagnoza je dodata u karton pacijenta");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
