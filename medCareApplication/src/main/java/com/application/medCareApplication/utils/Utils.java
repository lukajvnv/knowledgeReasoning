package com.application.medCareApplication.utils;

import java.awt.Component;
import java.util.Enumeration;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

public class Utils {

	/**
	 * Metoda koja parsira datum da bi bio prikazan u skladu sa trenutnim lokalom.
	 * @param dbValue - vrednost pristigla iz baze podataka
	 * @return - prikaz vrednosti koji je prilago�en lokalizaciji
	 */
	public static String parseDate(String dbValue) {
		String retVal = dbValue;
		
		String lan = Locale.getDefault().getLanguage();
		
		int i = dbValue.indexOf('-');
		int j = dbValue.indexOf('-', i+1);
		
		String year = dbValue.substring(0, i);
		String month = dbValue.substring(i+1, j);
		String day = dbValue.substring(j+1, j+3);
		
		if (lan.equals("sr")) {
			retVal = day + "." + month + "." + year + ".";
		} else if (lan.equals("en")) {
			retVal = month + "/" + day + "/" + year;
		}
		
		return retVal;
	}

	/**
	 * Metoda koja ekstrahuje delove datuma
	 * @param date - datum iz koga se ekstrahuju delovi.
	 * @return - niz celih brojeva od tri elementa:
	 * <b>[0]</b> - broj�ani prikaz dana 
	 * <b>[1]</b> - broj�ani prikaz meseca
	 * <b>[2]</b> - broj�ani prikaz godine
	 */
	public static int[] extractDate(String date) {
		int[] ret = new int[3];
		
		/*String lan = Locale.getDefault().getLanguage();
		
		if (lan.equals("sr")) {
			int i = date.indexOf('.');
			ret[2] = Integer.parseInt(date.substring(0, i));
		    int j = date.indexOf('.', i+1);
		    ret[1] = Integer.parseInt(date.substring(i+1, j)) - 1;
		    ret[0] = Integer.parseInt(date.substring(j+1, date.length() - 1));
		    
		} else if (lan.equals("en")) {
			int i = date.indexOf('/');
		    ret[1] = Integer.parseInt(date.substring(0, i)) - 1;
		    int j = date.indexOf('/', i+1);
		    ret[2] = Integer.parseInt(date.substring(i+1, j));
		    ret[0] = Integer.parseInt(date.substring(j+1, date.length()));
		}*/
		
			int i = date.indexOf('.');
			ret[2] = Integer.parseInt(date.substring(0, i));
		    int j = date.indexOf('.', i+1);
		    ret[1] = Integer.parseInt(date.substring(i+1, j)) - 1;
		    ret[0] = Integer.parseInt(date.substring(j+1, date.length() - 1));
		    
		
		return ret;
	}
	
	/**
	 * Prikazuje dijalog sa porukom o gre�ci.
	 * @param msg - poruka koja opisuje gre�ku
	 */
	public static void error(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Gre�ka", JOptionPane.ERROR_MESSAGE);
	}
	
	
		
	/**
	 * Prikazuje dijalog sa upozorenjem.
	 * @param msg - poruka koja opisuje upozorenje
	 */
	public static void warning(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Upozorenje", JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Prikazuje dijalog sa odgovaraju�om informacijom.
	 * @param msg - poruka koja predstavlja informaciju
	 */
	public static void info(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Prikazuje dijalog sa pitanjem.
	 * @param msg - poruka koja predstavlja pitanje 
	 */
	public static void question(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Pitanje", JOptionPane.QUESTION_MESSAGE);
	}
	
	/**
	 * Prikazuje dijalog sa pitanjem za potvrdu
	 * @param msg - pitanje na koje treba dati potvrdu ili odustati
	 * @return - odabir koji je klijent napravio u prikazanom dijalogu
	 */
	public static int confirm(String msg) {
		return JOptionPane.showConfirmDialog(null, msg, "Potvrda", JOptionPane.YES_NO_OPTION);
	}
	
	public static int confirm(Component owner, String msg) {
		return JOptionPane.showConfirmDialog(owner, msg, "Potvrda", JOptionPane.YES_NO_OPTION);
	}
	
	public static String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }
}
