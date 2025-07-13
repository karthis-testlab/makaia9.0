package com.makaia.servicenow.e2e.tests;

import org.testng.annotations.Test;

import static com.makaia.general.utils.PropertiesHandler.*;

import com.makaia.serivcenow.api.services.IncidentSerivce;
import com.makaia.servicenow.ui.pages.LoginPage;
import com.makaia.testng.hooks.TestNGHooks;

public class IncidentTests extends TestNGHooks {
	
	String incidentNumber;
	
	@Test
	public void userShouldAbleToCreateNewIncidentInUI() {
		new LoginPage()
		    .enterUserName(config("makaia.servicenow.username"))
		    .enterPassword(secret("service.now.instance.password"))
		    .clickLoingButton()
		    .gotoIncidentPage()
		    .getIncidentNumber()
		    .enterCallerId("Service Desk")
		    .enterShortDescription("Sub")
		    .clickSubmitButton()
		    .filterByNumber()
		    .validateIncidentCreation();		
	}
	
	@Test
	public void userShouldAbleToFetchUICreatedIncidentInAPI() {
		new IncidentSerivce()
		    .fetchIncidentRecordByNumber(incidentNumber)
		    .validateSuccessResponse()
		    .validateIncidentNumber(incidentNumber);	
	}
	
	@Test
	public void userShouldAbleToCreatedIncidentInAPI() {
		incidentNumber = new IncidentSerivce()
				             .createIncidentRecord()
				             .validateCreationResponse()
				             .extractIncidentNumber("result.number");
		System.out.println(incidentNumber);
	}
	
	@Test
	public void userShouldAbleToSeeIncidentInUI() {
		new LoginPage()
		    .enterUserName(config("makaia.servicenow.username"))
		    .enterPassword(secret("service.now.instance.password"))
		    .clickLoingButton()
		    .gotoListofIncidentsPage()
		    .filterByNumber(incidentNumber)
		    .validateIncidentCreation(incidentNumber);
		
	}

}