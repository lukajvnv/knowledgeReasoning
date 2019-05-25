package utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;

import application.model.Patient;

public class ListRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		
		Patient p = (Patient)value;
		
		setIcon(new ImageIcon("images/home_icon&16.png"));
		setText(p.toString());
		//setBackground(Color.MAGENTA);
		return this;
	}

}
