package com.application.medCareApplication.utils;

public class PatientsColumn {

	// Pomocna klasa koja sadrzi imena kolona predstavljenih kao konstante
	// kolone o pacijentu
	public static String patientId = "Id_Pacijenta";
	
	public static String firstNameColumn = "Ime";
	public static String lastNameColumn = "Prezime";
	public static String jmbgColumn = "Jmbg";
	public static String dateOfBirthColumn = "Datum_rodjenja";
	public static String addressColumn = "Adresa";
	public static String telephoneNumberColumn = "Telefon";
	
	// fizicki pregled kolone
	public static String physicalExaminationId = "physical_Examination_Id";
	public static String bodyTemperature = "Temperatura";
	public static String respiratorySound = "Disajni_zvuk";
	public static String respiratoryNoise = "Sumovi";
	public static String additionalExamination = "dopunska_ispitivanja";
	
	// anamneza pacijenta kolone
	public static String anamnesisId = "anamnesis_Id";
	public static String smoking = "Pusenje";
	public static String alcohol = "Alkohol";
	public static String employed = "Stanje";
	public static String workingCondition = "Tezina";
	public static String livingPlace = "Zivi";
	public static String livingObject = "Stanuje";
	public static String pet = "Ljubimci";
	

	// tabela svih resursa
	public static String resourceId = "resource_id";
	public static String resourceName = "resource_name";
	public static String resourceType = "resource_type";
	
	
	// ranije bolesti
	public static String patientIdRanije = "patient_id";
	public static String diagnosisId = "diagnosis_id";
	
	// dijagnoza pacijenta
	public static String diagnosis_Id = "diagnosis_Id";
	public static String diagnose = "Dijagnoza";
	public static String date = "Datum";
	
	// terapija pacijenta + diagnose + date odgore
	public static String therapyId = "therapy_Id";
	public static String therapy = "Terapija";
	
	//dodato radi CBR-a za dijagnozu
	public static String diagnosis = "diagnosis";
	
	// uv
	public static String uvId = "uv_id";
	public static String dubina_izliva = "dubina_izliva";
	public static String visina_izliva = "visina_izliva";
	public static String gustina_izliva = "gustina_izliva";
	public static String mesto_punkcije = "mesto_punkcije";
	
	// ct
	public static String ct_id = "ct_id";
	public static String ct = "ct";
	
	// rtg
	public static String rtg_id = "rtg_id";
	public static String rtg = "rtg";
	public static String lezije = "lezije";
	
	// krvna slika
	public static String krvna_id = "krvna_id";
	public static String leukociti = "leukociti";
	public static String eritrociti = "eritrociti";
	public static String parametarske_inflamacije = "parametarske_inflamacije";
	
	//additional examination
	
	public static String additional_examination_id = "additional_examination_id";
	public static String id_pacijenta = "id_pacijenta";
	public static String id_ct = "id_ct";
	public static String id_rtg = "id_rtg";
	public static String id_ks = "id_ks";
	public static String id_uz = "id_uz";
	
}
