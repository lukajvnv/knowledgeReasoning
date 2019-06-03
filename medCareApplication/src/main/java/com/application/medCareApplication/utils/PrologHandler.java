package com.application.medCareApplication.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ResourceUtils;

import com.ugos.jiprolog.engine.JIPEngine;
import com.ugos.jiprolog.engine.JIPQuery;
import com.ugos.jiprolog.engine.JIPTerm;
import com.ugos.jiprolog.engine.JIPVariable;

public class PrologHandler {

	private JIPEngine prologEngine;
	
	public PrologHandler() {
		this.prologEngine = new JIPEngine();
	}
	
	public String findResult(String fileName, String queryText) throws Exception{
		File file = null;
		String answer = "";
		try {
			file = ResourceUtils.getFile("classpath:prologResources/" + fileName);
			this.prologEngine.consultFile(file.getPath());
			JIPQuery query = this.prologEngine.openSynchronousQuery(queryText);
			
			JIPTerm solution;
			int i = 0;
			while ( (solution = query.nextSolution()) != null) {
				++i;
				System.out.println("solution: " + solution);
				for (JIPVariable var: solution.getVariables()) {
					System.out.println(var.getName() + "=" + var.getValue());
					answer = var.getName() + "=" + var.getValue();
				}
				if( i > 1) {
					System.out.println("Vise resenja!!!");
				}
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			this.prologEngine.closeAllQueries();
		}
		return (answer.equals("")) ? "Nema podataka" : answer;
	}
	
	public List<String> findResults(String fileName, String queryText) throws Exception{
		File file = null;
		List<String> answer = new ArrayList<String>();
		try {
			file = ResourceUtils.getFile("classpath:prologResources/" + fileName);
			this.prologEngine.consultFile(file.getPath());
			JIPQuery query = this.prologEngine.openSynchronousQuery(queryText);
			
			JIPTerm solution;
			String oneAnswer = "";
			int i = 0;
			while ( (solution = query.nextSolution()) != null) {
				++i;
				System.out.println("solution: " + solution);
				StringBuilder stBuilder = new StringBuilder(oneAnswer);
				for (JIPVariable var: solution.getVariables()) {
					System.out.println(var.getName() + "=" + var.getValue());
					stBuilder.append(var.getName() + " = " + var.getValue());
				}
				if( i > 1) {
					System.out.println("Vise resenja!!!");
				}
				//answer.add(stBuilder.toString());
				answer.add(solution.toString());
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			this.prologEngine.closeAllQueries();
		}
		return answer;
	}
}
