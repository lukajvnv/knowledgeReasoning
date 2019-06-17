package com.application.medCareApplication.view.displayExaminations;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.model.Therapy;
import com.application.medCareApplication.utils.PatientsColumn;

public class DisplayInfo extends JPanel {



	
	/**
	 * 
	 */
	private static final long serialVersionUID = 851479440450652368L;
	private Color backGroundColor;
	private Color foreGroundColor;
	private Font textFont;
	private LineBorder lineBorder;
	
	private String infoText;
	private String infoTemplate   = "Detalji %s :";
;
	
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public DisplayInfo(Object objectToDisplay) {
		setBounds(100, 100, 450, 300);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setBackground(Color.WHITE);

		backGroundColor = Color.WHITE;
		foreGroundColor = Color.BLUE;
		textFont = new Font("Tahoma", Font.PLAIN, 14);
		lineBorder = new LineBorder(Color.MAGENTA, 2, true);
		
		
		// Panel u kom se prikazuje uvodni tekst
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel);
		
	    lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setForeground(foreGroundColor);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel);
		
//		//Jedan red
//		JPanel panel_1 = new JPanel();
//		panel_1.setBackground(backGroundColor);
//		panel_1.setBorder(lineBorder);
//		add(panel_1);
//		panel_1.setLayout(new GridLayout(1, 2, 10, 0));
//		
//		//levi panel naziv atributa
//		JPanel panel_3 = new JPanel();
//		panel_3.setBackground(backGroundColor);
//		panel_1.add(panel_3);
//		
//		JLabel lblNewLabel_1 = new JLabel("Dijagnoza");
//		lblNewLabel_1.setForeground(foreGroundColor);
//		lblNewLabel_1.setFont(textFont);
//		panel_3.add(lblNewLabel_1);
//		
//		//desni panel vrednost atributa
//		JPanel panel_4 = new JPanel();
//		panel_4.setBackground(backGroundColor);
//		panel_1.add(panel_4);
//		
//		JLabel lblNewLabel_2 = new JLabel("Asthma");
//		lblNewLabel_2.setForeground(foreGroundColor);
//		lblNewLabel_2.setFont(textFont);
//		panel_4.add(lblNewLabel_2);
//		
//		JPanel panel_2 = new JPanel();
//		add(panel_2);
		
		
		if(objectToDisplay instanceof Anamnesis) {
			Anamnesis a = (Anamnesis) objectToDisplay;
			displayAnamnesis(a);
		} else if(objectToDisplay instanceof PhysicalExamination) {
			PhysicalExamination p = (PhysicalExamination) objectToDisplay;
			displayPhysicalExamination(p);
		} else if(objectToDisplay instanceof Diagnosis) {
			Diagnosis d = (Diagnosis) objectToDisplay;
			displayDiagnosis(d);
		} else if(objectToDisplay instanceof Therapy) {
			Therapy t = (Therapy) objectToDisplay;
			displayTherapy(t);
		} /*else if(ObjectToDisplay instanceof Anamnesis) {
			
		}*/
	
	}
	
	public void displayAnamnesis(Anamnesis anamnesis) {
		
	}
	
	public void displayPhysicalExamination(PhysicalExamination physicalExamination) {
		
	}

	public void displayDiagnosis(Diagnosis diagnosis) {
		infoText = String.format(infoTemplate, "dijagnoza");
		lblNewLabel.setText(infoText);
		
		//Jedan red
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(backGroundColor);
		panel_1.setBorder(lineBorder);
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(backGroundColor);
		panel_1.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.diagnosis_Id);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(diagnosis.getDiagnosisId()));
		lblNewLabel_2.setForeground(foreGroundColor);
		lblNewLabel_2.setFont(textFont);
		panel_4.add(lblNewLabel_2);
		
		
		
		//Jedan red
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(backGroundColor);
		panel_11.setBorder(lineBorder);
		add(panel_11);
		panel_11.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_31 = new JPanel();
		panel_31.setBackground(backGroundColor);
		panel_11.add(panel_31);
		
		JLabel lblNewLabel_11 = new JLabel(PatientsColumn.patientId);
		lblNewLabel_11.setForeground(foreGroundColor);
		lblNewLabel_11.setFont(textFont);
		panel_31.add(lblNewLabel_11);
		
		//desni panel vrednost atributa
		JPanel panel_41 = new JPanel();
		panel_41.setBackground(backGroundColor);
		panel_11.add(panel_41);
		
		JLabel lblNewLabel_21 = new JLabel(Integer.toString(diagnosis.getPatientId()));
		lblNewLabel_21.setForeground(foreGroundColor);
		lblNewLabel_21.setFont(textFont);
		panel_41.add(lblNewLabel_21);
		
		

		//Jedan red
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(backGroundColor);
		panel_12.setBorder(lineBorder);
		add(panel_12);
		panel_12.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_32 = new JPanel();
		panel_32.setBackground(backGroundColor);
		panel_12.add(panel_32);
		
		JLabel lblNewLabel_12 = new JLabel(PatientsColumn.diagnose);
		lblNewLabel_12.setForeground(foreGroundColor);
		lblNewLabel_12.setFont(textFont);
		panel_32.add(lblNewLabel_12);
		
		//desni panel vrednost atributa
		JPanel panel_42 = new JPanel();
		panel_42.setBackground(backGroundColor);
		panel_12.add(panel_42);
		
		JLabel lblNewLabel_22 = new JLabel(diagnosis.getDiagnose());
		lblNewLabel_22.setForeground(foreGroundColor);
		lblNewLabel_22.setFont(textFont);
		panel_42.add(lblNewLabel_22);
		
	

		//Jedan red
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(backGroundColor);
		panel_13.setBorder(lineBorder);
		add(panel_13);
		panel_13.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_33 = new JPanel();
		panel_33.setBackground(backGroundColor);
		panel_13.add(panel_33);
		
		JLabel lblNewLabel_13 = new JLabel(PatientsColumn.date);
		lblNewLabel_13.setForeground(foreGroundColor);
		lblNewLabel_13.setFont(textFont);
		panel_33.add(lblNewLabel_13);
		
		//desni panel vrednost atributa
		JPanel panel_43 = new JPanel();
		panel_43.setBackground(backGroundColor);
		panel_13.add(panel_43);
		
		JLabel lblNewLabel_23 = new JLabel(diagnosis.getDate());
		lblNewLabel_23.setForeground(foreGroundColor);
		lblNewLabel_23.setFont(textFont);
		panel_43.add(lblNewLabel_23);
		
		
	}

	public void displayTherapy(Therapy therapy) {
		infoText = String.format(infoTemplate, "terapije");
		lblNewLabel.setText(infoText);
		
		//Jedan red
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(backGroundColor);
		panel_1.setBorder(lineBorder);
		add(panel_1);
		panel_1.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(backGroundColor);
		panel_1.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.therapyId);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(therapy.getTherapyId()));
		lblNewLabel_2.setForeground(foreGroundColor);
		lblNewLabel_2.setFont(textFont);
		panel_4.add(lblNewLabel_2);
		
		
		
		//Jedan red
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(backGroundColor);
		panel_11.setBorder(lineBorder);
		add(panel_11);
		panel_11.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_31 = new JPanel();
		panel_31.setBackground(backGroundColor);
		panel_11.add(panel_31);
		
		JLabel lblNewLabel_11 = new JLabel(PatientsColumn.patientId);
		lblNewLabel_11.setForeground(foreGroundColor);
		lblNewLabel_11.setFont(textFont);
		panel_31.add(lblNewLabel_11);
		
		//desni panel vrednost atributa
		JPanel panel_41 = new JPanel();
		panel_41.setBackground(backGroundColor);
		panel_11.add(panel_41);
		
		JLabel lblNewLabel_21 = new JLabel(Integer.toString(therapy.getPatientId()));
		lblNewLabel_21.setForeground(foreGroundColor);
		lblNewLabel_21.setFont(textFont);
		panel_41.add(lblNewLabel_21);
		
		

		//Jedan red
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(backGroundColor);
		panel_12.setBorder(lineBorder);
		add(panel_12);
		panel_12.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_32 = new JPanel();
		panel_32.setBackground(backGroundColor);
		panel_12.add(panel_32);
		
		JLabel lblNewLabel_12 = new JLabel(PatientsColumn.diagnose);
		lblNewLabel_12.setForeground(foreGroundColor);
		lblNewLabel_12.setFont(textFont);
		panel_32.add(lblNewLabel_12);
		
		//desni panel vrednost atributa
		JPanel panel_42 = new JPanel();
		panel_42.setBackground(backGroundColor);
		panel_12.add(panel_42);
		
		JLabel lblNewLabel_22 = new JLabel(therapy.getDiagnose());
		lblNewLabel_22.setForeground(foreGroundColor);
		lblNewLabel_22.setFont(textFont);
		panel_42.add(lblNewLabel_22);
		
	

		//Jedan red
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(backGroundColor);
		panel_13.setBorder(lineBorder);
		add(panel_13);
		panel_13.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_33 = new JPanel();
		panel_33.setBackground(backGroundColor);
		panel_13.add(panel_33);
		
		JLabel lblNewLabel_13 = new JLabel(PatientsColumn.therapy);
		lblNewLabel_13.setForeground(foreGroundColor);
		lblNewLabel_13.setFont(textFont);
		panel_33.add(lblNewLabel_13);
		
		//desni panel vrednost atributa
		JPanel panel_43 = new JPanel();
		panel_43.setBackground(backGroundColor);
		panel_13.add(panel_43);
		
		JLabel lblNewLabel_23 = new JLabel(therapy.getTherapy());
		lblNewLabel_23.setForeground(foreGroundColor);
		lblNewLabel_23.setFont(textFont);
		panel_43.add(lblNewLabel_23);
		
		

		//Jedan red
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(backGroundColor);
		panel_14.setBorder(lineBorder);
		add(panel_14);
		panel_14.setLayout(new GridLayout(1, 2, 10, 0));
		
		//levi panel naziv atributa
		JPanel panel_34 = new JPanel();
		panel_34.setBackground(backGroundColor);
		panel_14.add(panel_34);
		
		JLabel lblNewLabel_14 = new JLabel(PatientsColumn.date);
		lblNewLabel_14.setForeground(foreGroundColor);
		lblNewLabel_14.setFont(textFont);
		panel_34.add(lblNewLabel_14);
		
		//desni panel vrednost atributa
		JPanel panel_44 = new JPanel();
		panel_44.setBackground(backGroundColor);
		panel_14.add(panel_44);
		
		JLabel lblNewLabel_24 = new JLabel(therapy.getDate());
		lblNewLabel_24.setForeground(foreGroundColor);
		lblNewLabel_24.setFont(textFont);
		panel_44.add(lblNewLabel_24);
		
		

		
		
	}

	public Color getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(Color backGroundColor) {
		this.backGroundColor = backGroundColor;
	}

	public Color getForeGroundColor() {
		return foreGroundColor;
	}

	public void setForeGroundColor(Color foreGroundColor) {
		this.foreGroundColor = foreGroundColor;
	}

	public Font getTextFont() {
		return textFont;
	}

	public void setTextFont(Font textFont) {
		this.textFont = textFont;
	}

	public LineBorder getLineBorder() {
		return lineBorder;
	}

	public void setLineBorder(LineBorder lineBorder) {
		this.lineBorder = lineBorder;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

}
