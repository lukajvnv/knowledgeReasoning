package com.application.medCareApplication.view.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.utils.ListRenderer;

public class SelectAnamnesisDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1173485553968123148L;
	private final JPanel contentPanel = new JPanel();
	private JList<Patient>  possibleValuesList;
	private JScrollPane possibleValuesScrollPane;
	private JList<Patient>  selectedValuesList;
	private JScrollPane selectedValuesScrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectAnamnesisDialog dialog = new SelectAnamnesisDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectAnamnesisDialog() {
		possibleValuesList = initList(possibleValuesList, true);
		selectedValuesList = initList(selectedValuesList, false);
		
		setTitle("Dodavanje anamneze za pacijenta: Luka Jovanovic");
		setModal(true);
		setFocusable(true);		//focus da bi se mogao trigerovati keyListener
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(600,550);
		
		//setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel infoPanel = new JPanel();
			contentPanel.add(infoPanel, BorderLayout.NORTH);
			{
				JLabel infoLabel = new JLabel("Odaberite anamneze iz spiska dostupnih anamneza:");
				infoPanel.add(infoLabel);
			}
		}
		{
			JPanel mainPanel = new JPanel();
			contentPanel.add(mainPanel, BorderLayout.CENTER);
			mainPanel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JPanel leftPanel = new JPanel();
				mainPanel.add(leftPanel);
				leftPanel.setLayout(new BorderLayout(0, 0));
				{
					JPanel infoLeftPanel = new JPanel();
					leftPanel.add(infoLeftPanel, BorderLayout.NORTH);
					{
						JLabel infoLeftLabel = new JLabel("Odaberite neki od mogu\u0107ih simptoma(bolesti):");
						infoLeftPanel.add(infoLeftLabel);
					}
				}
				{
					JPanel buttonPanel = new JPanel();
					leftPanel.add(buttonPanel, BorderLayout.EAST);
					GridBagLayout gbl_buttonPanel = new GridBagLayout();
					gbl_buttonPanel.columnWidths = new int[]{89, 0};
					gbl_buttonPanel.rowHeights = new int[] {42, 42, 30, 30, 30, 30, 30, 0, 30, 0, 30, 42, 42, 42, 42, 42, 42, 42};
					gbl_buttonPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
					gbl_buttonPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
					buttonPanel.setLayout(gbl_buttonPanel);
					{
						JButton leftToRightButton = new JButton("");
						leftToRightButton.setIcon(new ImageIcon("images/arrow_right_icon&48.png"));
						leftToRightButton.addActionListener(new AbstractAction() {
							
							/**
							 * 
							 */
							private static final long serialVersionUID = -3615722752100902806L;

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								List<Patient> selectedPatients = possibleValuesList.getSelectedValuesList();
				            	DefaultListModel<Patient> possibleValuesListModel = (DefaultListModel<Patient>) possibleValuesList.getModel();
				            	DefaultListModel<Patient> selectedValuesListModel = (DefaultListModel<Patient>) selectedValuesList.getModel();
				            	for(Patient p : selectedPatients) {
									selectedValuesListModel.addElement(p);
									possibleValuesListModel.removeElement(p);
								}
				            	possibleValuesList.clearSelection();
							}
						});
						GridBagConstraints gbc_leftToRightButton = new GridBagConstraints();
						gbc_leftToRightButton.insets = new Insets(0, 0, 5, 0);
						gbc_leftToRightButton.fill = GridBagConstraints.BOTH;
						gbc_leftToRightButton.gridx = 0;
						gbc_leftToRightButton.gridy = 7;
						buttonPanel.add(leftToRightButton, gbc_leftToRightButton);
					}
					{
						JButton rightToLeftButton = new JButton("");
						rightToLeftButton.setIcon(new ImageIcon("images/arrow_left_icon&48.png"));
						rightToLeftButton.addActionListener(new AbstractAction() {
							
							/**
							 * 
							 */
							private static final long serialVersionUID = -3776850270545347699L;

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								List<Patient> selectedPatients = selectedValuesList.getSelectedValuesList();
				            	DefaultListModel<Patient> possibleValuesListModel = (DefaultListModel<Patient>) possibleValuesList.getModel();
				            	DefaultListModel<Patient> selectedValuesListModel = (DefaultListModel<Patient>) selectedValuesList.getModel();
				            	for(Patient p : selectedPatients) {
									possibleValuesListModel.addElement(p);
									selectedValuesListModel.removeElement(p);
								}
				            	selectedValuesList.clearSelection();
							}
						});
						GridBagConstraints gbc_rightToLeftButton = new GridBagConstraints();
						gbc_rightToLeftButton.fill = GridBagConstraints.BOTH;
						gbc_rightToLeftButton.gridx = 0;
						gbc_rightToLeftButton.gridy = 9;
						buttonPanel.add(rightToLeftButton, gbc_rightToLeftButton);
					}
				}
				{
					possibleValuesScrollPane = new JScrollPane(possibleValuesList);
					leftPanel.add(possibleValuesScrollPane, BorderLayout.CENTER);
				}
			}
			{
				JPanel rightPanel = new JPanel();
				mainPanel.add(rightPanel);
				rightPanel.setLayout(new BorderLayout(0, 0));
				{
					JPanel infoRightpanel = new JPanel();
					rightPanel.add(infoRightpanel, BorderLayout.NORTH);
					{
						JLabel infoRightLabel = new JLabel("Trenutno odabrani simptomi(bolesti):");
						infoRightpanel.add(infoRightLabel);
					}
				}
				{
					selectedValuesScrollPane = new JScrollPane(selectedValuesList);
					rightPanel.add(selectedValuesScrollPane, BorderLayout.CENTER);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			fl_buttonPane.setHgap(25);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Dodaj");
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						System.out.println("add action button");
						DefaultListModel<Patient> s = (DefaultListModel<Patient>) possibleValuesList.getModel();
		            	s.addElement(new Patient(1, "ime", "prezime", "jmbg", "15.5.2018", "adresa", "telefon"));
		          
		            	possibleValuesList.clearSelection();
					}
				});
				buttonPane.add(okButton);
				//getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Prekini");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		//initList();
		
		
	}

	public JScrollPane getPossibleValuesScrollPane() {
		return possibleValuesScrollPane;
	}
	
	/*private void initList() {
		DefaultListModel<Patient> possibleValuesListModel = new DefaultListModel<Patient>();
		
		for(int i = 0; i < 15; i++) {
			Patient p = new Patient(i, "ime" + i, "prezime" + i, "jmbg" + i, "15.5.2018", "adresa" + i, "telefon" + i);
			
			possibleValuesListModel.addElement(p);
		}
		
		possibleValuesList = new JList<Patient>(possibleValuesListModel);
		possibleValuesList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(possibleValuesList.getSelectedValue());
			}
		});
		possibleValuesList.setForeground(Color.BLACK);
		possibleValuesList.setBackground(Color.WHITE);
		
		
		
		possibleValuesList.setCellRenderer(new ListRenderer());
		
		//2-klik pokrece mehanizam deljenja
		possibleValuesList.addMouseListener(new MouseAdapter() {
	           public void mouseClicked(MouseEvent e) {
	               if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  

	               }
	           }
	       });	   
	       
	       //enter pokrece mehanizam deljenja
		possibleValuesList.addKeyListener(new KeyAdapter() {
	    	   public void keyPressed(KeyEvent evt)
	            {
	                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
	                {              
	                }
	            }
		    });
	       

		//Dodavanje popUpMenija
		PopUpMenus p = new PopUpMenus();
		possibleValuesList.add(p);

		
		possibleValuesList.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			    	possibleValuesList.setSelectedIndex(possibleValuesList.locationToIndex(e.getPoint())); //select the item
			        p.show(possibleValuesList, e.getX(), e.getY()); //and show the menu
			    }
			}
		});
		
	}*/
	
	private JList<Patient> initList(JList<Patient> list, boolean shouldRetrieveData) {
		DefaultListModel<Patient> possibleValuesListModel = new DefaultListModel<Patient>();
		
		
		if (shouldRetrieveData) {
			for (int i = 0; i < 15; i++) {
				Patient p = new Patient(i, "ime" + i, "prezime" + i, "jmbg" + i, "15.5.2018", "adresa" + i,
						"telefon" + i);
				possibleValuesListModel.addElement(p);
			} 
		}
		list = new JList<Patient>(possibleValuesListModel);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("promena selekcije");
				System.out.println(possibleValuesList.getSelectedValue());
			}
		});
		list.setForeground(Color.BLACK);
		list.setBackground(Color.WHITE);
		
		
		
		list.setCellRenderer(new ListRenderer());
		       
	    //enter pokrece mehanizam deljenja
		/*possibleValuesList.addKeyListener(new KeyAdapter() {
	    	   public void keyPressed(KeyEvent evt)
	            {
	                if(evt.getKeyCode() == KeyEvent.VK_ENTER)
	                {              
	                }
	            }
		    });*/
	       

		//Dodavanje popUpMenija
		/*PopUpMenus p = new PopUpMenus();
		list.add(p);

		
		list.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)  {check(e);}
			public void mouseReleased(MouseEvent e) {check(e);}

			public void check(MouseEvent e) {
			    if (e.isPopupTrigger()) { //if the event shows the menu
			    	list.setSelectedIndex(list.locationToIndex(e.getPoint())); //select the item
			        p.show(list, e.getX(), e.getY()); //and show the menu
			    }
			}
			
			public void mouseClicked(MouseEvent e) {
	               if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {    	  

	               }
	           }
		});*/
		return list;
		
	}
	public JScrollPane getSelectedValuesScrollPane() {
		return selectedValuesScrollPane;
	}
}