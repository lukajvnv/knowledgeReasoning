package com.application.medCareApplication.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.util.ResourceUtils;

import com.application.medCareApplication.utils.QueryVariableWrapper.QueryAnswerType;

public class RDFHandler {

	private Model model;
	
	public RDFHandler(String fileName) {
		this.model = loadInfo(fileName);
	}
	
	private  Model loadInfo(String fileName) {
		Model model = ModelFactory.createDefaultModel();
		File file = null;
		InputStream is = null;
		try {
			file = ResourceUtils.getFile("classpath:rdfResources/" + fileName);
			is = new FileInputStream(file);
			RDFDataMgr.read(model, is, Lang.TTL);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		System.out.println("----- loaded model -----" + fileName);
		return model;
	}
	
	public List<String> findMedicaments(String diagnosis) {
		// SELECT medCare
		List<QueryVariableWrapper> list3 = new ArrayList<QueryVariableWrapper>();
		list3.add(new QueryVariableWrapper(QueryAnswerType.LITERAL, "imeLeka"));
		list3.add(new QueryVariableWrapper(QueryAnswerType.LITERAL, "brojPreskripcija"));
		String q = "PREFIX medCare: <http://www.ftn.uns.ac.rs/medCare#> "
				+ "PREFIX xsd:   <http://w3.org/2001/XMLSchema#> "
				+ "SELECT ?imeLeka (count(?imeLeka) as ?brojPreskripcija) "
				+ "WHERE { "
				+ "    ?case medCare:diagnose \"%DIAGNOSE%\"^^xsd:string ; "
				+ "          medCare:medicament ?imeLeka . " 
				+ "} "
				+ "group by ?imeLeka "  
				+ "order by desc(?brojPreskripcija) ";
		String que = q.replace("%DIAGNOSE%", diagnosis);
		return executeQuery(que, list3);
	}
	
	private List<String> executeQuery(String queryString, List<QueryVariableWrapper> list) {
		List<String> answer = new ArrayList<String>();
		
		System.out.println("Nakon upita:");
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect() ;
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution() ;
			answer.add(solution.toString());
			/*String a = "";
			for(QueryVariableWrapper qV : list) {
				if(qV.getQueryAnswerType() == QueryAnswerType.LITERAL) {
					a += solution.getLiteral(qV.getQueryAnswerName()).getString();
				}else {
					a += solution.getResource(qV.getQueryAnswerName()).getURI();
				}
				a += " ";
			}*/
		}
		
		return answer;
	}
	
	private void printStatements(Model model) {
		StmtIterator iter = model.listStatements();
		while (iter.hasNext()) {
		    Statement stmt = iter.nextStatement();
		    Resource subject = stmt.getSubject();
		    Property predicate = stmt.getPredicate();
		    RDFNode object = stmt.getObject();

		    System.out.print(subject.toString() + " ");
		    System.out.print(predicate.toString() + " ");
		    if (object instanceof Resource) {
		       System.out.print(object.toString());
		    } else {  // object is a literal
		        System.out.print(" \"" + object.toString() + "\"");
		    }
		    System.out.println(" .");
		}
	}
}
