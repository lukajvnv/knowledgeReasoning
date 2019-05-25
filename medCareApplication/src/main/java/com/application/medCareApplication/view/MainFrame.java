package com.application.medCareApplication.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.application.medCareApplication.controller.NewPatientDialogAction;
import com.application.medCareApplication.controller.ViewPatientDetailAction;
import com.application.medCareApplication.controller.ViewPatientsAction;


public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2118062538574262725L;


	private static MainFrame instance = null;

	
	private JPanel contentPane;
	private JLabel logoLabel;
	private JPanel mainPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		try {
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
			UIManager.put("Synthetica.tabbedPane.keepOpacity", true);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}
	
	public static MainFrame getInstance() {
		if(instance == null) {
			instance = new MainFrame();
		}
		return instance;
	}

	/**
	 * Create the frame.
	 */
	private MainFrame() {
//        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        //setSize(screenWidth/2 + 100, screenHeight/2 + 130);
        setSize(screenWidth/2 + 300, screenHeight/2 + 250);
        setLocationRelativeTo(null);
		
		setIconImage(new ImageIcon("images/medCareLogo.png").getImage());
		setTitle("Dobrodo\u0161li u medCare aplikaciju");
		//setBounds(100, 100, 550, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu patientsMenu = new JMenu("Pacijenti");
		menuBar.add(patientsMenu);
		
		JMenuItem viewPatientsMenuItem = new JMenuItem(new ViewPatientsAction("Svi pacijenti"));
		viewPatientsMenuItem.setIcon(new ImageIcon("images/db_icon&24.png"));
		patientsMenu.add(viewPatientsMenuItem);
		
		JMenuItem newPatientMenuItem = new JMenuItem(new NewPatientDialogAction("Dodavanje novog pacijenta"));
		newPatientMenuItem.setIcon(new ImageIcon("images/create_icon&24.png"));
		patientsMenu.add(newPatientMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("New menu");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("New menu item");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenu helpMenu = new JMenu("Pomo\u0107");
		menuBar.add(helpMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("O aplikaciji");
		aboutMenuItem.setIcon(new ImageIcon("images/info_icon&24.png"));
		helpMenu.add(aboutMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton newPatientButton = new JButton(new NewPatientDialogAction());
		newPatientButton.setText("Novi pacijent");
		newPatientButton.setIcon(new ImageIcon("images/create_icon&24.png"));
		newPatientButton.setToolTipText("Dodaj pacijenta");
		toolBar.add(newPatientButton);
		
		JButton viewPatientsButton = new JButton(new ViewPatientsAction());
		viewPatientsButton.setText("Svi pacijenti");
		viewPatientsButton.setIcon(new ImageIcon("images/db_icon&24.png"));
		viewPatientsButton.setToolTipText("Svi pacijenti");
		toolBar.add(viewPatientsButton);
		
		JButton btnNewButton = new JButton(new ViewPatientDetailAction());
		btnNewButton.setText("O pacijentu");
		btnNewButton.setIcon(new ImageIcon("images/info_icon&24.png"));
		toolBar.add(btnNewButton);
		
		
		
		mainPanel = new JPanel();
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		mainPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		ImageIcon logoIcon = new ImageIcon ("images/medCareLogo.png");
		Image image = logoIcon.getImage();
		Image newImg = image.getScaledInstance(screenWidth/6, screenWidth/6, Image.SCALE_SMOOTH);
		logoIcon = new ImageIcon(newImg);
		   
		logoLabel = new JLabel("");
		//logoLabel.setIcon(new ImageIcon("images/medCareLogo.png"));
		logoLabel.setIcon(logoIcon);
		logoLabel.setHorizontalAlignment(JLabel.CENTER);
		logoLabel.setVerticalAlignment(JLabel.CENTER);
				
		mainPanel.add(logoLabel, BorderLayout.CENTER);
		
		//status bar
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.DARK_GRAY);
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		contentPane.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(240, 248, 255));
		statusPanel.add(infoPanel, BorderLayout.CENTER);
		
		JLabel infoLabel = new JLabel("Dobrodo\u0161li u medCare aplikaciju");
		infoLabel.setForeground(new Color(0, 128, 0));
		infoPanel.add(infoLabel);
		
		JPanel datePanel = new JPanel();
		statusPanel.add(datePanel, BorderLayout.EAST);
		
		// Formatiranje i preuzimanje trenutnog datuma
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		LocalDate localDate = LocalDate.now();
		
		JLabel dateLabel = new JLabel(dtf.format(localDate));
		datePanel.add(dateLabel);
		
		/*addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		super.windowClosing(e);
        		
        		if (Utils.confirm(MainFrame.getInstance(),"Da li ste sigurni?") == JOptionPane.YES_OPTION) {
//        			MainFrame.getInstance().dispose();
					MainFrame.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        		}
        	}
		});*/
		
		/*EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
				UIManager.put("Synthetica.tabbedPane.keepOpacity", true);
					
				MainFrame.getInstance().setVisible(true);
			}catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			} catch (Exception eo) {
				eo.printStackTrace();
			}
		}
		});*/
		
	}
	public JLabel getLogoLabel() {
		return logoLabel;
	}
	public JPanel getMainPanel() {
		return mainPanel;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
}
