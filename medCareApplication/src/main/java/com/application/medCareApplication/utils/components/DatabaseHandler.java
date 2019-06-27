package com.application.medCareApplication.utils.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.application.medCareApplication.model.AdditionalExamination;
import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.Diagnosis;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;
import com.application.medCareApplication.model.Resources;
import com.application.medCareApplication.model.Therapy;
import com.application.medCareApplication.model.examination.CTpluca;
import com.application.medCareApplication.model.examination.KrvnaSlika;
import com.application.medCareApplication.model.examination.RTGPluca;
import com.application.medCareApplication.model.examination.UltraZvuk;
import com.application.medCareApplication.utils.PatientsColumn;

/**
 * 
 * Konkretizacija Ihandler interfejsa koja rukuje sa podacima dobijenim sa skladi�ta podataka.
 *
 */
public class DatabaseHandler {

	final static String DB_URL= "jdbc:mysql://localhost:3306/medcare_db?useSSL=false&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true";
	
	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	private Connection databaseConnection;
	
	public DatabaseHandler(){
		try {
			try {
				connect();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.log(Level.INFO, "Connection with database established");
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Failed to connect with database");
		}
	}
	
	public void connect() throws SQLException, FileNotFoundException, IOException{
		Properties p = new Properties();
		p.load(new FileInputStream("src/main/resources/application.properties"));
		
		String username = p.getProperty("spring.datasource.username");
		String password = p.getProperty("spring.datasource.password");
		
		databaseConnection = DriverManager.getConnection(DB_URL, username, password);
	}
		
	
	public void close() throws SQLException{
		if(databaseConnection != null)
			databaseConnection.close();
		log.log(Level.INFO, "Closing database connection");
	}
	
	/**
	 * Metoda koja broji broj torki odredjene tabele radi generisanje id za novokreiranu torku
	 * @param tableName - ima tabele cije se torke broje
	 * @return
	 */
	private int getId(String tableName) {
		String sql = String.format("SELECT %s FROM %s;", "*", tableName);
		StringBuilder st = new StringBuilder(sql);
		
		System.out.println(st);
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(st.toString());
						
			rset.last(); // moves cursor to the last row
			int size = rset.getRow(); // get row id 
			
			rset.close();
			stmt.close();
			
			return size + 1;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return -1;
		}
	}
	
	
	/** --------------BEGIN: Operacije za pacijenta ------------------------------- */
	public void createPatient(Patient patient) throws SQLException{
		
		int id = getId("patient");
		String template = "INSERT INTO patient (Id_Pacijenta, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			/*Statement stmt = databaseConnection.createStatement();
			stmt.execute(template);
			
			stmt.close();*/
			
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, patient.getFirstName());
			preparedStatement.setString(3, patient.getLastName());
			preparedStatement.setString(4, patient.getJmbg());
			preparedStatement.setString(5, patient.getTelephoneNumber());
			preparedStatement.setString(6, patient.getDateOfBirth());
			preparedStatement.setString(7, patient.getAddress());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	public Patient selectPatient(int patientId) {
		
		String sql = String.format("SELECT %s FROM %s WHERE %s = '%s';", "*", "patient", PatientsColumn.patientId, patientId);
		
		System.out.println(sql);
		
		
		try {
			Patient patient = null;

			
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nevalidan id");
			}
			
			while(rset.next()) {
				patient = makeOnePatient(rset);
			}
			
			
			rset.close();
			stmt.close();
			
			return patient;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	
	public List<Patient> selectAllPatients() {
	
		String sql = String.format("SELECT %s FROM %s;", "*", "patient");
		
		System.out.println(sql);
		
		List<Patient> patients = new ArrayList<Patient>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				Patient p = makeOnePatient(rset);
				if( p != null) {
					patients.add(p);
				}
			}
			
			
			rset.close();
			stmt.close();
			
			return patients;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	public void updatePatient(Patient patient) throws SQLException {
		String template = "UPDATE patient SET Ime = ?, Prezime = ?, Jmbg = ?, Telefon = ?, Datum_rodjenja = ?, Adresa = ? WHERE Id = ?";

		System.out.println(template);
		PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
		
		try {
			preparedStatement.setInt(7, patient.getPatientId());
			preparedStatement.setString(1, patient.getFirstName());
			preparedStatement.setString(2, patient.getLastName());
			preparedStatement.setString(3, patient.getJmbg());
			preparedStatement.setString(4, patient.getTelephoneNumber());
			preparedStatement.setString(5, patient.getDateOfBirth());
			preparedStatement.setString(6, patient.getAddress());

			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("");
		} finally {
			preparedStatement.close();
		}
	}
	
	private Patient makeOnePatient(ResultSet resultSet) {
		Patient patient = null;
		try {
			Integer id = resultSet.getInt(PatientsColumn.patientId);
			String firstName = resultSet.getString(PatientsColumn.firstNameColumn);
			String lastName = resultSet.getString(PatientsColumn.lastNameColumn);
			String address = resultSet.getString(PatientsColumn.addressColumn);
			String jmbg = resultSet.getString(PatientsColumn.jmbgColumn);
			String telephoneNumber = resultSet.getString(PatientsColumn.telephoneNumberColumn);
			String dateOfBirth = resultSet.getString(PatientsColumn.dateOfBirthColumn);
			patient = new Patient(id, firstName, lastName, jmbg, dateOfBirth, address, telephoneNumber);
			
			return patient;
		} catch (SQLException e) {
			return patient;
		}
	}
	/** --------------END: Operacije za pacijenta ------------------------------- */

	
	/** --------------BEGIN: Operacije za fizikalni pregled ------------------------------- */
	public PhysicalExamination createPhysicalExamination(PhysicalExamination ex) throws SQLException{
		
		int id = getId("physical_examination");
		String template = "INSERT INTO physical_examination (physical_Examination_Id, Id_Pacijenta, Temperatura, Disajni_zvuk, Sumovi) VALUES (?, ?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, ex.getPatientId()); 
			preparedStatement.setString(3, ex.getBodyTemperature());
			preparedStatement.setString(4, ex.getRespiratorySound());
			preparedStatement.setString(5, ex.getRespriratoryNoise());
		
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			return selectPatientPhysicalExamination(id);
			
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	/**
	 * 
	 * Metoda koja vraca sve fizikalne preglede iz baze na osnovu koje realizujemo CBR
	 * 
	 * */
	public List<PhysicalExamination> selectAllPatientPhysicalExamination() {
			
			String sql = String.format("SELECT %s FROM %s ;", "*", "physical_examination");
			
			System.out.println(sql);
			
			List<PhysicalExamination> pe = new ArrayList<PhysicalExamination>();
			
			try {
				Statement stmt = databaseConnection.createStatement();			
				
				ResultSet rset = stmt.executeQuery(sql);
				
				if(!rset.isBeforeFirst()) {
					System.out.println("Nema podataka");
				}
				
				while(rset.next()) {
					PhysicalExamination a = makeOnePhysicalExamination(rset);
					if( a != null) {
						pe.add(a);
					}
				}
					
				rset.close();
				stmt.close();
				
				return pe;
				
			} catch (SQLException e) {
				//e.printStackTrace();
				return null;
			}
		
		}
	
	
	/**
	 * 
	 * Metoda koja vraca sve fizikalne preglede iz baze na osnovu koje realizujemo CBR OSIM MOJE TRENUTNE (KAKO NE BI VRATILO NULL)
	 * 
	 * */
	public List<PhysicalExamination> selectAllPatientPhysicalExaminationWithoutCurrent(Patient p) {
			

			String sql = String.format("SELECT %s FROM %s WHERE %s != '%s' ;", "*", "physical_examination", PatientsColumn.patientId, p.getPatientId());
			
			System.out.println(sql);
			
			List<PhysicalExamination> pe = new ArrayList<PhysicalExamination>();
			
			try {
				Statement stmt = databaseConnection.createStatement();			
				
				ResultSet rset = stmt.executeQuery(sql);
				
				if(!rset.isBeforeFirst()) {
					System.out.println("Nema podataka");
				}
				
				while(rset.next()) {
					PhysicalExamination a = makeOnePhysicalExamination(rset);
					if( a != null) {
						pe.add(a);
					}
				}
					
				rset.close();
				stmt.close();
				
				return pe;
				
			} catch (SQLException e) {
				//e.printStackTrace();
				return null;
			}
		
		}
	public PhysicalExamination selectPatientPhysicalExamination(int physicalExaminationId) {
		String sql = String.format("SELECT %s FROM %s WHERE %s = '%s';", "*", "physical_examination", PatientsColumn.physicalExaminationId, physicalExaminationId);
		System.out.println(sql);
		try {
			PhysicalExamination physicalExamination = null;	
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nevalidan id");
			}
			
			while(rset.next()) {
				physicalExamination = makeOnePhysicalExamination(rset);
			}	
			rset.close();
			stmt.close();
			return physicalExamination;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	public List<PhysicalExamination> selectPatientPhysicalExaminations(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "physical_examination", p.getPatientId());
		
		System.out.println(sql);
		
		List<PhysicalExamination> physicalExaminatino = new ArrayList<PhysicalExamination>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				PhysicalExamination pE = makeOnePhysicalExamination(rset);
				if(pE != null) {
					physicalExaminatino.add(pE);
				}
			}
			
			
			rset.close();
			stmt.close();
			
			return physicalExaminatino;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	/*
	 * NOVO - VRATI MI SVE FIZIKALNE PREGLEDE ZA KONKRETNOG PACIJENTA
	 * */
	public List<PhysicalExamination> selectAllPatientPhysicalExamination(Patient p) {
			
			String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "physical_examination", p.getPatientId());
			
			System.out.println(sql);
			
			List<PhysicalExamination> anamnesis = new ArrayList<PhysicalExamination>();
			
			try {
				Statement stmt = databaseConnection.createStatement();			
				
				ResultSet rset = stmt.executeQuery(sql);
				
				if(!rset.isBeforeFirst()) {
					System.out.println("Nema podataka");
				}
				
				while(rset.next()) {
					PhysicalExamination a = makeOnePhysicalExamination(rset);
					if( a != null) {
						anamnesis.add(a);
					}
				}
					
				rset.close();
				stmt.close();
				
				return anamnesis;
				
			} catch (SQLException e) {
				//e.printStackTrace();
				return null;
			}
		
		}
	
	
	
	

	private PhysicalExamination makeOnePhysicalExamination(ResultSet resultSet) {
		PhysicalExamination pe = null;
		try {
			Integer physicalExaminationId = resultSet.getInt(PatientsColumn.physicalExaminationId);
			Integer patientId = resultSet.getInt(PatientsColumn.patientId);
			String bodyTemperature = resultSet.getString(PatientsColumn.bodyTemperature);
			String respiratorySound = resultSet.getString(PatientsColumn.respiratorySound);
			String respiratoryNoise = resultSet.getString(PatientsColumn.respiratoryNoise);
			String additionalExamination = resultSet.getString(PatientsColumn.additionalExamination);
			String recommendedDiagnosis = resultSet.getString(PatientsColumn.diagnosis);
			
			pe = new PhysicalExamination(physicalExaminationId, patientId, bodyTemperature, respiratorySound, respiratoryNoise,
					additionalExamination,recommendedDiagnosis);
			return pe;
		} catch (SQLException e) {
			return pe;
		}
	}
	
	/*private PhysicalExamination makeOnePhysicalExamination(ResultSet resultSet) {
	PhysicalExamination physicalExamination = null;
	try {
		Integer physicalExaminationId = resultSet.getInt(PatientsColumn.physicalExaminationId);
		Integer patientId = resultSet.getInt(PatientsColumn.patientId);
		String bodyTemperature = resultSet.getString(PatientsColumn.bodyTemperature);
		String respiratorySound = resultSet.getString(PatientsColumn.respiratorySound);
		String respiratoryNoise = resultSet.getString(PatientsColumn.respiratoryNoise);
		
		physicalExamination = new PhysicalExamination(physicalExaminationId, patientId, bodyTemperature, respiratorySound, respiratoryNoise);
		
		return physicalExamination;
	} catch (SQLException e) {
		return physicalExamination;
	}
}*/
	
	/** --------------END: Operacije za fizikalni pregled ------------------------------- */

	/** --------------BEGIN: Operacije za porodicne bolesti ------------------------------- */
	/**
	 * 
	 * Ubacivanje u posebnu tabelu za porodicne bolesti
	 * 
	 * */
	public void createPorodicneBolesti(Integer patientId, Integer resource) throws SQLException{
			
			String template = "INSERT INTO porodicne_bolesti (patient_id, diagnosis_id) VALUES (?, ?)";
			
			System.out.println(template);
		
			try {
				PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
				preparedStatement.setInt(1, patientId); 
				preparedStatement.setInt(2, resource);

				
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException e) {
				throw new SQLException("");
			}
			
		}
	/** --------------END: Operacije za porodicne bolesti ------------------------------- */

	

	/** --------------BEGIN: Operacije za anamneze ------------------------------- */
	public Anamnesis createAnamnesis(Anamnesis anamnesis) throws SQLException{
		int id = getId("anamnesis");
		String template = "INSERT INTO anamnesis (anamnesis_id, Id_Pacijenta, Pusenje, Alkohol, Stanje, Tezina, Zivi, Stanuje, Ljubimci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, anamnesis.getPatientId());
			preparedStatement.setString(3, anamnesis.getSmoking());
			preparedStatement.setString(4, anamnesis.getAlcohol());
			preparedStatement.setString(5, anamnesis.getEmployed());
			preparedStatement.setString(6, anamnesis.getWorkingCondition());
			preparedStatement.setString(7, anamnesis.getLivingPlace());
			preparedStatement.setString(8, anamnesis.getLivingObject());
			preparedStatement.setString(9, anamnesis.getPet());
			                               
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			return selectPatientAnamnesis(id);
			
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	public Anamnesis selectPatientAnamnesis(int anamnesisId) {
		String sql = String.format("SELECT %s FROM %s WHERE %s = '%s';", "*", "anamnesis", PatientsColumn.anamnesisId, anamnesisId);
		System.out.println(sql);
		try {
			Anamnesis anamnesis = null;	
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nevalidan id");
			}
			
			while(rset.next()) {
				anamnesis = makeOneAnamnesis(rset);
			}	
			rset.close();
			stmt.close();
			return anamnesis;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	public List<Anamnesis> selectAllPatientAnamnesis(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "anamnesis", p.getPatientId());
		
		System.out.println(sql);
		
		List<Anamnesis> anamnesis = new ArrayList<Anamnesis>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				Anamnesis a = makeOneAnamnesis(rset);
				if( a != null) {
					anamnesis.add(a);
				}
			}
				
			rset.close();
			stmt.close();
			
			return anamnesis;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	private Anamnesis makeOneAnamnesis(ResultSet resultSet) {
		Anamnesis anamnesis = null;
		try {
			Integer anamnesisId = resultSet.getInt(PatientsColumn.anamnesisId);
			Integer patientId = resultSet.getInt(PatientsColumn.patientId);
			String smoking = resultSet.getString(PatientsColumn.smoking);
			String alcohol = resultSet.getString(PatientsColumn.alcohol);
			String employed = resultSet.getString(PatientsColumn.employed);
			String workingCondition = resultSet.getString(PatientsColumn.workingCondition);
			String livingPlace = resultSet.getString(PatientsColumn.livingPlace);
			String livingObject = resultSet.getString(PatientsColumn.livingObject);
			String pet = resultSet.getString(PatientsColumn.pet);
			String additionalExamination = resultSet.getString(PatientsColumn.additionalExamination);
			String recommendedDiagnosis = resultSet.getString(PatientsColumn.diagnosis);
			
			anamnesis = new Anamnesis(anamnesisId, patientId, smoking, alcohol, employed, workingCondition, livingPlace, livingObject, pet,additionalExamination, recommendedDiagnosis);
			return anamnesis;
		} catch (SQLException e) {
			return anamnesis;
		}
	}
	
	
	
	
	/**
	 * 
	 * Metoda koja vraca sve anamneze iz baze na osnovu koje realizujemo CBR
	 * 
	 * */
	
	public List<Anamnesis> selectAllPatientAnamnesis() {
			
			String sql = String.format("SELECT %s FROM %s ;", "*", "anamnesis");
			
			System.out.println(sql);
			
			List<Anamnesis> anamnesis = new ArrayList<Anamnesis>();
			
			try {
				Statement stmt = databaseConnection.createStatement();			
				
				ResultSet rset = stmt.executeQuery(sql);
				
				if(!rset.isBeforeFirst()) {
					System.out.println("Nema podataka");
				}
				
				while(rset.next()) {
					Anamnesis a = makeOneAnamnesis(rset);
					if( a != null) {
						anamnesis.add(a);
					}
				}
					
				rset.close();
				stmt.close();
				
				return anamnesis;
				
			} catch (SQLException e) {
				//e.printStackTrace();
				return null;
			}
		
		}
	
	/**
	 * 
	 * Metoda koja vraca sve anamneze iz baze na osnovu koje realizujemo CBR OSIM MOJE TRENUTNE (KAKO NE BI VRATILO NULL)
	 * 
	 * */
	
	public List<Anamnesis> selectAllPatientAnamnesisWithoutCurrent(Patient p) {
			
			String sql = String.format("SELECT %s FROM %s WHERE %s != '%s' ;", "*", "anamnesis", PatientsColumn.patientId, p.getPatientId());
			
			System.out.println(sql);
			
			List<Anamnesis> anamnesis = new ArrayList<Anamnesis>();
			
			try {
				Statement stmt = databaseConnection.createStatement();			
				
				ResultSet rset = stmt.executeQuery(sql);
				
				if(!rset.isBeforeFirst()) {
					System.out.println("Nema podataka");
				}
				
				while(rset.next()) {
					Anamnesis a = makeOneAnamnesis(rset);
					if( a != null) {
						anamnesis.add(a);
					}
				}
					
				rset.close();
				stmt.close();
				
				return anamnesis;
				
			} catch (SQLException e) {
				//e.printStackTrace();
				return null;
			}
		
		}
	
	/** --------------END: Operacije za anamneze ------------------------------- */

	
	/** --------------BEGIN: Operacije za dopunska ispitivanja ------------------------------- */
	/*
	 * 
	 * Metoda koja kreira krvnu sliku i ubacuje u bazu!
	 * 
	 * drugi parametar predstavlja da li treba da se upise dodatno u tabelu
	 * za anamneze ili za fizikalne preglede u zavisnosti od toga odakle je 
	 * radjeno ispitivanje
	 * */
	public void createKrvnaSlika(KrvnaSlika krvna,Boolean anam) throws SQLException{
		int id = getId("krvna_slika");
		String template = "INSERT INTO krvna_slika (krvna_id, id_pacijenta, leukociti, eritrociti, parametarske_inflamacije) VALUES (?, ?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, krvna.getPatientId());
			preparedStatement.setString(3, krvna.getLeukociti());
			preparedStatement.setString(4, krvna.getEritrociti());
			preparedStatement.setString(5, krvna.getParametarske_inflamacije());
			
			                               
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
		
		//String template = "UPDATE patient SET Ime = ?, Prezime = ?, Jmbg = ?, Telefon = ?, Datum_rodjenja = ?, Adresa = ? WHERE Id = ?";
		String template2="";
		if(anam) { // update anamnesis
			template2 = "UPDATE anamnesis SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		} else { // update physical_examination
			template2 = "UPDATE physical_examination SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		}
		
		
		try {
			PreparedStatement ps = databaseConnection.prepareStatement(template2);
			ps.setString(1,"KRVNA_SLIKA");
			ps.setInt(2, krvna.getPatientId());
			
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			throw new SQLException("");
		}
		
		
	}
	

	/*
	 * 
	 * Metoda koja kreira rtg pluca i ubacuje u bazu!
	 * 
	 * */
	public void createRTGPluca(RTGPluca rtg,Boolean anam) throws SQLException{
		int id = getId("rtg_pluca");
		String template = "INSERT INTO rtg_pluca (rtg_id, id_pacijenta, rtg, lezije) VALUES (?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, rtg.getPatientId());
			preparedStatement.setString(3, rtg.getRtg());
			preparedStatement.setString(4, rtg.getHomogene_lezije());

			
			                               
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
		String template2="";
		if(anam) { // update anamnesis
			template2 = "UPDATE anamnesis SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		} else { // update physical_examination
			template2 = "UPDATE physical_examination SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		}
		
		
		try {
			PreparedStatement ps = databaseConnection.prepareStatement(template2);
			ps.setString(1,"RTG_PLUCA");
			ps.setInt(2, rtg.getPatientId());
			
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	/*
	 * 
	 * Metoda koja kreira ct pluca i ubacuje u bazu!
	 * 
	 * */
	public void createCTPluca(CTpluca ct,Boolean anam) throws SQLException{
		int id = getId("ct_pluca");
		String template = "INSERT INTO ct_pluca (ct_id, id_pacijenta, ct) VALUES (?, ?, ?)";
		
		System.out.println(template);
	
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, ct.getPatientId());
			preparedStatement.setString(3, ct.getCt());
			                               
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
		String template2="";
		if(anam) { // update anamnesis
			template2 = "UPDATE anamnesis SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		} else { // update physical_examination
			template2 = "UPDATE physical_examination SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		}
		
		
		try {
			PreparedStatement ps = databaseConnection.prepareStatement(template2);
			ps.setString(1,"CT_PLUCA");
			ps.setInt(2, ct.getPatientId());
			
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	/*
	 * 
	 * Metoda koja kreira ultrazvuk i ubacuje u bazu!
	 * 
	 * */
	public void createUltraZvuk(UltraZvuk uz,Boolean anam) throws SQLException{
		int id = getId("ultra_zvuk");
		String template = "INSERT INTO ultra_zvuk (uv_id, id_pacijenta, dubina_izliva,visina_izliva,gustina_izliva,mesto_punkcije) VALUES (?, ?, ?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, uz.getPatientId());
			preparedStatement.setString(3, uz.getDubina_izliva());
			preparedStatement.setString(4, uz.getVisina_izliva());
			preparedStatement.setString(5, uz.getGustina_izliva());
			preparedStatement.setString(6, uz.getMesto_punkcije());
			                               
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
		String template2="";
		if(anam) { // update anamnesis
			template2 = "UPDATE anamnesis SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		} else { // update physical_examination
			template2 = "UPDATE physical_examination SET dopunska_ispitivanja = ? WHERE id_pacijenta = ?";
		}
		
		
		try {
			PreparedStatement ps = databaseConnection.prepareStatement(template2);
			ps.setString(1,"UZ_PLUCNE_MARAMICE");
			ps.setInt(2, uz.getPatientId());
			
			ps.executeUpdate();
			ps.close();
		} catch(SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	public List<Object> selectAllPatientAdditionalExaminations(Patient p){
		List<Object> rgs = selectAllPatientRTG(p);
		List<Object> cts = selectAllPatientCt(p);
		List<Object> cbcs = selectAllPatientCBC(p);
		List<Object> uvs = selectAllPatientUV(p);
		
		List<Object> examinations = new ArrayList<Object>();
		
		if(rgs != null) {
			examinations.addAll(rgs);
		}
		
		if(cts != null) {
			examinations.addAll(cts);
		}
		
		if(cbcs != null) {
			examinations.addAll(cbcs);
		}
		
		if(uvs != null) {
			examinations.addAll(uvs);
		}
		
		return examinations;
	}
	
	
	public List<Object> selectAllPatientRTG(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "rtg_pluca", p.getPatientId());
		
		System.out.println(sql);
		
		List<Object> rtgs = new ArrayList<Object>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				RTGPluca a = makeOneRTG(rset);
				if( a != null) {
					rtgs.add(a);
				}
			}
				
			rset.close();
			stmt.close();
			
			return rtgs;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
public RTGPluca selectPatientRtg(int ksId) {
		
		String sql = String.format("SELECT %s FROM %s WHERE rtg_id = '%s';", "*", "rtg_pluca", ksId);
		
		System.out.println(sql);
		
		RTGPluca cts = new RTGPluca();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}

			while(rset.next()) {
				cts = makeOneRTG(rset);
			}
				

				
			rset.close();
			stmt.close();
			
			return cts;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	private RTGPluca makeOneRTG(ResultSet resultSet) {
		RTGPluca rtgPluca = null;
		try {
			Integer rtgId = resultSet.getInt(PatientsColumn.rtg_id);
			Integer patientId = resultSet.getInt(PatientsColumn.patientId);
			String rtg = resultSet.getString(PatientsColumn.rtg);
			String lezije = resultSet.getString(PatientsColumn.lezije);
		
			rtgPluca = new RTGPluca(rtgId, patientId, rtg, lezije);
			return rtgPluca;
		} catch (SQLException e) {
			return rtgPluca;
		}
	}
	
	public List<Object> selectAllPatientCt(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "ct_pluca", p.getPatientId());
		
		System.out.println(sql);
		
		List<Object> cts = new ArrayList<Object>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				CTpluca a = makeOneCT(rset);
				if( a != null) {
					cts.add(a);
				}
			}
				
			rset.close();
			stmt.close();
			
			return cts;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
public CTpluca selectPatientCt(int ctId) {
		
	String sql = String.format("SELECT %s FROM %s WHERE ct_id = '%s';", "*", "ct_pluca", ctId);
		
		System.out.println(sql);
		
		CTpluca cts = new CTpluca();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}


			while(rset.next()) {
				cts = makeOneCT(rset);
			}
			

				
			rset.close();
			stmt.close();
			
			return cts;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	private CTpluca makeOneCT(ResultSet resultSet) {
		CTpluca ctPluca = null;
		try {
			Integer ctId = resultSet.getInt(PatientsColumn.ct_id);
			Integer patientId = resultSet.getInt(PatientsColumn.patientId);
			String ct = resultSet.getString(PatientsColumn.ct);
		
			ctPluca = new CTpluca(ctId, patientId, ct);
			return ctPluca;
		} catch (SQLException e) {
			return ctPluca;
		}
	}
	
	public List<Object> selectAllPatientCBC(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "krvna_slika", p.getPatientId());
		
		System.out.println(sql);
		
		List<Object> cbcs = new ArrayList<Object>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				KrvnaSlika a = makeOneCBC(rset);
				if( a != null) {
					cbcs.add(a);
				}
			}
				
			rset.close();
			stmt.close();
			
			return cbcs;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
public KrvnaSlika selectPatientKs(int ksId) {
		
		String sql = String.format("SELECT %s FROM %s WHERE krvna_id = '%s';", "*", "krvna_slika", ksId);
		
		System.out.println(sql);
		
		KrvnaSlika cts = new KrvnaSlika();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}

			while(rset.next()) {
				cts = makeOneCBC(rset);
			}

				
			rset.close();
			stmt.close();
			
			return cts;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	private KrvnaSlika makeOneCBC(ResultSet resultSet) {
		KrvnaSlika krvnaSlika = null;
		try {
			Integer cbc_id = resultSet.getInt(PatientsColumn.krvna_id);
			Integer patientId = resultSet.getInt(PatientsColumn.patientId);
			String leukociti = resultSet.getString(PatientsColumn.leukociti);
			String eritrociti = resultSet.getString(PatientsColumn.eritrociti);
			String inflamacije = resultSet.getString(PatientsColumn.parametarske_inflamacije);

		
			krvnaSlika = new KrvnaSlika(cbc_id, patientId, leukociti, eritrociti, inflamacije);
			return krvnaSlika;
		} catch (SQLException e) {
			return krvnaSlika;
		}
	}
	
	public List<Object> selectAllPatientUV(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "ultra_zvuk", p.getPatientId());
		
		System.out.println(sql);
		
		List<Object> uvs = new ArrayList<Object>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				UltraZvuk a = makeOneUv(rset);
				if( a != null) {
					uvs.add(a);
				}
			}
				
			rset.close();
			stmt.close();
			
			return uvs;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	private UltraZvuk makeOneUv(ResultSet resultSet) {
		UltraZvuk uvPluca = null;
		try {
			Integer uv_id = resultSet.getInt(PatientsColumn.uvId);
			Integer patientId = resultSet.getInt(PatientsColumn.patientId);
			String dubina_izliva = resultSet.getString(PatientsColumn.dubina_izliva);
			String visina_izliva = resultSet.getString(PatientsColumn.visina_izliva);
			String gustina_izliva = resultSet.getString(PatientsColumn.gustina_izliva);
			String mesto_punkcije = resultSet.getString(PatientsColumn.mesto_punkcije);
		
			uvPluca = new UltraZvuk(uv_id, patientId, dubina_izliva, visina_izliva, gustina_izliva, mesto_punkcije);
			return uvPluca;
		} catch (SQLException e) {
			return uvPluca;
		}
	}
	
	
	/** --------------END: Operacije za dopunska ispitivanja ------------------------------- */
	
	/**
	 * 
	 * 
	 * 
	 * */
	/** --------------BEGIN: Operacije za ranije bolesti ------------------------------- */
	private HashMap<Integer, Integer> makeOneRanijaBolest(ResultSet resultSet) {
		HashMap<Integer, Integer> mapa = new HashMap<>();
		try {
			Integer patientId = resultSet.getInt(PatientsColumn.patientIdRanije);
			Integer diagnosisId = resultSet.getInt(PatientsColumn.diagnosisId);

			mapa.put(patientId, diagnosisId);
			return mapa;
		} catch (SQLException e) {
			return null;
		}

	}
	
	/**
	 * 
	 * Ubacivanje u posebnu tabelu za ranije bolesti
	 * 
	 * */
	public void createRanijeBolesti(Integer patientId, Integer resource) throws SQLException{
			
			String template = "INSERT INTO ranije_bolesti (patient_id, diagnosis_id) VALUES (?, ?)";
			
			System.out.println(template);
		
			try {
				PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
				preparedStatement.setInt(1, patientId); 
				preparedStatement.setInt(2, resource);

				
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} catch (SQLException e) {
				throw new SQLException("");
			}
			
		}
	
	public HashMap<Integer,Integer> selectAllRanijeBolesti(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE patient_id = %s;", "*", "ranije_bolesti", p.getPatientId());
		
		System.out.println(sql);
		
		HashMap<Integer, Integer> mapa = new HashMap<>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				System.out.println("Kako se ovo vraca uopste ? " + rset);
				mapa = makeOneRanijaBolest(rset);
				
			}
			
			
			rset.close();
			stmt.close();
			
			return mapa;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	/** --------------END: Operacije za ranije bolesti ------------------------------- */
	
	/** --------------BEGIN: Operacije za dijagnoze ------------------------------- */
	public void createDiagnosis(Diagnosis diagnosis) throws SQLException{
		
		int id = getId("diagnosis");
		String template = "INSERT INTO diagnosis (diagnosis_Id, Id_Pacijenta, Dijagnoza, Datum) VALUES (?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			/*Statement stmt = databaseConnection.createStatement();
			stmt.execute(template);
			
			stmt.close();*/
			
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, diagnosis.getPatientId());
			preparedStatement.setString(3, diagnosis.getDiagnose());
			preparedStatement.setString(4, diagnosis.getDate());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	public List<Diagnosis> selectAllPatientDiagnosis(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "diagnosis", p.getPatientId());
		
		System.out.println(sql);
		
		List<Diagnosis> diagnosis = new ArrayList<Diagnosis>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				Diagnosis d = makeOneDiagnosis(rset);
				if(d != null) {
					diagnosis.add(d);
				}
			}
			
			rset.close();
			stmt.close();
			
			return diagnosis;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	public Diagnosis selectDiagnosis(int diagnosisId) {
		
		String sql = String.format("SELECT %s FROM %s WHERE %s = '%s';", "*", "patient", PatientsColumn.diagnosis_Id, diagnosisId);
		
		System.out.println(sql);
		
		
		try {
			Diagnosis d = null;
			Statement stmt = databaseConnection.createStatement();				
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nevalidan id");
			}
			
			while(rset.next()) {
				d = makeOneDiagnosis(rset);
			}
			
			rset.close();
			stmt.close();
			
			return d;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	private Diagnosis makeOneDiagnosis(ResultSet resultSet) {
		Diagnosis d = null;
		try {
			Integer id = resultSet.getInt(PatientsColumn.diagnosis_Id);
			int patientId = resultSet.getInt(PatientsColumn.patientId);
			String diagnose = resultSet.getString(PatientsColumn.diagnose);
			String date = resultSet.getString(PatientsColumn.date);
			
			d = new Diagnosis(id, patientId, diagnose, date);
			
			return d;
		} catch (SQLException e) {
			return d;
		}
	}
	/** --------------END: Operacije za dijagnoze ------------------------------- */

	/** --------------BEGIN: Operacije za terapije ------------------------------- */
	public void createTherapy(Therapy therapy) throws SQLException{
		
		int id = getId("therapy");
		String template = "INSERT INTO therapy (therapy_id, Id_Pacijenta, Dijagnoza, Terapija, Datum) VALUES (?, ?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			/*Statement stmt = databaseConnection.createStatement();
			stmt.execute(template);
			
			stmt.close();*/
			
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, therapy.getPatientId());
			preparedStatement.setString(3, therapy.getDiagnose());
			preparedStatement.setString(4, therapy.getTherapy());
			preparedStatement.setString(5, therapy.getDate());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
	}
	
	public List<Therapy> selectAllPatientTherapies(Patient p) {
		
		String sql = String.format("SELECT %s FROM %s WHERE Id_Pacijenta = '%s';", "*", "therapy", p.getPatientId());
		
		System.out.println(sql);
		
		List<Therapy> therapies = new ArrayList<Therapy>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				Therapy t = makeOneTherapy(rset);
				if(t != null) {
					therapies.add(t);
				}
			}
			
			
			rset.close();
			stmt.close();
			
			return therapies;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	public Therapy selectTherapy(int therapyId) {
		
		String sql = String.format("SELECT %s FROM %s WHERE %s = '%s';", "*", "therapy", PatientsColumn.therapyId, therapyId);
		
		System.out.println(sql);
		
		
		try {
			Therapy therapy = null;	
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nevalidan id");
			}
			
			while(rset.next()) {
				therapy = makeOneTherapy(rset);
			}
			
			rset.close();
			stmt.close();
			
			return therapy;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	private Therapy makeOneTherapy(ResultSet resultSet) {
		Therapy t = null;
		try {
			Integer id = resultSet.getInt(PatientsColumn.therapyId);
			int patientId = resultSet.getInt(PatientsColumn.patientId);
			String diagnose = resultSet.getString(PatientsColumn.diagnose);
			String therapy = resultSet.getString(PatientsColumn.therapy);
			String date = resultSet.getString(PatientsColumn.date);
			t = new Therapy(id,	patientId, diagnose, therapy, date); 
			
			return t;
		} catch (SQLException e) {
			return t;
		}
	}
	/** --------------END: Operacije za terapije ------------------------------- */
	
	/** --------------BEGIN: Operacije za resurse koji se koriste u aplikaciji ------------------------------- */
	public List<Resources> selectAllParticularResource(String resourceType) {
		String sql = String.format("SELECT %s FROM %s WHERE resource_type = '%s';", "*", "resources", resourceType);
		
		System.out.println(sql);
		
		List<Resources> resources = new ArrayList<Resources>();
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				Resources r = makeOneResource(rset);
				if( r != null) {
					resources.add(r);
				}
			}
				
			rset.close();
			stmt.close();
			
			return resources;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	public Resources selectResourceById(Integer resourceId) {
		String sql = String.format("SELECT %s FROM %s WHERE resource_id = %s;", "*", "resources", resourceId);
		
		System.out.println(sql);
		Resources r = null;
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			ResultSet rset = stmt.executeQuery(sql);

			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				r = makeOneResource(rset);

			}
				
			rset.close();
			stmt.close();
			
			return r;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
	
	}
	
	private Resources makeOneResource(ResultSet resultSet) {
		Resources resource = null;
		try {
			Integer resourceId = resultSet.getInt(PatientsColumn.resourceId);
			String resourceName = resultSet.getString(PatientsColumn.resourceName);
			String resourceType = resultSet.getString(PatientsColumn.resourceType);
			
			resource = new Resources(resourceId, resourceName, resourceType);
			return resource;
		} catch (SQLException e) {
			return resource;
		}

	}

	/** --------------END: Operacije za resurse koji se koriste u aplikaciji ------------------------------- */
	
	/** --------------BEGIN: Operacije za additionalexamination ------------------------------- */
	
	
	public AdditionalExamination selectAdditionalExamination(int patientId) {
		
		String sql = String.format("SELECT %s FROM %s WHERE %s = '%s';", "*", "additional_examination", PatientsColumn.patientId, patientId);
		
		System.out.println(sql);
		
		
		try {
			AdditionalExamination ae = null;

			
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nevalidan id");
			}
			
			while(rset.next()) {
				ae = makeOneAdditionalExamination(rset);
			}
			
			
			rset.close();
			stmt.close();
			
			return ae;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return null;
		}
		
	}
	
	private AdditionalExamination makeOneAdditionalExamination(ResultSet resultSet) {
		AdditionalExamination ae = null;
		try {
			Integer id = resultSet.getInt(PatientsColumn.additional_examination_id);
			Integer id_pacijenta = resultSet.getInt(PatientsColumn.id_pacijenta);
			Integer id_ct = resultSet.getInt(PatientsColumn.id_ct);
			Integer id_rtg = resultSet.getInt(PatientsColumn.id_rtg);
			Integer id_ks = resultSet.getInt(PatientsColumn.id_ks);
			Integer id_uz = resultSet.getInt(PatientsColumn.id_uz);
			String diagnosis = resultSet.getString(PatientsColumn.diagnosis);
			
			ae = new AdditionalExamination(id, id_pacijenta, id_rtg, id_ct, id_ks, id_uz, diagnosis);
			
			
			return ae;
		} catch (SQLException e) {
			return ae;
		}
	}
	
	public void updateAdditionalExamination(AdditionalExamination ae) throws SQLException {
		String template = "UPDATE additional_examination SET id_pacijenta = ?, id_ct = ?, id_ks = ?, id_rtg = ?, id_uz = ?, diagnosis = ? WHERE additional_examination_id = ?";

		System.out.println(template);
		PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
		
		try {
			preparedStatement.setInt(7, ae.getAnamnesisId());
			preparedStatement.setInt(1, ae.getPatientId());
			preparedStatement.setInt(2, ae.getIdCt());
			preparedStatement.setInt(3, ae.getIdKs());
			preparedStatement.setInt(4,ae.getIdRtg());
			preparedStatement.setInt(5, ae.getIdUz());
			preparedStatement.setString(6, ae.getDiagnosis());

			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("");
		} finally {
			preparedStatement.close();
		}
	}
	
public void createAdditionalExamination(AdditionalExamination ae) throws SQLException{
		
		int id = getId("additional_examination");
		String template = "INSERT INTO additional_examination (additional_examination_id, id_pacijenta, id_ct, id_ks, id_rtg, id_uz, diagnosis) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		System.out.println(template);
	
		try {
			/*Statement stmt = databaseConnection.createStatement();
			stmt.execute(template);
			
			stmt.close();*/
			
			PreparedStatement preparedStatement = databaseConnection.prepareStatement(template);
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, ae.getPatientId());
			preparedStatement.setInt(3, ae.getIdCt());
			preparedStatement.setInt(4, ae.getIdKs());
			preparedStatement.setInt(5, ae.getIdRtg());
			preparedStatement.setInt(6, ae.getIdUz());
			preparedStatement.setString(7, ae.getDiagnosis());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
	}

}
