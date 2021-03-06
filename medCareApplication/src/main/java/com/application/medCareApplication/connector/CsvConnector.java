package com.application.medCareApplication.connector;

import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.utils.components.DatabaseHandler;
import com.application.medCareApplication.view.MainFrame;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;


public class CsvConnector implements Connector {
	
	private DatabaseHandler databaseHandler;
	private Patient curPat;
	
	@Override
	public Collection<CBRCase> retrieveAllCases() {
		databaseHandler = MainFrame.getInstance().getDatabaseHandler();
		curPat = MainFrame.getInstance().getCurrentPatient();
		
		if(MainFrame.getInstance().getIsAnamnesis()) {
			//slucajevi anamneze
			LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
			List<Anamnesis> anamnesis  = databaseHandler.selectAllPatientAnamnesisWithoutCurrent(curPat);
					
			for (Anamnesis a : anamnesis) {
				CBRCase cbrCase = new CBRCase(); // OVDE JE BILA GRESKA!!!
				System.out.println("Anamneza: " + a.getAnamnesisId());
				cbrCase.setDescription(a);
				cases.add(cbrCase);
			}
			
			return cases;
		} else if (MainFrame.getInstance().getIsDiagnosis()) {
			LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
			List<Diagnosis> diagnosis = databaseHandler.selectAllPatientDiagnosisWithoutCurrent(curPat);
			
			for (Diagnosis d : diagnosis) {
				CBRCase cbrCase = new CBRCase();
				System.out.println("Dijagnoza: " + d.getDiagnosisId());
				cbrCase.setDescription(d);
				cases.add(cbrCase);
			}
			
			return cases;
		} else {
			//slucajevi fizikalnog pregleda
			LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
			List<PhysicalExamination> pe  = databaseHandler.selectAllPatientPhysicalExaminationWithoutCurrent(curPat);
			
			for (PhysicalExamination physicalExamination : pe) {
				CBRCase cbrCase = new CBRCase(); // OVDE JE BILA GRESKA!!!
				System.out.println("Fiz pregled: " + physicalExamination.getPhysicalExaminationId());
				cbrCase.setDescription(physicalExamination);
				cases.add(cbrCase);
			}
			return cases;
		}
		
		
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
		return null;
	}

	@Override
	public void storeCases(Collection<CBRCase> arg0) {
	}
	
	@Override
	public void close() {
	}

	@Override
	public void deleteCases(Collection<CBRCase> arg0) {
	}

	@Override
	public void initFromXMLfile(URL arg0) throws InitializingException {
	}

}