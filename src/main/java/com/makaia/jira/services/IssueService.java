package com.makaia.jira.services;

import org.json.JSONObject;

import com.makaia.jira.pojos.CreateIssuePayload;
import com.makaia.jira.pojos.Fields;
import com.makaia.jira.pojos.IssueType;
import com.makaia.jira.pojos.Project;

import io.restassured.http.ContentType;

public class IssueService extends Jira {
	
	private static final String RESOURCE = "issue";
	
	public IssueService() {
		requestBuilder = globalRequest();
	}
	
	public IssueService createBugIssue(String summary) {
		CreateIssuePayload payload = new CreateIssuePayload();
		Fields fields = new Fields();
		IssueType issueType = new IssueType();
		Project project = new Project();
		project.setKey("TES");
		issueType.setName("Bug");
		fields.setSummary(summary);
		fields.setProject(project);
		fields.setIssuetype(issueType);
		payload.setFields(fields);
		response = restAssured.post(requestBuilder.setContentType(ContentType.JSON).build(), RESOURCE, payload);		
		return this;
	}
	
	public IssueAttachementService extractIssueId() {
		String responseBody = response.getBody();
		JSONObject json = new JSONObject(responseBody);
		return new IssueAttachementService(json.getString("id"));
	}

}