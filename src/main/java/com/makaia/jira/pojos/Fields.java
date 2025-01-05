package com.makaia.jira.pojos;

public class Fields {

	private String summary;
	private IssueType issuetype;
	private Project project;

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public IssueType getIssuetype() {
		return issuetype;
	}

	public void setIssuetype(IssueType issuetype) {
		this.issuetype = issuetype;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}