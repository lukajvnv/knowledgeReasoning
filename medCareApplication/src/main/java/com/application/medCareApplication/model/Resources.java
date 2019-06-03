package com.application.medCareApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Resources {

	@Id
	@Column
	private int resourceId;

	@Column
	private String resourceName;
	
	@Column
	private String resourceType;
	
	public Resources() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Resources(int resourceId, String resourceName, String resourceType) {
		super();
		this.resourceId = resourceId;
		this.resourceName = resourceName;
		this.resourceType = resourceType;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	@Override
	public String toString() {
		return "Resources [resourceId=" + resourceId + ", resourceName=" + resourceName + ", resourceType="
				+ resourceType + "]";
	}
	
	
}
