package com.application.medCareApplication.utils.components;

import java.util.HashMap;
import java.util.Map;

public class ReasoningAnswerData {
	
	private String output;
	private Map<String, String> answerVariables = new HashMap<String, String>();
	
	public ReasoningAnswerData(String output, Map<String, String> answerVariables) {
		super();
		this.output = output;
		this.answerVariables = answerVariables;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public Map<String, String> getAnswerVariables() {
		return answerVariables;
	}
	public void setAnswerVariables(Map<String, String> answerVariables) {
		this.answerVariables = answerVariables;
	}
	
	@Override
	public String toString() {
		return getOutput();
	}
	
	
	

}
