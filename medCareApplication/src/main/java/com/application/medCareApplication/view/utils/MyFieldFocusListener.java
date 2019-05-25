package com.application.medCareApplication.view.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MyFieldFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent arg0) {
		Component c = arg0.getComponent();
		c.setBackground(Color.WHITE);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		Component c = arg0.getComponent();
		c.setBackground(Color.LIGHT_GRAY);
	}

}
