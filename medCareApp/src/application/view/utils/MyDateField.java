package application.view.utils;

import java.util.Properties;

import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import utils.Utils;


public class MyDateField extends JDatePickerImpl {

	/**
	 * Kreiranje komponente.
	 */
	public MyDateField() {
		super(createPanel(), new DateLabelFormatter());
		setValue("01.01.1950.");
		getJDateInstantPanel().setShowYearButtons(true);
	}

	private static JDatePanelImpl createPanel() {
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		Properties p = new Properties();
		return new JDatePanelImpl(model, p);
	}
	
	private static UtilDateModel createModel() {
		UtilDateModel model = new UtilDateModel();
		model.setSelected(true);
		return model;
	}
	
	private static Properties setProperties() {
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "month");
		p.put("text.year", "year");
		return p;
	}
	
	public String getValue() {
		this.getJDateInstantPanel();
		DateModel<?> model = this.getModel();
		return model.isSelected() ? formatDateOutput(model) : "";
	}
	
	public void setValue(String s) {
		DateModel<?> model = this.getModel();
		int[] date = Utils.extractDate(s);
		/*model.setDay(date[0]);
		model.setMonth(date[1]);
		model.setYear(date[2]);*/
		model.setDay(date[2]);
		model.setMonth(date[1]);
		model.setYear(date[0]);
	}
	
	/**
	 * Funkcija koja formatira datum koji je selektovan
	 * @param model - model JDatePicker klase
	 * @return - string koji predstavlja datum u formatu yyyy.MM.dd
	 */
	public String formatDateOutput(DateModel<?> model){
		return String.format("%s.%s.%s", model.getYear(), model.getMonth()+1, model.getDay());
	}
}
