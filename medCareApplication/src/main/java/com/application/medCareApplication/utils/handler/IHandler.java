package com.application.medCareApplication.utils.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.application.medCareApplication.model.Patient;

/**
 * Apstrakcija konkretnih rukovaoca za manipulisanje razli�itim tipovima resursa.
 */

public interface IHandler {

	/**
	 * Dodavanje novog entiteta u odre�eni resurs
	 * @param table - tabela u koju se dodaje entitet 
	 * @param row - model reda koji repzentuje entitet koji je potrebno dodati
	 * @throws SQLException - nemogu�nost upisa zadate torke �eljenu tabelu 
	 */
	/*public void create(Table table, TableModelRow row) throws SQLException;
	
	*//**
	 * U�itavanje svih podataka resursa
	 * @param table - tabela �iji se podaci is�itavaju 
	 * @return - kolekcija TableModelRow objekata koji predstavljaju torke
	 * @throws SQLException - neuspelo �itanje tabele iz baze, 
	 * tabela ne postoji ili se obele�ja ne poklapaju
	 *//*
	public ArrayList<TableModelRow> read(Table table) throws SQLException;
	
	*//**
	 * A�uriranje odre�ene torke.
	 * @param table - tabela u kojoj se vr�i a�uriranje
	 * @param row - torka koja se a�urira
	 * @throws SQLException - neuspelo a�uriranje entiteta iz baze, 
	 * tabela ne postoji ili se obele�ja ne poklapaju
	 *//*
	public void update(Table table, TableModelRow row) throws SQLException ;
	
	*//**
	 * Brisanje odredjene torke
	 * @param table - tabela iz koje se vrsi brisanje
	 * @param keyValues - vrednosti obelezja koji jedinstveno odredjuju odredjenu torku
	 * @throws SQLException - neuspelo brisanje entiteta iz baze, 
	 * tabela ne postoji ili se obele�ja ne poklapaju
	 *//*
	public void delete(Table table, Map<String, String> keyValues) throws SQLException;

	*//**
	 * Projekcija torki na osnovu obele�ja originLabel
	 * @param originTable - tabela iz koje se dobavljaju entiteti
	 * @param originLabel - obelezje po kom se vrsi projekcija
	 * @return - vraca kolekciju torki koje sadr�e vrednosti za originLabel
	 *//*
	public Set<String> select(Table originTable, Label originLabel);

	*//**
	 * 
	 * @param table - tabela koja sadrzi obelezja
	 * @param rangeSearch - za svako obelezje sadrzi opseg koji treba primeniti na upitu
	 * @return - niz torki koje su zadovoljile uslove pretrage <br>
	 * <b>null</b> - u slu�aju izuzetka
	 * @throws SQLException - problem u komunikaciji sa bazom i nemogu�nost izvr�enja upita po zadatim kriterijumima
	 *//*
	public ArrayList<TableModelRow> search(Table table, Map<String, RangeSearch> rangeSearch) throws SQLException;*/
	

	/**
	 * Metoda koja inicijalizuje neophodne komponente za funkcionisanje handlera
	 * @throws SQLException - neuspe�no povezivanje sa skladi�tem
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void connect() throws SQLException, FileNotFoundException, IOException;
	
	/**
	 * Zatvaranje kreiranih komponenti neophodnih za handler
	 * @throws SQLException - nemogu�nost zatvaranja konekcije sa bazom
	 */
	public void close() throws SQLException;
	
}
