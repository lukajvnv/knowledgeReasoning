package com.application.medCareApplication.connector;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

import com.application.medCareApplication.utils.handler.DatabaseHandler;

import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

public class CsvConnector implements Connector {
	
	private DatabaseHandler databaseHandler;
	
	@Override
	public Collection<CBRCase> retrieveAllCases() {
		LinkedList<CBRCase> cases = new LinkedList<CBRCase>();
		
		
		try {
			//BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/example.csv")));
			BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("data/zadatak1.csv")));
			if (br == null)
				throw new Exception("Error opening file");

			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#") || (line.length() == 0))
					continue;
				String[] values = line.split(";");

				CBRCase cbrCase = new CBRCase();

			/*	OpisNekretnine nekretnina = new OpisNekretnine();
				
				// TODO konektor koji ucitava i ubacuje u bazu slucajeva
				nekretnina.setGodinaTransakcije(Integer.parseInt(values[0]));
				nekretnina.setStarost(Integer.parseInt(values[1]));
				nekretnina.setUdaljenost(Integer.parseInt(values[2]));
				nekretnina.setBrojProdavnica(Integer.parseInt(values[3]));
				nekretnina.setCenaKvadrata(Integer.parseInt(values[4]));
				
				
				cbrCase.setDescription(nekretnina);*/
			/*	Letovanje letovanje = new Letovanje();
				Smestaj smestaj = new Smestaj();
				smestaj.setBrojOsoba(Integer.parseInt(values[3]));
				smestaj.setTrajanje(Integer.parseInt(values[6]));
				
				// TODO konektor koji ucitava i ubacuje u bazu slucajeva
				letovanje.setTip(values[1]);
				letovanje.setCena(Integer.parseInt(values[2]));
				letovanje.setSmestaj(smestaj);
				letovanje.setDestinacija(values[4]);
				letovanje.setVrstaPrevoza(values[5]);*/
				
				//filmovi
			//	Filmovi filmovi = new Filmovi();

				
				// TODO konektor koji ucitava i ubacuje u bazu slucajeva
			/*	filmovi.setPol(values[1]);
				filmovi.setStarost(Integer.parseInt(values[2]));
				filmovi.setOcena(Integer.parseInt(values[4]));
				filmovi.setZanr(values[8]);
				filmovi.setGodiste(Integer.parseInt(values[7]));

				cbrCase.setDescription(filmovi);*/
				
				
				cases.add(cbrCase);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cases;
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