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
import com.application.medCareApplication.model.examination.CTpluca;
import com.application.medCareApplication.model.examination.KrvnaSlika;
import com.application.medCareApplication.model.examination.RTGPluca;
import com.application.medCareApplication.model.examination.UltraZvuk;
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
		lineBorder = new LineBorder(Color.CYAN, 2, true);
		
		
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
		} else if(objectToDisplay instanceof CTpluca) {
			CTpluca ct = (CTpluca) objectToDisplay;
			displayCt(ct);
		} else if(objectToDisplay instanceof RTGPluca) {
			RTGPluca ct = (RTGPluca) objectToDisplay;
			displayRtg(ct);
		} else if(objectToDisplay instanceof KrvnaSlika) {
			KrvnaSlika ct = (KrvnaSlika) objectToDisplay;
			displayKs(ct);
		} else if(objectToDisplay instanceof UltraZvuk) {
			UltraZvuk ct = (UltraZvuk) objectToDisplay;
			displayUz(ct);
		}
	
	}
	
	public void displayAnamnesis(Anamnesis anamnesis) {
		infoText = String.format(infoTemplate, "anamneze");
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
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.anamnesisId);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(anamnesis.getAnamnesisId()));
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
		
		JLabel lblNewLabel_21 = new JLabel(Integer.toString(anamnesis.getPatientId()));
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
		
		JLabel lblNewLabel_12 = new JLabel(PatientsColumn.alcohol);
		lblNewLabel_12.setForeground(foreGroundColor);
		lblNewLabel_12.setFont(textFont);
		panel_32.add(lblNewLabel_12);
		
		//desni panel vrednost atributa
		JPanel panel_42 = new JPanel();
		panel_42.setBackground(backGroundColor);
		panel_12.add(panel_42);
		
		JLabel lblNewLabel_22 = new JLabel(anamnesis.getAlcohol());
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
		
		JLabel lblNewLabel_13 = new JLabel(PatientsColumn.employed);
		lblNewLabel_13.setForeground(foreGroundColor);
		lblNewLabel_13.setFont(textFont);
		panel_33.add(lblNewLabel_13);
		
		//desni panel vrednost atributa
		JPanel panel_43 = new JPanel();
		panel_43.setBackground(backGroundColor);
		panel_13.add(panel_43);
		
		JLabel lblNewLabel_23 = new JLabel(anamnesis.getEmployed());
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
		
		JLabel lblNewLabel_14 = new JLabel(PatientsColumn.smoking);
		lblNewLabel_14.setForeground(foreGroundColor);
		lblNewLabel_14.setFont(textFont);
		panel_34.add(lblNewLabel_14);
		
		//desni panel vrednost atributa
		JPanel panel_44 = new JPanel();
		panel_44.setBackground(backGroundColor);
		panel_14.add(panel_44);
		
		JLabel lblNewLabel_24 = new JLabel(anamnesis.getSmoking());
		lblNewLabel_24.setForeground(foreGroundColor);
		lblNewLabel_24.setFont(textFont);
		panel_44.add(lblNewLabel_24);
		
		//Jedan red
				JPanel panel_15 = new JPanel();
				panel_15.setBackground(backGroundColor);
				panel_15.setBorder(lineBorder);
				add(panel_15);
				panel_15.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_35 = new JPanel();
				panel_35.setBackground(backGroundColor);
				panel_15.add(panel_35);
				
				JLabel lblNewLabel_15 = new JLabel(PatientsColumn.workingCondition);
				lblNewLabel_15.setForeground(foreGroundColor);
				lblNewLabel_15.setFont(textFont);
				panel_35.add(lblNewLabel_15);
				
				//desni panel vrednost atributa
				JPanel panel_45 = new JPanel();
				panel_45.setBackground(backGroundColor);
				panel_15.add(panel_45);
				
				JLabel lblNewLabel_25 = new JLabel(anamnesis.getWorkingCondition());
				lblNewLabel_25.setForeground(foreGroundColor);
				lblNewLabel_25.setFont(textFont);
				panel_45.add(lblNewLabel_25);
				
		//Jedan red
				JPanel panel_16 = new JPanel();
				panel_16.setBackground(backGroundColor);
				panel_16.setBorder(lineBorder);
				add(panel_16);
				panel_16.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_36 = new JPanel();
				panel_36.setBackground(backGroundColor);
				panel_16.add(panel_36);
				
				JLabel lblNewLabel_16 = new JLabel(PatientsColumn.pet);
				lblNewLabel_16.setForeground(foreGroundColor);
				lblNewLabel_16.setFont(textFont);
				panel_36.add(lblNewLabel_16);
				
				//desni panel vrednost atributa
				JPanel panel_46 = new JPanel();
				panel_46.setBackground(backGroundColor);
				panel_16.add(panel_46);
				
				JLabel lblNewLabel_26 = new JLabel(anamnesis.getPet());
				lblNewLabel_26.setForeground(foreGroundColor);
				lblNewLabel_26.setFont(textFont);
				panel_46.add(lblNewLabel_26);
				
		//Jedan red
				JPanel panel_17 = new JPanel();
				panel_17.setBackground(backGroundColor);
				panel_17.setBorder(lineBorder);
				add(panel_17);
				panel_17.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_37 = new JPanel();
				panel_37.setBackground(backGroundColor);
				panel_17.add(panel_37);
				
				JLabel lblNewLabel_17 = new JLabel(PatientsColumn.livingPlace);
				lblNewLabel_17.setForeground(foreGroundColor);
				lblNewLabel_17.setFont(textFont);
				panel_37.add(lblNewLabel_17);
				
				//desni panel vrednost atributa
				JPanel panel_47 = new JPanel();
				panel_47.setBackground(backGroundColor);
				panel_17.add(panel_47);
				
				JLabel lblNewLabel_27 = new JLabel(anamnesis.getLivingPlace());
				lblNewLabel_27.setForeground(foreGroundColor);
				lblNewLabel_27.setFont(textFont);
				panel_47.add(lblNewLabel_27);
				
		//Jedan red
				JPanel panel_18 = new JPanel();
				panel_18.setBackground(backGroundColor);
				panel_18.setBorder(lineBorder);
				add(panel_18);
				panel_18.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_38 = new JPanel();
				panel_38.setBackground(backGroundColor);
				panel_18.add(panel_38);
				
				JLabel lblNewLabel_18 = new JLabel(PatientsColumn.livingObject);
				lblNewLabel_18.setForeground(foreGroundColor);
				lblNewLabel_18.setFont(textFont);
				panel_38.add(lblNewLabel_18);
				
				//desni panel vrednost atributa
				JPanel panel_48 = new JPanel();
				panel_48.setBackground(backGroundColor);
				panel_18.add(panel_48);
				
				JLabel lblNewLabel_28 = new JLabel(anamnesis.getLivingObject());
				lblNewLabel_28.setForeground(foreGroundColor);
				lblNewLabel_28.setFont(textFont);
				panel_48.add(lblNewLabel_28);
				
		//Jedan red
				JPanel panel_19 = new JPanel();
				panel_19.setBackground(backGroundColor);
				panel_19.setBorder(lineBorder);
				add(panel_19);
				panel_19.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_39 = new JPanel();
				panel_39.setBackground(backGroundColor);
				panel_19.add(panel_39);
				
				JLabel lblNewLabel_19 = new JLabel(PatientsColumn.additionalExamination);
				lblNewLabel_19.setForeground(foreGroundColor);
				lblNewLabel_19.setFont(textFont);
				panel_39.add(lblNewLabel_19);
				
				//desni panel vrednost atributa
				JPanel panel_49 = new JPanel();
				panel_49.setBackground(backGroundColor);
				panel_19.add(panel_49);
				
				JLabel lblNewLabel_29 = new JLabel(anamnesis.getAdditionalExamination());
				lblNewLabel_29.setForeground(foreGroundColor);
				lblNewLabel_29.setFont(textFont);
				panel_49.add(lblNewLabel_29);
				
				//Jedan red
				JPanel panel_20 = new JPanel();
				panel_20.setBackground(backGroundColor);
				panel_20.setBorder(lineBorder);
				add(panel_20);
				panel_20.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_40 = new JPanel();
				panel_40.setBackground(backGroundColor);
				panel_20.add(panel_40);
				
				JLabel lblNewLabel_20 = new JLabel(PatientsColumn.diagnosis);
				lblNewLabel_20.setForeground(foreGroundColor);
				lblNewLabel_20.setFont(textFont);
				panel_40.add(lblNewLabel_20);
				
				//desni panel vrednost atributa
				JPanel panel_50 = new JPanel();
				panel_50.setBackground(backGroundColor);
				panel_20.add(panel_50);
				
				JLabel lblNewLabel_30 = new JLabel(anamnesis.getDiagnosis());
				lblNewLabel_30.setForeground(foreGroundColor);
				lblNewLabel_30.setFont(textFont);
				panel_50.add(lblNewLabel_30);
				
				
				
				
	}
	
	public void displayPhysicalExamination(PhysicalExamination physicalExamination) {
		infoText = String.format(infoTemplate, "fizikalnog pregleda");
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
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.physicalExaminationId);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(physicalExamination.getPhysicalExaminationId()));
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
				
				JLabel lblNewLabel_21 = new JLabel(Integer.toString(physicalExamination.getPatientId()));
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
				
				JLabel lblNewLabel_12 = new JLabel(PatientsColumn.respiratoryNoise);
				lblNewLabel_12.setForeground(foreGroundColor);
				lblNewLabel_12.setFont(textFont);
				panel_32.add(lblNewLabel_12);
				
				//desni panel vrednost atributa
				JPanel panel_42 = new JPanel();
				panel_42.setBackground(backGroundColor);
				panel_12.add(panel_42);
				
				JLabel lblNewLabel_22 = new JLabel(physicalExamination.getRespiratoryNoise());
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
				
				JLabel lblNewLabel_13 = new JLabel(PatientsColumn.respiratorySound);
				lblNewLabel_13.setForeground(foreGroundColor);
				lblNewLabel_13.setFont(textFont);
				panel_33.add(lblNewLabel_13);
				
				//desni panel vrednost atributa
				JPanel panel_43 = new JPanel();
				panel_43.setBackground(backGroundColor);
				panel_13.add(panel_43);
				
				JLabel lblNewLabel_23 = new JLabel(physicalExamination.getRespiratorySound());
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
				
				JLabel lblNewLabel_14 = new JLabel(PatientsColumn.bodyTemperature);
				lblNewLabel_14.setForeground(foreGroundColor);
				lblNewLabel_14.setFont(textFont);
				panel_34.add(lblNewLabel_14);
				
				//desni panel vrednost atributa
				JPanel panel_44 = new JPanel();
				panel_44.setBackground(backGroundColor);
				panel_14.add(panel_44);
				
				JLabel lblNewLabel_24 = new JLabel(physicalExamination.getBodyTemperature());
				lblNewLabel_24.setForeground(foreGroundColor);
				lblNewLabel_24.setFont(textFont);
				panel_44.add(lblNewLabel_24);
				
				//Jedan red
				JPanel panel_15 = new JPanel();
				panel_15.setBackground(backGroundColor);
				panel_15.setBorder(lineBorder);
				add(panel_15);
				panel_15.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_35 = new JPanel();
				panel_35.setBackground(backGroundColor);
				panel_15.add(panel_35);
				
				JLabel lblNewLabel_15 = new JLabel(PatientsColumn.additionalExamination);
				lblNewLabel_15.setForeground(foreGroundColor);
				lblNewLabel_15.setFont(textFont);
				panel_35.add(lblNewLabel_15);
				
				//desni panel vrednost atributa
				JPanel panel_45 = new JPanel();
				panel_45.setBackground(backGroundColor);
				panel_15.add(panel_45);
				
				JLabel lblNewLabel_25 = new JLabel(physicalExamination.getAdditionalExamination());
				lblNewLabel_25.setForeground(foreGroundColor);
				lblNewLabel_25.setFont(textFont);
				panel_45.add(lblNewLabel_25);
				
				//Jedan red
				JPanel panel_16 = new JPanel();
				panel_16.setBackground(backGroundColor);
				panel_16.setBorder(lineBorder);
				add(panel_16);
				panel_16.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_36 = new JPanel();
				panel_36.setBackground(backGroundColor);
				panel_16.add(panel_36);
				
				JLabel lblNewLabel_16 = new JLabel(PatientsColumn.diagnosis);
				lblNewLabel_16.setForeground(foreGroundColor);
				lblNewLabel_16.setFont(textFont);
				panel_36.add(lblNewLabel_16);
				
				//desni panel vrednost atributa
				JPanel panel_46 = new JPanel();
				panel_46.setBackground(backGroundColor);
				panel_16.add(panel_46);
				
				JLabel lblNewLabel_26 = new JLabel(physicalExamination.getDiagnosis());
				lblNewLabel_26.setForeground(foreGroundColor);
				lblNewLabel_26.setFont(textFont);
				panel_46.add(lblNewLabel_26);
		
		
	}

	public void displayDiagnosis(Diagnosis diagnosis) {
		infoText = String.format(infoTemplate, "dijagnoze");
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

	public void displayCt(CTpluca ct) {
		infoText = String.format(infoTemplate, "ct pluca");
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
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.ct_id);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(ct.getId()));
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
		
		JLabel lblNewLabel_21 = new JLabel(Integer.toString(ct.getPatientId()));
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
		
		JLabel lblNewLabel_12 = new JLabel(PatientsColumn.ct);
		lblNewLabel_12.setForeground(foreGroundColor);
		lblNewLabel_12.setFont(textFont);
		panel_32.add(lblNewLabel_12);
		
		//desni panel vrednost atributa
		JPanel panel_42 = new JPanel();
		panel_42.setBackground(backGroundColor);
		panel_12.add(panel_42);
		
		JLabel lblNewLabel_22 = new JLabel(ct.getCt());
		lblNewLabel_22.setForeground(foreGroundColor);
		lblNewLabel_22.setFont(textFont);
		panel_42.add(lblNewLabel_22);
		
	

		
	}
	
	public void displayRtg(RTGPluca ct) {
		infoText = String.format(infoTemplate, "rtg pluca");
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
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.rtg_id);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(ct.getId()));
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
		
		JLabel lblNewLabel_21 = new JLabel(Integer.toString(ct.getPatientId()));
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
		
		JLabel lblNewLabel_12 = new JLabel(PatientsColumn.rtg);
		lblNewLabel_12.setForeground(foreGroundColor);
		lblNewLabel_12.setFont(textFont);
		panel_32.add(lblNewLabel_12);
		
		//desni panel vrednost atributa
		JPanel panel_42 = new JPanel();
		panel_42.setBackground(backGroundColor);
		panel_12.add(panel_42);
		
		JLabel lblNewLabel_22 = new JLabel(ct.getRtg());
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
				
				JLabel lblNewLabel_13 = new JLabel(PatientsColumn.lezije);
				lblNewLabel_13.setForeground(foreGroundColor);
				lblNewLabel_13.setFont(textFont);
				panel_33.add(lblNewLabel_13);
				
				//desni panel vrednost atributa
				JPanel panel_43 = new JPanel();
				panel_43.setBackground(backGroundColor);
				panel_13.add(panel_43);
				
				JLabel lblNewLabel_23 = new JLabel(ct.getHomogene_lezije());
				lblNewLabel_23.setForeground(foreGroundColor);
				lblNewLabel_23.setFont(textFont);
				panel_43.add(lblNewLabel_23);
	}
	
	public void displayKs(KrvnaSlika ct) {
		infoText = String.format(infoTemplate, "krvna slika");
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
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.krvna_id);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(ct.getId()));
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
		
		JLabel lblNewLabel_21 = new JLabel(Integer.toString(ct.getPatientId()));
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
		
		JLabel lblNewLabel_12 = new JLabel(PatientsColumn.eritrociti);
		lblNewLabel_12.setForeground(foreGroundColor);
		lblNewLabel_12.setFont(textFont);
		panel_32.add(lblNewLabel_12);
		
		//desni panel vrednost atributa
		JPanel panel_42 = new JPanel();
		panel_42.setBackground(backGroundColor);
		panel_12.add(panel_42);
		
		JLabel lblNewLabel_22 = new JLabel(ct.getEritrociti());
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
				
				JLabel lblNewLabel_13 = new JLabel(PatientsColumn.leukociti);
				lblNewLabel_13.setForeground(foreGroundColor);
				lblNewLabel_13.setFont(textFont);
				panel_33.add(lblNewLabel_13);
				
				//desni panel vrednost atributa
				JPanel panel_43 = new JPanel();
				panel_43.setBackground(backGroundColor);
				panel_13.add(panel_43);
				
				JLabel lblNewLabel_23 = new JLabel(ct.getLeukociti());
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
				
				JLabel lblNewLabel_14 = new JLabel(PatientsColumn.parametarske_inflamacije);
				lblNewLabel_14.setForeground(foreGroundColor);
				lblNewLabel_14.setFont(textFont);
				panel_34.add(lblNewLabel_14);
				
				//desni panel vrednost atributa
				JPanel panel_44 = new JPanel();
				panel_44.setBackground(backGroundColor);
				panel_14.add(panel_44);
				
				JLabel lblNewLabel_24 = new JLabel(ct.getParametarske_inflamacije());
				lblNewLabel_24.setForeground(foreGroundColor);
				lblNewLabel_24.setFont(textFont);
				panel_44.add(lblNewLabel_24);
	}
	
	public void displayUz(UltraZvuk ct) {
		infoText = String.format(infoTemplate, "ultrazvuk");
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
		
		JLabel lblNewLabel_1 = new JLabel(PatientsColumn.uvId);
		lblNewLabel_1.setForeground(foreGroundColor);
		lblNewLabel_1.setFont(textFont);
		panel_3.add(lblNewLabel_1);
		
		//desni panel vrednost atributa
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(backGroundColor);
		panel_1.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel(Integer.toString(ct.getId()));
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
		
		JLabel lblNewLabel_21 = new JLabel(Integer.toString(ct.getPatientId()));
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
		
		JLabel lblNewLabel_12 = new JLabel(PatientsColumn.dubina_izliva);
		lblNewLabel_12.setForeground(foreGroundColor);
		lblNewLabel_12.setFont(textFont);
		panel_32.add(lblNewLabel_12);
		
		//desni panel vrednost atributa
		JPanel panel_42 = new JPanel();
		panel_42.setBackground(backGroundColor);
		panel_12.add(panel_42);
		
		JLabel lblNewLabel_22 = new JLabel(ct.getDubina_izliva());
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
				
				JLabel lblNewLabel_13 = new JLabel(PatientsColumn.gustina_izliva);
				lblNewLabel_13.setForeground(foreGroundColor);
				lblNewLabel_13.setFont(textFont);
				panel_33.add(lblNewLabel_13);
				
				//desni panel vrednost atributa
				JPanel panel_43 = new JPanel();
				panel_43.setBackground(backGroundColor);
				panel_13.add(panel_43);
				
				JLabel lblNewLabel_23 = new JLabel(ct.getGustina_izliva());
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
				
				JLabel lblNewLabel_14 = new JLabel(PatientsColumn.mesto_punkcije);
				lblNewLabel_14.setForeground(foreGroundColor);
				lblNewLabel_14.setFont(textFont);
				panel_34.add(lblNewLabel_14);
				
				//desni panel vrednost atributa
				JPanel panel_44 = new JPanel();
				panel_44.setBackground(backGroundColor);
				panel_14.add(panel_44);
				
				JLabel lblNewLabel_24 = new JLabel(ct.getMesto_punkcije());
				lblNewLabel_24.setForeground(foreGroundColor);
				lblNewLabel_24.setFont(textFont);
				panel_44.add(lblNewLabel_24);
				
				//Jedan red
				JPanel panel_15 = new JPanel();
				panel_15.setBackground(backGroundColor);
				panel_15.setBorder(lineBorder);
				add(panel_15);
				panel_15.setLayout(new GridLayout(1, 2, 10, 0));
				
				//levi panel naziv atributa
				JPanel panel_35 = new JPanel();
				panel_35.setBackground(backGroundColor);
				panel_15.add(panel_35);
				
				JLabel lblNewLabel_15 = new JLabel(PatientsColumn.visina_izliva);
				lblNewLabel_15.setForeground(foreGroundColor);
				lblNewLabel_15.setFont(textFont);
				panel_35.add(lblNewLabel_15);
				
				//desni panel vrednost atributa
				JPanel panel_45 = new JPanel();
				panel_45.setBackground(backGroundColor);
				panel_15.add(panel_45);
				
				JLabel lblNewLabel_25 = new JLabel(ct.getVisina_izliva());
				lblNewLabel_25.setForeground(foreGroundColor);
				lblNewLabel_25.setFont(textFont);
				panel_45.add(lblNewLabel_25);
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
