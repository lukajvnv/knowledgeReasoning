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
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.application.medCareApplication.model.Resources;
import com.application.medCareApplication.utils.ListRenderer;
import com.application.medCareApplication.utils.PrologHandler;
import com.application.medCareApplication.utils.RDFHandler;
import com.application.medCareApplication.utils.Utils;
import com.application.medCareApplication.utils.handler.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;
import com.application.medCareApplication.view.utils.AutoComplete;

public class MedicamentsRecommendationFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6142157244487361175L;
	private JPanel contentPane;
	private JTextField diagnoseTextField;
	private JComboBox<String> reasoningTypeComboBox;
	
	private JList<String> medicamentsList = new JList<String>();
	private List<String> allPossibleDiagnosis;
	private static final String COMMIT_ACTION = "commit";

	//private String[] defaultValues =  {};
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MedicamentsRecommendationFrame frame = new MedicamentsRecommendationFrame();
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
	@SuppressWarnings("serial")
	public MedicamentsRecommendationFrame() {
		initList();
		
		setTitle("Preporucivanje terapije za pacijenta");
		/*setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);*/
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(500,500);
		setResizable(false);
		
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
		
		JButton btnNewButton = new JButton("New button");
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
		
		JLabel reasoningTypeLabel = new JLabel("Zakljucivanje:");
		reasoningTypeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_reasoningTypeLabel = new GridBagConstraints();
		gbc_reasoningTypeLabel.insets = new Insets(0, 0, 0, 5);
		gbc_reasoningTypeLabel.gridx = 2;
		gbc_reasoningTypeLabel.gridy = 1;
		upperPanel.add(reasoningTypeLabel, gbc_reasoningTypeLabel);
		
		reasoningTypeComboBox = new JComboBox<String>();
		reasoningTypeComboBox.addItem("Rule based");
		reasoningTypeComboBox.addItem("Case based");
		GridBagConstraints gbc_reasoningTypeComboBox = new GridBagConstraints();
		gbc_reasoningTypeComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_reasoningTypeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_reasoningTypeComboBox.gridx = 4;
		gbc_reasoningTypeComboBox.gridy = 1;
		upperPanel.add(reasoningTypeComboBox, gbc_reasoningTypeComboBox);
		
		JButton findMedicamentsButton = new JButton("Pogledaj");
		GridBagConstraints gbc_findMedicamentsButton = new GridBagConstraints();
		gbc_findMedicamentsButton.gridx = 5;
		gbc_findMedicamentsButton.gridy = 1;
		findMedicamentsButton.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String diagnose = diagnoseTextField.getText().trim();
				boolean valid = validateDiagnoseInput(diagnose);
				if(valid) {
					String reasoning = (String) reasoningTypeComboBox.getSelectedItem();
					
					List<String> medicamentsList = new ArrayList<String>();
					if(reasoning.equals("Rule based")) {
						PrologHandler prologHandler = MainFrame.getInstance().getPrologHandler();
						String queryText = String.format("diagnose_medicaments(%s, M,	P)", diagnose);
						try {
							medicamentsList =  prologHandler.findResults("medicaments_facts.pl", queryText);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {
						RDFHandler rdfHandler = new RDFHandler("diagnosisMedicaments.ttl");
						medicamentsList = rdfHandler.findMedicaments(diagnose);
					}
						
					displayMedicaments(medicamentsList);
				}else {
					Utils.error("Nepravilno uneta dijagnoza");
				}
			}
		});
		upperPanel.add(findMedicamentsButton, gbc_findMedicamentsButton);
		
		JPanel lowerPanel = new JPanel();
		mainPanel.add(lowerPanel);
		lowerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel resultInfoPanel = new JPanel();
		lowerPanel.add(resultInfoPanel, BorderLayout.NORTH);
		
		JLabel resultInfoLabel = new JLabel("Preporuceni lekovi za preskripciju su:");
		resultInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		resultInfoPanel.add(resultInfoLabel);
		
		JScrollPane medicamentsSrollPane = new JScrollPane(medicamentsList);
		lowerPanel.add(medicamentsSrollPane, BorderLayout.CENTER);
	}

	private void displayMedicaments(List<String> medicaments) {
		DefaultListModel<String> model = new DefaultListModel<String>();
		for(String s : medicaments) {
			model.addElement(s);
		}
		//medicamentsList = new JList<String>(model);
		medicamentsList.setModel(model);
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
	
	private void initList() {
		medicamentsList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(medicamentsList.getSelectedValue());
			}
		});
		medicamentsList.setForeground(Color.BLACK);
		medicamentsList.setBackground(Color.WHITE);
		
		
		
		//medicamentsList.setCellRenderer(new ListRenderer());
		//Dodavanje popUpMenija
				/*PopUpMenus p = new PopUpMenus();
				medicamentsList.add(p);

				
				medicamentsList.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e)  {check(e);}
					public void mouseReleased(MouseEvent e) {check(e);}

					public void check(MouseEvent e) {
					    if (e.isPopupTrigger()) { //if the event shows the menu
					    	medicamentsList.setSelectedIndex(medicamentsList.locationToIndex(e.getPoint())); //select the item
					        p.show(medicamentsList, e.getX(), e.getY()); //and show the menu
					    }
					}
					
					public void mouseClicked(MouseEvent e) {
			               if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  

			               }
			           }
				});*/
	}
	
	public JTextField getDiagnoseTextField() {
		return diagnoseTextField;
	}
	public JComboBox<String> getResoningTypeComboBox() {
		return reasoningTypeComboBox;
	}
}
