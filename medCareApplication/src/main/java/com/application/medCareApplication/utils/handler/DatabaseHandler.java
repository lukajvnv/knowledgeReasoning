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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.application.medCareApplication.model.Patient;
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
		
		int id = getId();
		String template = "INSERT INTO patient (Id, Ime, Prezime, Jmbg, Telefon, Datum_rodjenja, Adresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
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
				patients.add(makeOnePatient(rset));
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
	
	private int getId () {
		String sql = String.format("SELECT %s FROM %s;", "*", "patient");
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
	
	/*

	@Override
	public ArrayList<TableModelRow> read(Table table) throws SQLException{
		String sql = String.format("select * from %s", table.getId());
		Statement stmt;
		ArrayList<TableModelRow> modelRows = new ArrayList<TableModelRow>();
		
		try {
			stmt = databaseConnection.createStatement();			
			
			ResultSet rset = stmt.executeQuery(sql);
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				TableModelRow row = addNewRow(rset, table);
				if(row != null){
					modelRows.add(row);
				}
			}
			
			rset.close();
			stmt.close();
	
			return modelRows;
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new SQLException(Utils.dict("handler.read.error") + e.getMessage());
		}
	}
	
	
	
	*//**
	 * Formiranje reda u tabeli koji prezentuje TableModelRow klasa na osnovu vrednosti koji su dobijene u result
	 * @param result - rezultat upita, trenutno aktuelan red
	 * @param table - tabela iz koje se dobavljaju podaci
	 * @return - red u tabeli prezentovan TableModelRow klasom
	 *//*
	private TableModelRow addNewRow(ResultSet result, Table table){
		TableModelRow row = new TableModelRow();
		
		Set<Entry<String, Label>> entrySets = table.getLabels().entrySet();
		for(Entry<String, Label> set : entrySets){
			try {
				String key = set.getKey();
				Label label = set.getValue();
				
				String dbValue = result.getString(key);
				if(dbValue == null){
					dbValue = "NULL";
				} else {
					Types type = label.getDomain().getDataType();
					if(type == Types.BOOLEAN){
						dbValue = (dbValue.equals("0")) ? Utils.dict("no") : Utils.dict("yes");
					} else if (type == Types.DOUBLE){
						dbValue = dbValue.replace('.', Utils.dict("sep").charAt(0));
					} else if (type == Types.DATE) {
						dbValue = Utils.parseDate(dbValue);
					}
				}
				row.addColumnValue(key, dbValue);
			} catch (SQLException e) {
				return null;
			}
		}
				
		return row;
	}
	
	@Override
	public void delete(Table table, Map<String, String> keyValues) throws SQLException{	
		String sql = String.format("DELETE FROM %s WHERE ", table.getId());
		StringBuilder st = new StringBuilder(sql);

		addConditionalPartOfStatement(st, keyValues);
		st.append(";");
				
		try {
			Statement stmt = databaseConnection.createStatement();					
			stmt.execute(st.toString());
			
		} catch (SQLException s) {
			throw new SQLException(Utils.dict("handler.delete.error") + s.getMessage());
		} catch (Exception e){
			
		}
		
	}

	@Override
	public void update(Table table, TableModelRow row) throws SQLException {
		String sql = String.format("UPDATE %s SET ", table.getId());
		StringBuilder st = new StringBuilder(sql);
		
		addValuesOfStatement(st, row, table.getLabels());
		st.append(" WHERE ");
		Map<String, String> keyValues = row.getKeyValues(table.getKey());
		addConditionalPartOfStatement(st, keyValues);
		st.append(";");
		
		System.out.println(st);
		
		try {
			Statement stmt = databaseConnection.createStatement();					
			stmt.execute(st.toString());			
		} catch (SQLException s) {
			throw new SQLException(Utils.dict("handler.update.error") + s.getMessage());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<TableModelRow> search(Table table, Map<String, RangeSearch> rangeSearch) throws SQLException {
		String sql = String.format("SELECT * FROM %s WHERE ", table.getId());
		System.out.println("SQL : " + sql);
		StringBuilder st = new StringBuilder(sql);
		
		// Ako je sve ignorisano
		if (rangeSearch.size() == 0) {
			st = new StringBuilder("SELECT * FROM " + table.getId());
		}
		
		addConditionalPartOfStatementForSearch(st, rangeSearch, table);
		st.append(";");
		
		Statement stmt;
		ArrayList<TableModelRow> modelRows = new ArrayList<TableModelRow>();

		
		try {
			stmt = databaseConnection.createStatement();			
			
			System.out.println(st);
			
			ResultSet rset = stmt.executeQuery(st.toString());
			
			if(!rset.isBeforeFirst()) {
				System.out.println("Nema podataka");
			}
			
			while(rset.next()) {
				TableModelRow row = addNewRow(rset, table);
				if(row != null){
					modelRows.add(row);
				}
			}
			
			rset.close();
			stmt.close();
			
			return modelRows;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	*//**
	 * Kreiranje vise uslova upita koji svi moraju biti ispunjeni
	 * @param st - upit koji se dora�uje
	 * @param filterParams - obele�ja koja sa�injavaju uslove po kojima se vr�i filtriranje
	 *//*
	private void addConditionalPartOfStatement(StringBuilder st, Map<String, String> filterParams){
		int i = 0;
		for(Entry<String, String> param : filterParams.entrySet()){
			st.append(param.getKey()).append("='").append(param.getValue()).append("' ");
			if(++i < filterParams.size()){
				st.append(" and ");
			}
		}
	}
	
	*//**
	 * Metoda koja formira deo upita koji sadrzi nove azurirane vrednosti koje ce biti unete u tabelu za vec postojecu torku
	 * @param st - StringBuilder klasa koja kreira upit
	 * @param row - torka sa svojim obelezjima i njihovim vrednostima
	 * @param labels - obelezja tabele
	 *//*
	private void addValuesOfStatement(StringBuilder st, TableModelRow row, Map<String, Label> labels){
		int i = 0;
		int columnsNumber = row.getRowValues().size();
		for(Entry<String, String> rowColumnValue : row.getRowValues().entrySet()){
			String value = rowColumnValue.getValue();
			if (value.trim().equals("")) {
				value = "NULL";
			} else {
				Types type = labels.get(rowColumnValue.getKey()).getDomain().getDataType();
					
				if (type == Types.DOUBLE) {
					value = value.replace(Utils.dict("sep").charAt(0), '.');
				} else if (type == Types.DATE) {
					// TODO:
				}
				
				value = "\'" + value + "\'";
			}
			st.append(rowColumnValue.getKey()).append("=").append(value);
			if(++i < columnsNumber){
				st.append(", ");
			}
		}
	}
	
	*//**
	 * 
	 * @param st - StringBuilder klasa koja kreira upit
	 * @param rangeSearch - Kolekcija objekata RangeSearch {@link utils.RangeSearch}
	 * @param table - Objekat klase Table
	 *//*
	private void addConditionalPartOfStatementForSearch(StringBuilder st, Map<String, RangeSearch> rangeSearch, Table table){
		int i = 0;
		for( Entry<String, RangeSearch> entry : rangeSearch.entrySet()){
			String key = entry.getKey();
			String lowerLimit = entry.getValue().getLowerLimit();
			String upperLimit = entry.getValue().getUpperLimit();
			Label label = table.getLabels().get(key);
			switch(label.getDomain().getDataType()){
			case VARCHAR:
				st.append(key).append(" LIKE '%").append(lowerLimit).append("%'");
				break;
			case BOOLEAN:
				st.append(key).append("='").append(lowerLimit).append("' ");
				break;
			case DOUBLE:
				lowerLimit = lowerLimit.replace(Utils.dict("sep").charAt(0), '.');
				upperLimit = upperLimit.replace(Utils.dict("sep").charAt(0), '.');
			case INTEGER:
				st.append(key).append(" >= '").append(lowerLimit).append("' AND ").append(key).append(" <= '").append(upperLimit).append("'");
				break;
			case DATE:
				st.append(key).append(" >= '").append(lowerLimit).append("' AND ").append(key).append(" <= '").append(upperLimit).append("'");
				break;
			case CHAR:
				st.append(key).append(" LIKE '%").append(lowerLimit).append("%'");
			default:
				break;
			}
			if(++i < rangeSearch.size()){
				st.append(" and ");
			}
		}
	}*/
	
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
