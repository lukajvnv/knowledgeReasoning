package com.application.medCareApplication.utils;

public class QueryVariableWrapper {
	
		public enum QueryAnswerType {
			RESOURCE, LITERAL
		}
	
	
		private QueryAnswerType queryAnswerType;
		private String queryAnswerName;
		
	
		public QueryVariableWrapper(QueryAnswerType queryAnswerType, String queryAnswerName) {
			super();
			this.queryAnswerType = queryAnswerType;
			this.queryAnswerName = queryAnswerName;
		}
		public QueryAnswerType getQueryAnswerType() {
			return queryAnswerType;
		}
		public void setQueryAnswerType(QueryAnswerType queryAnswerType) {
			this.queryAnswerType = queryAnswerType;
		}
		public String getQueryAnswerName() {
			return queryAnswerName;
		}
		public void setQueryAnswerName(String queryAnswerName) {
			this.queryAnswerName = queryAnswerName;
		}
	
}
