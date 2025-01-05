package com.makaia.serivcenow.api.services;

import io.restassured.http.ContentType;

import static com.makaia.general.utils.PropertiesHandler.*;

import com.makaia.api.design.ResponseAPI;
import com.makaia.api.rest.assured.base.RequestSpecBuilder;
import com.makaia.api.rest.assured.base.RestAssuredBase;

public class ServiceNow {	
	
	protected ResponseAPI response;
	protected RestAssuredBase restAssured = new RestAssuredBase();	
	protected RequestSpecBuilder requestBuilder;	
	
	protected RequestSpecBuilder globalRequest() {
		return new RequestSpecBuilder()
				   .setBaseUri(config("service.now.base.uri"))	
				   .setBasePath(config("service.now.base.path"))
				   .setUsername(config("sevice.now.username"))
			       .setPassword(secret("service.now.instance.password"))
				   .setAccept(ContentType.JSON);
	}	
	
}