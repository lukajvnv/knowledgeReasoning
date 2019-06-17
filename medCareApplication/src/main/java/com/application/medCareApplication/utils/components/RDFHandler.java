package com.application.medCareApplication.utils.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.util.ResourceUtils;

import com.application.medCareApplication.utils.components.QueryVariableWrapper.QueryAnswerType;

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
	
	public List<String> findPreventionExamination(String age, String personalAnamnesis, String earlyAnamnesis,  String familyAnamnesis) {
		List<QueryVariableWrapper> list = new ArrayList<QueryVariableWrapper>();
		list.add(new QueryVariableWrapper(QueryAnswerType.LITERAL, "imePreventivnogPregleda"));
		list.add(new QueryVariableWrapper(QueryAnswerType.LITERAL, "brojPreskripcija"));
	
		String queryText = createQuery(personalAnamnesis, earlyAnamnesis, familyAnamnesis);
		
		return executeQuery(queryText, list);
	}
	
	/**
	 * Metoda za dinamicko kreiranje upita u zavisnosti da li su uneti sve moguci parametri upita
	 * @param personalAnamnesis odabrana licna anamneza
	 * @param earlyAnamnesis odabrana ranije anamneza
	 * @param familyAnamnesis odabrana porodicna anamneza
	 * @return
	 */
	private String createQuery(String personalAnamnesis, String earlyAnamnesis,  String familyAnamnesis) {
		String oneQueryParamTemplete = "medCare:%s \"%s\" ; ";
		
		String queryTemplate = "PREFIX medCare: <http://www.ftn.uns.ac.rs/medCare#> "
				+ "PREFIX xsd:   <http://w3.org/2001/XMLSchema#> "
				+ "SELECT ?imePreventivnogPregleda (count(?imePreventivnogPregleda) as ?brojPreskripcija) "
				+ "WHERE { "
				+ "    ?case (%QUERYPARAM%) "
				+ "			 medCare:preventExamination ?imePreventivnogPregleda. "
				+ "} "
				+ "group by ?imePreventivnogPregleda "  
				+ "order by desc(?brojPreskripcija) ";
		
		StringBuilder stBuilder = new StringBuilder();
		
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("personalAnamnesis", personalAnamnesis);
		queryParams.put("earlyAnamnesis", earlyAnamnesis);
		queryParams.put("familyAnamnesis", familyAnamnesis);
		for(Entry<String, String> e : queryParams.entrySet()) {
			if(!(e.getValue().equals("*"))) {
				//String text = "medCare:" + e.getKey() + " " + e.getValue() + " ; ";
				stBuilder.append(String.format(oneQueryParamTemplete, e.getKey(), e.getValue()));
			}
		}
		
		
		return queryTemplate.replace("(%QUERYPARAM%)", stBuilder.toString());
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
	
	public List<ReasoningAnswerData> findMedicamentsEncapsulated(String diagnosis) {
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
		return executeQueryEncapsulated(que, list3);
	}
	
	public List<ReasoningAnswerData> findPreventionExaminationEncapsulated(String age, String personalAnamnesis, String earlyAnamnesis,  String familyAnamnesis) {
		List<QueryVariableWrapper> list = new ArrayList<QueryVariableWrapper>();
		list.add(new QueryVariableWrapper(QueryAnswerType.LITERAL, "imePreventivnogPregleda"));
		list.add(new QueryVariableWrapper(QueryAnswerType.LITERAL, "brojPreskripcija"));
	
		String queryTemplate = "PREFIX medCare: <http://www.ftn.uns.ac.rs/medCare#> "
				+ "PREFIX xsd:   <http://w3.org/2001/XMLSchema#> "
				+ "SELECT ?imePreventivnogPregleda (count(?imePreventivnogPregleda) as ?brojPreskripcija) "
				+ "WHERE { "
				+ "    ?case medCare:personalAnamnesis \"%s\" ; "
				+ "          medCare:earlyAnamnesis \"%s\" ; "
				+ "          medCare:familyAnamnesis \"%s\" ;"
				+ "			 medCare:preventExamination ?imePreventivnogPregleda. "
				+ "} "
				+ "group by ?imePreventivnogPregleda "  
				+ "order by desc(?brojPreskripcija) ";
		String queryText = String.format(queryTemplate, personalAnamnesis, earlyAnamnesis, familyAnamnesis);
		
		return executeQueryEncapsulated(queryText, list);
	}
	
	
	private List<ReasoningAnswerData> executeQueryEncapsulated(String queryString, List<QueryVariableWrapper> list) {
		List<ReasoningAnswerData> answer = new ArrayList<ReasoningAnswerData>();
		
		System.out.println("Nakon upita:");
		Query query = QueryFactory.create(queryString) ;
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet results = qexec.execSelect() ;
		while (results.hasNext()) {
			QuerySolution solution = results.nextSolution() ;
			
			//doterivanje ispisa
			String output = "";
			String variable = "";
			Map<String, String> answerVariables = new HashMap<String, String>();
			for(QueryVariableWrapper qV : list) {
				if(qV.getQueryAnswerType() == QueryAnswerType.LITERAL) {
					variable = solution.getLiteral(qV.getQueryAnswerName()).getString();
					output += variable;
					answerVariables.put(qV.getQueryAnswerName(), variable);
				}else {
					variable = solution.getResource(qV.getQueryAnswerName()).getURI();
					output += variable;
					answerVariables.put(qV.getQueryAnswerName(), variable);
				}
				output += " ";
			}
			answer.add(new ReasoningAnswerData(solution.toString(), answerVariables));
		}
		
		return answer;
	}
	
	/*private void printStatements(Model model) {
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
	}*/
}
