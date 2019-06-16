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
	
	// dijagnoza pacijenta + diagnose + date odgore
	public static String therapyId = "therapy_Id";
	public static String therapy = "Terapija";

}
