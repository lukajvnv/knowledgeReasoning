package com.application.medCareApplication.utils.handler;

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

import com.application.medCareApplication.model.Anamnesis;
import com.application.medCareApplication.model.CTpluca;
import com.application.medCareApplication.model.KrvnaSlika;
import com.application.medCareApplication.model.Patient;
import com.application.medCareApplication.model.PhysicalExamination;

import com.application.medCareApplication.model.RTGPluca;
import com.application.medCareApplication.model.UltraZvuk;

import com.application.medCareApplication.model.Resources;

import com.application.medCareApplication.utils.PatientsColumn;

/**
 * 
 * Konkretizacija Ihandler interfejsa koja rukuje sa podacima dobijenim sa skladi�ta podataka.
 *
 */
public class DatabaseHandler implements IHandler {

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
	
	/*public DatabaseHandler(String address, String dbName, String username, String password) throws SQLException{
		try {
			connect(address, dbName, username, password);
			log.log(Level.INFO, "Connection with database established");
		} catch (SQLException e) {
			log.log(Level.SEVERE, "Failed to connect with database");
			throw new SQLException();
		}
	}*/
	
	@Override
	public void connect() throws SQLException, FileNotFoundException, IOException{
		Properties p = new Properties();
		p.load(new FileInputStream("src/main/resources/application.properties"));
		
		String username = p.getProperty("spring.datasource.username");
		String password = p.getProperty("spring.datasource.password");
		
		databaseConnection = DriverManager.getConnection(DB_URL, username, password);
	}
		
	@Override
	public void close() throws SQLException{
		if(databaseConnection != null)
			databaseConnection.close();
		log.log(Level.INFO, "Closing database connection");
	}
	
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
	
	private int getId(String tableName) {
		String sql = String.format("SELECT %s FROM %s;", "*", tableName);
		StringBuilder st = new StringBuilder(sql);
		
		System.out.println(st);
		
		try {
			Statement stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(st.toString());
						
			rset.last();
			int size = rset.getRow();
			
			/*int size =0;
			if (rs != null) 
			{
			  rs.last();    // moves cursor to the last row
			  size = rs.getRow(); // get row id 
			}*/

			rset.close();
			stmt.close();
			
			return size + 1;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			return -1;
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
	
	public void createPhysicalExamination(PhysicalExamination ex) throws SQLException{
		
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
		} catch (SQLException e) {
			throw new SQLException("");
		}
		
	}
	
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
	
	public void createAnamnesis(Anamnesis anamnesis) throws SQLException{
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
			
			anamnesis = new Anamnesis(anamnesisId, patientId, smoking, alcohol, employed, workingCondition, livingPlace, livingObject, pet,additionalExamination);
			return anamnesis;
		} catch (SQLException e) {
			return anamnesis;
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
			
			pe = new PhysicalExamination(physicalExaminationId, patientId, bodyTemperature, respiratorySound, respiratoryNoise,
					additionalExamination);
			return pe;
		} catch (SQLException e) {
			return pe;
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
	
	/*
	 * 
	 * Metoda koja kreira krvnu sliku i ubacuje u bazu!
	 * 
	 * */
	public void createKrvnaSlika(KrvnaSlika krvna) throws SQLException{
		int id = getId("krvna_slika");
		String template = "INSERT INTO krvna_slika (id, id_pacijenta, leukociti, eritrociti, parametarske_inflamacije) VALUES (?, ?, ?, ?, ?)";
		
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
		
	}
	

	/*
	 * 
	 * Metoda koja kreira rtg pluca i ubacuje u bazu!
	 * 
	 * */
	public void createRTGPluca(RTGPluca rtg) throws SQLException{
		int id = getId("rtg_pluca");
		String template = "INSERT INTO rtg_pluca (id, id_pacijenta, rtg, lezije) VALUES (?, ?, ?, ?)";
		
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
		
	}
	
	/*
	 * 
	 * Metoda koja kreira ct pluca i ubacuje u bazu!
	 * 
	 * */
	public void createCTPluca(CTpluca ct) throws SQLException{
		int id = getId("ct_pluca");
		String template = "INSERT INTO ct_pluca (id, id_pacijenta, ct) VALUES (?, ?, ?)";
		
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
		
	}
	
	/*
	 * 
	 * Metoda koja kreira ultrazvuk i ubacuje u bazu!
	 * 
	 * */
	public void createUltraZvuk(UltraZvuk uz) throws SQLException{
		int id = getId("ultra_zvuk");
		String template = "INSERT INTO ultra_zvuk (id, id_pacijenta, dubina_izliva,visina_izliva,gustina_izliva,mesto_punkcije) VALUES (?, ?, ?, ?, ?, ?)";
		
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
	}
	
	/**
	 * 
	 * 
	 * 
	 * */
	
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

	/**
	 * Dobavljanje objekat Connection klase
	 * @return - objekat koji pretdstavlja konekciju sa bazom podataka
	 */
	public Connection getDatabaseConnection() {
		return databaseConnection;
	}

	/**
	 * Postavljanje konekcije ka bazi sa objektom Connection klase
	 * @param databaseConnection - objekat koji pretdstavlja konekciju sa bazom podataka
	 */
	public void setDatabaseConnection(Connection databaseConnection) {
		this.databaseConnection = databaseConnection;
	}

}
