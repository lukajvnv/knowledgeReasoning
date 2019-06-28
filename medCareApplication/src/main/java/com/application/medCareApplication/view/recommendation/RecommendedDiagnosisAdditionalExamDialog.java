package com.application.medCareApplication.view.recommendation;

import java.awt.BorderLayout;
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
import java.util.List;

import javax.swing.AbstractAction;
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
import javax.swing.border.EmptyBorder;

import org.hibernate.property.access.internal.PropertyAccessMapImpl.GetterImpl;

import com.application.medCareApplication.connector.CbrApplication;
import com.application.medCareApplication.model.AdditionalExamination;
import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.model.examination.CTpluca;
import com.application.medCareApplication.model.examination.KrvnaSlika;
import com.application.medCareApplication.model.examination.RTGPluca;
import com.application.medCareApplication.model.examination.UltraZvuk;
import com.application.medCareApplication.utils.AdditionalExaminationEnum;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.utils.DateLabelFormatter;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CBRQuery;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;
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

public class RecommendedDiagnosisAdditionalExamDialog extends JDialog {

	private static final long serialVersionUID = 21203257384245855L;
	private final JPanel contentPanel = new JPanel();
	
	private Patient patient;
	private JList<Anamnesis> patientAnamnesisList;
	
	private Anamnesis patientAnamnesis = new Anamnesis(); //anamneza konkretnog pacijenta na osnovu koje cbr treba da nam da rezultat dopunskog pregleda
	private PhysicalExamination patientPhysicalExamination = new PhysicalExamination(); // fiz pregled konkretnog pacijenta
	private Diagnosis patientDiagnosis = new Diagnosis();
	
	private JComboBox<String> combo;
	private JList<String> solutionList = new JList<String>();
	private List<String> allPossibleDiagnosis;
	
	private AdditionalExaminationEnum addEnum;
	private String poslednji4 = "";
	
	private Boolean vrsta; // vrsta ili ti da li je anamneza(true) ili fizikalni pregled (false) u pitanju
	private JButton btnUradi = new JButton("");
	private JButton button = new JButton("Pogledaj");
	
	
	//additional
	private AdditionalExamination additinal = new AdditionalExamination();
	private KrvnaSlika ks = new KrvnaSlika();
	private RTGPluca rtg = new RTGPluca();
	private CTpluca ct = new CTpluca();
	private UltraZvuk uz = new UltraZvuk();
	
	public RecommendedDiagnosisAdditionalExamDialog(Patient p) {
		this.patient = p;
		
		//za dopunska ispitivanja
				//DefaultListModel<AdditionalExamination> physicalListModel = new DefaultListModel<AdditionalExamination>();
		DatabaseHandler dbHandler = MainFrame.getInstance().getDatabaseHandler();
		additinal = dbHandler.selectAdditionalExamination(patient.getPatientId());
		
		if(additinal.getIdCt() != 0) {
			ct = dbHandler.selectPatientCt(additinal.getIdCt());
			System.out.println(ct.toString());
			
		} else {
			ct = null;
		}
		
		if(additinal.getIdKs() != 0) {
			ks = dbHandler.selectPatientKs(additinal.getIdKs());
		} else {
			ks = null;
		}
		
		if(additinal.getIdRtg() != 0) {
			rtg = dbHandler.selectPatientRtg(additinal.getIdRtg());
		} else {
			rtg = null;
		}
		
		/*if(additinal.getIdUz() != 0) {
			
		} else {
			
		}*/
		
		
		
		
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
		//	personalAnamnesisPanel.add(textArea, gbc_textArea);
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
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String reasoning = (String) combo.getSelectedItem();
				List<String> solutionList = new ArrayList<String>();
				poslednji4="";
				
				if(reasoning.equals("Rule based")) {
					ProbabilisticNetwork net = new ProbabilisticNetwork("example");
					BaseIO io = new NetIO();
					try {
						net = (ProbabilisticNetwork)io.load(new File("dijagnoze_dopunska_ispitivanja.net"));
					} catch (LoadException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					//djuka
					IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
					algorithm.setNetwork(net);
					algorithm.run();
					
					ProbabilisticNode factNode2 = (ProbabilisticNode)net.getNode("CT");
					int stateIndex2;
					//System.out.println("Getct: " + ct.getCt());
					if(ct != null) {
						if(ct.getCt().equals("normalan")) {
							stateIndex2 = 0;
						} else {
							stateIndex2 = 1;
						}
						factNode2.addFinding(stateIndex2);
					} else {
						//nikom nista
					}
					
					if(rtg != null) {
						ProbabilisticNode factNode1 = (ProbabilisticNode)net.getNode("RTG");
						int stateIndex1;
						if(rtg.getRtg().equals("normalan")) {
							stateIndex1 = 0;
						} else {
							stateIndex1 = 1;
						}
						factNode1.addFinding(stateIndex1);
					} else {
						//nikom nista
					}
					
					
					
					
				/*	ProbabilisticNode factNode3 = (ProbabilisticNode)net.getNode("krvna_slika");
					int stateIndex3;
					if(ks.getParametarske_inflamacije().equals("U normai")) {
						stateIndex3 = 0;
					} else if (ks.getLeukociti().equals("Poviseni")) {
						stateIndex3 = 1;
					} else if (ks.getLeukociti().equals("Snizeni")) {
						stateIndex3 = 2;
					} else if (ks.getEritrociti().equals("Poviseni")) {
						stateIndex3 = 3;
					} else {
						stateIndex3 = 4;
					}*/
					
					if(ks != null) {
						ProbabilisticNode factNode3 = (ProbabilisticNode)net.getNode("eritrociti");
						int stateIndex3;
						if(ks.getEritrociti().equals("Poviseni")) {
							
							stateIndex3 = 0;
						} else {
							stateIndex3 = 1;
						}
						
						System.out.println("State index 3: " + stateIndex3);
						factNode3.addFinding(stateIndex3);
						
						ProbabilisticNode factNode4 = (ProbabilisticNode)net.getNode("leukociti");
						int stateIndex4;
						if(ks.getEritrociti().equals("Poviseni")) {
							
							stateIndex4 = 0;
						} else {
							stateIndex4 = 1;
						}
						
						System.out.println("State index 3: " + stateIndex4);
						factNode4.addFinding(stateIndex4);
						
						ProbabilisticNode factNode5 = (ProbabilisticNode)net.getNode("parametarske_inflamacije");
						int stateIndex5;
						if(ks.getEritrociti().equals("Povisene")) {
							
							stateIndex5 = 0;
						} else {
							stateIndex5 = 1;
						}
						
						System.out.println("State index 3: " + stateIndex5);
						factNode5.addFinding(stateIndex5);
					} else {
						//nikom nista
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
					
					List<Float> listaVerovatnoca = new ArrayList<Float>();
					
					String temp="";
					Node solution = net.getNode("resenje");
					System.out.println("Solution: " + solution.getName());
					temp+= "Diagnosis: " + solution.getName() + "\n";
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
					
					System.out.println(maxId + ": " + max);
					
					poslednji4 += maxId;
					
					btnUradi.setText("Dodaj '" + poslednji4 + "'" );
					
					System.out.println("*************\n" + temp);
					
					
					//pop
			/*		IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
					algorithm.setNetwork(net);
					algorithm.run();
					
					ProbabilisticNode factNode1 = (ProbabilisticNode)net.getNode("RTG");
					int stateIndex1;
					if(patientAnamnesis.getRezDopunskih().equals("normalan_rtg")) {
						stateIndex1 = 0;
					} else {
						stateIndex1 = 1;
					}
					factNode1.addFinding(stateIndex1);
									
					ProbabilisticNode factNode2 = (ProbabilisticNode)net.getNode("CT");
					int stateIndex2;
					if(patientAnamnesis.getRezDopunskih().equals("normalan")) {
						stateIndex2 = 0;
					} else {
						stateIndex2 = 1;
					}
					factNode2.addFinding(stateIndex2);
					
					ProbabilisticNode factNode3 = (ProbabilisticNode)net.getNode("krvna_slika");
					int stateIndex3;
					if(patientAnamnesis.getRezDopunskih().equals("u_normali")) {
						stateIndex3 = 0;
					} else if (patientAnamnesis.getRezDopunskih().equals("poviseni_leukociti")) {
						stateIndex3 = 1;
					} else if (patientAnamnesis.getRezDopunskih().equals("snizeni_leukociti")) {
						stateIndex3 = 2;
					} else if (patientAnamnesis.getRezDopunskih().equals("poviseni_eritrociti")) {
						stateIndex3 = 3;
					} else {
						stateIndex3 = 4;
					}
					factNode3.addFinding(stateIndex3);
					*/
					/*try {
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
					
					List<Float> listaVerovatnoca = new ArrayList<Float>();
					
					String temp="";
					Node solution = net.getNode("diagnosis");
					System.out.println("Solution: " + solution.getName());
					temp+= "Diagnosis: " + solution.getName() + "\n";
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
					}*/
				}
				else {
					System.out.println("Tu smo 2");
					
					CbrApplication app = new CbrApplication();
					try {
						app.configure();
						
						app.preCycle();
						
						CBRQuery query = new CBRQuery();

						Diagnosis diagnosis = new Diagnosis();
						
						diagnosis.setCt(patientDiagnosis.getCt());
						diagnosis.setRtg(patientDiagnosis.getRtg());
						diagnosis.setEritrociti(patientDiagnosis.getEritrociti());
						diagnosis.setLeukociti(patientDiagnosis.getLeukociti());
						diagnosis.setParametarske_inflamacije(patientDiagnosis.getParametarske_inflamacije());

						
						query.setDescription(diagnosis);
						
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
					
					String poslednji = prvi[prvi.length-7];
					poslednji = poslednji.trim();
					
					String[] poslednji2 = poslednji.split("=");
					String poslednji3 = poslednji2[1];
					
					poslednji4 = poslednji3.substring(0, poslednji3.length());
					System.out.println("Poslednji : " + poslednji4);
					//solutionList.add(temp);
					btnUradi.setText("Dodaj '" + poslednji4 + "'");
					
				
				}
				displaySolutions(solutionList);
		
			}
	
		});
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 4;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		contentPanel.add(panel_1, gbc_panel_1);
		
		JLabel label_2 = new JLabel("Preporucena dopunska ispitivanja su:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		panel_1.add(label_2);
		
		JScrollPane medicamentsSrollPane = new JScrollPane(solutionList);
		panel_1.add(medicamentsSrollPane, BorderLayout.CENTER);
		
		//JButton btnUradi = new JButton("Uradi '" + poslednji4 + "'" );
		GridBagConstraints gbc_btnUradi = new GridBagConstraints();
		gbc_btnUradi.insets = new Insets(0, 0, 0, 5);
		gbc_btnUradi.gridx = 0;
		gbc_btnUradi.gridy = 5;
		contentPanel.add(btnUradi, gbc_btnUradi);
		btnUradi.setName("Dodaj '" + poslednji4 + "'" );
		
		btnUradi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					/*Izvuci sta je selektovan podatak konkretno lek/terapija */
					DateLabelFormatter dF = new DateLabelFormatter();		
					Diagnosis newDiagnosis = new Diagnosis(-1, patient.getPatientId(), poslednji4.toLowerCase(), dF.getDateFormatter().format(new Date()));
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