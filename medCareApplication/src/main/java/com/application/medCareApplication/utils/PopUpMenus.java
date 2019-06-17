package com.application.medCareApplication.utils;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopUpMenus extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -537122247849394924L;

	public PopUpMenus() {
		JMenuItem miAdd = new JMenuItem("f");
		
		JMenuItem miUpdate = new JMenuItem("f");
		
	
		
		add(miAdd);
		add(miUpdate);
	}
}
