package com.application.medCareApplication.utils;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import com.application.medCareApplication.model.Resources;

public class ResourcesListRenderer extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		Resources p = (Resources)value;
		
		setIcon(new ImageIcon("images/home_icon&16.png"));
		setText(p.toString());
		//setBackground(Color.MAGENTA);
		return this;
	}

}
