package utils;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenus extends JPopupMenu {

	public PopUpMenus() {
		JMenuItem miAdd = new JMenuItem("f");
		
		JMenuItem miUpdate = new JMenuItem("f");
		
	
		
		add(miAdd);
		add(miUpdate);
	}
}
