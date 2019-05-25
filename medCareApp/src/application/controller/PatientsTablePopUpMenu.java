package application.controller;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PatientsTablePopUpMenu extends JPopupMenu {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PatientsTablePopUpMenu() {
		JMenuItem addNewPatientMenuItem = new JMenuItem(new NewPatientDialogAction("Novi pacijent"));
		addNewPatientMenuItem.setIcon(new ImageIcon("images/create_icon&16.png"));
		
		JMenuItem viewPatientMenuItem = new JMenuItem(new ViewPatientDetailAction("O pacijentu"));
		viewPatientMenuItem.setIcon(new ImageIcon("images/db_icon&16.png"));
		
		add(addNewPatientMenuItem);
		add(viewPatientMenuItem);
		
		addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				//System.out.println("popup become visible");	
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				
			}
	
		});
		
	}
}
