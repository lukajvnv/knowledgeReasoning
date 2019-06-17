package com.application.medCareApplication.view.recommendation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.Resources;
import com.application.medCareApplication.model.Therapy;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.utils.components.PrologHandler;
import com.application.medCareApplication.utils.components.RDFHandler;
import com.application.medCareApplication.utils.components.ReasoningAnswerData;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.utils.AutoComplete;
import com.application.medCareApplication.view.utils.DateLabelFormatter;

public class MedicamentsRecommendationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6142157244487361175L;
	private JPanel contentPane;
	private JTextField diagnoseTextField;
	
	private JList<ReasoningAnswerData> cbrMedicamentsList = new JList<ReasoningAnswerData>();
	private JList<ReasoningAnswerData> rbrMedicamentsList = new JList<ReasoningAnswerData>();
	private List<String> allPossibleDiagnosis;
	private static final String COMMIT_ACTION = "commit";
	
	private ReasoningAnswerData lastSelectedTherapyElement;
	private String diagnose;
	
	private Patient patient;

	//private String[] defaultValues =  {};
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings("serial")
	public MedicamentsRecommendationFrame(Patient p, Diagnosis selectedDiagnosis) {
		this.patient = p;
		
		initList(rbrMedicamentsList);
		initList(cbrMedicamentsList);
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Preporucivanje terapije za pacijenta");
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);*/
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//setSize(500,500);
		setSize(700, 700);
		setResizable(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel infoPanel = new JPanel();
		contentPane.add(infoPanel, BorderLayout.NORTH);
		
		JLabel infoLabel = new JLabel("Preporucivanje lekova");
		infoLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		infoPanel.add(infoLabel);
		
		JPanel buttonPanel = new JPanel();
		FlowLayout fl_buttonPanel = (FlowLayout) buttonPanel.getLayout();
		fl_buttonPanel.setAlignment(FlowLayout.RIGHT);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Dodaj novu terapiju");
		btnNewButton.setIcon(new ImageIcon("images/doc_new_icon&24.png"));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Set<Field> fields = ReflectionUtils.getAllFields(Diagnosis.class, new Predicate() {
//				    public boolean apply(Object input) {
//				        return true;
//				    }
//
//					@Override
//					public boolean test(Object t) {
//						// TODO Auto-generated method stub
//						return false;
//					}});
//				   Map<Field, Object) values = new HashMap<Field, Object>();
//				   for(Field f : fields) {
//				        f.setAccessible(true);
//				        values.put(f, f.get(obj);
//				    }
				
				if(lastSelectedTherapyElement != null) {
					String therapy = findTherapyString(lastSelectedTherapyElement);
					if(therapy.equals("")) {
						Utils.error("Ne postoji ime leka");
						return;
					}
					
					/*Izvuci sta je selektovan podatak konkretno lek/terapija */
					DateLabelFormatter dF = new DateLabelFormatter();		
					Therapy newTherapy = new Therapy(-1, patient.getPatientId(), diagnose, therapy, dF.getDateFormatter().format(new Date()));
					DatabaseHandler handler = MainFrame.getInstance().getDatabaseHandler();
					try {
						handler.createTherapy(newTherapy);
						Utils.info("Terapija je uspesno dodata u karton pacijenta");
						dispose();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonPanel.add(btnNewButton);
		
		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel upperPanel = new JPanel();
		mainPanel.add(upperPanel);
		GridBagLayout gbl_upperPanel = new GridBagLayout();
		gbl_upperPanel.columnWidths = new int[] {30, 30, 0, 30, 0, 0, 3};
		gbl_upperPanel.rowHeights = new int[] {50, 50};
		gbl_upperPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_upperPanel.rowWeights = new double[]{0.0, 0.0};
		upperPanel.setLayout(gbl_upperPanel);
		
		JLabel diagnoseLabel = new JLabel("Dijagnoza:");
		diagnoseLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_diagnoseLabel = new GridBagConstraints();
		gbc_diagnoseLabel.insets = new Insets(0, 0, 5, 5);
		gbc_diagnoseLabel.gridx = 2;
		gbc_diagnoseLabel.gridy = 0;
		upperPanel.add(diagnoseLabel, gbc_diagnoseLabel);
		
		diagnoseTextField = new JTextField();
		GridBagConstraints gbc_diagnoseTextField = new GridBagConstraints();
		gbc_diagnoseTextField.insets = new Insets(0, 0, 5, 0);
		gbc_diagnoseTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_diagnoseTextField.gridx = 4;
		gbc_diagnoseTextField.gridy = 0;
		upperPanel.add(diagnoseTextField, gbc_diagnoseTextField);
		
		List<String> allDiagnosis = getAllPossibleDiagnosis();
		
		AutoComplete autoComplete = new AutoComplete(diagnoseTextField, allDiagnosis);
		diagnoseTextField.setColumns(10);
		diagnoseTextField.setFocusTraversalKeysEnabled(false);
		diagnoseTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
		diagnoseTextField.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());
		diagnoseTextField.getDocument().addDocumentListener(autoComplete);
		
		// ako je selektovana neka dijagnoza upisi u text polje
		if(selectedDiagnosis != null) {
			diagnoseTextField.setText(selectedDiagnosis.getDiagnose());
		}
		
		JButton findMedicamentsButton = new JButton("Pogledaj rezultate");
		findMedicamentsButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		findMedicamentsButton.setIcon(new ImageIcon("images/zoom_icon&24.png"));
		GridBagConstraints gbc_findMedicamentsButton = new GridBagConstraints();
		gbc_findMedicamentsButton.gridx = 4;
		gbc_findMedicamentsButton.gridy = 1;
		findMedicamentsButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				diagnose = diagnoseTextField.getText().trim();
				boolean valid = validateDiagnoseInput(diagnose);
				if(valid) {
					
					List<ReasoningAnswerData> rbrMedicamentsResultList = new ArrayList<ReasoningAnswerData>();
					List<ReasoningAnswerData> cbrMedicamentsResultList = new ArrayList<ReasoningAnswerData>();
					
					PrologHandler prologHandler = MainFrame.getInstance().getPrologHandler();
					String queryText = String.format("diagnose_medicaments(%s, Medicament,	Percentage)", diagnose);
					try {
						rbrMedicamentsResultList =  prologHandler.findResultsEncapsulated("medicaments_facts.pl", queryText);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					RDFHandler rdfHandler = new RDFHandler("diagnosisMedicaments.ttl");
					cbrMedicamentsResultList = rdfHandler.findMedicamentsEncapsulated(diagnose);
					
						
					displayMedicaments(rbrMedicamentsList, rbrMedicamentsResultList);
					displayMedicaments(cbrMedicamentsList, cbrMedicamentsResultList);

				}else {
					Utils.error("Nepravilno uneta dijagnoza");
				}
			}
		});
		upperPanel.add(findMedicamentsButton, gbc_findMedicamentsButton);
		
		JPanel lowerPanel = new JPanel();
		mainPanel.add(lowerPanel);
		lowerPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel rbrPanel = new JPanel();
		lowerPanel.add(rbrPanel);
		rbrPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel rbrInfoPanel = new JPanel();
		rbrPanel.add(rbrInfoPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Terapija na osnovu RBR:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		rbrInfoPanel.add(lblNewLabel);
		
		JScrollPane rbrMedicamentsScrollPane = new JScrollPane(rbrMedicamentsList);
		rbrPanel.add(rbrMedicamentsScrollPane);
		
		JPanel cbrPanel = new JPanel();
		lowerPanel.add(cbrPanel);
		cbrPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel cbrInfoPanel = new JPanel();
		cbrPanel.add(cbrInfoPanel, BorderLayout.NORTH);
		
		JLabel resultInfoLabel = new JLabel("Terapija na osnovu CBR:");
		cbrInfoPanel.add(resultInfoLabel);
		resultInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JScrollPane cbrMedicamentsSrollPane = new JScrollPane(cbrMedicamentsList);
		cbrPanel.add(cbrMedicamentsSrollPane);
	}

	private void displayMedicaments(JList<ReasoningAnswerData> list, List<ReasoningAnswerData> medicaments) {
		DefaultListModel<ReasoningAnswerData> model = new DefaultListModel<ReasoningAnswerData>();
		for(ReasoningAnswerData s : medicaments) {
			model.addElement(s);
		}
		//cbrMedicamentsList = new JList<String>(model);
		list.setModel(model);
	}
	
	private List<String> getAllPossibleDiagnosis(){
		 allPossibleDiagnosis = new ArrayList<String>();
		
		DatabaseHandler databaseHandler = MainFrame.getInstance().getDatabaseHandler();
		List<Resources> resourceList = databaseHandler.selectAllParticularResource("diagnosis");
		for(Resources r : resourceList) {
			allPossibleDiagnosis.add(r.getResourceName());
		}
		
		return allPossibleDiagnosis;
	}
	
	private boolean validateDiagnoseInput(String input) {
		boolean valid = false;
		for(String v : allPossibleDiagnosis) {
			if(v.equals(input)) {
				return true;
			}
		}
		
		return valid;
	}
	
//	private void initList() {
//		cbrMedicamentsList.addListSelectionListener(new ListSelectionListener() {
//			
//			@Override
//			public void valueChanged(ListSelectionEvent e) {
//				// TODO Auto-generated method stub
//				System.out.println("promena selekcije");
//				System.out.println(cbrMedicamentsList.getSelectedValue());
//			}
//		});
//		cbrMedicamentsList.setForeground(Color.BLACK);
//		cbrMedicamentsList.setBackground(Color.WHITE);
//		
//		
//		
//		//cbrMedicamentsList.setCellRenderer(new ListRenderer());
//		//Dodavanje popUpMenija
//				/*PopUpMenus p = new PopUpMenus();
//				cbrMedicamentsList.add(p);
//
//				
//				cbrMedicamentsList.addMouseListener(new MouseAdapter() {
//					public void mousePressed(MouseEvent e)  {check(e);}
//					public void mouseReleased(MouseEvent e) {check(e);}
//
//					public void check(MouseEvent e) {
//					    if (e.isPopupTrigger()) { //if the event shows the menu
//					    	cbrMedicamentsList.setSelectedIndex(cbrMedicamentsList.locationToIndex(e.getPoint())); //select the item
//					        p.show(cbrMedicamentsList, e.getX(), e.getY()); //and show the menu
//					    }
//					}
//					
//					public void mouseClicked(MouseEvent e) {
//			               if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  
//
//			               }
//			           }
//				});*/
//	}
	
	private void initList(JList<ReasoningAnswerData> list) {
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(list.getSelectedValue());
				lastSelectedTherapyElement = list.getSelectedValue();
			}
		});
		list.setForeground(Color.BLACK);
		list.setBackground(Color.WHITE);
		
		
		
		//cbrMedicamentsList.setCellRenderer(new ListRenderer());
		//Dodavanje popUpMenija
				/*PopUpMenus p = new PopUpMenus();
				cbrMedicamentsList.add(p);

				
				cbrMedicamentsList.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e)  {check(e);}
					public void mouseReleased(MouseEvent e) {check(e);}

					public void check(MouseEvent e) {
					    if (e.isPopupTrigger()) { //if the event shows the menu
					    	cbrMedicamentsList.setSelectedIndex(cbrMedicamentsList.locationToIndex(e.getPoint())); //select the item
					        p.show(cbrMedicamentsList, e.getX(), e.getY()); //and show the menu
					    }
					}
					
					public void mouseClicked(MouseEvent e) {
			               if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  

			               }
			           }
				});*/
	}
	
	public String findTherapyString(ReasoningAnswerData data) {
		String ret = "";
		String cbrAnswer =  data.getAnswerVariables().get("imeLeka");
		String rbrAnswer = data.getAnswerVariables().get("Medicament");
		
		if(rbrAnswer != null) {
			return rbrAnswer;
		}
		
		if(cbrAnswer != null) {
			return cbrAnswer;
		}
		
		return ret;
	}
	
	public JTextField getDiagnoseTextField() {
		return diagnoseTextField;
	}
	
}
